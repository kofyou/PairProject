package model

import (
	"backend/util"
	"fmt"
	_ "github.com/mattn/go-sqlite3"
	"time"
	"xorm.io/xorm"
)

var Engine *xorm.Engine

func Database(dataPath string) {
	engine, err := xorm.NewEngine("sqlite3", dataPath)

	if err != nil {
		util.Log().Error(err.Error())
		fmt.Println(err.Error())
		panic(err.Error())
	}

	engine.DB().SetMaxIdleConns(50)
	engine.DB().SetMaxOpenConns(100)
	engine.DB().SetConnMaxLifetime(time.Second * 30)

	Engine = engine

	err = sync()

	if err != nil {
		util.Log().Error(err.Error())
	}

	return
}
