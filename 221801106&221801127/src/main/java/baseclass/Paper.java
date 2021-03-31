package baseclass;

public class Paper {
    public int ID;
    public String 摘要;
    public String 会议和年份;
    public String[] 关键词;
    public String 发布时间;
    public String 论文名称;
    public String 原文链接;
	//论文类
	

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("ID: %s\n摘要：%s\n会议和年份：%s\n发布时间：%s\n论文名称：%s\n原文链接：%s\n",this.ID,this.摘要,this.会议和年份,this.发布时间,this.论文名称,this.原文链接));
        for (int i=1;i<=this.关键词.length;i++){
            stringBuffer.append("热词"+i+":"+this.关键词[i-1]+"\n");
        }
        return stringBuffer.toString();
    }
}
