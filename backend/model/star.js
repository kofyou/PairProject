const { Sequelize, DataTypes, Model,Op } = require('sequelize');
const config = require("../config")
const sequelize = new Sequelize(config.sqlurl);
class Star extends Model{
  static add(uid,aid){
    return Star.findOne({
      where:{
        [Op.and]:[
          {uid},
          {aid}
        ]
      }
    }).then(star=>{
      if(star === null){
        return Star.create({uid,aid})
        
      }else{
        return Promise.reject(new Error("已经收藏过了"))
      }
    })
  }

  static delete(uid,aid){
    return Star.destroy({
      where: {
        [Op.and]:[
          {uid},
          {aid}
        ]
      }
    }).then(num=>{
      if(num === 1){
        return 1
      }else{
        return Promise.reject(new Error("删除了"+ num.toString() + "条记录"))
      }
    })
  }

  static list(uid){
    Star.findAll({
      where:{
        uid
      }
    }).then(stars=>{
      let starlist = []
      stars.forEach(star=>{
        starlist.push(star.aid)
      })
      return starlist
    })
  }


}
Star.init({
  sid:{
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement:true
  },
  aid: {
    type: DataTypes.INTEGER,
  },
  uid: {
    type: DataTypes.INTEGER,
  }
}, {
  sequelize, 
  modelName: 'Star',
  tableName: 'star',
  timestamps: false
})
Star.sync()
module.exports = Star