## 前端规范

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



## 后端规范

Go 编程格式使用官方[gofmt](https://golang.org/pkg/cmd/gofmt/)工具自动格式化。

