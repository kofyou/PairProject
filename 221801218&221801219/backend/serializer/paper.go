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

// BuildPaper 序列化论文
func BuildPaper(paper model.Paper) Paper {
	return Paper{
		Id:         paper.Id,
		Title:      paper.Title,
		Abstract:   paper.Abstract,
		Meeting:    paper.Meeting,
		Year:       paper.Year,
		OriginLink: paper.Meeting,
		Keywords:   paper.GetPaperKeywordStrings(),
	}
}

// BuildPaperList 序列化论文列表
func BuildPaperList(paper []model.Paper, pageCount int64, page int64) PaperList {
	papers := make([]Paper, 0)
	for _, p := range paper{
		papers = append(papers, Paper{
			Id:         p.Id,
			Title:      p.Title,
			Meeting:    p.Meeting,
			Year:       p.Year,
		})
	}

	return PaperList{
		Page:      page,
		PageCount: pageCount,
		Papers:    papers,
	}
}

// BuildSearchResult 序列化查询结果
func BuildSearchResult(paper []model.Paper, pageCount int64, page int64) PaperList {
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


// BuildPaperResponse 序列化论文列表响应
func BuildPaperResponse(paper model.Paper) Response {
	return Response{
		Data:  BuildPaper(paper),
		Msg: "Success",
	}
}

// BuildPaperListResponse 序列化论文列表响应
func BuildPaperListResponse(paper []model.Paper, pageCount int64, page int64) Response {
	return Response{
		Data:  BuildPaperList(paper, pageCount, page),
		Msg: "Success",
	}
}

// BuildSearchResultResponse 序列化查询结果响应
func BuildSearchResultResponse(paper []model.Paper, pageCount int64, page int64) Response {
	return Response{
		Data:  BuildSearchResult(paper, pageCount, page),
		Msg: "Success",
	}
}
