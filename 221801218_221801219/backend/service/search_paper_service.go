package service

import (
	"backend/model"
	"backend/serializer"
	"backend/util"
	"regexp"
	"strconv"
)

type SearchPaperService struct {
}

func (service *SearchPaperService) CheckSubscriptionStatus(user *model.User, paperId int64) serializer.Response {
	if user != nil {
		return serializer.BuildSubscriptionStatusResponse(user.Id, paperId, true)
	} else {
		return serializer.BuildSubscriptionStatusResponse(-1, -1, false)
	}
}

func (service *SearchPaperService) Search(title, keyword string, page int64, meeting string) serializer.Response {
	if title == "" && keyword == "" {
		return serializer.ParamErr("参数错误", nil)
	}

	if _, err := model.Engine.Exec("CREATE TEMPORARY TABLE `temp_table` (`id` INTEGER PRIMARY KEY NOT NULL, `title` TEXT NOT NULL, `abstract` TEXT NOT NULL, `meeting` TEXT NOT NULL, `year` INTEGER NOT NULL, `origin_link` TEXT NOT NULL, `click` INTEGER DEFAULT 0 NOT NULL);"); err != nil {
		return serializer.DBErr("搜索太频繁，请稍后再试", err)
	}

	re := regexp.MustCompile(`\s`)
	t := re.Split(title, -1)
	k := re.Split(keyword, -1)

	var sql string
	for _, i := range k {
		sql = "INSERT OR IGNORE INTO temp_table SELECT * FROM paper WHERE paper.id IN (SELECT paper_keyword.paper_id FROM paper_keyword WHERE paper_keyword.keyword_id IN (SELECT keyword.id FROM keyword WHERE keyword.content LIKE '%" + i + "%'));"

		if i == "" {
			continue
		}
		_, err := model.Engine.Exec(sql)
		if err != nil {
			util.Log().Error(err.Error())
			return serializer.ParamErr("0", err)
		}
	}

	if title != "" {
		for _, tt := range t {
			sql = "INSERT OR IGNORE INTO temp_table SELECT * FROM paper WHERE title LIKE '%" + tt + "%'"
			sqlDelete := "DELETE FROM temp_table WHERE title NOT LIKE '%" + tt + "%'"
			if tt == "" {
				continue
			}

			if keyword == "" {
				_, err := model.Engine.Exec(sql)
				if err != nil {
					util.Log().Error(err.Error())
					return serializer.ParamErr("2", err)
				}
			}

			_, err := model.Engine.Exec(sqlDelete)
			if err != nil {
				util.Log().Error(err.Error())
				return serializer.DBErr("2.5", err)
			}
		}
	}

	if meeting != "" {
		sqlDelete := "DELETE FROM temp_table WHERE meeting != ?"
		_, err := model.Engine.Exec(sqlDelete, meeting)
		if err != nil {
			util.Log().Error(err.Error())
			return serializer.ParamErr("meeting", err)
		}
	}

	result, _ := model.Engine.Query("SELECT DISTINCT Count(id) FROM temp_table")
	total, _ := strconv.Atoi(string(result[0]["Count(id)"]))
	pageCount := util.TotalPages(int64(total))
	if util.PageOverFlow(int64(total), page) {
		return serializer.ParamErr("没有结果", nil)
	}

	var papers []model.Paper
	if err := model.Engine.Table("temp_table").Distinct().Limit(util.PaperPageMaxSize, int(util.PaperPageMaxSize*(page-1))).Asc("id").Find(&papers); err != nil {
		util.Log().Error(err.Error())
		return serializer.ParamErr("3", err)
	}
	for _, p := range papers {
		p.GetPaperKeywordList()
	}

	_, _ = model.Engine.Exec("drop table temp.temp_table")

	return serializer.BuildSearchResultResponse(papers, pageCount, page, int64(total))
}
