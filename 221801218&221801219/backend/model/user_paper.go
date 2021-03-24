package model

import (
	"backend/util"
	"math"
)

type UserPaper struct {
	Id      	int64
	PaperId 	int64 	`xorm:"index notnull"`
	UId     	int64 	`xorm:"index notnull"`
}

func (user *User) UserAddPaper(paperId int64) error {
	return PaperSubscribe(user.Id, paperId)
}

func PaperSubscribe(userId, paperId int64) error {
	userPaper := UserPaper{
		PaperId:   paperId,
		UId: 	userId,
	}

	_, err := userPaper.Insert()
	return err
}

func PaperUnsubscribe(userId, paperId int64) (int64, error) {
	affected, err := Engine.Where("user_id = ? and paper_id = ?", userId, paperId).Delete(&UserPaper{})
	return affected, err
}

// GetPaperIdList 页面超出返回nil, -1
func (user *User) GetPaperIdList(p int64) ([]int64, int64) {
	total, _ := Engine.Where("u_id = ?", user.Id).Count(&UserPaper{})
	if int64(math.Ceil(float64(total) / float64(UserPaperListPageMaxSize))) < p {
		return nil, -1
	}

	list := make([]int64, 0)
	err := Engine.Table("user_paper").Where("u_id = ?", user.Id).Cols("paper_id").Limit(UserPaperListPageMaxSize, int(UserPaperListPageMaxSize * (p - 1))).Find(&list)
	if err != nil {
		util.Log().Error(err.Error())
	}
	return list, total
}

func (userPaper *UserPaper) Insert() (int64, error) {
	affected, err := Engine.Insert(userPaper)
	if err != nil {
		util.Log().Error(err.Error())
		return affected, err
	}
	return affected, err
}