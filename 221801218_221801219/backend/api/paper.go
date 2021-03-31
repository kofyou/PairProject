package api

import (
	"backend/service"
	"errors"
	"github.com/gin-gonic/gin"
	"strconv"
)

func ShowPaperDetail(c *gin.Context) {
	var service service.ShowPaperListService
	idString := c.Param("id")
	id, err := strconv.Atoi(idString)
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	} else {
		res := service.ShowPaperDetail(int64(id))
		c.JSON(200, res)
	}
}

func ShowPapersByKeywordID(c *gin.Context) {
	var service service.ShowPaperListService
	p := c.DefaultQuery("page", "1")
	k := c.DefaultQuery("keyword", "-1")
	page, err := strconv.Atoi(p)
	keyword, err1 := strconv.Atoi(k)
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	} else if err1 != nil {
		c.JSON(200, ErrorResponse(err1))
	} else if keyword == -1 {
		c.JSON(200, ErrorResponse(errors.New("关键词参数错误")))
	} else {
		res := service.ShowPaperListByKeyword(int64(keyword), int64(page))
		c.JSON(200, res)
	}
}

func ShowPaperList(c *gin.Context) {
	var service service.ShowPaperListService
	p := c.DefaultQuery("page", "1")
	page, err := strconv.Atoi(p)
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	} else {
		res := service.ShowPaperList(*CurrentUser(c), int64(page))
		c.JSON(200, res)
	}
}

func PaperSubscribe(c *gin.Context) {
	var service service.SubscribePaperService
	if err := c.ShouldBind(&service); err == nil {
		res := service.Subscribe(*CurrentUser(c))
		c.JSON(200, res)
	} else {
		c.JSON(200, ErrorResponse(err))
	}
}

func PaperUnsubscribe(c *gin.Context) {
	var service service.SubscribePaperService
	if err := c.ShouldBind(&service); err == nil {
		res := service.Unsubscribe(*CurrentUser(c))
		c.JSON(200, res)
	} else {
		c.JSON(200, ErrorResponse(err))
	}
}

func CheckSubscriptionStatus(c *gin.Context)  {
	var service service.SearchPaperService
	paperId, err := strconv.Atoi(c.Param("id"))
	if err != nil {
		c.JSON(200, ErrorResponse(err))
	}
	res := service.CheckSubscriptionStatus(CurrentUser(c), int64(paperId))
	c.JSON(200, res)
}