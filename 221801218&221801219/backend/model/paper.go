package model

import (
	"backend/util"
)

type Paper struct {
	Id         int64
	Title      string `xorm:"notnull"`
	Abstract   string `xorm:"notnull"`
	Meeting    string `xorm:"varchar(8) notnull"`
	Year       int    `xorm:"notnull"`
	OriginLink string `xorm:"notnull"`
	Click      int64  `xorm:"notnull default 0"`
}

func GetPaper(ID interface{}) (Paper, bool) {
	paper := new(Paper)
	has, err := Engine.ID(ID).Get(paper)
	if err != nil {
		util.Log().Error(err.Error())
	}
	return *paper, has
}

func (paper *Paper) Add() (int64, error) {
	affected, err := Engine.Insert(paper)
	if err != nil {
		util.Log().Error(err.Error())
		return affected, err
	}
	return affected, err
}

// SearchPaperByTitle 页面超出返回nil, -1
func SearchPaperByTitle(title []string, page int64, meeting string) ([]Paper, int64) {
	var sql string
	for _, t := range title {
		sql = sql + " or " + `title like "%` + t + `%"`
	}

	return SearchPaperByParam("1 = 0"+sql, page, meeting)
}

func SearchPaperByKeyword(keyword []string, page int64) ([]Paper, int64) {
	var sql string
	for _, k := range keyword {
		sql = sql + " or " + "content like \"%" + k + "%\""
	}

	var keywordId, paperId []string
	if err := Engine.Table("keyword").Select("id").Where(sql).Find(&keywordId); err != nil {
		util.Log().Error(err.Error())
	}

	total, err := Engine.Table("paper_keyword").Select("paperId").Where("keyword_id in (?)", keywordId).Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize*(page-1))).Count()
	if err != nil {
		util.Log().Error(err.Error())
	}

	if util.PageOverFlow(total, page) {
		return nil, -1
	}

	if err := Engine.Table("paper_keyword").Select("paperId").Where("keyword_id in (?)", keywordId).Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize*(page-1))).Find(&paperId); err != nil {
		util.Log().Error(err.Error())
	}

	paper := make([]Paper, 0)
	for _, id := range paperId {
		p, _ := GetPaper(id)
		paper = append(paper, p)
	}

	return paper, total
}

// SearchPaperByParam 搜索 没有结果时返回0
func SearchPaperByParam(sql string, page int64, meeting string) ([]Paper, int64) {
	b := meeting == ""
	paper := make([]Paper, 0)

	var total int64
	var err error

	if b {
		total, err = Engine.Where(sql).Count(&Paper{})
		if err != nil {
			util.Log().Error(err.Error())
		}
	} else {
		total, err = Engine.Where(sql).And("meeting = ?", meeting).Count(&Paper{})
		if err != nil {
			util.Log().Error(err.Error())
		}
	}
	if total == 0 {
		return nil, total
	}

	if util.PageOverFlow(total, page) {
		return nil, -1
	}

	if b {
		err = Engine.Where(sql).Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize*(page-1))).Find(&paper)
		if err != nil {
			util.Log().Error(err.Error())
		}
	} else {
		err = Engine.Where(sql).And("meeting = ?", meeting).Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize*(page-1))).Find(&paper)
		if err != nil {
			util.Log().Error(err.Error())
		}
	}
	return paper, util.TotalPages(total)
}
