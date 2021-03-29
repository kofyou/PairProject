package user

import (
	"backend/database"
	_ "backend/database"
	_ "database/sql"
	"github.com/gin-gonic/gin"
	"net/http"
	_ "strings"
)


// UserLogin 用户登录
func UserLogin(c *gin.Context){
	username := c.Query("username")
	password := c.Query("password")

	ifExist, _ := database.DB.Raw("select * from user where username = ? and password = ?", username, password).Rows()
	var i int
	if ifExist.Next() {
		i++
	}

	if (i == 1) {
		c.JSON(http.StatusOK, gin.H{"ifCorrect":true})
	}else {
		c.JSON(http.StatusOK, gin.H{"ifCorrect":false})
	}

}


// UserRegister 用户注册
func UserRegister(c *gin.Context) {

}


