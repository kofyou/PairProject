package service

import (
	"backend/model"
	"backend/serializer"
)

type ShowPaperListService struct {
}

func (service *ShowPaperListService) ShowPaperList(user model.User, page int64) serializer.Response {
	paper, pageCount := user.GetPaperList(page)

	if pageCount == -1 {
		return serializer.ParamErr("获取页面错误", nil)
	}

	return serializer.BuildPaperResponse(paper, pageCount, page)
}