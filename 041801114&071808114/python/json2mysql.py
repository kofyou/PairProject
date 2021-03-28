# -*- coding: utf-8 -*-
import os
import json
import pymysql

db = pymysql.connect(host='####',user='####',password='####',database='####')
sql = "INSERT INTO posts(year, meeting, title, keywords, abstract, url) VALUES (%s, %s, %s, %s, %s, %s)"
cursor = db.cursor()
def foo(meeting):
    for file in os.listdir("论文数据/" + meeting):
        with open("论文数据/" + meeting + '/' + file, 'r', encoding='utf-8') as f:
            data = json.loads(f.read()[:-1])
            title = data["formulaStrippedArticleTitle"]
            abstract = data.get("abstract" , "")
            keywords = ','.join(sum([value for value in [value["kwd"] for value in [value for value in data.get("keywords", {})]]],[]))
            url = data["doiLink"]
            year = data["publicationYear"]
            cursor.execute(sql, (year, meeting, title, keywords, abstract, url))
try:
    foo('CVPR')
    foo('ICCV')

    for eccv in os.listdir(parent):
        with open("论文数据/ECCV/" + eccv, 'r', encoding='utf-8') as f:
            data = json.load(f)
            cursor.execute(sql, (data["发布时间"][-4:], "ECCV", data["论文名称"], ','.join(data["关键词"]), data["摘要"], data["原文链接"]))
except Exception as e:
    print(e)
    db.rollback()
else:
    db.commit()
db.close()