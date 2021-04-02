package jiekou.demo.entity;

public class Paperkey {
    private Integer keyword_id;
    private String word;
    private Integer paper_id;

    public void setKeyword_id(Integer keyword_id) {
        this.keyword_id = keyword_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public Integer getKeyword_id() {
        return keyword_id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Paperkey(){}
    public Paperkey(Integer keyword_id, String word, Integer paper_id) {
        this.keyword_id = keyword_id;
        this.word = word;
        this.paper_id = paper_id;
    }
}
