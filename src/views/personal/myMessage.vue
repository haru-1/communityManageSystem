<template>
  <div class="myMessage">
    <div class="myMessage-header">
      <span class="header-title">个人信息</span>
    </div>
    <div class="myMessage-content">
      <div v-for="(item, index) in meaasgeList" :key="index">
        <p>
          <span class="title">{{ item.title }}:</span>
          <span class="content">{{ item.content }}</span>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "myMessage",
    data() {
    return {
      meaasgeList: [
        {
          title: "姓名",
          content: "",
        },
        {
          title: "性别",
          content:
            "",
        },
        {
          title: "所在学院",
          content: "",
        },
        {
          title: "年级",
          content: "",
        },
        {
          title: "专业",
          content: "",
        },
        {
          title: "手机号码",
          content: "",
        },
      ],
    };
  },
  created(){
    this.getMessage()
  },
  methods:{
    getMessage(){
      const token = localStorage.getItem("token")
      this.$axios
      .post('/api/personal/getPersonalMessage',
     {},{
       headers:{
         token:token
       }
     })
     .then(res=>{
       if(res.data.state === 200){
         this.meaasgeList[0].content = res.data.data.stu_name
         this.meaasgeList[1].content = res.data.data.stu_sex
         this.meaasgeList[2].content = res.data.data.stu_major
         this.meaasgeList[3].content = res.data.data.stu_grade
         this.meaasgeList[4].content = res.data.data.stu_college
         this.meaasgeList[5].content = res.data.data.stu_tel
       }
     })
    }
  }

};

</script>

<style lang="scss" scoped>
.myMessage-header {
  text-align: center;
  padding: 0 0 10px 0;
  .header-title {
    font-weight: 600;
    font-size: 18px;
    color: #abc6e2;
  }
}

.myMessage-content {
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