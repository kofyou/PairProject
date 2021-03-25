"""
  相关配置信息
"""

from flask import Flask
from flask_sqlalchemy import  SQLAlchemy


class Config(object):
    SQLALCHEMY_DATABASE_URL = "mysql+pymysql://%s:%s@%s/%s" % ('root', 'HRYYCFLqswslhk1', '127.0.0.1', 'paper')
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    DEBUG = True


app = Flask(__name__)
app.config.from_object(Config)

db = SQLAlchemy(app)


@app.route('/')
def hello_world():
    return 'Hello World!'


if __name__ == '__main__':
    app.run()
