<template>
  <div class="myAssociation">
    <el-row class="myAssociation">
      <el-col
        :span="10"
        offset="1"
        v-for="(item, index) in associationList"
        :key="index"
      >
        <el-card
          class="content"
          shadow="hover"
          @click.native="associationDetail(item.as_name)"
        >
          <div class="content-header">{{ item.as_name }}</div>
          <div>
            社团类型：<span class="content-title">{{ item.as_level }}</span>
          </div>
          <div>
            社团身份：<span class="content-title">{{ item.mem_type }}</span>
          </div>
          <div class="as-button" v-if="item.mem_type == '社长'">
            <el-button
              type="primary"
              plain
              @click.stop="associationManage(item.as_name)"
              >前往管理</el-button
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "myAssociation",
  data() {
    return {
      associationList: [],
    };
  },
  created() {
    this.getMyAssociation();
  },
  methods: {
    associationDetail(as_name) {
      this.$router.push({
        name: "associationDetail",
        params: { as_name: as_name },
      });
    },
    getMyAssociation() {
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/personal/getMyAssociation",
          {},
          {
            headers: {
              token: token,
            },
          }
        )
        .then((res) => {
          if (res.data.state === 200) {
            this.associationList = res.data.data;
          }
        });
    },
    associationManage(as_name){
      this.$router.push({
        name: "associationIntroduce",
        params: { as_name: as_name },
      });
    }
  },
};
</script>

<style lang="scss" scoped>
.myAssociation {
  width: 100%;
}
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
  line-height: 30px;
  height: 175px;
  .content-header {
    font-weight: 600;
    font-size: 18px;
  }
  .content-title {
    color: gray;
  }
  .as-button {
    margin: 10px 0 0 0;
  }
}
</style>