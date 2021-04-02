<?php

namespace frontend\controllers;

use Yii;
use common\models\Eccv;
use common\models\EccvSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\db\Connection;
use yii\data\DataProviderInterface;
use yii\data\SqlDataProvider;

/**
 * EccvController implements the CRUD actions for Eccv model.
 */
class EccvController extends Controller
{
    /**
     * @inheritdoc
     */
    public function behaviors()
    {
        return [
            'verbs' => [
                'class' => VerbFilter::className(),
                'actions' => [
                    'delete' => ['POST'],
                ],
            ],
        ];
    }

    /**
     * Lists all Eccv models.
     * @return mixed
     */
    public function actionIndex($_y)
    {
        if($_y==1)
        {   
            $dataProvider = new SqlDataProvider([
                'sql' => 'select * from eccvkey_16 order by wordcount desc',
                'params' => [
                    ':limit'=>10,
                ],
                'totalCount' => 10,
                'sort' => [ ],
                'pagination' => [
                    'pageSize' => 10,
                ],
            ]);
               
        }
        else{
            if($_y==2)
           {   
            $dataProvider = new SqlDataProvider([
                'sql' => 'select * from eccvkey_18 order by wordcount desc',
                'params' => [
                    ':limit'=>10,
                ],
                'totalCount' => 10,
                'sort' => [ ],
                'pagination' => [
                    'pageSize' => 10,
                ],
            ]);               
            }
            else{
                $dataProvider = new SqlDataProvider([
                    'sql' => 'select * from eccvkey_20 order by wordcount desc',
                    'params' => [
                        ':limit'=>10,
                    ],
                    'totalCount' => 10,
                    'sort' => [ ],
                    'pagination' => [
                        'pageSize' => 10,
                    ],
                ]);
            }
        }

        return $this->render('index', [
            'dataProvider' => $dataProvider,
        ]);      
    }

    /**
     * Displays a single Eccv model.
     * @param integer $id
     * @return mixed
     */
    public function actionView($id)
    {
        return $this->render('view', [
            'model' => $this->findModel($id),
        ]);
    }

    /**
     * Creates a new Eccv model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Eccv();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Eccv model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->id]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Eccv model.
     * If deletion is successful, the browser will be redirected to the 'index' page.
     * @param integer $id
     * @return mixed
     */
    public function actionDelete($id)
    {
        $this->findModel($id)->delete();

        return $this->redirect(['index']);
    }

    /**
     * Finds the Eccv model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return Eccv the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Eccv::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }
}
