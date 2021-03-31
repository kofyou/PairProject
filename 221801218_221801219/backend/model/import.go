package model

import (
	"backend/deserializer"
	"backend/util"
	"regexp"
	"strconv"
)

func ImportPaperICCV(iccv deserializer.CVPRandICCV, meeting, title string) {
	reg := regexp.MustCompile(`[\d]{4}`)
	year, err := strconv.Atoi(reg.FindString(iccv.PublicationYear))
	if err != nil {
		util.Log().Error("model/import.go " + err.Error())
	}
	paper := Paper{
		Title:      title,
		Abstract:   iccv.Abstract,
		Meeting:    meeting,
		Year:       year,
		OriginLink: iccv.OriginLink,
	}

	var paperId, keywordId int64

	if has, _ := Engine.Where("origin_link = ?", paper.OriginLink).Exist(&Paper{}); !has {
		_, _ = paper.Add()
		_, _ = Engine.Get(&paper)
		paperId = paper.Id
	} else {
		util.Log().Error("Paper Exist")
	}

	for _, k := range iccv.Keyword {
		for _, kwd := range k.Keyword {
			keyword := Keyword{
				Year:    uint16(year),
				Meeting: meeting,
				Content: kwd,
			}

			keywordId = -1
			if has, _ := Engine.Where("content = ?", keyword.Content).And("meeting = ?", meeting).Exist(&Keyword{}); !has {
				if _, err = keyword.Insert(); err != nil {
					util.Log().Error(err.Error())
				}
				kwd := new(Keyword)
				_, _ = Engine.Where("content = ?", keyword.Content).And("meeting = ?", keyword.Meeting).Get(kwd)
				keywordId = kwd.Id
			} else {
				kwd := new(Keyword)
				_, _ = Engine.Where("content = ?", keyword.Content).And("meeting = ?", keyword.Meeting).Get(kwd)
				keywordId = kwd.Id
				keyword.Id = kwd.Id
				keyword.Freq = kwd.Freq + 1
				affected, _ := Engine.ID(kwd.Id).Update(keyword)
				util.Log().Debug("%d records affected", affected)
			}

			if keywordId != -1 {
				paperKeyword := PaperKeyword{
					PaperId:   paperId,
					KeywordId: keywordId,
				}

				if has, _ := Engine.Where("paper_id = ?", paperKeyword.PaperId).And("keyword_id = ?", paperKeyword.KeywordId).Exist(&PaperKeyword{}); !has {
					if _, err = paperKeyword.Add(); err != nil {
						util.Log().Error(err.Error())
					}
				}
			}
		}
	}
}

func ImportPaperECCV(eccv deserializer.ECCV, title string) {
	reg := regexp.MustCompile(`[\d]{4}`)
	year, err := strconv.Atoi(reg.FindString(eccv.PublicationYear))
	if err != nil {
		util.Log().Error("model/import.go " + err.Error())
	}
	paper := Paper{
		Title:      title,
		Abstract:   eccv.Abstract,
		Meeting:    "ECCV",
		Year:       year,
		OriginLink: eccv.OriginLink,
	}

	var paperId, keywordId int64

	if has, _ := Engine.Where("origin_link = ?", paper.OriginLink).Exist(&Paper{}); !has {
		_, _ = paper.Add()
		_, _ = Engine.Get(&paper)
		paperId = paper.Id
	} else {
		util.Log().Error("Paper Exist")
		return
	}

	for _, k := range eccv.Keyword {
		keyword := Keyword{
			Year:    uint16(year),
			Meeting: "ECCV",
			Content: k,
		}

		keywordId = -1
		if has, _ := Engine.Where("content = ?", keyword.Content).And("meeting = ?", "ECCV").Exist(&Keyword{}); !has {
			if _, err = keyword.Insert(); err != nil {
				util.Log().Error(err.Error())
			}
			kwd := new(Keyword)
			_, _ = Engine.Where("content = ?", keyword.Content).And("meeting = ?", keyword.Meeting).Get(kwd)
			keywordId = kwd.Id
		} else {
			kwd := new(Keyword)
			_, _ = Engine.Where("content = ?", keyword.Content).And("meeting = ?", keyword.Meeting).Get(kwd)
			keywordId = kwd.Id
			keyword.Id = kwd.Id
			keyword.Freq = kwd.Freq + 1
			affected, _ := Engine.ID(kwd.Id).Update(keyword)
			util.Log().Error("%d records affected", affected)
		}

		if keywordId != -1 {
			paperKeyword := PaperKeyword{
				PaperId:   paperId,
				KeywordId: keywordId,
			}

			if has, _ := Engine.Where("paper_id = ?", paperKeyword.PaperId).And("keyword_id = ?", paperKeyword.KeywordId).Exist(&PaperKeyword{}); !has {
				if _, err = paperKeyword.Add(); err != nil {
					util.Log().Error(err.Error())
				}
			}
		}
	}
}
