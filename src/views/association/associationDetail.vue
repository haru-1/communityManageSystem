<template>
  <div class="association-detail">
    <div class="detail-header">
      <span class="header-return">
        <el-link @click="back()"> &lt;&nbsp;返回</el-link>
      </span>
      <span class="header-title">{{as_name}}</span>
    </div>
    <div class="detail-content">
      <div v-for="(item, index) in associationList" :key="index">
        <p>
          <span class="title">{{ item.title }}:</span>
          <span class="content">{{ item.content }}</span>
        </p>
      </div>
      <div><el-button type="primary" @click="dialogFormVisible=true">申请加入社团</el-button></div>
      <el-dialog
        title="申请加入社团"
        :visible.sync="dialogFormVisible"
        width="30%"
        class="apply-content"
      >
        <el-form :model="form">
          <el-form-item label="申请理由">
            <el-input
              type="textarea"
              v-model="form.content"
              :rows="6"
            ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="applyAssociation">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "associationDetail",
  data() {
    return {
      as_name:'',
      dialogFormVisible: false,
      associationList: [
        {
          title: "社团名称",
          content: "",
        },
        {
          title: "社团简介",
          content:
            "",
        },
        {
          title: "社团社长",
          content: "",
        },
        {
          title: "目前人数",
          content: "",
        },
        {
          title: "社团类型",
          content: "",
        },
      ],
      form: {
        content: "",
      },
    };
  },
  created() {
    this.as_name = this.$route.params.as_name
    this.getAssociationDetail();
  },
  methods: {
    back() {
      this.$router.go(-1);
    },
    getAssociationDetail() {
      this.$axios
        .post(
          "/api/associations/GetAssociationDetail",
          this.$qs.stringify({
            as_name: this.as_name,
          })
        )
        .then((res) => {
          if (res.data.state === 200) {
              this.associationList[0].content = res.data.data.as_name
              this.associationList[1].content = res.data.data.as_introduce
              this.associationList[2].content = res.data.data.stu_name
              this.associationList[3].content = res.data.data.as_number
              this.associationList[4].content = res.data.data.as_level
          }
        });
    },
    applyAssociation() {
      let self = this
      const token = window.localStorage.getItem("token")
      this.$axios
      .post(
        '/api/apply/addApply',
        this.$qs.stringify({
          as_name:this.as_name,
          ap_content:this.form.content
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
              message: "申请社团成功",
              type: "success"
            });
        }
        else {
            this.$notify.error({
              title: "失败",
              message: "已经申请过社团，请勿重新申请"
            });
          }
      })
      this.form.content = ''
      this.dialogFormVisible = false;
    },
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
  .title {
    font-weight: 600;
  }
  .content {
    padding: 0 10px;
    color: gray;
  }
}
</style>