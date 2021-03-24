package server

import (
	"backend/api"
	"backend/middleware"
	"github.com/gin-gonic/gin"
	"os"
)

func NewRouter() *gin.Engine {
	r := gin.Default()

	r.Use(middleware.Session(os.Getenv("SESSION_SECRET")))
	r.Use(middleware.Cors())
	r.Use(middleware.CurrentUser())

	v1 := r.Group("/api/v1")
	{
		v1.POST("ping", api.Ping)

		// 用户注册
		v1.POST("register", api.UserRegister)

		// 用户登陆
		v1.POST("login", api.UserLogin)

		// 登陆保护
		authed := v1.Group("/")
		authed.Use(middleware.LoginRequired())
		{
			// User PaperList
			authed.GET("user/list", api.ShowPaperList)
			//authed.DELETE("user/logout", api.UserLogout)
			authed.POST("user/subscribe", api.AddPaperList)

		}
	}

	return r
}