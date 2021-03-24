package model

import "backend/util"

type User struct {
	Id             int64
	UserName       string `xorm:"varchar(200) unique notnull"`
	PasswordDigest string `xorm:"varchar(200) notnull"`
}

func (User *User) Add() (int64, error) {
	affected, err := Engine.Update(&User)
	if err != nil {
		util.Log().Error(err.Error())
		return -1, err
	}
	return affected, nil
}
