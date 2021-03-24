package deserializer

type ECCV struct {
	Abstract        string   `json:"摘要"`
	Keyword         []string `json:"关键词"`
	OriginLink      string   `json:"原文链接"`
	PublicationYear string   `json:"会议和年份"`
}
