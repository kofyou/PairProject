const { Sequelize, DataTypes, Model,Op } = require('sequelize');
const config = require("../config")
const sequelize = new Sequelize(config.sqlurl);
class Article extends Model{
  static search(title,keyword){
    if(typeof title !== "string" || title === ""){
      title = ""
    }
    if(typeof keyword !== "string" || keyword === ""){
      keyword = ""
    }
    return Article.findAll({
      where:{
        [Op.and]:[
          {
            title:{
              [Op.like]: '%'+title+"%",
            }
          },
          {
            keywords: {
              [Op.like]:"%"+keyword+"%"
            }
          }
        ]
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
module.exports = Article