package cn.itcast.domain;

public class Keynum {
    private String keyword;
    private int appeartimes;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getAppeartimes() {
        return appeartimes;
    }

    public void setAppeartimes(int appeartimes) {
        this.appeartimes = appeartimes;
    }

    @Override
    public String toString() {
        return "Keynum{" +
                "keyword='" + keyword + '\'' +
                ", appeartimes='" + appeartimes + '\'' +
                '}';
    }
}
