<template>
    <div class="personal">
        <div class="personal-content">
            <router-view/>
        </div>
        <div class="personal-type">
            <el-card shadow="always" >
        <el-menu
          default-active="myMessage"
          @select="handleSelect"
           mode="horizontal" 
           router
           v-if="isAdmin=='false'"
        >
             
          <el-menu-item index="myMessage">个人信息</el-menu-item>
          <el-menu-item index="approvalMessage">我的审批</el-menu-item>
          <el-menu-item index="applyAssociation">申请建社</el-menu-item>
          <el-menu-item index="myAssociation">已参加社团</el-menu-item>
          <el-menu-item index="myActivity">已参加活动</el-menu-item>
        </el-menu>
        <el-menu
          default-active="allStudent"
          @select="handleSelect"
           mode="horizontal" 
           router
           v-else
        >
          <el-menu-item index="allStudent">学生管理</el-menu-item>
          <el-menu-item index="approvalAssociation">社团审批</el-menu-item>
          <el-menu-item index="approvalActivity">活动审批</el-menu-item>
        </el-menu>
      </el-card>
        </div>
    </div>
</template>
<script>
export default {
    name:"personal",
    data(){
        return{
            isAdmin:false,
        }
    },
    created(){
      this.isAdmin = localStorage.getItem("isAdmin");
    }
}
</script>
<style lang="scss" scoped>
.personal{
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.personal-content {
  width: 70%;
  padding: 20px;
  border-radius: 5px ;
  box-shadow: 0 0 10px #bdbdbd;
  background-color: #fff;
  margin: 20px;
  text-align: center;
  color: #303133;
}

.personal-type{
  width: 150px;
  margin: 20px;
}
</style>
