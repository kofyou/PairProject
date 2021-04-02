<?php
use yii\db\Connection;

function add_ekey($tname,$keyname){
    Yii::$app->db->createCommand()->insert($tname, [
        'wordname' => $keyname,
        'wordcount'=>1,
    ])->execute();
}

function update_ekey($tname,$keyname,$d){
    $command = Yii::$app->db->createCommand('select wordcount from '.$tname.' where wordname=\''.$keyname.'\'');    
    $post = $command->queryScalar();
    $c=$post;
    $c+=$d;
    if($c==0)
    {
        $delstr='delete from '.$tname.' where wordname=\''.$keyname.'\'';
        Yii:$app->db->createCommand($delstr)->execute();
    }
    else
    {
       $upstr='update '.$tname.' set wordcount='.$c.' where wordname=\''.$keyname.'\'';
      Yii::$app->db->createCommand($upstr)->execute();
    }
}

?>