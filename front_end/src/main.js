import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from "element-plus"
import "element-plus/dist/index.css"
import axios from "axios"
import store from "@/store"
import router from "@/router";

const app = createApp(App)
axios.defaults.baseURL = 'http://localhost:8001'
app.config.globalProperties.$http = axios

app.use(ElementPlus)
app.use(store)
app.use(router)
app.mount('#app')
