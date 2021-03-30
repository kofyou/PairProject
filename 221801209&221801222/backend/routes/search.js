const express = require('express')
const router = express.Router()
const Article = require('../model/article')

router.get('/', function (req, res, next) {
  Article.search(req.query.title, req.query.keyword)
    .then(article => {
      res.json({ code: 0, article })
    })
    .catch(err => {
      res.json({ code: 1, err: err.message })
    })
})

module.exports = router
