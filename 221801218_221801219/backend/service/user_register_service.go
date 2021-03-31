package service

import (
	"backend/model"
	"backend/serializer"
	"backend/util"
)

type UserRegisterService struct {
	Uid             string `form:"user_id" json:"user_id" binding:"required,min=3,max=30"`
	Password        string `form:"password" json:"password" binding:"required,min=6,max=40"`
	PasswordConfirm string `form:"password_confirm" json:"password_confirm" binding:"required,min=6,max=40"`
}

// valid 表单验证
func (service *UserRegisterService) valid() *serializer.Response {
	if service.PasswordConfirm != service.Password {
		return &serializer.Response{
			Code: serializer.CodeParamErr,
			Msg:  "两次输入的密码不相同",
		}
	}

	has, _ := model.Engine.Where("uid = ?", service.Uid).Exist(&model.User{})

	if has {
		return &serializer.Response{
			Code: serializer.CodeParamErr,
			Msg:  "用户名已经存在",
		}
	}

	return nil
}

// Register 用户注册
func (service *UserRegisterService) Register() serializer.Response {
	user := model.User{
		Uid: service.Uid,
	}

	if err := service.valid(); err != nil {
		return *err
	}

	if err := user.SetPassword(service.Password); err != nil {
		util.Log().Error("用户注册，密码加密失败")
		return serializer.Err(serializer.CodeEncryptError, "密码加密失败", err)
	}

	if err := user.Create(); err != nil {
		return serializer.ParamErr("注册失败", err)
	}

	return serializer.BuildUserResponse(user)
}
