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
		v1.POST("signup", api.UserRegister)

		// 用户登陆
		v1.POST("login", api.UserLogin)

		v1.GET("paper/:id", api.ShowPaperDetail)

		v1.GET("paper", api.ShowPapersByKeyword)

		v1.GET("search", api.PaperSearch)

		// 登陆保护
		authed := v1.Group("/")
		authed.Use(middleware.LoginRequired())
		{
			// User PaperList
			authed.GET("user/list", api.ShowPaperList)

			authed.POST("paper/subscribe", api.AddPaperList)

			authed.DELETE("logout", api.UserLogout)
		}
	}

	return r
}