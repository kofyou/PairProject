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
		go func(paper model.Paper) {
			paper.Click = paper.Click + 1
			_, err := model.Engine.Update(paper)
			if err != nil {
				util.Log().Error(err.Error())
			}
		}(paper)
		return serializer.BuildPaperResponse(paper)
	}
}

func (service *ShowPaperListService) ShowPaperList(user model.User, page int64) serializer.Response {
	paper, pageCount := user.GetPaperList(page)

	if pageCount == -1 {
		return serializer.ParamErr("获取页面错误", nil)
	}

	return serializer.BuildPaperListResponse(paper, pageCount, page)
}