import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'


//views
import index from '@/views/index'
import thesisSearch from '@/views/thesisSearch'

//components
import navMenu from '@/components/navMenu'

//table
import tableHot from '@/components/tableHot'
import tableKeyword from '@/components/tableKeyword'
import tableTrend from '@/components/tableTrend'
import tableHistogram from '@/components/tableHistogram'
import tableRing from '@/components/tableRing'
import tablePie from '@/components/tablePie'
import login from '@/components/login'


Vue.use(Router)



export default new Router({
  routes: [
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
  
    //views
    //首页
    {
      path: '/',
      redirect: '/',
      name: 'index',
      meta:{title:"首页"},
      component: index,
      children:[
        //论文搜索
        {
          path: 'thesisSearch',
          name: 'thesisSearch',
          component: thesisSearch
        },
        //论文分析
        {
          path: 'tableTrend',
          name: 'tableTrend',
          component: tableTrend
        },
        //热门领域
        {
          path: 'tableKeyword',
          name: 'tableKeyword',
          component: tableKeyword
        },
        //柱状图
        {
          path: 'tableHistogram',
          name: 'tableHistogram',
          component: tableHistogram
        },
        //环形
        {
          path: 'tableRing',
          name: 'tableRing',
          component: tableRing
        },
        //环形
        {
          path: 'tableRing',
          name: 'tableRing',
          component: tableRing
        },
        //饼形
        {
          path: 'tablePie',
          name: 'tablePie',
          component: tablePie
        },
        //登录
        {
          path: 'login',
          name: 'login',
          component: login
        }
      ]
    },
    
    //components
    //导航栏
    {
      path: '/navMenu',
      name: 'navMenu',
      component: navMenu
    },
    //图表
    {
      path: '/tableHot',
      name: 'tableHot',
      component: tableHot
    }

  ]
})
