<template>
  <div class="association">
    <div class="association-table">
      <router-view :level = "level" />
    </div>
    <div class="association-type">
      <el-card shadow="always" >
        <el-menu
          :default-active="activeIndex"
          @select="handleSelect"
           mode="horizontal" 
        >
          <el-menu-item index="全部社团">全部社团</el-menu-item>
          <el-menu-item index="校级社团">校级社团</el-menu-item>
          <el-menu-item index="一级社团">一级社团</el-menu-item>
          <el-menu-item index="二级社团">二级社团</el-menu-item>
        </el-menu>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "association",
  data() {
    return {
       activeIndex: '全部社团',
       level:'全部社团',
    };
  },
  methods: {
    handleSelect(index,indexpath){
      this.level = index
    }
  },
};
</script>

<style lang="scss" scoped>
.association{
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.association-table {
  width: 70%;
  padding: 20px;
  border-radius: 5px ;
  box-shadow: 0 0 10px #bdbdbd;
  background-color: #fff;
  margin: 20px;
  text-align: center;
  color: #303133;
}

.association-type{
  width: 150px;
  margin: 20px;
}

</style>
