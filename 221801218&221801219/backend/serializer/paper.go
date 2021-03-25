package serializer

import "backend/model"

type Paper struct {
	Id				int64		`json:"id"`
	Title      		string		`json:"title"`
	Abstract		string		`json:"abstract"`
	Meeting			string		`json:"meeting"`
	Year       		int			`json:"year"`
	OriginLink 		string		`json:"origin_link"`
	Keywords		[]string	`json:"keywords"`
}

// Page 当前页
// PageCount 总页数
type PaperList struct {
	Page      int64     `json:"page"`
	PageCount int64   	`json:"page_count"`
	Papers    []Paper 	`json:"papers"`
}

// BuildPaperList 序列化论文列表
func BuildPaperList(paper []model.Paper, pageCount int64, page int64) PaperList {
	papers := make([]Paper, 0)
	for _, p := range paper{
		papers = append(papers, Paper{
			Id:         p.Id,
			Title:      p.Title,
			Abstract:   p.Abstract,
			Meeting:    p.Meeting,
			Year:       p.Year,
			OriginLink: p.OriginLink,
			Keywords: p.GetPaperKeywordStrings(),
		})
	}

	return PaperList{
		Page:      page,
		PageCount: pageCount,
		Papers:    papers,
	}
}

// BuildPaperListResponse 序列化论文列表响应
func BuildPaperResponse(paper []model.Paper, pageCount int64, page int64) Response {
	return Response{
		Data:  BuildPaperList(paper, pageCount, page),
		Msg: "Success",
	}
}