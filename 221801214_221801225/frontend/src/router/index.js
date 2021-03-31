import Vue from 'vue'
import VueRouter from 'vue-router'

import Addpaper from "../components/Addpaper"
import ShowView from "../components/ShowView"
import Echartstest from "../components/Echartstest"
import Laa from "../components/Laa"
import PaperItem from "../components/PaperItem"


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name:'showview',
    component: ShowView
  }, {
    path: '/show',
    name:'showview',
    component: ShowView
  }, {
    path: '/add',
    component: Addpaper
  }, {
    path: '/echar',
    component: Echartstest
  }, {
    path: '/ceshi',
    component: Laa
  }
  , {
    path: '/paper',
    component: PaperItem
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
