const { Sequelize, DataTypes, Model } = require('sequelize');
const config = require("../config")
const sequelize = new Sequelize(config.sqlurl);
class Article extends Model{
  static search(title){
    if(typeof title !== "string" || title === ""){
      return Promise.reject(new Error("title为空或类型错误"))
    }
    return Article.findAll({
      where:{
        title:{
          [Op.like]: '%'+title+"%",
        }
      }
    }).then(articles=>{
      if(articles === null){
        return {}
      }else{
        return articles
      }
    })
  }
}
Article.init({
  aid:{
    type: DataTypes.INTEGER,
    primaryKey: true,
  },
  title: {
    type: DataTypes.STRING,
  },
  no: {
    type: DataTypes.INTEGER,
  },
  author:{
    type:DataTypes.STRING,
  },
  type:{
    type:DataTypes.STRING,
  },
  keywords:{
    type:DataTypes.STRING,
  },
  url:{
    type:DataTypes.STRING,
  }
}, {
  sequelize, 
  modelName: 'Article',
  tableName: 'article',
  timestamps: false
})