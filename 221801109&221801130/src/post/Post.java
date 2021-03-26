package pojo;

public class Post {
    private String title;
    private String content;
    private String link;
    private String keyWord;
    private String time;
    private String platform;


	public Post(String title, String content, String link, String keyWord, String time, String platform) {
		this.title = title;
		this.content = content;
		this.link = link;
		this.keyWord = keyWord;
		this.time = time;
		this.platform = platform;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	/*public String getAnonymousName() {
        if (null == name)
            return null;

        if (name.length() <= 1)
            return "*";

        if (name.length() == 2)
            return name.substring(0, 1) + "*";

        char[] categories = name.toCharArray();
        for (int i = 1; i < categories.length - 1; i++) {
            categories[i] = '*';
        }
        return new String(categories);


    }*/

}
