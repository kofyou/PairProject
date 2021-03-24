package model

import "backend/util"

type PaperKeyword struct {
	Id        int64
	PaperId   int64 `xorm:"index notnull"`
	KeywordId int64 `xorm:"index notnull"`
}

func (paperKeyword *PaperKeyword) Add() (int64, error) {
	affected, err := Engine.Insert(paperKeyword)
	if err != nil {
		util.Log().Error(err.Error())
		return affected, err
	}
	return affected, nil
}
