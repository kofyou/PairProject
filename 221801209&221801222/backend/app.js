const express = require('express')
const path = require('path')
const logger = require('morgan')
const session = require('express-session')
const usersRouter = require('./routes/users')
const searchRouter = require('./routes/search')
const starRouter = require('./routes/star')
const top10Router = require('./routes/top10')
const articleRouter = require('./routes/article')
const config = require('./config')
const app = express()
const cors = require('cors')
const { Sequelize } = require('sequelize')
// test mysql
const sequelize = new Sequelize(config.sqlurl)
sequelize.authenticate()
  .then(() => {
    console.log('Connection has been established successfully.')
  })
  .catch((e) => {
    console.error('Unable to connect to the database:', e)
    process.exit(1)
  })
// view engine setup

app.use(session({
  cookie: {
    sameSite: true
  },
  secret: config.sessionSecret
}))
app.use(logger('combined'))
app.use(express.json())
app.use(express.urlencoded({ extended: false }))

app.use(cors())
app.use('/user', usersRouter)
app.use('/search', searchRouter)
app.use('/star', starRouter)
app.use('/top10', top10Router)
app.use('/article', articleRouter)
// catch 404 and forward to error handler
app.use(express.static(path.join(__dirname, 'public')))

// error handler
// app.use(function(err, req, res, next) {
//   // set locals, only providing error in development
//   res.locals.message = err.message;
//   res.locals.error = req.app.get('env') === 'development' ? err : {};

//   // render the error page
//   res.status(err.status || 500);
//   console.log(err)
//   res.send('error');
// });

module.exports = app
