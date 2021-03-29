from flask import Flask, render_template, current_app, request
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

    title = db.Column(db.String(255), primary_key=True)
    abstract = db.Column(db.Text)
    typeandyear = db.Column(db.String(255))
    keywords = db.Column(db.Text)
    releasetime = db.Column(db.String(255))
    link = db.Column(db.String(255))

    def to_short_dict(self):
        if len(self.abstract) > 300:
            abstract = self.abstract[0:300]+"..."
        else:
            abstract = self.abstract
        paper = {
            "title": self.title,
            "abstract": abstract,
            "typeandyear": self.typeandyear,
            "keywords": self.keywords,
            "releasetime":self.releasetime,
            "link":self.link
        }
        return paper

    def __repr__(self):
        return '<title:%s %s>' % (self.title, self.abstract)


class TopWord(db.Model):
    __tablename__ = "topword"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))
    frequency = db.Column(db.Integer)


# 显示首页
@app.route('/')
def hello_world():

    # 分页展示论文列表
    page = request.args.get("p", "1")

    # 搜索的关键词
    keywords = request.args.get("keywords","")
    keywords = keywords.replace('+', ' ')
    filters = []
    isSearch = False
    perPage = 10
    if keywords:
        isSearch = True
        filters.append(Paper.title.contains(keywords))
        filters.append(Paper.keywords.contains(keywords))
        filters.append(Paper.abstract.contains(keywords))
        perPage = Paper.query.filter(*filters).count()
    try:
        page = int(page)
    except Exception as e:
        page = 1
    try:
        paginate = Paper.query.filter(*filters).order_by("title").paginate(page, perPage, False)
    except Exception as e:
        print(e)
        print("err!")
    totalPage = paginate.pages
    currentPage = page
    items = paginate.items
    paper_list = []
    for paper in items:
        paper_list.append(paper.to_short_dict())
        
    # 顶会热词获取
    try:
        topWord = TopWord.query.all()
    except Exception as e:
        print("err!!获取失败")
    top_list = []
    for i in topWord:
        top_list.append(i.name)
    data = {
        "isSearch": isSearch,
        "totalPage": totalPage,
        "currentPage": currentPage,
        "top": top_list,
        "searchCount":perPage,
        "searchWord":keywords
    }
    return render_template("index.html", data=data)


if __name__ == '__main__':
    app.run()