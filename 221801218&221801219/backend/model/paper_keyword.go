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

func (paper *Paper) GetPaperKeywordIdList() []int64 {
	list := make([]int64, 0)
	err := Engine.Where("paper_id = ?", paper.Id).Find(list)
	if err != nil {
		util.Log().Error(err.Error())
	}
	return list
}
