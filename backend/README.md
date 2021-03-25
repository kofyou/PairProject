## 数据库设置

User表-论文表-收藏表

User表：uid-username-password

论文表：aid-title-no-author-type-keyworks-url-abstract   

关键词表：kid-keyword-aid-year

收藏文件表：sid-aid-uid



## 数据导入

```python
import json
import os
from os.path import join
import mysql.connector
def extract1(d):
  authors = []
  for author in d["authors"]:
    authors.append(author["name"])
  keywords = []
  abstract = ""
  try:
    for keyword in d["keywords"]:
      for kwd in keyword["kwd"]:
        keywords.append(kwd)

  except Exception :
    pass
  try:
    abstract = d["abstract"]
  except Exception:
    pass
  return {"abstract":abstract,"title": d["displayPublicationTitle"], "no": d["articleNumber"], "author": authors, "type": d["subType"], "keywords": keywords, "url": d["doiLink"],"year":d["publicationYear"]}

def extract2(d):
  t,year = d["会议和年份"].split(" ")
  return {"abstract":d["摘要"],"title": d["论文名称"], "no": None, "author": [], "type": t, "keywords": d["关键词"], "url": d["原文链接"],"year":year}



data = []

for root, dirs, files in os.walk("."):
  if root == ".":
    continue
  for f in files:
    with open(join(root,f),"r",encoding="utf-8") as jsonfile:
      if "ECCV" in root:
        text = jsonfile.read()
        data.append(extract2(json.loads(text)))
      else:
        text = jsonfile.read()[:-1]
        data.append(extract1(json.loads(text)))

conn = mysql.connector.connect(user='root', password='123456', database='article',host="172.28.144.1")
for i in range(len(data)):
  d = data[i]
  cursor = conn.cursor()
  cursor.execute('insert into article (title, no,author,type,keywords,url,abstract) values (%s,%s,%s,%s,%s,%s,%s)', [d["title"],d["no"],json.dumps(d["author"]), d["type"],json.dumps(d["keywords"]),d["url"],d["abstract"]])
  for keyword in d["keywords"]:
    cursor.execute('insert into keywords (keyword,aid,year) values (%s,%s,%s)', [keyword,i,d["year"]])
  conn.commit()
  cursor.close()%
```





## 后端开发

使用nodejs

## API 描述

### /user/login

**简要描述：**

- 用户登入接口

**请求方式：**

- POST

**参数：**

| 参数名   | 必选 | 类型   | 说明   |
| -------- | ---- | ------ | ------ |
| username | 是   | string | 用户名 |
| password | 是   | string | 密码   |

**返回示例**

登入成功

```
{
    "code": 0,
    "msg": "登入成功"
}
```

登入失败

```
{
    "code": 1,
    "err": "账号或密码错误"
}
```





**返回参数说明**

| 参数名 | 类型   | 说明                 |
| ------ | ------ | -------------------- |
| code   | int    | 1:错误,0:正常运行    |
| err    | string | 错误信息             |
| msg    | string | 正常运行下返回的信息 |



### /user/register

**简要描述：**

- 用户注册接口

**请求方式：**

- POST

**参数：**

| 参数名   | 必选 | 类型   | 说明   |
| -------- | ---- | ------ | ------ |
| username | 是   | string | 用户名 |
| password | 是   | string | 密码   |

**返回示例**

注册成功

```
{
    "code": 0
}
```

注册失败

```
{
    "code": 1,
    "err": "用户存在"
}
```





**返回参数说明**

| 参数名 | 类型   | 说明                 |
| ------ | ------ | -------------------- |
| code   | int    | 1:错误,0:正常运行    |
| err    | string | 错误信息             |
| msg    | string | 正常运行下返回的信息 |

### /search

**简要描述：**

- 通过title或keyword搜索论文

**请求方式：**

- GET

**参数：**

| 参数名  | 必选 | 类型   | 说明   |
| ------- | ---- | ------ | ------ |
| title   | 否   | string | 标题   |
| keyword | 否   | string | 关键字 |

**返回示例**

搜索结果

