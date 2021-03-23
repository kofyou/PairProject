$(function(){
    $a = $(window).height();
    $("#left").height($a);
    $("#myclose").click(function(){
        $("#left").animate({left:'-500px'});
        $("#open").delay(500).animate({left:'0'});
    });
    $("#open").click(function(){
        $("#open").animate({left:'-50px'});
        $("#left").delay(300).animate({left:'0'});
    });
});