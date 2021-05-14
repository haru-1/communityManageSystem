<template>
  <div class="activity-detail">
    <div class="detail-header">
      <span class="header-return">
        <!-- <i class="el-icon-arrow-left"/> -->
        <el-link @click="back()"> &lt;&nbsp;返回</el-link>
      </span>
      <span class="header-title">{{act_name}}</span>
    </div>
    <div class="detail-content">
      <div v-for="(item, index) in activityList" :key="index">
        <p>
          <span class="title">{{ item.title }}:</span>
          <span class="content">{{ item.content }}</span>
        </p>
      </div>
      <div><el-button type="primary" @click="addAttendActivity">参加活动</el-button></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "activityDetail",
  data() {
    return {
      act_name:'',
      activityList: [
        {
          title: "活动名称",
          content: "",
        },
        {
          title: "活动内容",
          content: "",
        },
        {
          title: "活动形式",
          content: "",
        },
        {
          title: "活动地点",
          content: "",
        },
        {
          title: "报名开始时间",
          content: "",
        },
        {
          title: "报名截止时间",
          content: "",
        },
        {
          title: "活动开始时间",
          content: "",
        },
        {
          title: "活动截止时间",
          content: "",
        },
        {
          title: "需要的人数",
          content: "",
        },
        {
          title: "活动类型",
          content: "",
        },
        {
          title: "发布机构",
          content: "",
        },
      ],
    };
  },
  created(){
    this.act_name = this.$route.params.act_name
    this.getActivityDetail()
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    getActivityDetail(){
      this.$axios
      .post('/api/activity/GetActivityDetail',
      this.$qs.stringify({
        act_name:this.act_name
      }))
      .then(res=>{
        if(res.data.state === 200){
          this.activityList[0].content = res.data.data.act_name
          this.activityList[1].content = res.data.data.act_content
          this.activityList[2].content = res.data.data.act_form
          this.activityList[3].content = res.data.data.act_address
          this.activityList[4].content = res.data.data.act_regStart
          this.activityList[5].content = res.data.data.act_regEnd
          this.activityList[6].content = res.data.data.act_start
          this.activityList[7].content = res.data.data.act_end
          this.activityList[8].content = res.data.data.act_number
          this.activityList[9].content = res.data.data.act_level
          this.activityList[10].content = res.data.data.as_name
        }
      })
    },
    addAttendActivity(){
      const token = window.localStorage.getItem("token")
      this.$axios
      .post(
        '/api/attend/addAttendActivity',
        this.$qs.stringify({
          act_name:this.act_name,
        }),
        {
            headers: {
              token: token
            }
          }
      )
      .then(res=>{
        if(res.data.state ===200 ){
          this.$notify({
              title: "成功",
              message: res.data.msg,
              type: "success"
            });
        }
        else {
            this.$notify.error({
              title: "失败",
              message: res.data.msg
            });
          }
      })
    }
  },
};
</script>

<style lang="scss" scoped>
.detail-header {
  text-align: center;
  padding: 0 0 10px 0;
  .header-return {
    float: left;
    display: flex;
    padding: 0 10px;
  }
  .header-title {
    font-weight: 600;
    font-size: 18px;
  }
}

.detail-content {
    text-align: left;
    margin: 10px;
    line-height: 40px;
    .title{
        font-weight:600;
    }
    .content{
        padding: 0 10px;
        color: gray;
    }
}
</style>