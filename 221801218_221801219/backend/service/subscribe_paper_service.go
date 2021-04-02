package service

import (
	"backend/model"
	"backend/serializer"
)

type SubscribePaperService struct {
	PaperId int64 `form:"paper_id" json:"paper_id"`
}

func (service *SubscribePaperService) Subscribe(user model.User) serializer.Response {
	if has, err := model.Engine.Where("paper_id = ? and u_id = ?", service.PaperId, user.Id).Exist(&model.UserPaper{}); err != nil {
		return serializer.DBErr("论文订阅查询失败", err)
	} else if has {
		return serializer.ParamErr("论文请勿重复订阅", nil)
	}

	if has, err := model.Engine.ID(service.PaperId).Exist(&model.Paper{}); err != nil {
		return serializer.DBErr("论文库查询失败", err)
	} else if !has {
		return serializer.ParamErr("论文不存在", nil)
	}

	err := model.PaperSubscribe(user.Id, service.PaperId)
	if err != nil {
		return serializer.DBErr("论文订阅保存失败", err)
	}
	return serializer.Response{
		Code: 0,
		Msg:  "论文订阅成功",
	}
}

func (service *SubscribePaperService) Unsubscribe(user model.User) serializer.Response {
	if has, err := model.Engine.Where("paper_id = ? and u_id = ?", service.PaperId, user.Id).Exist(&model.UserPaper{}); err != nil {
		return serializer.DBErr("论文订阅查询失败", err)
	} else if !has {
		return serializer.ParamErr("论文未订阅", nil)
	}

	if has, err := model.Engine.ID(service.PaperId).Exist(&model.Paper{}); err != nil {
		return serializer.DBErr("论文库查询失败", err)
	} else if !has {
		return serializer.ParamErr("论文不存在", nil)
	}

	affected, err := model.PaperUnsubscribe(user.Id, service.PaperId)
	if err != nil {
		return serializer.DBErr("论文订阅保存失败", err)
	}
	if affected != 1 {
		return serializer.DBErr("论文取消订阅失败", nil)
	}
	return serializer.Response{
		Code: 0,
		Msg:  "论文取消订阅成功",
	}
}
