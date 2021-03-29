package model

import (
	"backend/util"
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

	err = Engine.Table("paper_keyword").Where("keyword_id = ?", keywordId).Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize * (page - 1))).Cols("paper_id").Find(&paperId)
	if err != nil {
		util.Log().Error(err.Error())
	}
	papers := make([]Paper, 0)
	for _, id := range paperId{
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

func GetKeywordTop10() ([]Keyword, error) {
	var keywords []Keyword
	err := Engine.Desc("freq").Limit(10).Find(&keywords)
	return keywords, err
}