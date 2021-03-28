# -*- coding: utf-8 -*-
import os
import json
import pymysql

cursor = db.cursor()

parent = '论文数据/CVPR'
for cvpr in os.listdir(parent):
    with open(parent + '/' + cvpr, 'r', encoding='utf-8') as f:
        data = json.loads(f.read()[:-1])
        out = {}
        out["title"] = cvpr[:-5]
        out["abstract"] = ""
        if data.get("abstract") != None:
            out["abstract"] = data["abstract"]
        out["keywords"] = ""
        if data.get("keywords") != None:
            _set = set()
            for value in data["keywords"]:
                for kwd in value["kwd"]:
                    _set.add(kwd)
            out["keywords"] = ','.join(_set)
        out["url"] = ""
        if data.get("pdfUrl"):
            out["url"] = "https://ieeexplore.ieee.org" + data["pdfUrl"]
        try:
            sql = "INSERT INTO posts(title, keywords, abstract, url) VALUES (%s, %s, %s, %s)"
            cursor.execute(sql, (out["title"], out["keywords"], out["abstract"], out["url"]))
            db.commit()
        except Exception as e:
            print(e)
            db.rollback()
            break
db.close()