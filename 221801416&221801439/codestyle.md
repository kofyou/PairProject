W3C的XHTML代码规范、php-fig组织制定的PSR以及来自HTML中文网的CSS代码标准

### 一、标准的XHTML头信息格式：

```
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
```

### 二、JavaScript定义：

必须以<script language="javascript" type="text/javascript">

### 三、标签与属性

1、所有标签元素和属性的名字都必须使用小写

2、所有属性必须用引号“ ”括起来

3、给所有属性都必须有一个值

4、所有标记都必须有一个相应的结束标记

5、所有标记都必须合理嵌套

### 四、PHP规范

1、源文件只能使用<?php和<?=两种标签

2、源文件必须是不带BOM的UTF-8编码文件

3、源文件缩进采用4个空格

4、严格控制每行120个字符

5、关键字必须小写

6、if、else、elseif、switch、for、foreach、case、while、go、try、catch 等关键词后面必须加空格，流程控制语句起始的花括号是不需要另起一行。

### 五、CSS语法

1、对于以逗号分隔的属性值，每个逗号后面都应该插入一个空格

2、不要在rgb()、rgba()值内部的逗号后面插入表格，这样有利于从多个属性值中区分多个颜色值

3、对于属性值或者颜色参数，省略小于1的小数前面的0

4、十六进制应该全部小写，尽量使用简写模式

5、避免为0值指定单位，用margin:0代替margin:0px

6、不要用@import，可使用多个<link>元素代替

### 六、class命名

1、class名称中只能出现小写字符和破折号

2、避免过度任意的简写

3、名称应尽可能短且意义明确





