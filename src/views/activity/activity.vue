<template>
  <div class="activity">
    <div class="activity-table">
      <router-view :level="level" />
    </div>
    <div class="activity-type">
      <el-card shadow="always">
        <el-menu
          :default-active="activeIndex"
          @select="handleSelect"
          mode="horizontal"
        >
          <el-menu-item index="全部活动">全部活动</el-menu-item>
          <el-menu-item index="校级活动">校级活动</el-menu-item>
          <el-menu-item index="院级活动">院级活动</el-menu-item>
          <el-menu-item index="社团活动">社团活动</el-menu-item>
        </el-menu>
      </el-card>
    </div>
  </div>
</template>
<script>
export default {
  name: "activity",
  data() {
    return {
      activeIndex: "全部活动",
      level:"全部活动"
    };
  },
  methods:{
    handleSelect(index,indexpath){
      this.level = index
    }
  }
};
</script>
<style lang="scss" scoped>
.activity {
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.activity-table {
  width: 70%;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px #bdbdbd;
  background-color: #fff;
  margin: 20px;
  text-align: center;
  color: #303133;
}

.activity-type {
  width: 150px;
  margin: 20px;
}
</style>
