package service

import (
	"backend/model"
	"backend/serializer"
	"backend/util"
)

type ShowPaperListService struct {
}

func (service *ShowPaperListService) ShowPaperDetail(paperId int64) serializer.Response {
	paper, has := model.GetPaper(paperId)
	if !has {
		return serializer.ParamErr("论文不存在", nil)
	} else {
		paper.Click = paper.Click + 1
		_, err := model.Engine.ID(paperId).Update(paper)
		if err != nil {
			util.Log().Error(err.Error())
		}
		return serializer.BuildPaperResponse(paper)
	}
}

func (service *ShowPaperListService) ShowPaperList(user model.User, page int64) serializer.Response {
	paper, pageCount := user.GetPaperList(page)

	if pageCount == 0 {
		return serializer.BuildPaperListResponse(nil, pageCount, page)
	}

	if pageCount == -1 {
		return serializer.ParamErr("获取页面错误", nil)
	}

	return serializer.BuildPaperListResponse(paper, pageCount, page)
}

// ShowPaperListByKeyword 获取关键词对应的论文列表
func (service *ShowPaperListService) ShowPaperListByKeyword(keywordId int64, page int64) serializer.Response {
	paper, pageCount := model.GetPapersByKeywordId(keywordId, page)

	if pageCount == -1 {
		return serializer.ParamErr("获取页面错误", nil)
	}

	return serializer.BuildPaperListResponse(paper, pageCount, page)
}
