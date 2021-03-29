import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Graph from '@/views/Graph.vue'

const routes = [{
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: '/graph',
        name: 'Graph',
        component: Graph
    },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router