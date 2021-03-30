const { Sequelize, DataTypes, Model, Op, Deferrable } = require('sequelize')
const config = require('../config')
const User = require('./user')
const Article = require('./article')
const sequelize = new Sequelize(config.sqlurl)
class Star extends Model {
  static add (uid, aid) {
    return Star.findOne({
      where: {
        [Op.and]: [
          { uid },
          { aid }
        ]
      }
    }).then(star => {
      if (star === null) {
        return Star.create({ uid, aid })
      } else {
        return Promise.reject(new Error('已经收藏过了'))
      }
    })
  }

  static delete (uid, aid) {
    return Star.destroy({
      where: {
        [Op.and]: [
          { uid },
          { aid }
        ]
      }
    }).then(num => {
      if (num === 1) {
        return 1
      } else {
        return Promise.reject(new Error('删除了' + num.toString() + '条记录'))
      }
    })
  }

  static list (uid) {
    return Star.findAll({
      where: {
        uid
      }
    }).then(stars => {
      const starlist = []
      stars.forEach(star => {
        starlist.push(star.aid)
      })
      return starlist
    })
  }
}
Star.init({
  sid: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  aid: {
    type: DataTypes.INTEGER,
    references: {
      model: Article,
      key: 'aid',
      deferrable: Deferrable.INITIALLY_IMMEDIATE
    }
  },
  uid: {
    type: DataTypes.INTEGER,
    references: {
      model: User,
      key: 'uid',
      deferrable: Deferrable.INITIALLY_IMMEDIATE
    }
  }
}, {
  sequelize,
  modelName: 'Star',
  tableName: 'star',
  timestamps: false
})
Star.sync()
  .catch(e => {
    console.log(e)
  })
module.exports = Star
