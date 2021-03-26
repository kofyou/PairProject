package util

import "math"

const (
	// PaperPageMaxSize 论文列表单页条数
	PaperPageMaxSize = 15
)

func PageOverFlow(total int64, page int64) bool {
	return int64(math.Ceil(float64(total) / float64(PaperPageMaxSize))) < page
}

func TotalPages(total int64) int64 {
	if total % PaperPageMaxSize != 0 {
		return total / PaperPageMaxSize + 1
	} else {
		return total / PaperPageMaxSize
	}
}