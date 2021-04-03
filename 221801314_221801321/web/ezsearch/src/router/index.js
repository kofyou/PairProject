import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Graph from '@/views/Graph.vue'
import HotSpot from '@/components/graph/HotSpot.vue'
import Top10 from '@/components/graph/Top10.vue'
import WordCloud from '@/components/graph/WordCloud.vue'

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
        component: Graph,
        children: [{
                path: '/graph',
                redirect: Top10
            },
            {
                path: '/hotspot',
                name: 'HotSpot',
                component: HotSpot
            },
            {
                path: '/top10',
                name: 'Top10',
                component: Top10
            },
            {
                path: '/wordCloud',
                name: 'WordCloud',
                component: WordCloud
            },
        ]
    },

]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router