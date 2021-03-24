package model

func sync() error {
	return Engine.Sync2(new(Keyword), new(Paper), new(User), new(PaperKeyword), new(UserPaper))
}
