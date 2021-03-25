package model

import "backend/util"

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

func GetKeyword(ID int64) Keyword {
	var kwd Keyword
	_, err := Engine.ID(ID).Get(&kwd)
	if err != nil {
		util.Log().Error("model.GetKeyword:" + err.Error())
	}
	return kwd
}