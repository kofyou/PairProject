package model

import (
	"backend/util"
	"golang.org/x/crypto/bcrypt"
)

type User struct {
	Id             	int64
	Uid       		string 		`xorm:"varchar(200) unique notnull"`
	PasswordDigest 	string 		`xorm:"varchar(200) notnull"`
}

const (
	// PasswordCost 密码加密难度
	PasswordCost = 12

	// UserPaperListPageMaxSize 论文列表单页条数
	UserPaperListPageMaxSize = 15
)

// GetUser 由Id获取用户
func GetUser(ID interface{}) (User, bool) {
	user := new(User)
	has, err := Engine.ID(ID).Get(user)
	if err != nil {
		util.Log().Error(err.Error())
	}
	return *user, has
}

// SetPassword 设置用户密码
func (user *User) SetPassword(password string) error {
	bytes, err := bcrypt.GenerateFromPassword([]byte(password), PasswordCost)
	if err != nil {
		util.Log().Error(err.Error())
		return err
	}
	user.PasswordDigest = string(bytes)
	return nil
}

// CheckPassword 验证用户密码
func (user *User) CheckPassword(password string) bool {
	err := bcrypt.CompareHashAndPassword([]byte(user.PasswordDigest), []byte(password))
	return err == nil
}

func (user *User) Create() error {
	_, err := Engine.Insert(user)
	if err != nil {
		util.Log().Error(err.Error())
		return err
	}
	return nil
}

func (user *User) Exist() bool {
	has, err := Engine.ID(user.Id).Get(&User{})
	if err != nil {
		util.Log().Error(err.Error())
	}
	return has
}

// GetPaperList 获取用户论文列表，返回第p页结果，以及总页数
func (user *User) GetPaperList(p int64) ([]Paper, int64) {
	paperIdList, total := user.GetPaperIdList(p)
	if total == -1 {
		return nil, -1
	}

	paperList := make([]Paper, 0)

	for _, id := range paperIdList {
		paper, has := GetPaper(id)
		if !has {
			util.Log().Error("Paper Missing.")
		}
		paperList = append(paperList, paper)
	}

	var pageCount int64
	if total % UserPaperListPageMaxSize != 0 {
		pageCount = total / UserPaperListPageMaxSize + 1
	} else {
		pageCount = total / UserPaperListPageMaxSize
	}

	return paperList, pageCount
}