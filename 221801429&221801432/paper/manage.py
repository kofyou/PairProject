from flask import Flask,render_template, current_app
from flask_sqlalchemy import SQLAlchemy
from flask_wtf.csrf import CSRFProtect
from config import Config
from redis import StrictRedis


app = Flask(__name__)
app.config.from_object(Config)
CSRFProtect(app)
db = SQLAlchemy(app)
# redis_store = StrictRedis(host= Config.REDIS_HOST, port= Config.REDIS_PORT, decode_responses=True)


# 模型类定义
class Paper(db.Model):
    __tablename__ = 'paper'

    title = db.Column(db.String(255),primary_key=True)
    abstract = db.Column(db.Text)
    typeandyear = db.Column(db.String(255))
    keywords = db.Column(db.Text)
    releasetime = db.Column(db.String(255))
    link = db.Column(db.String(255))

    def __repr__(self):
        return '<title:%s %s>' % (self.title, self.abstract)


class TopWord(db.Model):
    __tablename__ = "topword"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))
    frequency = db.Column(db.Integer)


@app.route('/')
def hello_world():
    try:
        count = TopWord.query.all()
    except Exception as e:
        print("err!!获取数量失败")
    top_list = []
    for i in count:
        top_list.append(i.name)
    data = {
        "top": top_list
    }
    return render_template("index.html", data=data)


if __name__ == '__main__':
    app.run()
