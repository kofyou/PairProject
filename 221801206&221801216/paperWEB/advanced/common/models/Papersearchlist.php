<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "papersearchlist".
 *
 * @property integer $id
 * @property integer $storeID
 * @property string $displayTitle
 * @property string $abstract
 * @property string $year
 * @property string $pubdate
 * @property string $link
 * @property string $keyword
 */
class Papersearchlist extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'papersearchlist';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['id', 'storeID', 'displayTitle', 'abstract', 'year', 'pubdate', 'link', 'keyword'], 'required'],
            [['id', 'storeID'], 'integer'],
            [['displayTitle', 'abstract', 'year', 'pubdate', 'link', 'keyword'], 'string'],
            [['id'], 'unique'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
            'id' => 'ID',
            'storeID' => 'Store ID',
            'displayTitle' => 'Display Title',
            'abstract' => 'Abstract',
            'year' => 'Year',
            'pubdate' => 'Pubdate',
            'link' => 'Link',
            'keyword' => 'Keyword',
        ];
    }
}
