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
	r.POST("/user/login/check", user.UserLogin)

	// username=?&&password=?
	r.POST("/user/register/check", user.UserRegister)

	// username=?&&thesis_id=?
	r.POST("/search/list/like", user.UserLikeAdd)

	// username=?&&thesis_id=?
	r.POST("/user/like/delete", user.UserLikeDelete)

	// username=?
	r.POST("/user/like", user.UserLikeShow)

	// source=?&&keyword=?&&year=?&&page=?
	r.POST("/search/list", thesisSearch.GetThesisList)

	r.POST("/")
	r.Run(":8080")
}

