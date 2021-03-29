<?php
namespace app\index\controller;

use app\model\Keywordtendency;
use app\model\Maxkeyword;
use app\model\Paper;
use think\Controller;
use think\Db;
use think\Response;

header('content-type:application:json;charset=utf8');
header('Access-Control-Allow-Origin:*');
header('Access-Control-Allow-Methods:GET');
header('Access-Control-Allow-Headers:x-requested-with,content-type');

class Serchfunction extends Controller
{
    public function index()
    {
    }

    public function serchdata($words = " Information")
    {
        $words = $_GET["words"];  //等实现前后端接口交互的时候可以用来获取想要搜索的词
        $data = Db::query("select title,keyword,releasetime from paper where title like '%".$words."%'");
        return json($data);  
    }

    public function serchbykeyword($words= "face recognition")
    {
        $words = $_GET["words"];  
        $data = Db::query("select title,keyword,releasetime from paper where keyword like '%".$words."%'");
        return json($data); 
    }

    public function serchforpaperscan($words = "Contextual-Based Image Inpainting: Infer, Match, and Translate")
    {
        $words = $_GET["words"];  
        $data = Db::query("select abstract,link from paper where title='".$words."'");
        return json($data); 
    }

    public function getMaxword()
    {
        $data = Maxkeyword::select();
        return json($data);
    }

    public function getKeywordtendency()
    {
        $data = Keywordtendency::select();
        return json($data);
    }

    public function get2018CVPRkey()
    {
        $data = Db::query("select keyword,time from keywordtendency where type='CVPR' and year='2018'");
        return json($data);
    }

    public function get2019CVPRkey()
    {
        $data = Db::query("select keyword,time from keywordtendency where type='CVPR' and year='2019'");
        return json($data);
    }

    public function get2020CVPRkey()
    {
        $data = Db::query("select keyword,time from keywordtendency where type='CVPR' and year='2020'");
        return json($data);
    }

    public function get2016ECCVkey()
    {
        $data = Db::query("select keyword,time from keywordtendency where type='ECCV' and year='2016'");
        return json($data);
    }

    public function get2018ECCVkey()
    {
        $data = Db::query("select keyword,time from keywordtendency where type='ECCV' and year='2018'");
        return json($data);
    }

    public function get2020ECCVkey()
    {
        $data = Db::query("select keyword,time from keywordtendency where type='ECCV' and year='2020'");
        return json($data);
    }

}
