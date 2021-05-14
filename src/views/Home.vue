<template>
  <div class="home">
    <div class="header">
      <div class="header-title">校园社团管理系统</div>
      <div class="header-menu">
        <el-menu
          default-active="association"
          class="header-menu-detail"
          mode="horizontal"
          router="true"
          @select="handleSelect"
        >
          <el-menu-item index="association">社团详情</el-menu-item>
          <el-menu-item index="activity">活动详情</el-menu-item>
          <el-menu-item index="personal">个人中心</el-menu-item>

          <div class="menu-login">
            <div v-if="!seen">
              <el-link href="/#/login">登录</el-link>
            </div>
            <div v-else>
              <el-dropdown>
                <span class="el-dropdown-link">
                  欢迎你
                  <i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>
                    <el-button type="text" @click="cancelAccount">注销</el-button>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <el-button type="text" @click="dialogVisible = true"
                      >修改密码</el-button
                    >
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              <!-- 修改密码弹框 -->
              <el-dialog
                title="修改密码"
                :visible.sync="dialogVisible"
                width="30%"
                center
              >
                <el-form ref="alterpass" :model="alterpass" label-width="80px">
                  <el-form-item label="原密码">
                    <el-input
                      type="password"
                      v-model="alterpass.oldpass"
                    ></el-input>
                  </el-form-item>
                  <el-form-item label="新密码">
                    <el-input
                      type="password"
                      v-model="alterpass.newpass"
                    ></el-input>
                  </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                  <el-button type="primary" @click="alterpassword"
                    >确 定</el-button
                  >
                </span>
              </el-dialog>
            </div>
          </div>
        </el-menu>
      </div>
    </div>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      seen: "",
      dialogVisible: false,
      alterpass:{
        oldpass:'',
        newpass:''
      }
    };
  },
  created() {
    if (window.localStorage.getItem("token") != null) {
      this.seen = true;
    } else {
      this.seen = false;
    }
    // this.seen = window.localStorage.getItem("seen");
  },
  methods: {
    handleSelect(tab, event) {
    },
    cancelAccount(){
      let self = this;
      this.seen = false;
      localStorage.removeItem("token");
      localStorage.removeItem("isAdmin");
      window.location.reload();
    },
    alterpassword() {
      let self = this;
      const token = window.localStorage.getItem("token");
      this.$axios
        .post(
          "/api/login/UpdatePassword",
           this.$qs.stringify({
        password: self.alterpass.oldpass,
        newpassword: self.alterpass.newpass
      }),
          {
            headers: {
              token: token
            }
          }
        )
        .then(res => {
          if (res.data.state===200) {
            this.$notify({
              title: "成功",
              message: "修改密码成功",
              type: "success"
            });
            self.alterpass.oldpass = "";
            self.alterpass.newpass = "";
          } else {
            this.$notify.error({
              title: "错误",
              message: "修改密码失败"
            });
          }
        });
    }
  },
};
</script>

<style lang="scss" scoped>
.header {
  height: 20%;
  padding: 0;
  .header-title {
    color: rgb(161, 161, 161);
    background-color: #abc6e2;
    font-size: 20px;
    font-weight: 600;
    height: 40px;
    padding: 20px 0 0 20px;
  }
  .header-menu {
    background-color: white;
    padding: 0 100px;
    text-align: center;
    margin: 0 auto;
    // .header-menu-detail{
    //   // width: 500px;
    // }
  }
}
.el-menu-item {
  font-size: 16px;
}

.menu-login {
  float: right;
  margin: 20px 0;
  // height: 50px;
}
</style>
