import {createWebHashHistory, createRouter} from "vue-router"

import NewsCenter from "@/components/NewsCenter";
import NewsPage from "@/components/NewsPage";
import ControlPage from "@/components/ControlPage";
import FrontPage from "@/components/FrontPage";
import NewsAddingPage from "@/components/NewsAddingPage";
import NewsModifyPage from "@/components/NewsModifyPage";
import TopicAddingPage from "@/components/TopicAddingPage";
import TopicModifyPage from "@/components/TopicModifyPage";

const routes = [
    {
        path: '/',
        redirect: '/home',
    },
    {
        path: '/home',
        redirect: '/home/front',
        component: FrontPage,
        children: [
            {
                path: 'front',
                component: NewsCenter,
            },
            {
                path: 'news/:id',
                component: NewsPage,
            },
        ]
    },
    {
        path: '/space',
        redirect: '/space/modify_news',
        component: ControlPage,
        children: [
            {
                path: 'add_news',
                component: NewsAddingPage,
            },
            {
                path: 'modify_news',
                component: NewsModifyPage,
            },
            {
                path: 'add_topic',
                component: TopicAddingPage,
            },
            {
                path: 'modify_topic',
                component: TopicModifyPage,
            },
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router