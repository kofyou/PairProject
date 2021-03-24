package api

import (
	"backend/serializer"
	"encoding/json"
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