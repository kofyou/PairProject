# -*- coding: utf-8 -*-
import os
import json
import pymysql

db = pymysql.connect(host='####',user='####',password='####',database='####')
cursor = db.cursor()
def foo(dir):
    for file in os.listdir(dir):
        with open(dir + '/' + file, 'r', encoding='utf-8') as f:
            data = json.loads(f.read()[:-1])
            out = {}
            out["title"] = data["formulaStrippedArticleTitle"]
            out["abstract"] = data.get("abstract" , "")
            out["keywords"] = ','.join(sum([value for value in [value["kwd"] for value in [value for value in data.get("keywords", {})]]],[]))
            out["url"] = data["doiLink"]
            try:
                sql = "INSERT INTO posts(title, keywords, abstract, url) VALUES (%s, %s, %s, %s)"
                cursor.execute(sql, (out["title"], out["keywords"],
                out["abstract"], out["url"]))
                db.commit()
            except Exception as e:
                print(e)
                db.rollback()
                break
foo('论文数据/CVPR')
foo('论文数据/ICCV')

db.close()