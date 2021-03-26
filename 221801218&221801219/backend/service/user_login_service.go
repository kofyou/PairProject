package service

import (
	"backend/model"
	"backend/serializer"
	"github.com/gin-contrib/sessions"
	"github.com/gin-gonic/gin"
)

type UserLoginService struct {
	Uid					string		`form:"user_id" json:"user_id" binding:"required,min=3,max=30"`
	Password			string		`form:"password" json:"password" binding:"required,min=6,max=40"`
}

// setSession 设置session
func (service *UserLoginService) setSession(c *gin.Context, user model.User)  {
	s := sessions.Default(c)
	s.Clear()
	s.Set("user_id", user.Id)
	_ = s.Save()
}

// Login 用户登录
func (service *UserLoginService) Login(c *gin.Context) serializer.Response {
	if has, _ := model.Engine.Where("uid = ?", service.Uid).Exist(&model.User{}); !has {
		return serializer.ParamErr("账号或密码错误", nil)
	}

	user := new(model.User)
	_, _ = model.Engine.Where("uid = ?", service.Uid).Get(user)
	if user.CheckPassword(service.Password) == false {
		return serializer.ParamErr("账号或密码错误", nil)
	}

	service.setSession(c, *user)

	return serializer.BuildUserResponse(*user)
}