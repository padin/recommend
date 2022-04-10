import { createRouter,createWebHashHistory } from 'vue-router'
import store from "../store/index.js";
import {getUserInfo} from "../api/index.js";

import App from '../App.vue'
import Enroll from '../components/Enroll.vue'
import Login from '../components/Login.vue'
import Coupon from '../components/Coupon.vue'
import Referees from '../components/Referees.vue'
import AddMerchants from '../components/AddRefereesView.vue'
import ListCustomersView from '../components/ListCustomersView.vue'
import CustomerInfo from "../components/CustomerInfo.vue";
import MyInfoView from "../components/MyInfoView.vue";
import Settings from "../components/Settings.vue";
import SettingsIndex from "../components/SettingsIndex.vue";
import AddOrgView from "../components/AddOrgView.vue";
import ListOrgView from "../components/ListOrgView.vue";
import AddRefereesView from "../components/AddRefereesView.vue";
import SetCouponView from "../components/SetCouponView.vue";
import SetPasswordView from "../components/SetPasswordView.vue"
import RefereesIndex from "../components/RefereesIndex.vue";
import Check from "../components/Check.vue";



const routes = [
  {
    path:'/',
    component:App,
    children:[
      {
        path: 'enroll/:id',
        component:Enroll
      },
      {
        path: 'coupon/:id',
        component:Coupon
      },
      {
        path: 'check/:id',
        component:Check
      },
      {
        path: 'referees',
        component: Referees,
        children: [
          {
            path: 'refereesIndex',
            component:RefereesIndex
          },
          {
            path: 'listCustomersView/:id',
            component:ListCustomersView
          },
          {
            path: 'customerInfo/:id',
            component:CustomerInfo
          },
        ]
      },
      {
        path: 'settings',
        component: Settings,
        children:[
          {
            path: 'index',
            component: SettingsIndex
          },
          {
            path: 'addOrgView',
            component: AddOrgView
          },
          {
            path:'setCouponView',
            component:SetCouponView
          },
          {
            path: 'setPasswordView',
            component: SetPasswordView
          },
          {
            path: 'login',
            component:Login
          },
          {
            path: 'addRefereesView',
            component: AddRefereesView
          },
          {
            path: '/settings/myInfoView',
            component:MyInfoView
          },
        ]
      }
    ]
  },
  {
    path:'/listOrgView',
    component:ListOrgView
  },


  {
  path: '/addMerchants',
    component:AddMerchants
  },


]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {

  let token = localStorage.getItem("token")
  let role = store.state.role
  let uid = store.state.uid

  console.log("ROLE",role,"UID",uid)

  let enroll_patten = /\/enroll\d*/
  let coupon_patten = /\/coupon\d*/
  //如果登陆，就让它去登陆
  if (to.path == '/settings/login'||enroll_patten.test(to.path)||coupon_patten.test(to.path)) {
    next()
    //如果没有token，就让它去登陆
  } else if (!token||token==0) {
    next({path: '/settings/login'})
    //如果没有角色,就去拿角色
  } else if(!role || role == 0) {
    getUserInfo().then(res=> {
      store.commit("SET_ROLE", res.data.role)
      store.commit("SET_OID", res.data.oid)
      store.commit("SET_UID", res.data.id)
    })
  }
  next()
})


//导出路由对象
export default router






