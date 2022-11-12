import {createRouter, createWebHistory} from "vue-router";
import Index from '/src/views/index.vue'

const routes = [
    {path: '/', redirect: '/index'},
    {path: '/index', component: Index}
]

export const router = createRouter({
    history: createWebHistory(),
    routes
})