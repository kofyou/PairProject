package service

import (
	"backend/model"
	"backend/serializer"
	"backend/util"
	"strconv"
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

func (service *KeywordStatService) GetStatistics() serializer.Response {
	sql := `SELECT * FROM keyword WHERE id IN
			(SELECT keyword_id FROM paper_keyword WHERE paper_id IN
			(SELECT paper.id FROM paper WHERE paper.year = ?) GROUP BY keyword_id ORDER BY COUNT(*) DESC LIMIT 10)`

	keywords := make([][]model.Keyword, 7)
	for i := 2014; i < 2021; i++ {
		result, err := model.Engine.Query(sql, i)
		if err != nil {
			util.Log().Error(err.Error())
		}
		var keys []model.Keyword
		for _, r := range result{
			id, _ := strconv.Atoi(string(r["id"]))
			keys = append(keys, model.Keyword{
				Id:      int64(id),
				Year: uint16(i),
				Freq:    model.GetKeywordCount(id, i),
				Content: string(r["content"]),
			})
		}
		keywords[i - 2014] = keys
	}
	return serializer.BuildKeywordStatListResponse(keywords)
}
