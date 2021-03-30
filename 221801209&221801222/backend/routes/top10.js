const express = require('express')
const router = express.Router()
const Keyword = require('../model/keywords')
const isNumber = require('is-number')

router.post('/', function (req, res, next) {
  if (!req.body.min || !isNumber(req.body.min)) {
    req.body.min = 0
  } else {
    req.body.min = parseInt(req.body.min)
  }

  if (!req.body.max || !isNumber(req.body.max)) {
    req.body.max = 9999
  } else {
    req.body.max = parseInt(req.body.max)
  }

  Keyword.top10(req.body.min, req.body.max)
    .then(top => {
      res.json({ code: 0, top, total: top.total })
    })
    .catch(err => {
      res.json({ code: 1, err: err.message })
    })
})

module.exports = router
