const { Sequelize, DataTypes, Model } = require('sequelize')
const config = require('../config')
const sequelize = new Sequelize(config.sqlurl)

class User extends Model {
  static login (username, password) {
    return User.findOne({
      where: {
        username,
        password
      }
    }).then(user => {
      if (user === null) {
        return Promise.reject(new Error('账号或密码错误'))
      } else {
        return { uid: user.uid, msg: '登入成功' }
      }
    })
  }

  static register (username, password) {
    return User.findOne({
      where: {
        username: username
      }
    }).then(user => {
      console.log(user)
      if (user == null) {
        return User.create({ username, password })
      } else {
        return Promise.reject(new Error('用户存在'))
      }
    })
  }
}

User.init({
  uid: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true
  },
  username: {
    type: DataTypes.STRING,
    allowNull: false
  },
  password: {
    type: DataTypes.STRING,
    allowNull: false
  }
}, {
  sequelize,
  modelName: 'User',
  tableName: 'account',
  timestamps: false
})
module.exports = User
