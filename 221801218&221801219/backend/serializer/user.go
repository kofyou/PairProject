package serializer

import "backend/model"

type User struct {
	Uid			string		`json:"uid"`
}

// BuildUser 序列化用户
func BuildUser(user model.User) User {
	return User{Uid: user.Uid}
}

// BuildUserResponse 序列化用户响应
func BuildUserResponse(user model.User) Response {
	return Response{
		Data:  BuildUser(user),
	}
}