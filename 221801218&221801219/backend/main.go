package main

import (
	"backend/config"
	"backend/server"
	"backend/service"
	"backend/util"
	"os"
)

func Init() {
	err := os.Mkdir("data", 0777)
	if err != nil {
		util.Log().Error(err.Error())
	}

	err = os.Chdir("./data")
	if err != nil {
		util.Log().Error(err.Error())
	}

	_, err = os.Create("database.sql")
	if err != nil {
		util.Log().Error(err.Error())
	}
}

func ImportData() error {
	return service.FileMeetingDataImport("/Users/Freeman/Downloads/论文数据/ICCV（2001年至2019年，3196篇）", "/Users/Freeman/Downloads/论文数据/CVPR（2000年至2020年，6916篇", "/Users/Freeman/Downloads/论文数据/ECCV（2016至2020，3033份）")
}

func main() {
	if os.Args[0] == "init" {
		Init()
	}

	config.Init()

	router := server.NewRouter()
	_ = router.Run(":3000")

}
