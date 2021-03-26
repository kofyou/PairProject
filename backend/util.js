const auth = function (req, res, next) {
  if (req.session.auth) {
    next()
  } else {
    res.status(401)
    res.json({ code: 1, err: '请先登入' })
  }
}
module.exports = {
  auth
}
