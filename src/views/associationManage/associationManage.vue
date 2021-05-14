<template>
  <div class="associationManage">
    <div class="associationManage-content">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item>
          <el-link :underline="false" @click="returnAssociation"
            >参加的社团</el-link
          >
        </el-breadcrumb-item>
        <el-breadcrumb-item>{{ as_name }}</el-breadcrumb-item>
        <el-breadcrumb-item>{{ title }}</el-breadcrumb-item>
      </el-breadcrumb>
      <router-view :as_name="as_name" />
    </div>
    <div class="associationManage-type">
      <el-card shadow="always">
        <el-menu :default-active="defaultTitle" mode="horizontal" router>
          <el-menu-item index="associationIntroduce">社团首页</el-menu-item>
          <el-menu-item index="associationApproval">社团人员审批</el-menu-item>
          <el-menu-item index="associationMember">社团人员管理</el-menu-item>
          <el-menu-item index="updateAssociation">修改社团信息</el-menu-item>
          <el-menu-item index="manageActivity">管理社团活动</el-menu-item>
          <el-menu-item index="createActivity">发布社团活动</el-menu-item>
        </el-menu>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  name: "associationManage",
  data() {
    return {
      as_name: "",
      defaultTitle: "associationIntroduce",
      title: "社团首页",
    };
  },
  created() {
    this.as_name = this.$route.params.as_name;
  },
  watch: {
    $route(to, from) {
      switch (to.name) {
        case "associationIntroduce":
          this.title = "社团首页";
          break;
        case "associationApproval":
          this.title = "社团人员审批";
          break;
        case "associationMember":
          this.title = "社团人员管理";
          break;
        case "updateAssociation":
          this.title = "修改社团信息";
          break;
        case "manageActivity":
          this.title = "管理社团活动";
          break;
        case "createActivity":
          this.title = "发布社团活动";
          break;
      }
    },
  },
  methods: {
    returnAssociation() {
      this.$router.push('/myAssociation');
    },
  },
};
</script>
<style lang="scss" scoped>
.associationManage {
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.associationManage-content {
  // display: flex;
  // flex-direction: column;
  // align-items: center;
  // justify-content: center;
  width: 70%;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px #bdbdbd;
  background-color: #fff;
  margin: 20px;
  text-align: center;
  color: #303133;
}

.associationManage-type {
  width: 150px;
  margin: 20px;
}
</style>
