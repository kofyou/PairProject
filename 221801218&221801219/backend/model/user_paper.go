package model

type UserPaper struct {
	Id      int64
	PaperId uint64 `xorm:"index notnull"`
	UId     uint64 `xorm:"index notnull"`
}

func (user *User) AddUserPaper(ID interface{})  {
	{

	}
}