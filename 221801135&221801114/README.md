|这个作业属于哪个课程|[2021春软件工程实践S班 (福州大学)](https://edu.cnblogs.com/campus/fzu/FZUSESPR21)|
|--    |--    |
|这个作业要求在哪里|[作业连接](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11891)|
|结对学号|[221801135](https://www.cnblogs.com/Flutpiggip/) & [221801114](https://www.cnblogs.com/silicon-bond/)|
|这个作业的目标|顶会热词统计的实现|
|其他参考文献|《码出高效_阿里巴巴Java开发手册》|

# 项目介绍

## [云服务器的访问链接](http://120.77.216.4:8080/paper/)

## 功能介绍

### 功能1：对已爬取的论文列表进行操作

- 可对论文列表进行删除；
- 可对论文列表进行查询详细信息（支持模糊查询，查询结果的展示、排序等功能可自行设计）;

### 功能2：分析已爬取到的论文信息，提取top10个热门领域或热门研究方向

- 形成如关键词图谱之类直观的查看方式，点击某个关键词可展现相关的论文；
- 可对多年间、不同顶会的热词呈现热度走势对比，以**动图**的形式呈现（这里将范畴限定在计算机视觉的三大顶会CVPR、ICCV、ECCV内）

## 成品展示
- 初始页面：
![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331062701%E6%88%AA%E5%9B%BE1.png)

- 论文列表页

列表左上角显示了一共有多少篇论文。点击列表项可以进入论文详情页。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331064018%E6%88%AA%E5%9B%BE2.gif)

列表实现了分页功能，也能跳转到指定的页面。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331064818%E6%88%AA%E5%9B%BE3.gif)

论文列表右上角的放大镜图标可以唤出搜索框，输入内容可以实现对论文标题的模糊查询。如果搜索框里的内容是空串时，搜索的论文为全部已有论文

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331065134%E6%88%AA%E5%9B%BE4.gif)

点击论文项右侧的删除图标可以删除该论文。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331065404%E6%88%AA%E5%9B%BE5.gif)

- 论文详情页

论文详情页展示了该论文的详细信息，包括标题、原文链接、年份、会议、关键词和摘要。点击左上角的返回图标可以返回刚刚的论文列表。点击某一个关键词可以显示拥有该关键词的论文列表。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331070702%E6%88%AA%E5%9B%BE6.gif)

点击原文链接可以跳转到这篇论文的原链接。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331070708%E6%88%AA%E5%9B%BE7.gif)

- 关键词图谱页
  
点击图表下方的按钮可以控制显示哪几个关键词。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331072356%E6%88%AA%E5%9B%BE8.gif)

点击某一个关键词可以显示拥有该关键词的论文列表。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331072223%E6%88%AA%E5%9B%BE9.gif)

- 热词走势图界面
  
以折线图或者柱状图的形式展现数据，鼠标移动到上面可以显示具体数据。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331073356%E6%88%AA%E5%B1%8F10.gif)

点击图表上方的按钮可以选择不展示哪一个会议的数据。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331080234%E6%88%AA%E5%B1%8F11.gif)

点击下方的关键词按钮可以选择展示某一个关键词的相关数据。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331081220%E6%88%AA%E5%9B%BE12.gif)

可以通过点击图表右上方的按钮切换图表样式。

![](https://images.cnblogs.com/cnblogs_com/silicon-bond/1954919/o_210331081627%E6%88%AA%E5%9B%BE13.gif)
