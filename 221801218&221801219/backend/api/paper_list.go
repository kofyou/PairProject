package api

import (
	"backend/service"
	"github.com/gin-gonic/gin"
	"strconv"
)

func ShowPaperList(c *gin.Context)  {
	var service service.ShowPaperListService
	p := c.DefaultQuery("page", "1")
	page, err := strconv.Atoi(p)
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	}
	res := service.ShowPaperList(*CurrentUser(c), int64(page))
	c.JSON(200, res)
}

func AddPaperList(c *gin.Context)  {
	var service service.AddPaperListService
	if err := c.ShouldBind(&service); err == nil {
		res := service.Add(*CurrentUser(c))
		c.JSON(200, res)
	} else {
		c.JSON(200, ErrorResponse(err))
	}
}