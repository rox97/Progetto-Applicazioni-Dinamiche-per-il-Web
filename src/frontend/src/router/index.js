import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/service",
      meta: {requiresAuth: true},
      name: "service",
      component: () => import("../views/ServiceView.vue"),
    },
    {
      path: "/orders",
      meta: {requiresAuth: true},
      name: "ordersPage",
      component: () => import("../components/Orders.vue"),
    },
    {
      path: "/agents",
      meta: {requiresAuth: true},
      name: "agentsPage",
      component: () => import("../components/Orders.vue"),
    },
    {
      path: "/customers",
      meta: {requiresAuth: true},
      name: "customersPage",
      component: () => import("../components/Orders.vue"),
    },
    {
      path: "/about",
      meta: {requiresAuth: false},
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/test",
      meta: {requiresAuth: true},
      name: "testPage",
      component: () => import("../views/TestView.vue"),
    },
    {
      path: "/login",
      meta: {requiresAuth: false},
      name: "loginPage",
      component: () => import("../views/LoginView.vue"),
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && localStorage.getItem("userLogged") === "false") {
    next({path:'/login'});
  }
  else {
    next();
  }
  });


export default router;
