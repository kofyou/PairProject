package api

import (
	"backend/serializer"
	"backend/service"
	"github.com/gin-contrib/sessions"
	"github.com/gin-gonic/gin"
)

func UserRegister(c *gin.Context)  {
	var service service.UserRegisterService
	if err := c.ShouldBind(&service); err == nil {
		res := service.Register()
		c.JSON(200, res)
	} else {
		c.JSON(200, ErrorResponse(err))
	}
}

func UserLogin(c *gin.Context)  {
	var service service.UserLoginService
	if err := c.ShouldBind(&service); err == nil {
		res := service.Login(c)
		c.JSON(200, res)
	} else {
		c.JSON(200, ErrorResponse(err))
	}
}

// UserLogout 用户登出
func UserLogout(c *gin.Context) {
	s := sessions.Default(c)
	s.Clear()
	_ = s.Save()
	c.JSON(200, serializer.Response{
		Code: 0,
		Msg:    "登出成功",
	})
}