package deserializer

import "encoding/json"

type CVPRandICCV struct {
	Abstract        string     `json:"abstract"`
	Keyword         []Keywords `json:"keywords"`
	OriginLink      string     `json:"doiLink"`
	PublicationYear string     `json:"publicationDate"`
}

func DeserializeCVPRandICCV(jsonString string, v interface{}) error {
	err := json.Unmarshal([]byte(jsonString), &v)
	if err != nil {
		return err
	}
	return nil
}

func DeserializeECCV(jsonString string, v interface{}) error {
	err := json.Unmarshal([]byte(jsonString), &v)
	if err != nil {
		return err
	}
	return nil
}
