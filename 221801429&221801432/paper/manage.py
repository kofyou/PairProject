"""
  相关配置信息
"""

from flask import Flask
from flask_sqlalchemy import  SQLAlchemy
from redis import StrictRedis


class Config(object):
    SQLALCHEMY_DATABASE_URL = "mysql+pymysql://%s:%s@%s/%s" % ('root', 'HRYYCFLqswslhk1', '127.0.0.1', 'paper')
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    DEBUG = True
    REDIS_HOST = "127.0.0.1"
    REDIS_PORT = 6379


app = Flask(__name__)
app.config.from_object(Config)

db = SQLAlchemy(app)

redis_store = StrictRedis(host= Config.REDIS_HOST, port= Config.REDIS_PORT, decode_responses=True)

@app.route('/')
def hello_world():
    redis_store.set("name", "陈耀")
    print(redis_store.get("name"))
    print("1111")
    return 'Hello World!'+ redis_store.set("name", "陈耀")


if __name__ == '__main__':
    app.run()
