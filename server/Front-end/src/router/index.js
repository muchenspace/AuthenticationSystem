import { createRouter, createWebHistory } from "vue-router"

import loginVue from "../views/login.Vue"
import mainMenu from "../views/mainMenu.vue"
import appVue from "../views/verification/app.vue"
import keyVue from "../views/verification/key.vue"

const routes = [
    {
        path: "/", component: mainMenu,redirect:"/app",
        children: [
            { path: '/app', component: appVue },
            { path: '/key', component: keyVue },
        ]
    },
    {
        path: "/login", component: loginVue
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})

export default router