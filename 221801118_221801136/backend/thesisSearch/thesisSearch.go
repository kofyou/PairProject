package thesisSearch

import (
	"backend/database"
	"github.com/gin-gonic/gin"
	"net/http"
	"strconv"
	"strings"
)

type AnalyzedThesis struct {
	ID int			`json:"id"`
	Source string	`json:"source"`
	Year int		`json:"year"`
	Title string	`json:"title"`
	Author string	`json:"author"`
	Keyword string	`json:"keyword"`
	Abstract string	`json:"abstract"`
}

type thesisSearchParam struct {
	Source string	`json:"source" binding:"required"`
	Keyword string	`json:"password" binding:"required"`
	Year int		`json:"year" binding:"required"`
	Page int		`json:"page" binding:"required"`
}


// GetThesisList 模糊搜索、查找相应文章
func GetThesisList(c *gin.Context) {
	param := thesisSearchParam{}
	_ = c.ShouldBindJSON(&param)

	source := param.Source
	year := param.Year
	keyword := param.Keyword
	page := param.Page
	yearStr := strconv.Itoa(year)

	keyword = strings.ReplaceAll(keyword, "\n", "%")
	keyword = strings.ReplaceAll(keyword, "\r", "%")
	keyword = strings.ReplaceAll(keyword, "\t", "%")
	keyword = strings.ReplaceAll(keyword, " ",  "%")
	keyword = "%" + keyword + "%"

	var selectStr string = "select * from analyzed_thesis where "
	if source != "" {
		selectStr += "Source = '" + source + "' "
	}
	if yearStr != "" {
		if source != "" {
			selectStr += "and "
		}
		selectStr += "Year = '" + yearStr + "' "
	}
	if keyword != "" {
		if !(source == "" && yearStr == "") {
			selectStr += "and "
		}
		selectStr += "Keyword like '" + keyword + "' "
	}
	if source == "" && yearStr == "" && keyword == "" {
		length := len(selectStr) - 6
		selectStr = selectStr[0:length]
	}
	if page >= 1 {
		page --
	} else {
		page = 0
	}
	page *= 4
	pageStr := strconv.Itoa(page)
	selectStr += " limit " + pageStr + " , 4"
	selectStr += " ;"


	//var Keys []string

	rows, _ := database.DB.Raw(selectStr).Rows()
	var ID int
	var Source string
	var Year int
	var Title string
	var Author string
	var Keyword string
	var Abstract string
	var thesisArr []AnalyzedThesis
	for rows.Next() {
		temp := AnalyzedThesis{}
		_ = rows.Scan(&ID, &Source, &Year, &Title, &Author, &Keyword, &Abstract)
		temp.ID = ID
		temp.Source = Source
		temp.Year = Year
		temp.Title = Title
		temp.Author = Author
		temp.Keyword = Keyword
		temp.Abstract = Abstract
		thesisArr = append(thesisArr,temp)

		//Keys = append(Keys, Keyword)
	}

	//c.JSON(http.StatusOK, Keys)
	c.JSON(http.StatusOK, thesisArr)
}


