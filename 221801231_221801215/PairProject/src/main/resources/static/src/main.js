// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from  'vuex'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import "./assets/icon/font-awesome-4.7.0/css/font-awesome.min.css";
import axios from 'axios';
import api from './api/global_variable';
import md5 from 'js-md5';
import echarts from 'echarts'
import 'echarts/map/js/china'
import  store from './store/index'
Vue.use(Vuex)
Vue.prototype.$echarts = echarts;
Vue.prototype.$md5 = md5;
Vue.prototype.$api=api;
Vue.prototype.$axios = axios;
Vue.use(ElementUI);
Vue.config.productionTip = false
/* eslint-disable no-new */
axios.defaults.withCredentials = true
new Vue({
  el: '#app',
  router,
  api,
  render: h => h(App),

})
