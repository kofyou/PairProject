from flask import Flask, request, render_template
from fileoperate import *
from Adapter import *
app = Flask(__name__)
operate=FileOperate("D:/论文数据")
@app.route('/', methods=['GET'])
def homeget():
    global operate
    operate=FileOperate("D:/论文数据")
    return render_template('home.html',Quene="Start Reverse")

@app.route('/', methods=['POST'])
def homepost():
    print(request.form)
    if request.form.__contains__('sign'):
        dname=request.form['filename']+".json"
        gname=request.form['search']
        results=operate.file_search(gname)
        if operate.file_delete(dname):
            return render_template('home.html',message="删除成功",results=results)
        return render_template('home.html',message="删除失败",results=results)
    elif request.form.__contains__('sort'):
        if operate.file_sort():
            return render_template('home.html',IsSort="Reverse",Quene="Start Not Reverse")
        else:
            return render_template('home.html',IsSort="Unreverse",Quene="Start  Reverse")

@app.route('/picture', methods=['GET'])
def pictureget():
    return render_template('picture.html',results=["1","2"])


@app.route('/picture', methods=['POST'])
def picturepost():
    if request.form.__contains__('sign'):
        adapter=AdapterClass()
        key=request.form.get('key')
        lis=adapter.get_keys()
        for d in lis:
            print(d)
        print(adapter.get_key_list("Videos"))
    else:
        print("无")
    return render_template('picture.html',results=["1","2"])





if __name__ == '__main__':
    app.run()