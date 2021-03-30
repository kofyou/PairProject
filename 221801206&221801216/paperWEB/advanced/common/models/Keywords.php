<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "keywords".
 *
 * @property string $keywordname
 * @property integer $keyWordCount
 */
class Keywords extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'keywords';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['keywordname', 'keyWordCount'], 'required'],
            [['keywordname'], 'string'],
            [['keyWordCount'], 'integer'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'keywordname' => 'Keywordname',
            'keyWordCount' => 'Key Word Count',
        ];
    }

    public function getPapers()
    {
        return $this->hasMany(Papersearchlist::className(), ['keyword' => 'keywordname']);
    }

    public function afterSave($insert, $changedAttributes){
        parent::afterSave($insert, $changedAttributes);
        echo "after save";
    }

    public static function gettypeList(){
        $keyList = Keywords::find()->all();
        $listString = '';
        foreach($keyList as $list){
            $listString.='{value:'.$list->keyWordCount.
                 ',name:"'.$list->keywordname.'"},';
        }   
        return $listString;
    }
}