```
{
    "code": 0,
    "article": [
        {
            "aid": 8195,
            "title": "Teaching Machines to Understand Baseball Games: Large-Scale Baseball Video Database for Multiple Video Understanding Tasks",
            "no": null,
            "author": "[]",
            "type": "ECCV",
            "keywords": "[\"Video understanding\", \"Large-scale video dataset\", \"Action recognition\", \"Temporal localization\"]",
            "url": "https://doi.org/10.1007/978-3-030-01267-0_25",
            "abstract": "A major/."
        }
    ]
}
```





**返回参数说明**

| 参数名  | 类型   | 说明                 |
| ------- | ------ | -------------------- |
| code    | int    | 1:错误,0:正常运行    |
| err     | string | 错误信息             |
| msg     | string | 正常运行下返回的信息 |
| article | list   | 搜索结果             |



### /star/add

**简要描述：**

- 收藏文章

**请求方式：**

- POST

**参数：**

| 参数名 | 必选 | 类型   | 说明   |
| ------ | ---- | ------ | ------ |
| aid    | 是   | string | 论文id |

**返回示例**



```
{
    "code": 0,
    "msg": "添加成功"
}
```



报错：

```
{
    "code": 1,
    "err": "Cannot add or update a child row: a foreign key constraint fails (`article`.`star`, CONSTRAINT `star_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `article` (`aid`))"
}
```





**返回参数说明**

| 参数名  | 类型   | 说明                 |
| ------- | ------ | -------------------- |
| code    | int    | 1:错误,0:正常运行    |
| err     | string | 错误信息             |
| msg     | string | 正常运行下返回的信息 |
| article | list   | 搜索结果             |



### /star/delete

**简要描述：**

- 删除收藏文章

**请求方式：**

- POST

**参数：**

| 参数名 | 必选 | 类型   | 说明   |
| ------ | ---- | ------ | ------ |
| aid    | 是   | string | 论文id |

**返回示例**



```
{
    "code": 0,
    "msg": "成功删除1条数据"
}
```



**返回参数说明**

| 参数名 | 类型   | 说明                 |
| ------ | ------ | -------------------- |
| code   | int    | 1:错误,0:正常运行    |
| err    | string | 错误信息             |
| msg    | string | 正常运行下返回的信息 |





### /star/list

**简要描述：**

- 列出用户收藏文章

**请求方式：**

- GET

**参数：**

无

**返回示例**



```
{
    "code": 0,
    "starlist": [
        1
    ]
}
```



**返回参数说明**

| 参数名   | 类型   | 说明                 |
| -------- | ------ | -------------------- |
| code     | int    | 1:错误,0:正常运行    |
| err      | string | 错误信息             |
| msg      | string | 正常运行下返回的信息 |
| starlist | list   | 收藏文件aid列表      |

### /top10

**简要描述：**

- 返回top10的关键词和论文数量,以及总的论文数量

**请求方式：**

- POST

**参数：**

| 参数名 | 必选 | 类型   | 说明   |
| ------ | ---- | ------ | ------ |
| min    | 否   | int | 最小年份 |
| max    | 否   | int | 最大年份 |

**返回示例**



```
{
    "code": 0,
    "top": [
        {
            "keyword": "Computer vision",
            "count": 3887
        },
        {
            "keyword": "feature extraction",
            "count": 3439
        },
        {
            "keyword": "Cameras",
            "count": 2860
        },
        {
            "keyword": "image segmentation",
            "count": 2856
        },
        {
            "keyword": "learning (artificial intelligence)",
            "count": 2493
        },
        {
            "keyword": "Object detection",
            "count": 2487
        },
        {
            "keyword": "training",
            "count": 2028
        },
        {
            "keyword": "image reconstruction",
            "count": 1951
        },
        {
            "keyword": "image classification",
            "count": 1623
        },
        {
            "keyword": "face recognition",
            "count": 1485
        }
    ],
    "total": 237919
}
```



**返回参数说明**

| 参数名 | 类型   | 说明                 |
| ------ | ------ | -------------------- |
| code   | int    | 1:错误,0:正常运行    |
| err    | string | 错误信息             |
| msg    | string | 正常运行下返回的信息 |
| total  | int    | 在min和max之间论文总数             |
| top    | list   | 在min和max之间top10论文和数量      |

