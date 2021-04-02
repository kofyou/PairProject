const express = require('express')
const router = express.Router()
const User = require('../model/user')
const md5 = require('md5')
router.use('/', function (req, res, next) {
  let username = req.body.username
  let password = req.body.password
  if (typeof username !== 'string' || typeof password !== 'string') {
    return res.json({ code: 1, err: '用户名和密码必须为字符串' })
  }
  username = username.trim()
  if (username === '') {
    return res.json({ code: 1, err: '用户名不能为空' })
  }
  if (password === '') {
    return res.json({ code: 1, err: '密码不能为空' })
  }
  password = md5(password)
  req.body.username = username
  req.body.password = password
  next()
})
/* GET users listing. */
router.post('/login', function (req, res, next) {
  User.login(req.body.username, req.body.password)
    .then(data => {
      req.session.auth = true
      req.session.username = req.body.username
      req.session.uid = data.uid
      res.json({ code: 0, msg: data.msg })
    })
    .catch(err => res.json({ code: 1, err: err.message }))
})

router.post('/register', function (req, res, next) {
  User.register(req.body.username, req.body.password)
    .then(() => {
      res.json({ code: 0 })
    })
    .catch(err => {
      res.json({ code: 1, err: err.message })
    })
})

router.get('/whoami', function (req, res, next) {
  if (req.session.auth) {
    res.json({ code: 0, username: req.session.username })
  } else {
    res.json({ code: 1, err: '请先登入' })
  }
})

module.exports = router
