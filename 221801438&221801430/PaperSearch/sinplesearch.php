<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui@2.15.1/lib/index.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


    <style>
        @import url("//unpkg.com/element-ui@2.15.1/lib/theme-chalk/index.css");
        .search {
            position: absolute;
            align-content: center;
            left: 15%;
            top: 22%;
            width: 60%;
            height: 35%;
            padding: 10%;
            line-height: 20px;
            opacity: 0.8;
            background-color: rgba(255,255,255,0.9);;
            border-radius: 25px 25px 25px 25px;
            font-size: 14px;
            text-align: center;
            box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.4);
            font-family: Roboto;
            border: 1px solid rgba(255, 255, 255, 100);
        }
        #typesreach {
            position: absolute;
            padding: 8px;
            height: 5%;
            left: 15%;
            top: 12%;
            width: 50%;
            background-color: rgba(255, 255, 255, 0.9);
            opacity: 0.8;
            border-radius: 50px;
            text-align: center;
            box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
            border: 1px solid rgba(255, 255, 255, 100);
        }

        #inputtext {
            position: relative;
            vertical-align: middle;
            padding: auto;
            height:15%;
            background-color: rgba(255, 255, 255, 0.9);
            opacity: 0.78;
            border: none;
            vertical-align: middle;
            border-radius: 25px 25px 25px 25px;
            text-align: left;
            box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.4);
            border: 3px solid rgba(255, 255, 255, 100);
        }

        #input {
            position: relative;
            border: none;
            margin: 5px;
            width: 85%;
            padding:1%;
            font-size: 14pt;
            vertical-align: middle;
            background-color: rgba(255,255,255,0);
            outline:none;
        }

        #typesreach-item {
            position: relative;
            width: 95%;
            left: 3%;
            font-size: 12pt;
        }

        #submit {
            position: absolute;
            border: none;
            left: 90%;
            top: 24%;
            height: 66%;
            width:10%;
            background-repeat: no-repeat;
            vertical-align: middle;
            background-color: rgba(0,0,0,0);
            background-image: url("image/search_certain.svg");
            border: none;
            outline: none;
        }

        #fire {
            position: absolute;
            border: none;
            left: 15%;
            top: 40%;
            width: 22px;
            height: auto;
        }

        #hotsearch {
            position: absolute;
            border: none;
            left: 18%;
            top: 40%;
            font-size: 13pt;
        }

        #line {
            position: absolute;
            border: 0.3px solid rgba(187, 187, 187, 100);
            width: 75%;
            top: 44%;
        }

    </style>
    
