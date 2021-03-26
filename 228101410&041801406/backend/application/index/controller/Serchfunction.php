<?php
namespace app\index\controller;

use app\model\Keywordtendency;
use app\model\Maxkeyword;
use app\model\Paper;
use think\Controller;
use think\Db;

class Serchfunction extends Controller
{
    public function index()
    {
    }

    public function serchdata($words = "Object")
    {
        //$words = $_GET["words"];  //等实现前后端接口交互的时候可以用来获取想要搜索的词->column('title,keyword,releasetime')
        $data = Paper::where('title','like','%'.$words.'%')->select();
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
}
