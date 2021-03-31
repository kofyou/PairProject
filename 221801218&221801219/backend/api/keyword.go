package api

import (
	"backend/service"
	"github.com/gin-gonic/gin"
)

func GetTopKeywords(c *gin.Context) {
	var service service.KeywordStatService
	res := service.GetTop10()
	c.JSON(200, res)
}

func GetKeywordsTrendStatistics(c *gin.Context)  {
	var service service.KeywordStatService
	res := service.GetStatistics()
	c.JSON(200, res)
}
