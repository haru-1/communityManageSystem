import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from './store'
import axios from 'axios'
import qs from 'qs';

Vue.use(ElementUI);
Vue.prototype.$qs = qs;
Vue.prototype.$axios = axios
Vue.config.productionTip = false

// axios 配置

axios.defaults.timeout = 5000;

//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';

// axios.defaults.baseURL = 'http://localhost:8080/项目名/';

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')



// 添加请求拦截器
axios.interceptors.request.use(config => {
  // 在发送请求之前做些什么
  //判断是否存在token，如果存在将每个页面header都添加token
    if(store.state.token){
      config.headers.common['Authorization']=store.state.token.token
    }
    return config;
  }, error => {
  // 对请求错误做些什么
    return Promise.reject(error);
  });
  
  // http response 拦截器
  axios.interceptors.response.use(
    response => {
      return response;
    },
    error => {
      if (error.response) {
        switch (error.response.status) {
          case 401:
            this.$store.commit('del_token');
            router.replace({
              path: '/login',
              query: {redirect: router.currentRoute.fullPath}//登录成功后跳入浏览的当前页面
            })
        }
      }
      return Promise.reject(error.response.data)
    });

