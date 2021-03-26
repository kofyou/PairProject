
new Vue().$mount('#app');
var title = document.getElementById("title");
var abstract = document.getElementById("abstract");
var link = "http://www.baidu.com";
title.setAttribute("href", link);
title.textContent = "abc";
var post = "结对作业一 这个作业属于哪个课程 &lt;2021春软件工程实践S班 "+
"(福州大学)&gt; 这个作业要求在哪里 &lt;结对作业一&gt; 结对学号 &lt;221801410、041801406"
+"&gt; 这个作业的目标 &lt;完成原型设计、撰写博客&gt; 其他参考文献 无 作业基本信息描述:找到"
+"结对队友，阅读《构建之法》第三章、第八章，完成";
post= post+ post+post+post+post+post+post+post+post+post+post;
abstract.textContent=post;