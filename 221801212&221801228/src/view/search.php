<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <script src="view/jquery/jquery.min.js"></script>
    <link href="view/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="view/css/cloud.css">
    <script src="view/bootstrap/bootstrap.min.js"></script>
    <title>搜索</title>
    <link type="text/css" rel="stylesheet" href="view/css/search.css" />
    <?php require_once(dirname(__FILE__) . "\\PaperListView.php"); ?>
    <?php require_once(dirname(__FILE__) . "\\PopularWordView.php"); ?>
</head>

<body>


    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <ul class="nav nav-pills">
                    <li class="active">
                        <a href="index.php">搜索</a>
                    </li>
                    <li>
                        <a href="?statistics=true">统计</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-4 column">
                <h3 class="text-info text-center">
                    爬取论文
                </h3>
                <div><label>热门标签:</label></div>
                <div>
                    <?php PopularWordView::renderWords($result['popularWords']) ?>
                </div>
                <br></br>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="input-group">
                            <form action="?r=search" method="GET">
                                <input type="text" class="form-control" placeholder="标题" name="title" value="<?=$result['title']?>">
                                <input type="text" class="form-control" placeholder="关键词" name="keyword" value="<?=$result['keyword']?>">
                                <input type=“text” class="form-control" placeholder="年份" name="year" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value="<?=$result['year']?>">
                                <select name="forum" id="forum" class="form-control">
                                    <option value="">论坛</option>
                                    <option value="ECCV">ECCV</option>
                                    <option value="CPVR">CPVR</option>
                                    <option value="ICCV">ICCV</option>
                                </select>
                                <input type="submit" class="form-control" value="搜索">
                            </form>
                        </div>
                        <label for="file" class=" btn btn-default">选择文件</label>
                        <input id="file" type="file" style="display:none">
                        <button type="button" class="btn btn-info">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-clockwise" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M8 3a5 5 0 1 0 4.546 2.914.5.5 0 0 1 .908-.417A6 6 0 1 1 8 2v1z"></path>
                                <path d="M8 4.466V.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384L8.41 4.658A.25.25 0 0 1 8 4.466z"></path>
                            </svg>
                            清除
                        </button>
                    </div>

                </div>
            </div>
            <div class="col-md-8 column">
                <h3 class="text-info text-center">
                    显示论文
                    <?php
                    if (!$result['isFirst']) {
                        $total = $result['total'];
                        echo "共（";
                        echo $total;
                        echo "）条记录";
                    }
                    ?>
                </h3>
                <?php
                if (!$result['isFirst']) {
                    if ($result['total'] == 0) {
                        echo '<h3>没有相关的记录</h3>';
                    } else {
                        PaperListView::renderPaperList($result['papers']);
                    }
                }
                ?>
            </div>
        </div>
    </div>
</body>

</html>