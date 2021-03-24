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
}

func (paper *Paper) Add() (int64, error) {
	affected, err := Engine.Insert(paper)
	if err != nil {
		util.Log().Error(err.Error())
		return affected, err
	}
	return affected, err
}
