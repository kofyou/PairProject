package api

import (
	"backend/service"
	"errors"
	"github.com/gin-gonic/gin"
	"strconv"
)

func PaperSearch(c *gin.Context)  {
	var service service.SearchPaperService
	param1 := c.DefaultQuery("title", "")
	param2 := c.DefaultQuery("page", "1")
	param3 := c.DefaultQuery("meeting", "")
	param4 := c.DefaultQuery("keyword", "")
	page, err := strconv.Atoi(param2)
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	} else if param1 == "" && param4 == "" {
		c.JSON(200, ErrorResponse(errors.New("搜索参数错误")))
	} else {
		res := service.Search(param1, param4, int64(page), param3)
		c.JSON(200, res)
	}
}