</head>
    <body>

        <div class="content">

            <div id="left">
                <img class="head" src="image/head.png" alt="alt"/>

                <div class="icons">
                    <div class="this_">
                        <img id="this_icon" src="image/mb-search.svg" alt="alt"/>
                        <p id="this_p">论文查询</p>
                    </div>
                    <div class="icon" id="icon2" onclick="change2()">
                        <img src="image/md-dehaze.svg" alt="alt"/>
                        <p>列表</p>
                    </div>
                    <div class="icon" id="icon3" onclick="change3()">
                        <img src="image/antOutline-line-chart.svg" alt="alt"/>
                        <p>数据分析</p>
                    </div>
                    <div class="icon" id="icon4">
                        <img src="image/antOutline-setting.svg" alt="alt"/>
                        <p>设置</p>
                    </div>
                </div>
            </div>

            <div class="header">
                <img src="image/logo.png" alt="alt"/>
                <a class="link" id="link1" href="#" target="_blank">首页</a>
                <a class="link" id="link2" href="https://www.cnki.net/" target="_blank">中国知网</a>
                <a class="link" id="link3" href="https://www.wanfangdata.com.cn/index.html" target="_blank">万方</a>
                <a class="link" id="link4"href="http://www.gov.cn/shuju/index.htm" target="_blank">国家数据</a>
            </div>

            <script src="//unpkg.com/vue/dist/vue.js"></script>
            <script src="//unpkg.com/element-ui@2.15.1/lib/index.js"></script>
            <div id="typesreach">
                <template>
                    <el-tabs v-model="activeName" id="typesreach-item" @tab-click="handleClick">
                        <el-tab-pane label="单个搜索" name="first"></el-tab-pane>
                        <el-tab-pane label="批量查询" name="second"></el-tab-pane>
                    </el-tabs>
                </template>
            </div>

            <div class="search">
                <form id="inputtext" action="search_result.php">
                    <input type="text" id="input" name="in" placeholder="请输入论文标题"></input>
                    <?php
                        @session_start();
                        $_SESSION["input"] = isset($_GET["in"])?$_GET["in"]:"";
                    ?>
                    <a href="search_result.php" id="submit"></a>
                </form>
                

                <img id="fire" src="image/antOutline-fire.svg" alt="alt"/>
                <label id="hotsearch">热门搜索</label>
                <div id="line"></div>
                
                <label style="position:absolute; text-align: center; left: 45%; top: 65%; font-size: 17pt;">deep learning</label>
                <label style="position:absolute; text-align: center; left: 23%; top: 57%; font-size: 14pt;">object detection</label>
                <label style="position:absolute; text-align: center; left: 39%; top: 58%; font-size: 13pt;">semantic segmentation</label>
                <label style="position:absolute; text-align: center; left: 55%; top: 52%; font-size: 12pt;">domain adaptation</label>
                <label style="position:absolute; text-align: center; left: 20%; top: 63%; font-size: 15pt;">convolutional neural network</label>
                <label style="position:absolute; text-align: center; left: 60%; top: 63%; font-size: 10pt;">convolutional neural networks</label>
                <label style="position:absolute; text-align: center; left: 59%; top: 72%; font-size: 16pt;">3d reconstruction</label>
                <label style="position:absolute; text-align: center; left: 20%; top: 50%; font-size: 13pt;">unsupervised learning</label>
                <label style="position:absolute; text-align: center; left: 33%; top: 70%; font-size: 13pt;">attention</label>
                <label style="position:absolute; text-align: center; left: 28%; top: 75%; font-size: 12pt;">generative adversarial networks</label>
                <label style="position:absolute; text-align: center; left: 65%; top: 55%; font-size: 13pt;">deep learning</label>
                <label style="position:absolute; text-align: center; left: 63%; top: 49%; font-size: 10pt;">3d reconstruction</label>
                <label style="position:absolute; text-align: center; left: 20%; top: 89%; font-size: 10pt;">attention</label>
                <label style="position:absolute; text-align: center; left: 41%; top: 80%; font-size: 11pt;">convolutional neural networks</label>
                <label style="position:absolute; text-align: center; left: 50%; top: 89%; font-size: 12pt;">convolutional neural network</label>
                <label style="position:absolute; text-align: center; left: 69%; top: 85%; font-size: 13pt;">semantic segmentation</label>
                <label style="position:absolute; text-align: center; left: 19%; top: 81%; font-size: 13pt;">domain adaptation</label>
                <label style="position:absolute; text-align: center; left: 63%; top: 80%; font-size: 14pt;">object detection</label>

            </div>
            
        </div>


    </body>
    <script>
        var input = {
            data() {
                return {
                    input: ''
                }
            }
        }
        var Ctor = Vue.extend(input)
        new Ctor().$mount('#inputtext')

        var title = {
            data() {
            return {
                activeName: 'first'
            };
            },
            methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
            }
        };
        var Ctor = Vue.extend(title)
        new Ctor().$mount('#typesreach')
        
        function change2() {
            //防止表单跳转
            event.returnValue = false;
            window.location.href = "paper_list.php";
        }

        function change3() {
            //防止表单跳转
            event.returnValue = false;
            window.location.href = "data_analyse.html";
        }

    </script>
</html>