package util

import (
	"io/ioutil"
	"os"
)

// 获取目录下一级所有文件，子文件夹内文件不考虑。
func GetAllFilesInDir(dirPath string) (files []string) {
	dir, err := ioutil.ReadDir(dirPath)
	if err != nil {
		BuildLogger("error")
		Log().Error("From func GetAllFilesInDir \n" + err.Error())
		return nil
	}

	pathSep := string(os.PathSeparator)

	for _, file := range dir {
		if !file.IsDir() {
			files = append(files, dirPath + pathSep + file.Name())
		}
	}

	return files
}