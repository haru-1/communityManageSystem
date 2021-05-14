<template>
  <div class="myActivity">
    <el-row class="myActivity">
      <el-col
        :span="10"
        offset="1"
        v-for="(item, index) in activityList"
        :key="index"
      >
        <el-card class="content" shadow="hover" @click.native="activityDetail(item.act_name)">
          <div class="content-header">{{ item.act_name }}</div>
          <div>
            活动类型：<span class="content-title">{{ item.act_level }}</span>
          </div>
          <div>
            参加活动时间：<span class="content-title">{{ item.att_date }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "myActivity",
  data() {
    return {
      activityList: [],
    };
  },
  created() {
    this.getMyActivity();
  },
  methods: {
    activityDetail(act_name) {
      this.$router.push({
        name: "activityDetail",
        params: { act_name: act_name },
      });
    },
    getMyActivity() {
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/personal/getMyActivity",
          {},
          {
            headers: {
              token: token,
            },
          }
        )
        .then((res) => {
          if (res.data.state === 200) {
            this.activityList = res.data.data;
          }
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.myActivity {
  width: 100%;
}
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
  line-height: 30px;
  height: 140px;
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