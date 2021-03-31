package serializer

import "backend/model"

type User struct {
	Uid string `json:"uid"`
}

type SubscriptionStatus struct {
	IsSubscribed bool `json:"is_subscribed"`
}

// BuildUser 序列化用户
func BuildUser(user model.User) User {
	return User{Uid: user.Uid}
}

// BuildUserResponse 序列化用户响应
func BuildUserResponse(user model.User) Response {
	return Response{
		Data: BuildUser(user),
		Msg:  "Success",
	}
}

func BuildSubscriptionStatusResponse(userID, paperID int64, isLogin bool) Response {
	if !isLogin {
		return Response{
			Code:  0,
			Data:  SubscriptionStatus{IsSubscribed: false},
		}
	}

	if is, err := model.IsSubscribed(userID, paperID); err != nil {
		return ParamErr("查询错误", err)
	} else if is {
		return Response{
			Code:  0,
			Data:  SubscriptionStatus{IsSubscribed: true},
		}
	} else {
		return Response{
			Code:  0,
			Data:  SubscriptionStatus{IsSubscribed: false},
		}
	}
}