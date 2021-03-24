package model

func Exist(model interface{}) bool {
	has, _ := Engine.Get(model)
	return has
}
