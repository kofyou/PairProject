const express = require('express');
const path = require('path');
const logger = require('morgan');
const session = require('express-session')
const usersRouter = require('./routes/users');
const config = require("./config")
const app = express();
const { Sequelize} = require('sequelize');
//test mysql
let sequelize = new Sequelize(config.sqlurl)
sequelize.authenticate()
  .then(()=>{
    console.log('Connection has been established successfully.');
  })
  .catch((e)=>{
    console.error('Unable to connect to the database:', e);
    process.exit(1)
  })
// view engine setup

app.use(session({
  cookie:{
    sameSite:true
  },
  secret: config.sessionSecret
}))
app.use(logger('combined'))
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use(express.static(path.join(__dirname, 'public')));

app.use('/user', usersRouter);

// catch 404 and forward to error handler


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

module.exports = app;
