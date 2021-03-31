## 一、命名规范
#### 1、驼峰式命名法由小(大)写字母开始，后续每个单词首字母都大写。
按照第一个字母是否大写，分为：
① Pascal Case 大驼峰式命名法：首字母大写。eg：StudentInfo、UserInfo、ProductInfo
② Camel Case 小驼峰式命名法：首字母小写。eg：studentInfo、userInfo、productInfo
#### 2、变量命名
命名方法：小驼峰式命名法。
命名规范：前缀应当是名词。(函数的名字前缀为动词，以此区分变量和函数)
命名建议：尽量在变量名字中体现所属类型，如:length、count等表示数字类型；而包含name、title表示为字符串类型。
示例：

// 好的命名方式
var maxCount = 10;
var tableTitle = 'LoginTable';

// 不好的命名方式
var setCount = 10;
var getTitle = 'LoginTable';

#### 3、函数命名
命名方法：小驼峰式命名法。
命名规范：前缀应当为动词。
命名建议：可使用常见动词约定

示例：
// 是否可阅读

function canRead() {
    return true;
}

// 获取名称
function getName() {
    return this.name;
}

#### 4、常量
命名方法：名称全部大写。
命名规范：使用大写字母和下划线来组合命名，下划线用以分割单词。
命名建议：无。
示例：

var MAX_COUNT = 10;
var URL = 'http://www.baidu.com';

#### 5、构造函数
介绍：在JS中，构造函数也属于函数的一种，只不过采用new 运算符创建对象。
命名方法：大驼峰式命名法，首字母大写。
命名规范：前缀为名称。
命名建议：无。
示例：

function Student(name) {
    this.name = name;
}

var st = new Student('tom');

#### 6 类的成员
类的成员包含：
① 公共属性和方法：跟变量和函数的命名一样。
② 私有属性和方法：前缀为_(下划线)，后面跟公共属性和方法一样的命名方式。

function Student(name) {
    var _name = name; // 私有成员

    // 公共方法
    this.getName = function () {
        return _name;
    }
     
    // 公共方式
    this.setName = function (value) {
        _name = value;
    }
}
var st = new Student('tom');
st.setName('jerry');
console.log(st.getName()); // => jerry：输出_name私有变量的值

## 二、代码注释
#### 1、JS支持两种不同类型的注释：单行注释和多行注释。
#### 2、注释参数

@param @argument 指定参数名和说明来描述一个函数参数
@returns 描述函数的返回值
@author 指示代码的作者
@deprecated 指示一个函数已经废弃，而且在将来的代码版本中将彻底删除。要避免使用这段代码
@see 创建一个HTML链接，指向指定类的描述
@version 指定发布版本
@requires 创建一个HTML链接，指向这个类所需的指定类
@throws @exception 描述函数可能抛出的异常的类型
{@link} 创建一个HTML链接，指向指定的类。这与@see很类似，但{@link}能嵌在注释文本中
@fileoverview 这是一个特殊的标记。如果在文件的第一个文档块中使用这个标记，则指定该文档块的余下部分将用来提供这个文件的概述
@class 提供类的有关信息，用在构造函数的文档中
@constructor 明确一个函数是某个类的构造函数
@type 指定函数的返回类型
@extends 指示一个类派生了另一个类。JSDoc通常自己就可以检测出这种信息，不过，在某些情况下则必须使用这个标记
@private 指示一个类或函数是私有的。私有类和函数不会出现在HTML文档中，除非运行JSDoc时提供了–private命令行选项
@final 指示一个值是常量值。要记住JavaScript无法真正保证一个值是常量
@ignore JSDoc忽略有这个标记的函数

示例：

/**
 * 将助手1.0 2.0 办理项 情绪禁语的推荐话术渲染在下滑块中
 * @param {object} msg 用于获取suffix参数（必填）
 * @param {string} id 话术推送渲染的唯一id（必填）
 * @param ResultInfo 需要展示的话术（必填）
 * @param mainIntentionName：助手1.0意图名称（非必填）
 * @param flowName：助手2.0预处理名称（非必填）
 * @author 王玥 2020/12/10
 */