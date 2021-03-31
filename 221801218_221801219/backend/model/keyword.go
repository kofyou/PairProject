package model

import (
	"backend/util"
	"strconv"
)

type Keyword struct {
	Id      int64
	Year    uint16 `xorm:"notnull"`
	Freq    uint64 `xorm:"notnull"`
	Meeting string `xorm:"notnull"`
	Content string `xorm:"notnull"`
}

func (keyword *Keyword) Insert() (int64, error) {
	affected, err := Engine.Insert(keyword)
	if err != nil {
		util.Log().Error(err.Error())
		return -1, err
	}
	return affected, nil
}

func GetPapersByKeywordId(keywordId, page int64) ([]Paper, int64) {
	paperId := make([]string, 0)
	total, err := Engine.Where("keyword_id = ?", keywordId).Count(&PaperKeyword{})
	if err != nil {
		util.Log().Error(err.Error())
	}
	if util.PageOverFlow(total, page) {
		return nil, -1
	}

	err = Engine.Table("paper_keyword").Where("keyword_id = ?", keywordId).Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize*(page-1))).Cols("paper_id").Find(&paperId)
	if err != nil {
		util.Log().Error(err.Error())
	}
	papers := make([]Paper, 0)
	for _, id := range paperId {
		paper, _ := GetPaper(id)
		papers = append(papers, paper)
	}
	return papers, util.TotalPages(total)
}

func GetKeyword(ID int64) Keyword {
	var kwd Keyword
	_, err := Engine.ID(ID).Get(&kwd)
	if err != nil {
		util.Log().Error("model.GetKeyword:" + err.Error())
	}
	return kwd
}

func GetKeywordCount(id, year int) uint64 {
	sql := "SELECT COUNT(*) FROM paper WHERE id IN (SELECT paper_id FROM paper_keyword WHERE keyword_id = ?) AND year = ?"
	result, _ := Engine.Query(sql, id, year)
	freq, _ := strconv.Atoi(string(result[0]["COUNT(*)"]))
	return uint64(freq)
}

func GetKeywordTop10() ([]Keyword, error) {
	var keywords []Keyword
	result, err := Engine.Query("SELECT * FROM keyword WHERE id IN (SELECT keyword_id FROM paper_keyword WHERE paper_id IN (SELECT paper.id FROM paper) GROUP BY keyword_id ORDER BY COUNT(*) DESC LIMIT 10)")
	for _, r := range result{
		id, _ := strconv.Atoi(string(r["id"]))
		year, _ := strconv.Atoi(string(r["year"]))
		freq, _ := strconv.Atoi(string(r["freq"]))
		keywords = append(keywords, Keyword{
			Id:      int64(id),
			Year:    uint16(year),
			Freq:    uint64(freq),
			Meeting: string(r["meeting"]),
			Content: string(r["content"]),
		})
	}
	return keywords, err
}
