const express = require('express');
const router = express.Router();
const Keyword = require("../model/keywords")
const util = require("../util")

router.use("/",util.auth)

router.get('/', function(req, res, next) {
  Keyword.top10()
    .then(top=>{
      res.json({code:0,top,total:top.total})
    })
    .catch(err=>{
      res.json({code:1,err:err.message})
    })
});


module.exports = router;
