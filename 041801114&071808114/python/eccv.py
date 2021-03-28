# -*- coding: utf-8 -*-
import os
import json
import pymysql

cursor = db.cursor()

parent = '论文数据/ECCV'
for cvpr in os.listdir(parent):
    with open(parent + '/' + cvpr, 'r', encoding='utf-8') as f:
        data = json.load(f)
        try:
            sql = "INSERT INTO posts(title, keywords, abstract, url) VALUES (%s, %s, %s, %s)"
            cursor.execute(sql, (data["论文名称"], ','.join(data["关键词"]), data["摘要"], data["原文链接"]))
            db.commit()
        except Exception as e:
            print(e)
            db.rollback()
            break
db.close()