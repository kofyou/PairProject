<?php

namespace frontend\controllers;

use Yii;
use common\models\Paperstore;
use common\models\PaperstoreSearch;
use yii\web\Controller;
use yii\web\NotFoundHttpException;
use yii\filters\VerbFilter;
use yii\db\Connection;
use common\models\Papersearchlist;

/**
 * PaperstoreController implements the CRUD actions for Paperstore model.
 */
class PaperstoreController extends Controller
{
    /**
     * @inheritdoc
     */
    public $added=0;
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
     * Lists all Paperstore models.
     * @return mixed
     */
    public function actionIndex()
    {
        $searchModel = new PaperstoreSearch();
        $dataProvider = $searchModel->search(Yii::$app->request->queryParams);

        return $this->render('index', [
            'searchModel' => $searchModel,
            'dataProvider' => $dataProvider,
        ]);
    }

    /**
     * Displays a single Paperstore model.
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
     * Creates a new Paperstore model.
     * If creation is successful, the browser will be redirected to the 'view' page.
     * @return mixed
     */
    public function actionCreate()
    {
        $model = new Paperstore();

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->storeID]);
        } else {
            return $this->render('create', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Updates an existing Paperstore model.
     * If update is successful, the browser will be redirected to the 'view' page.
     * @param integer $id
     * @return mixed
     */
    public function actionUpdate($id)
    {
        $model = $this->findModel($id);

        if ($model->load(Yii::$app->request->post()) && $model->save()) {
            return $this->redirect(['view', 'id' => $model->storeID]);
        } else {
            return $this->render('update', [
                'model' => $model,
            ]);
        }
    }

    /**
     * Deletes an existing Paperstore model.
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
     * Finds the Paperstore model based on its primary key value.
     * If the model is not found, a 404 HTTP exception will be thrown.
     * @param integer $id
     * @return Paperstore the loaded model
     * @throws NotFoundHttpException if the model cannot be found
     */
    protected function findModel($id)
    {
        if (($model = Paperstore::findOne($id)) !== null) {
            return $model;
        } else {
            throw new NotFoundHttpException('The requested page does not exist.');
        }
    }

    public function actionAddpaper($_id){
        $added=1;
        $searchstr='select * from paperstore where storeID=\''.$_id.'\'';
        $_exist=Yii::$app->db->createCommand($searchstr)->queryAll();
        if(!($_exist))
        {     
            $resstr='select * from papersearchlist where storeID=\''.$_id.'\'';
            $res=Yii::$app->db->createCommand($resstr)->queryOne();
            $_title=$res['displayTitle'];
            $_abstract=$res['abstract'];
            $_y=$res['year'];
            $_pub=$res['pubdate'];
            $_link=$res['link'];
            $_key=$res['keyword'];

            Yii::$app->db->createCommand()->insert('paperstore', [
            'storeID' => $_id,
            'displayTitle' => $_title,
            'abstract'=>$_abstract,
            'year'=>$_y,
            'pubdate'=>$_pub,
            'link'=>$_link,
            'keyword'=>$_key,
            ])->execute();

            $this->added=1;
        }
        else{
            $this->added=0;           
        }
        return $this->render('addpaper',['added'=>$this->added]);

    }  
}
