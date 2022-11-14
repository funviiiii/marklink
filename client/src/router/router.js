import {createRouter, createWebHistory} from "vue-router";
import Index from '/src/views/Index.vue'
import Account from '/src/views/Account.vue'

const routes = [
    {path: '/', redirect: '/index'},
    {path: '/index', component: Index},
    {path: '/account', component: Account}
]

export const router = createRouter({
    history: createWebHistory(),
    routes
})