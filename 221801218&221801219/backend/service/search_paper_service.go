package service

import (
	"backend/model"
	"backend/serializer"
	"regexp"
)

type SearchPaperService struct {

}

// SearchByTitle 按标题搜索论文， meeting留空则不考虑
func (service *SearchPaperService) SearchByTitle(title string, page int64, meeting string) serializer.Response {
	re := regexp.MustCompile(`"[+]"`)
	t := re.Split(title, -1)
	papers, pageCount := model.SearchPaperByTitle(t, page, meeting)
	if pageCount == -1 {
		return serializer.ParamErr("获取页面错误", nil)
	}
	return serializer.BuildPaperListResponse(papers, pageCount, page)
}

func (service *SearchPaperService) SearchByKeyword(keyword string, page int64) serializer.Response {
	re := regexp.MustCompile(`"[+]"`)
	k := re.Split(keyword, -1)
	keywords, pageCount := model.SearchPaperByKeyword(k, page)
	if pageCount == -1 {
		return serializer.ParamErr("获取页面错误", nil)
	}
	return serializer.BuildPaperListResponse(keywords, pageCount, page)
}