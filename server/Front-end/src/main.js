import './assets/main.scss'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router/index.js'
import { createPinia } from 'pinia'
import {createPersistedState} from "pinia-persistedstate-plugin"

const persist = createPersistedState()
const pinia = createPinia()
const app = createApp(App)

pinia.use(persist)

app.use(pinia)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
