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
	//r.Use(middleware.CurrentUser())

	v1 := r.Group("/api/v1")
	{
		v1.POST("ping", api.Ping)
	}

	return r
}