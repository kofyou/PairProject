var express = require('express');
var router = express.Router();
const util = require("../util")
const Star = require("../model/star")

router.use("/",util.auth)
/* GET users listing. */
router.post('/add', function(req, res, next) {
  if(typeof req.body.aid === "number"){
    Star.add(req.session.uid,aid)
      .then(()=>res.json({code:0,msg:"添加成功"}))
      .catch(err=>res.json({code:1,err:err.message}))
  }else{
    res.json({code:1,err:"需要aid参数"})
  }
});

router.post('/delete', function(req, res, next) {
  if(typeof req.body.aid === "number"){
    Star.delete(req.session.uid,aid)
      .then(num=>res.json({code:0,msg:"成功删除" + num.toString()+"条数据"}))
      .catch(err=>res.json({code:1,err:err.message}))
  }else{
    res.json({code:1,err:"需要aid参数"})
  }
});


router.get('/list', function(req, res, next) {
  Star.list(req.session.uid)
    .then(starlist=>res.json({code:0,starlist}))
    .catch(err=>res.json({code:1,err:err.message}))
});

module.exports = router;
