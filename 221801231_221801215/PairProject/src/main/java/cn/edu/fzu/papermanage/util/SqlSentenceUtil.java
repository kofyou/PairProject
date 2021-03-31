package cn.edu.fzu.papermanage.util;

public class SqlSentenceUtil {


    /**
     * 分隔字符串并加入%，方便模糊查询
     *
     * <p>使用<em>,.，。 (空格)</em>分割</p>
     *
     * @param origin the origin 要处理的字符串
     * @return the string 包含%用于模糊查询的字符串
     */
    public static String splitAndAddFuzzy(String origin) {
        //以空格，逗号（中英文），句号（中英文)分割
        String[] words = origin.split("[ ,，.。]");
        StringBuilder queryString = new StringBuilder("%");
        //拼接查询字符串
        for (String word : words) {
            if (!word.isEmpty()) {
                queryString.append(word).append("%");
            }
        }
        return queryString.toString();
    }
}
