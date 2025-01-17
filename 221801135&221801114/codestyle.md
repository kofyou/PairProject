# 代码规范（参考自《码出高效_阿里巴巴Java开发手册》）

## 缩进
缩进采用tab（四个空格）。

## 变量命名
1. 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束；
2. 代码中的命名严禁使用拼音与英文混合的方式，更不允许直接使用中文的方式；
3. 参数名、成员变量、局部变量都统一使用lowerCamelCase风格，必须遵从驼峰形式；
```
正例： localValue /  inputUserId
```
4. 中括号是数组类型的一部分，数组定义如下：String[] args；
5. 包名统一使用小写，点分隔符之间有且仅有一个自然语义的英语单词。包名统一使用单数形式，但是类名如果有复数含义，类名可以使用复数形式；
6.  杜绝完全不规范的缩写，避免望文不知义；

## 每行最多字符数
   单行字符数限制不超过  120个，超出需要换行，换行时遵循如下原则：
* 第二行相对第一行缩进   4个空格，从第三行开始，不再继续缩进，参考示例。
* 运算符与下文一起换行。
* 方法调用的点符号与下文一起换行。
* 在多个参数超长，逗号后进行换行。
* 在括号前不要换行，见反例。
```
正例：
StringBuffer sb = new StringBuffer();
//超过120个字符的情况下，换行缩进4个空格，并且方法前的点符号一起换行
sb.append("zi").append("xin")...
	.append("huang")...
	.append("huang")...
	.append("huang");
反例：
StringBuffer sb = new StringBuffer();
//超过120个字符的情况下，不要在括号前换行
sb.append("zi").append("xin")...append
	("huang");
//参数很多的方法调用可能超过120个字符，不要在逗号前换行
method(args1, args2, args3, ...
	, argsX);
```

## 函数最大行数
一般不超过80行。

## 函数、类命名
1. 函数命名使用lowerCamelCase风格，必须遵从驼峰形式；
```
正例：getHttpMessage()
```
2.  类名使用UpperCamelCase风格，必须遵从驼峰形式；
```
正例：MarcoPolo / UserDO / XmlService / TcpUdpDeal /   TaPromotion
反例：macroPolo / UserDo / XMLService / TCPUDPDeal /   TAPromotion
```
3. 抽象类命名使用Abstract或Base开头；异常类命名使用Exception结尾；测试类命名以它要测试的类的名称开始，以Test结尾。
   
## 常量
1. 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长；
2. 不允许出现任何魔法值（即未经定义的常量）直接出现在代码中；
3. long或者Long初始赋值时，必须使用大写的L，不能是小写的l，小写容易跟数字1混淆，造成误解；
4. 不要使用一个常量类维护所有常量，应该按常量功能进行归类，分开维护。如：缓存相关的常量放在类：CacheConsts下；系统配置相关的常量放在类：ConfigConsts下；
   
## 空行规则
   方法体内的执行语句组、变量的定义语句组、不同的业务逻辑之间或者不同的语义之间插入一个空行。相同业务逻辑和语义之间不需要插入空行。

## 注释规则
1. 类、类属性、类方法的注释必须使用Javadoc规范，使用/**内容*/格式，不得使用//xxx方式。
2. 代码修改的同时，注释也要进行相应的修改，尤其是参数、返回值、异常、核心逻辑等的修改；
3. 方法内部单行注释，在被注释语句上方另起一行，使用//注释。方法内部多行注释使用/ */注释，注意与代码对齐。

## 操作符前后空格
1. 任何运算符左右必须加一个空格。
> 说明：运算符包括赋值运算符=、逻辑运算符&&、加减乘除符号、三目运行符等。

## 其他规则
1. 大括号的使用约定。如果是大括号内为空，则简洁地写成{}即可，不需要换行；如果是非空代码块则：
* 左大括号前不换行。
* 左大括号后换行。
* 右大括号前换行。
* 右大括号后还有else等代码则不换行；表示终止右大括号后必须换行。
1. 左括号和后一个字符之间不出现空格；同样，右括号和前一个字符之间也不出现空格；
2. if/for/while/switch/do等保留字与左右括号之间都必须加空格；
3. 方法参数在定义和传入时，多个参数逗号后边必须加空格；