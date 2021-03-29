package user

import (
	"backend/database"
	_ "backend/database"
	"backend/thesisSearch"
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
	username := c.Query("username")
	password := c.Query("password")

	rows, _ := database.DB.Raw("select * from user where username = ? ", username).Rows()
	var i int
	if rows.Next() {
		i++
	}

	if (i == 0 && len(username) <= 60 && len(password) >= 6) {
		affected := database.DB.Exec("insert into user values(?, ?)", username, password).RowsAffected
		if(affected == 1){
			c.JSON(http.StatusOK, gin.H{"ifSucceed":true})
		} else {
			c.JSON(http.StatusOK, gin.H{"ifSucceed":false})
		}
	} else {
		c.JSON(http.StatusOK, gin.H{"ifSucceed":false})
	}
}


// UserAddLike 用户增加收藏
func UserAddLike(c *gin.Context) {
	username := c.Query("username")
	thesisID := c.Query("thesis_id")

	//先判断用户和论文是否都存在
	user, _ := database.DB.Raw("select * from user where username = ? ", username).Rows()
	var i int
	if user.Next() {
		i++
	}
	thesis, _ := database.DB.Raw("select * from analyzed_thesis where ID = ? ", thesisID).Rows()
	var j int
	if thesis.Next() {
		j++
	}
	//判断该用户是否已收藏该文章
	rows, _ := database.DB.Raw("select * from user_like where username = ? and thesis_id = ? ", username, thesisID).Rows()
	var k int
	if rows.Next() {
		k++
	}

	if i>0 && j>0 && k==0 {
		x := database.DB.Exec("insert into user_like(username, thesis_id) values(?,?)", username, thesisID).RowsAffected
		if( x==1 ) {
			c.JSON(http.StatusOK, gin.H{"ifSucceed":true})
		} else {
			c.JSON(http.StatusOK, gin.H{"ifSucceed":false})
		}


	} else {
		c.JSON(http.StatusOK, gin.H{"ifSucceed":false})
	}

}


// UserShowLike 返回用户收藏列表
func UserShowLike(c *gin.Context) {
	username := c.Query("username")

	//取出该用户所有收藏文章的ID号
	idRows ,_ := database.DB.Raw("select thesis_id from user_like where username = ?", username).Rows()
	var thesisIdArr []int
	var tempID int
	for idRows.Next() {
		_ = idRows.Scan(&tempID)
		thesisIdArr = append(thesisIdArr, tempID)
	}

	//根据收藏ID数组查找论文
	rows, _ := database.DB.Raw("select * from analyzed_thesis where ID in ?", thesisIdArr).Rows()
	var ID int
	var Source string
	var Year int
	var Title string
	var Author string
	var Keyword string
	var Abstract string
	var thesisArr []thesisSearch.AnalyzedThesis
	for rows.Next() {
		temp := thesisSearch.AnalyzedThesis{}
		_ = rows.Scan(&ID, &Source, &Year, &Title, &Author, &Keyword, &Abstract)
		temp.ID = ID
		temp.Source = Source
		temp.Year = Year
		temp.Title = Title
		temp.Author = Author
		temp.Keyword = Keyword
		temp.Abstract = Abstract
		thesisArr = append(thesisArr,temp)
	}

	c.JSON(http.StatusOK, thesisArr)
}
