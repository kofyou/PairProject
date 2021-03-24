package api

import (
	"backend/model"
	"backend/serializer"
	"encoding/json"
	"github.com/gin-gonic/gin"
)

func ErrorResponse(err error) serializer.Response {
	//if ve, ok := err.(validator.ValidationErrors); ok {
	//	for _, e := range ve {
	//		field := config.
	//	}
	//}

	if _, ok := err.(*json.UnmarshalTypeError); ok {
		return serializer.ParamErr("JSON类型不匹配", err)
	}

	return serializer.ParamErr("参数错误", err)
}

// CurrentUser 获取当前用户
func CurrentUser(c *gin.Context) *model.User {
	if user, _ := c.Get("user"); user != nil {
		if u, ok := user.(*model.User); ok {
			return u
		}
	}
	return nil
}