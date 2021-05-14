import Vue from 'vue'
import VueRouter from 'vue-router'
import login from '../views/login/login.vue'
import home from '../views/home.vue'

import activity from '../views/activity/activity.vue'
import activityContent from '../views/activity/activityContent.vue'
import activityDetail from '../views/activity/activityDetail.vue'

import association from '../views/association/association.vue'
import associationContent from '../views/association/associationContent.vue'
import associationDetail from '../views/association/associationDetail.vue'

import personal from '../views/personal/personal.vue'
import myMessage from '../views/personal/myMessage.vue'
import approvalMessage from '../views/personal/approvalMessage.vue'
import applyAssociation from '../views/personal/applyAssociation.vue'
import myAssociation from '../views/personal/myAssociation.vue'
import myActivity from '../views/personal/myActivity.vue'

import allStudent from '../views/admin/allStudent.vue'
import approvalActivity from '../views/admin/approvalActivity.vue'
import approvalAssociation from '../views/admin/approvalAssociation.vue'

import associationManage from '../views/associationManage/associationManage.vue'
import associationIntroduce from '../views/associationManage/associationIntroduce.vue'
import associationMember from '../views/associationManage/associationMember.vue'
import associationApproval from '../views/associationManage/associationApproval.vue'
import updateAssociation from '../views/associationManage/updateAssociation.vue'
import manageActivity from '../views/associationManage/manageActivity.vue'
import createActivity from '../views/associationManage/createActivity.vue'




Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/login',
    name: 'login',
    component: login
  },
  {
    path: '/home',
    name: 'home',
    redirect: '/association',
    component: home,
    children: [
      {
        path: '/activity',
        name: 'activity',
        redirect: '/activityContent',
        component: activity,
        children: [
          {
            path: '/activityDetail',
            name: 'activityDetail',
            component: activityDetail
          },
          {
            path: '/activityContent',
            name: 'activityContent',
            component: activityContent
          }
        ]
      },
      {
        path: '/association',
        name: 'association',
        component: association,
        redirect: '/associationContent',
        children: [
          {
            path: '/associationDetail',
            name: 'associationDetail',
            component: associationDetail
          },
          {
            path: '/associationContent',
            name: 'associationContent',
            component: associationContent
          }
        ]
      },
      {
        path: '/personal',
        name: 'personal',
        // redirect: '/myMessage',
        redirect: to => {
          if(localStorage.getItem("isAdmin")==='true'){
            return '/allStudent'
          }
          else{
            return '/myMessage'
          }
          // 方法接收 目标路由 作为参数
          // return 重定向的 字符串路径/路径对象
        },
        component: personal,
        children: [
          {
            path: '/myMessage',
            name: 'myMessage',
            component: myMessage,
          },
          {
            path: '/approvalMessage',
            name: 'approvalMessage',
            component: approvalMessage
          },
          {
            path:'/applyAssociation',
            name:'applyAssociation',
            component:applyAssociation
          },
          {
            path: '/myAssociation',
            name: 'myAssociation',
            component: myAssociation
          },
          {
            path: '/myActivity',
            name: 'myActivity',
            component: myActivity
          },
          {
            path:'/allStudent',
            name:'allStudent',
            component:allStudent
          },
          {
            path:'/approvalActivity',
            name:'approvalActivity',
            component:approvalActivity
          },
          {
            path:'/approvalAssociation',
            name:'approvalAssociation',
            component:approvalAssociation
          }
        ]
      },
      {
        path:'/associationManage',
        name:'associationManage',
        redirect:'/associationIntroduce',
        component:associationManage,
        children:[
          {
            path:'/associationIntroduce',
            name:'associationIntroduce',
            component:associationIntroduce
          },
          {
            path:'/associationMember',
            name:'associationMember',
            component:associationMember
          },
          {
            path:'/associationApproval',
            name:'associationApproval',
            component:associationApproval
          },
          {
            path:'/updateAssociation',
            name:'updateAssociation',
            component:updateAssociation
          },
          {
            path:'/manageActivity',
            name:'manageActivity',
            component:manageActivity
          },
          {
            path:'/createActivity',
            name:'createActivity',
            component:createActivity
          },
        ]
      },
    ]
  },
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/activityContent' || to.path === '/associationContent') {
    next();
  } else {
    let token = localStorage.getItem('token');
    if (token === null || token === '') {
      alert("权限不足请先登录");
      next({
        path: '/login',//没有token就跳转到登录页面
        query: {
          redirect: to.fullPath,// 将跳转的路由path作为参数，登录成功后跳转到该路由
          // pageNum: 1,
        }
      })
    } else {
      next();
    }
  }
});

export default router
