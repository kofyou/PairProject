from flask import Flask, request, render_template
from fileoperate import *
from Adapter import *
app = Flask(__name__)
adapter=AdapterClass()
@app.route('/', methods=['GET'])
def homeget():
    keys=adapter.get_keys()
    print(keys)
    return render_template('home.html',keys=keys)


@app.route('/a', methods=['POST'])
def homepost():
    print(request.form)
    if request.form.__contains__('sign'):
        dname=request.form['filename']+".json"
        gname=request.form['search']
        results=adapter.get_json_messages(gname)
        print(results)
        if adapter.file_delete(dname):
            return render_template('list.html',message="删除成功",results=results)
        return render_template('list.html',message="删除失败",results=results)
    elif request.form.__contains__('sort'):
        if adapter.file_sort():
            return render_template('home.html',IsSort="Reverse",Quene="Start Not Reverse")
        else:
            return render_template('home.html',IsSort="Unreverse",Quene="Start  Reverse")

@app.route('/picture', methods=['GET'])
def pictureget():
    return render_template('picture.html',results=["1","2"])


@app.route('/picture', methods=['POST'])
def picturepost():
    if request.form.__contains__('sign'):
        key=request.form.get('key')
        #得到所有关键词
        # lis=adapter.get_keys()
        # for d in lis:
        #     print(d)
        #打印一个关键词的文章列表
        # print(adapter.get_key_list("Friction"))
        print(adapter.get_dict_meet_year_num())
    else:
        print("无")
    return render_template('picture.html',results=["1","2"])





if __name__ == '__main__':
    app.run()