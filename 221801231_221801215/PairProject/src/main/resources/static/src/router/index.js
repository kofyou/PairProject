import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login.vue'
import index from '../views/index.vue'
import Register from "../views/Register";
import CrawlResult from "../views/CrawlResult";

Vue.use(Router)

export default new Router({
  routes: [
     {
      //首页
      path: '/',
      name: 'LoginIndex',
      component: Login
    },
    {
      //登录页
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      //主页
      path: '/index',
      name: 'Index',
      component: index
    },
    {
      //注册页
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      //爬取结果页
      path: '/crawlresult',
      name: 'CrawlResult',
      component:CrawlResult
    },

  ]
})
