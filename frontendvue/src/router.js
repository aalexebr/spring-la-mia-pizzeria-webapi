import { createRouter, createWebHistory } from 'vue-router';
import HomePage from './pages/HomePage.vue';
import NotFoundPage from './pages/NotFoundPage.vue';

const router = createRouter({
    history: createWebHistory(),
        routes: [
            {
                path: '/',
                name: 'home',
                component: HomePage
            },
            {
                path: '/:pathMatch(.*)*',
                name: 'notFound',
                component: NotFoundPage
            }
        ]
    });
export { router };