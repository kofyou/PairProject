const { Sequelize, DataTypes, Model,Op,Deferrable } = require('sequelize');
const config = require("../config")
const User = require("./user")
const Article = require("./article")
const sequelize = new Sequelize(config.sqlurl);
class Keyword extends Model{
  static top10(){
    let toplist
    return Keyword.findAll({
      attributes:[
        "keyword",
        [sequelize.fn('COUNT', sequelize.col('keyword')), 'count']
      ],
      group: "keyword",
      order:[
        [sequelize.fn('COUNT', sequelize.col('keyword')), 'DESC']
      ],
      limit: 10
    }).then(top=>{
      toplist = top
      return Keyword.count()
    }).then(total=>{
      toplist.total = total
      return toplist
    })
  }
}
Keyword.init({
  kid:{
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement:true
  },
  keyword:{
    type: DataTypes.STRING,
  },
  aid: {
    type: DataTypes.INTEGER,
  },
  year: {
    type: DataTypes.INTEGER,
  }
}, {
  sequelize, 
  modelName: 'Keyword',
  tableName: 'keywords',
  timestamps: false
})
module.exports = Keyword