const express = require('express')
const router = express.Router()
const Article = require('../model/article')

router.get('/:id', function (req, res, next) {
  Article.findOne({
    where: {
      aid: req.params.id
    }
  }).then(data => {
    res.json({ code: 0, article: data })
  }).catch(err => {
    res.json({ code: 1, err: err.message })
  })
})

module.exports = router
