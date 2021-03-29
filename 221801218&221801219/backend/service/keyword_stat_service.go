package service

import (
	"backend/model"
	"backend/serializer"
)

type KeywordStatService struct {
}

func (service *KeywordStatService) GetTop10() serializer.Response {
	keywords, err := model.GetKeywordTop10()
	if err != nil {
		return serializer.DBErr("数据库错误", err)
	}
	return serializer.BuildKeywordStatResponse(keywords)
}
