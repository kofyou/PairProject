// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui' //element-ui的全部组件
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
import axios from 'axios'
import VueAxios from 'vue-axios'
import * as echarts from 'echarts'



Vue.use(ElementUI) //使用elementUI
Vue.use(VueAxios,axios);
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false;
axios.defaults.timeout = 5000;
// axios.defaults.headers.post['Content-Type'] = 'json;charset=UTF-8';
axios.defaults.baseURL = 'http://ccreater.top:63000/';

// // 设置axios请求的token
// axios.defaults.headers.common['token'] = 'f4c902c9ae5a2a9d8f84868ad064e706'
// //设置请求头
// axios.defaults.headers.post["Content-type"] = "application/json"


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
