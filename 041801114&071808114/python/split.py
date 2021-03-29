# -*- coding: utf-8 -*-
import os
import json
import pymysql

dbin = pymysql.connect(host='######',user='######',password='######',database='######', charset='utf8')
sqlin = "SELECT id, keywords FROM cv_posts WHERE id LIMIT %s, 500;"

cursorin = pymysql.cursors.SSCursor(dbin)

offset = 9000

dbout = pymysql.connect(host='######',user='######',password='######',database='######', charset='utf8')
sqlout = "INSERT INTO cv_keyword VALUES (%s, %s);"
sqlrow = "SELECT count(*) FROM (SELECT * FROM cv_posts WHERE id LIMIT %s, 500) AS temp"
cursorout = dbout.cursor()

cnt = 0

while True:
    try:
        cursorout.execute(sqlrow, (offset))
        if cursorout.fetchone()[0] > 0:
            cursorin.execute(sqlin, (offset))
            offset += 500
            while True:
                row = cursorin.fetchone()
                if not row:
                    break
                for kwd in [raw for raw in set(row[1].split(","))]:
                    cursorout.execute(sqlout, (row[0], kwd))
                cnt += 1
                if cnt % 10 == 0:
                    print(cnt)
    except Exception as e:
        print(e)
        dbout.rollback()
        break
    else:
        dbout.commit()
        break

dbin.close()
dbout.close()