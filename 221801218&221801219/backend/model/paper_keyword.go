package model

import (
	"backend/util"
)

type PaperKeyword struct {
	Id        int64
	PaperId   int64 `xorm:"index notnull"`
	KeywordId int64 `xorm:"index notnull"`
}

func (paperKeyword *PaperKeyword) Add() (int64, error) {
	if has, _ := Engine.Where("paper_id = ? and keyword_id = ?",
		paperKeyword.PaperId, paperKeyword.KeywordId).Exist(&PaperKeyword{}); has {
		return 0, nil
	}

	affected, err := Engine.Insert(paperKeyword)
	if err != nil {
		util.Log().Error(err.Error())
		return affected, err
	}
	return affected, nil
}

func (paper *Paper) GetPaperKeywordStrings() []Keyword {
	return paper.GetPaperKeywordList()
}

func (paper *Paper) GetPaperKeywordList() []Keyword {
	list := make([]int64, 0)
	err := Engine.Table("paper_keyword").Where("paper_id = ?", paper.Id).Cols("keyword_id").Find(&list)
	if err != nil {
		util.Log().Error(err.Error())
	}

	kwds := make([]Keyword, 0)
	for _, i := range list{
		kwds = append(kwds, GetKeyword(i))
	}
	return kwds
}
