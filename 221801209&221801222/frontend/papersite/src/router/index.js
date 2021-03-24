import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/login.vue'
import Register from '../components/register.vue'
import Base from '../components/base'
import Index from '../components/index'
import StarList from '../components/starlist'
import Analysis from '../components/Analysis'


Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', redirect: '/login'},
    {path: '/login', name: 'login', component: Login},
    {path: '/register', name: 'register', component: Register},
    {path: '/index', name: 'index', component: Index},
    {path: '/starlist', name: 'starlist', component: StarList},
    {path: '/analysis', name: 'analysis', component: Analysis},
    {path: '/base', name: 'base', component: Base}
  ]
})
