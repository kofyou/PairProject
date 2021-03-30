package main

import (
	_ "backend/database"
	"backend/thesisSearch"
	"backend/user"
	"github.com/gin-gonic/gin"
)


func main() {

	r := gin.Default()

	// username=?&&password=?
	r.POST("/user/login", user.UserLogin)

	// username=?&&password=?
	r.POST("/user/register", user.UserRegister)

	// username=?&&thesis_id=?
	r.POST("/user/add/like", user.UserAddLike)

	// username=?&&thesis_id=?
	r.POST("/user/delete/like", user.UserDeleteLike)

	// username=?
	r.POST("/user/show/like", user.UserShowLike)

	// source=?&&keyword=?&&year=?&&page=?
	r.POST("/search/list", thesisSearch.GetThesisList)

	r.POST("/")
	r.Run(":8080")
}

