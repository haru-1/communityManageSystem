<template>
  <!-- 登录页面 -->
  <div class="login">
    <div class="login-form">
      <div class="form-title">登录</div>
      <el-form
        ref="form"
        v-bind:model="login"
        label-width="80px"
        :rules="rules"
      >
        <el-form-item label="账号" prop="userid">
          <el-input
            v-model="login.userid"
            prefix-icon="el-icon-user"
            placeholder="请输入账号"
          ></el-input>
        </el-form-item>
        <el-form-item label-position="top" label="密码" prop="password">
          <el-input
            type="password"
            v-model="login.password"
            prefix-icon="el-icon-lock"
            placeholder="请输入密码"
          ></el-input>
        </el-form-item>
        <el-form-item label-position="top" label="验证码" prop="code">
          <div class="form-code">
            <el-input
              v-model="code"
              prefix-icon="el-icon-key"
              placeholder="请输入验证码"
            ></el-input>
            <div @click="refreshCode">
              <s-identify :identifyCode="identifyCode"></s-identify>
            </div>
          </div>
        </el-form-item>
        <el-form-item >
          <el-button type="primary" @click="userLogin">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex';
import SIdentify from "@/components/login/sidentify";
// import { mapMutations } from "vuex";

export default {
  //   props: {
  //     userid2: "",
  //     password2: ""
  //   },
  name: "login",
  data() {
    return {
      seen: false,
      code: "",
      login: {
        userid: "",
        password: "",
      },
      identifyCodes: "23456789qwertyupasdfghjkzxcvbnm",
      identifyCode: "",
      //验证规则
      rules: {
        userid: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        // code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
        code: [{ required: false, message: "请输入验证码", trigger: "blur" }],
      },
    };
  },

  methods: {
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },

    refreshCode() {
      this.identifyCode = "";
      this.makeCode(this.identifyCodes, 4);
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.randomNum(0, this.identifyCodes.length)
        ];
      }
    },
    ...mapMutations(["setToken"]),
    userLogin() {
      //验证码验证
      if (this.login.password === "" || this.login.userid === "") {
        this.$notify({
          title: "警告",
          message: "用户名与密码不能为空",
          type: "warning",
        });
        return;
      }
      if (this.code !== this.identifyCode) {
        this.code = "";
        this.refreshCode();
        this.$notify({
          title: "警告",
          message: "请输入正确的验证码",
          type: "warning",
        });
        return;
      }
      let self = this
      this.$axios
      .post('/api/login/Login',
      this.$qs.stringify({
        id: self.login.userid,
        password: self.login.password,
      }))
      .then(res=>{
        if(res.data.state === 200){
          self.token = res.data.token;
          self.setToken({token: self.token});
          window.localStorage.setItem("seen", true);
          window.localStorage.setItem("isAdmin", false);
          if(res.data.name==="isAdmin"){
             window.localStorage.setItem("isAdmin", true);
          }
          this.$router.push('/');
        }
        else if (res.data.state !== 200) {
            self.$notify({
              title: "登录失败",
              message: "用户名或密码错误",
              type: "error",
            });
            this.code = "";
            this.refreshCode();
        }
      }).catch(err=>{
      });
    },
  },
  mounted() {
    this.identifyCode = "";
    this.makeCode(this.identifyCodes, 4);
  },
  created() {
    this.refreshCode();
  },
  components: { SIdentify },
};
</script>

<style>
.login {
  background-color: #abc6e2;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-form {
  background-color: rgba(255, 255, 255, 0.7);
  width: 350px;
  padding: 20px;
  border-radius: 5px;
}

.form-title {
  text-align: center;
  margin: 0 0 10px 0;
}

.form-code {
  display: flex;
  align-items: center;
  justify-content: center;
  width:300px;
}
</style>