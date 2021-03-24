package service

import (
	"backend/model"
	"backend/serializer"
)

type DeletePaperListService struct {
}

func (service *DeletePaperListService) Delete(user model.User, paperId int64) serializer.Response {
	if _, exist := model.GetPaper(paperId); !exist {
		return serializer.ParamErr("论文不存在", nil)
	}

	affected, err := model.PaperUnsubscribe(user.Id, paperId)
	if err != nil {
		return serializer.DBErr("数据库错误", err)
	}
	if affected == 0 {
		return serializer.ParamErr("论文不存在", nil)
	}

	return serializer.Response{
		Code:  0,
		Msg:   "Success",
	}
}