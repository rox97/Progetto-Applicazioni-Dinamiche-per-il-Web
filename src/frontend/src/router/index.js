import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      meta: {requiresAuth: false},
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
      path: "/createOrder",
      meta: {requiresAuth: true, forAdmin: false, forAgent: true},
      name: "createOrderPage",
      component: () => import("../components/createOrder.vue"),
    },
    {
      path: "/agents",
      meta: {requiresAuth: true, forAdmin: true, forAgent: false},
      name: "agentsPage",
      component: () => import("../components/Agents.vue"),
    },
    {
      path: "/updateAgent/:data",
      meta: {requiresAuth: true, forAdmin: true, forAgent: false},
      name: "updateAgentPage",
      component: () => import("../components/updateAgent.vue"),
    },
    {
      path: "/updateCustomer/:data",
      meta: {requiresAuth: true, forAdmin: true, forAgent: false},
      name: "updateCustomerPage",
      component: () => import("../components/updateCustomer.vue"),
    },
    {
      path: "/updateOrder/:data",
      meta: {requiresAuth: true,forAdminAndAgent: true},
      name: "updateOrderPage",
      component: () => import("../components/updateOrder.vue"),
    },
    {
      path: "/customers",
      meta: {requiresAuth: true, forAdmin: true, forAgent: false},
      name: "customersPage",
      component: () => import("../components/Customers.vue"),
    },
    {
      path: "/login",
      meta: {requiresAuth: false},
      name: "loginPage",
      component: () => import("../views/LoginView.vue"),
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth && localStorage.getItem("userLogged") === "false") {
    next({path:'/login'});
  }
  else if(to.meta.forAdmin) {
    if(localStorage.getItem("userRole") === "admin"){
      next();
    }
    else{
      next({path:'/service'});
    }
  }
  else if(to.meta.forAgent) {
    if (localStorage.getItem("userRole") === "agent") {
      next();
    } else {
      next({path: '/service'});
    }
  }
  else if(to.meta.forAdminAndAgent) {
    console.log("qui")
    if (localStorage.getItem("userRole") === "admin" || localStorage.getItem("userRole") === "agent") {
      next();
    } else {
      next({path: '/service'});
    }
  }
  else{
    next();
  }
  });


export default router;
