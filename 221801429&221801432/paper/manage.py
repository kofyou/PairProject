from flask import Flask, render_template, current_app, request, flash, redirect
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

    id = db.Column(db.Integer, autoincrement=True, primary_key=True)
    title = db.Column(db.String(255))
    abstract = db.Column(db.Text)
    typeandyear = db.Column(db.String(255))
    keywords = db.Column(db.Text)
    releasetime = db.Column(db.String(255))
    link = db.Column(db.String(255))
    def to_short_dict(self):
        if self.abstract:
            if len(self.abstract) > 300:
                abstract = self.abstract[0:300]+"..."
            else:
                abstract = self.abstract
        else:
            abstract = 'null'
        paper = {
            "id": self.id,
            "title": self.title,
            "abstract": abstract,
            "typeandyear": self.typeandyear,
            "keywords": self.keywords,
            "releasetime": self.releasetime,
            "link": self.link
        }
        return paper

    def __repr__(self):
        return '<title:%s %s>' % (self.title, self.abstract)


class TopWord(db.Model):
    __tablename__ = "topword"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))
    frequency = db.Column(db.Integer)


# 历年热词分析模型类
class Analysis(db.Model):
    __tablename__ = "analysis"

    id = db.Column(db.Integer, autoincrement=True, primary_key=True)
    keywordid = db.Column(db.String(255))
    keyword = db.Column(db.String(255))
    frequency = db.Column(db.String(255))
    type = db.Column(db.String(255))
    year = db.Column(db.String(255))


# 显示首页/搜索
@app.route('/')
def hello_world():

    # 分页展示论文列表
    page = request.args.get("p", "1")

    # 搜索的关键词
    keywords = request.args.get("keywords", "")
    keywords = keywords.replace('+', ' ')
    filters = []
    isSearch = False
    perPage = 10
    if keywords:
        isSearch = True
        filters.append(Paper.title.contains(keywords))
        perPage = Paper.query.filter(*filters).count()
    page = int(page)
    paginate = Paper.query.filter(*filters).order_by("title").paginate(page, perPage, False)
    totalPage = paginate.pages
    currentPage = page
    items = paginate.items
    paper_list = []
    for paper in items:
        paper_list.append(paper.to_short_dict())
        
    # 顶会热词获取
    topWord = TopWord.query.all()
    top_list = []
    for i in topWord:
        top_list.append(i.name)
    data = {
        "isSearch": isSearch,
        "totalPage": totalPage,
        "currentPage": currentPage,
        "top": top_list,
        "paper": paper_list,
        "searchCount": perPage,
        "searchWord": keywords
    }
    return render_template("index.html", data=data)


# 从列表中删除
@app.route('/delete/<id>')
def delete(id):
    paper = Paper.query.filter(Paper.id == id).first()
    db.session.delete(paper)
    db.session.commit()
    return redirect('/')


# 查看详情页面
@app.route('/detail/<path:id>')
def goto_detail(id):

    topWord = TopWord.query.all()
    top_list = []
    for i in topWord:
        top_list.append(i.name)
    detail = Paper.query.filter_by(id=id).first()
    if detail.keywords is None:
        detail.keywords = "暂无"
    data = {
        'title': detail.title,
        'abstract': detail.abstract,
        'typeandyear': detail.typeandyear,
        'keywords': detail.keywords,
        'releasetime': detail.releasetime,
        'link': detail.link,
        'top': top_list
    }
    return render_template('detail.html', data=data)


# 查看统计图表
@app.route('/chart')
def goto_chart():
    topWord = TopWord.query.all()
    top_list = []
    for i in topWord:
        top_list.append(i.name)
    # 对分析的数据进行分类
    list_word = ['learning', 'feature extraction', 'training', 'image recon',
                 'neural nets', 'task analysis', 'computer vision', 'cameras',
                 'object detection', 'convolutional neural nets']
    paper_type = ['CVPR', 'ECCV', 'ICCV']
    cvpr_year = [2020, 2019, 2018]
    eccv_year = [2020, 2018, 2016]
    iccv_year = [2019, 2017, 2015]
    year_list = [cvpr_year, eccv_year, iccv_year]
    print(year_list)
    analysis_list = []
    for i in range(3):
        for j in range(3):
            temp_list = Analysis.query.filter_by(type=paper_type[i], year=year_list[i][j])\
                .order_by(Analysis.keyword).all()
            analysis_list.append(temp_list)
    data = {
        'top': top_list,
        'list_word': list_word,
        'analysis_list': analysis_list
    }
    return render_template('chart.html', data=data)


if __name__ == '__main__':
    app.run()
