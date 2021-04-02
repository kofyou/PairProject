<?php
namespace app\index\controller;

use Collator;
use think\Controller;

class Paperview extends Controller
{
    public function index()
    {
        return $this->fetch("paperview");
    }
    
}
