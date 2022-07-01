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
      meta: {requiresAuth: true, forAdmin: false},
      name: "service",
      component: () => import("../views/ServiceView.vue"),
    },
    {
      path: "/orders",
      meta: {requiresAuth: true, forAdmin: false},
      name: "ordersPage",
      component: () => import("../components/Orders.vue"),
    },
    {
      path: "/createOrder",
      meta: {requiresAuth: true, forAdmin: false},
      name: "createOrderPage",
      component: () => import("../components/createOrder.vue"),
    },
    {
      path: "/agents",
      meta: {requiresAuth: true, forAdmin: true},
      name: "agentsPage",
      component: () => import("../components/Agents.vue"),
    },
    {
      path: "/updateAgent/:data",
      meta: {requiresAuth: false, forAdmin: false},
      name: "updateAgentPage",
      component: () => import("../components/updateAgent.vue"),
    },
    {
      path: "/customers",
      meta: {requiresAuth: true, forAdmin: true},
      name: "customersPage",
      component: () => import("../components/Customers.vue"),
    },
    {
      path: "/about",
      meta: {requiresAuth: false, forAdmin: false},
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/AboutView.vue"),
    },
    {
      path: "/test",
      meta: {requiresAuth: true, forAdmin: false},
      name: "testPage",
      component: () => import("../views/TestView.vue"),
    },
    {
      path: "/login",
      meta: {requiresAuth: false, forAdmin: false},
      name: "loginPage",
      component: () => import("../views/LoginView.vue"),
    },
  ],
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && localStorage.getItem("userLogged") === "false") {
    next({path:'/login'});
  }
  /*else if(to.meta.forAdmin && localStorage.getItem("userRole") !== "admin"){
    next({path:'/service'});
  }*/
  else{
    next();
  }
  });


export default router;
