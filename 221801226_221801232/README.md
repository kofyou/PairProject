# README
---

##结对学号
221801226&221801232
##项目名称
PaperSearchForU
##项目介绍
1. 基础功能
功能1：对已爬取的论文列表进行操作

 - 可对论文列表进行删除；
 - 可对论文列表进行查询详细信息（支持模糊查询，查询结果的展示、排序等功能可自行设计）;

 功能2：分析已爬取到的论文信息，提取top10个热门领域或热门研究方向

 - 形成如关键词图谱之类直观的查看方式，点击某个关键词可展现相关的论文；
 - 可对多年间、不同顶会的热词呈现热度走势对比，以动图的形式呈现（这里将范畴限定在计算机视觉的三大顶会CVPR、ICCV、ECCV内）

 ##项目环境
系统环境：windows10
JDK版本：jdk-12.0.1
IDE:eclipse 4.12.0
服务器版本：apache-tomcat-9.0.44
 ## 项目实现
 使用MVC开发流程进行架构，利用Servlet+JSP+Jdbc实现功能。
 ##项目如何运行
 1. 搭建eclipse JSP环境
 2. 数据库使用MySQL,辅助navicat for mysql管理。建立数据库PaperSearch 账号:root 密码:123456 端口号：3306
 3. 
 - 第一次运行：将爬取到的论文的数据文件夹放入D盘，如D:\ECCV，在main.jsp将`//GetDataToDB logfile = new GetDataToDB("D://ECCV");`注释取消，运行main.jsp，即可将数据导入本地数据库。
 - 非第一次运行/数据已导入数据库：编译运行main.jsp
    `run as>run on server`
