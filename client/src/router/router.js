import {createRouter, createWebHistory} from "vue-router";
import Index from '/src/views/Index.vue'
import SignUp from '/src/views/SignUp.vue'

const routes = [
    {path: '/', redirect: '/index'},
    {path: '/index', component: Index},
    {path: '/signup', component: SignUp}
]

export const router = createRouter({
    history: createWebHistory(),
    routes
})