import { createApp } from 'vue'
import { createStore } from 'vuex'
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import './styles/normalize.css';
import App from './App.vue'
import router from './router'
import axios from './js/axios.js';
import * as echarts from 'echarts'

const store = createStore({
    pageNum() {
        return {
            number: 0,
        }
    },
    mutations: {
        setPages(pageNum, { cur }) {
            pageNum.number = cur;
        }
    },
    getters: {
        getPages: (pageNum) => {
            return pageNum.number;
        }
    }
})

const app = createApp(App)
app.config.globalProperties.$http = axios;
app.echarts = echarts
app.use(router).use(ElementPlus).use(store).mount('#app')