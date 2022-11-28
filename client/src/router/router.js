import {createRouter, createWebHistory} from "vue-router";
import Index from '/src/views/Index.vue'
import Account from '/src/views/Account.vue'
import AccountForm from '/src/components/AccountForm.vue'
import EmailForm from '/src/components/EmailForm.vue'
import Personnel from '/src/components/Personnel.vue'
import Salary from '/src/components/salary.vue'
import Info from '/src/components/info.vue'
import Advance from '/src/components/advance.vue'

const routes = [
    {path: '/', redirect: '/index'},
    {
        path: '/index', component: Index, children: [
            {path: 'personnel', name: 'personnel', component: Personnel},
            {path: 'salary', name: 'salary', component: Salary},
            {path: 'info', name: 'info', component: Info},
            {path: 'advance', name: 'advance', component: Advance},
        ]
    },
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