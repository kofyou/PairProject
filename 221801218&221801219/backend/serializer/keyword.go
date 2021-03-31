package serializer

import "backend/model"

type KeywordStatList struct {
	KeywordStat []KeywordStat	`json:"keyword_stat"`
}

type KeywordStat struct {
	KeywordStatUnits []KeywordStatUnit `json:"keyword_stat_units"`
}

type KeywordStatUnit struct {
	ID      int    `json:"id"`
	Content string `json:"content"`
	Year    uint16 `json:"year"`
	Freq    uint64 `json:"freq"`
	Meeting string `json:"meeting"`
}

func BuildKeywordStat(keywords []model.Keyword) KeywordStat {
	var keywordStat KeywordStat
	cnt := 1
	for _, keyword := range keywords {
		keywordStat.KeywordStatUnits = append(keywordStat.KeywordStatUnits, KeywordStatUnit{
			ID:      cnt,
			Content: keyword.Content,
			Year:    keyword.Year,
			Freq:    keyword.Freq,
			Meeting: keyword.Meeting,
		})
		cnt = cnt + 1
	}

	return keywordStat
}

func BuildKeywordStatList(keywords [][]model.Keyword) KeywordStatList {
	var keywordStatList KeywordStatList
	for _, keyword := range keywords {
		cnt := 1
		var keywordStat KeywordStat
		for _, key := range keyword {
			keywordStat.KeywordStatUnits = append(keywordStat.KeywordStatUnits, KeywordStatUnit{
				ID:      cnt,
				Content: key.Content,
				Year:    key.Year,
				Freq:    key.Freq,
				Meeting: key.Meeting,
			})
			cnt = cnt + 1
		}
		keywordStatList.KeywordStat = append(keywordStatList.KeywordStat, keywordStat)
	}

	return keywordStatList
}

func BuildKeywordStatResponse(keywords []model.Keyword) Response {
	return Response{
		Code: 0,
		Data: BuildKeywordStat(keywords),
		Msg:  "Success",
	}
}

func BuildKeywordStatListResponse(keywords [][]model.Keyword) Response {
	return Response{
		Code: 0,
		Data: BuildKeywordStatList(keywords),
		Msg:  "Success",
	}
}