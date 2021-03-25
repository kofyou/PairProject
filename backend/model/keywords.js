const { Sequelize, DataTypes, Model,Op,Deferrable } = require('sequelize');
const config = require("../config")
const User = require("./user")
const Article = require("./article")
const sequelize = new Sequelize(config.sqlurl);
class Keyword extends Model{
  static top10(min,max){
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
      where:{
        [Op.and]:[
          {
            year:{[Op.gte]:min},
          },
          {
            year:{[Op.lte]:max}
          }
        ]
      },
      limit: 10
    }).then(top=>{
      toplist = top
      return Keyword.count({
        where:{
          [Op.and]:[
            {
              year:{[Op.gte]:min},
            },
            {
              year:{[Op.lte]:max}
            }
          ]
        }
      })
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