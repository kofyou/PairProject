package service

import (
	"backend/deserializer"
	"backend/model"
	"backend/util"
	"fmt"
	"io/ioutil"
	"path/filepath"
	"strings"
)

func FileMeetingDataImport(iccvPath, cvprPath, eccvPath string) error {
	iccv := util.GetAllFilesInDir(iccvPath)
	cvpr := util.GetAllFilesInDir(cvprPath)
	eccv := util.GetAllFilesInDir(eccvPath)

	for _, i := range iccv {
		jsonString, _ := ReadJsonDataFile(i)
		title := strings.TrimSuffix(filepath.Base(i), ".json")
		var v deserializer.CVPRandICCV
		err := deserializer.DeserializeCVPRandICCV(jsonString, &v)
		if err != nil {
			util.Log().Error(err.Error())
		}
		model.ImportPaperICCV(v, "ICCV", title)
	}
	fmt.Printf("Go 1 Finished")

	for _, i := range cvpr {
		jsonString, _ := ReadJsonDataFile(i)
		title := strings.TrimSuffix(filepath.Base(i), ".json")
		var v deserializer.CVPRandICCV
		err := deserializer.DeserializeCVPRandICCV(jsonString, &v)
		if err != nil {
			util.Log().Error(err.Error())
		}
		model.ImportPaperICCV(v, "CVPR", title)
	}
	fmt.Printf("Go 1 Finished")

	for _, i := range eccv {
		jsonString, _ := ReadJsonDataFile(i)
		title := strings.TrimSuffix(filepath.Base(i), ".json")
		var v deserializer.ECCV
		err := deserializer.DeserializeECCV(jsonString, &v)
		if err != nil {
			util.Log().Error(err.Error())
		}
		model.ImportPaperECCV(v, title)
	}

	return nil
}

func ReadJsonDataFile(filePath string) (string, error) {
	f, err := ioutil.ReadFile(filePath)
	if err != nil {
		util.Log().Error(err.Error())
		return "", err
	}
	jsonString := string(f)
	jsonString = strings.Trim(jsonString, ";")
	return jsonString, nil
}
