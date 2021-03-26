package api

import (
	"backend/service"
	"errors"
	"github.com/gin-gonic/gin"
	"strconv"
)

func PaperSearch(c *gin.Context)  {
	var service service.SearchPaperService
	param1 := c.Query("title")
	param2 := c.DefaultQuery("page", "1")
	param3 := c.Query("meeting")
	param4 := c.Query("keyword")
	page, err := strconv.Atoi(param2)
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	} else if param4 == "" {
		res := service.SearchByTitle(param1, int64(page), param3)
		c.JSON(200, res)
	} else if param1 == "" {
		res := service.SearchByKeyword(param4, int64(page))
		c.JSON(200, res)
	} else {
		c.JSON(200, ErrorResponse(errors.New("搜索参数错误")))
	}
}
