package thesisSearch

import (
	"github.com/gin-gonic/gin"
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


// GetThesisList 模糊搜索、查找相应文章
func GetThesisList(c *gin.Context) {

}


