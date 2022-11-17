import {createRouter, createWebHistory} from "vue-router";
import Index from '/src/views/Index.vue'
import Account from '/src/views/Account.vue'
import AccountForm from '/src/components/AccountForm.vue'
import EmailForm from '/src/components/EmailForm.vue'

const routes = [
    {path: '/', redirect: '/index'},
    {path: '/index', component: Index},
    {
        path: '/account', component: Account, redirect: '/account/username', children: [
            {path: 'username', component: AccountForm},
            {path: 'email', component: EmailForm}
        ]
    },
]

export const router = createRouter({
    history: createWebHistory(),
    routes
})