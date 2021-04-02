<?php
use yii\db\Connection;

function add_key($keyname){
    Yii::$app->db->createCommand()->insert('keywords', [
        'keywordname' => $keyname,
        'keyWordCount'=>1,
    ])->execute();
}

function update_key($keyname,$d){
    $command = Yii::$app->db->createCommand('select keyWordCount from keywords where keywordname=\''.$keyname.'\'');    
    $post = $command->queryScalar();
    $c=$post;
    $c+=$d;
    if($c==0)
    {
        $delstr='delete from keywords where keywordname=\''.$keyname.'\'';
        Yii:$app->db->createCommand($delstr)->execute();
    }
    else
    {
       $upstr='update keywords set keyWordCount='.$c.' where keywordname=\''.$keyname.'\'';
      Yii::$app->db->createCommand($upstr)->execute();
    }
}

?>