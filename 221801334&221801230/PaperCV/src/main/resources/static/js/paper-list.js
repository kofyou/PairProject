$(document).ready(function () {
    var httpRoot = "http://localhost:8080";
    // var httpRoot = "http://120.24.27.29:8080";
    // var MAX_COUNT = 9999999999;

    var items = [];
    $("#read-list").click(function (){
        $("#item-list").empty();
        selectPaper($("#paperSearch").val());
    });

    selectPaper("all");

    function selectPaper(title) {
        $.ajax({
            // url:httpRoot + `/paper/` + $("#paperSearch").val(),
            url:httpRoot + `/paper/` + title,
            type:"GET",
            dataType:"json",
            async:false,
            success:function(result){
                for (var i = 0;i < result.length;i++){
                    // if (i >= MAX_COUNT){
                    //     break;
                    // }
                    var new_item = "    <div class=\"item\" id=\"item" + i + "\">\n" +
                        "        <div class=\"content\">\n" +
                        "            <div class=\"img\"><img src=\"img/paper.png\" alt=\"paper\" width=\"270px\" height=\"340px\"></div>\n" +
                        "            <div class=\"text\">\n" +
                        "                <p class=\"text-head\">" + result[i].title +
                        "                </p>\n" +
                        "                <p style=\"font-weight: bolder; margin: 10px 0;\">Abstract:</p>\n" +
                        "                <div class=\"text-content\">" + result[i].paperAbstract + "</div>\n" +
                        "                <div style=\"float: right\"><a href=\"" + result[i].url + "\">查看更多</a></div>"+
                        "                <div style=\"float: left\"><p><span style=\"font-weight: bolder;\">Keywords:</span>" + result[i].keyWord + "</p></div>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "        <div class=\"footer\">\n" +
                        "            <div><a href=\"" + result[i].url + "\">" + result[i].url + "</a></div>\n" +
                        "            <div>\n" +
                        "                <a type=\"submit\" class=\"btn btn-primary mb-2\">翻译</a>\n" +
                        "                <a type=\"submit\" class=\"btn btn-primary mb-2\">收藏</a>\n" +
                        "                <a type=\"submit\" name=\"item" + i + "\" class=\"btn btn-primary mb-2" +
                        " delete-item\">删除</a>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>"
                    items.push(new_item);
                }
            },
        });
        for (var i = 0;i < items.length;i++) {
            $("#item-list").append(items[i]);
        }

        $(".delete-item").click(function () {
            var id = $(this).attr("name")
            $("#"+id).remove();
        });
    }

});
