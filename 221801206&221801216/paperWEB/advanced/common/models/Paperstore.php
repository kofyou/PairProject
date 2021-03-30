<?php

namespace common\models;

use Yii;

/**
 * This is the model class for table "paperstore".
 *
 * @property integer $storeID
 * @property string $displayTitle
 * @property string $abstract
 * @property string $year
 * @property string $pubdate
 * @property string $link
 * @property string $keyword
 */
class Paperstore extends \yii\db\ActiveRecord
{
    /**
     * @inheritdoc
     */
    public static function tableName()
    {
        return 'paperstore';
    }

    /**
     * @inheritdoc
     */
    public function rules()
    {
        return [
            [['storeID', 'displayTitle', 'abstract', 'year', 'pubdate', 'link', 'keyword'], 'required'],
            [['storeID'], 'integer'],
            [['displayTitle', 'abstract', 'year', 'pubdate', 'link', 'keyword'], 'string'],
            [['storeID'], 'unique'],
        ];
    }

    /**
     * @inheritdoc
     */
    public function attributeLabels()
    {
        return [
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
