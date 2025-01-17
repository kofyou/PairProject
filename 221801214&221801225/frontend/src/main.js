import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Element from 'element-ui'
import echarts from 'echarts'
Vue.use(Element)
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
