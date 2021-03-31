package config

import (
	"backend/model"
	"backend/util"
	"github.com/joho/godotenv"
	"os"
)

func Init() {
	_ = godotenv.Load()

	util.BuildLogger(os.Getenv("LOG_LEVEL"))

	model.Database(os.Getenv("DATABASE_PATH"))
}
