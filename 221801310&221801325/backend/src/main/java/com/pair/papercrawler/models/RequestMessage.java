package com.pair.papercrawler.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*** 
* @Description: 用于接收前端发送来的数据
* @Param: searchType：指明搜索的数据是什么 title\author\keyword
* @return:  
* @Author: top
* @Date: 2021/3/30 
*/
@Getter
@Setter
@AllArgsConstructor
public class RequestMessage {
    private String searchType;
    private String content;
}
