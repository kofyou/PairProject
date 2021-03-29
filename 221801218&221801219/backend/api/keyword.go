package api

import (
	service2 "backend/service"
	"github.com/gin-gonic/gin"
)

func GetTopKeywords(c *gin.Context) {
	var service service2.KeywordStatService
	res := service.GetTop10()
	c.JSON(200, res)
}
