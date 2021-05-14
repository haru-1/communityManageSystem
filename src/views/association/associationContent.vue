<template>
  <div class="association-content">
    <div class="content-search">
      <el-form :inline="true" :model="association" class="serch-name">
        <el-form-item label="社团名称">
          <el-input
            v-model="association.name"
            placeholder="社团名称"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchAssociation">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="content-list">
      <el-table
        :data="tableData"
        style="width: 100%"
        cell-style="text-align:center;"
        header-cell-style="text-align:center;"
        @row-click="showName"
      >
        <div v-for="(item, index) in tableHeader" :key="index">
          <el-table-column
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
            v-if="item.prop != 'as_name'"
            show-overflow-tooltip
          >
          </el-table-column>
          <el-table-column prop="as_name" label="社团名称" width="150" v-else>
            <template slot-scope="scope">
              <el-link
                type="primary"
                @click="associationDetail(scope.row.as_name)"
                >{{ scope.row.as_name }}</el-link
              >
            </template>
          </el-table-column>
        </div>
        <el-table-column prop="operate" label="操作" width="180">
          <template>
            <el-button
              size="mini"
              type="primary"
              @click="dialogFormVisible=true"
              >申请加入社团</el-button
            >
          </template>
        </el-table-column>
      </el-table>
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
      <div class="table-list-page">
        <el-pagination layout="prev, pager, next" :total="1000">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "associationContent",
  props: ["level"],
  data() {
    return {
      association: {
        name: "",
      },
      tableHeader: [
        {
          prop: "as_number",
          label: "人数",
          width: "100",
        },
        {
          prop: "as_name",
          label: "社团名称",
          width: "150",
        },
        {
          prop: "as_introduce",
          label: "社团简介",
          width: "250",
        },
      ],
      tableData: [],
      level: "",
      dialogFormVisible: false,
      form: {
        content: "",
      },
      as_name:'',
    };
  },
  created() {
    this.getAssociationList();
  },
  watch: {
    level(value) {
      this.level = value;
      this.getAssociationList();
    },
  },
  methods: {
    searchAssociation() {
      this.tableData = [];
      let self = this;
      this.$axios
        .post(
          "/api/associations/GetAssociationForSearch",
          this.$qs.stringify({
            as_name: self.association.name,
          })
        )
        .then((res) => {
          if (res.data.state === 200) {
            this.tableData = res.data.data;
          }
        });
    },
    getAssociationList() {
      this.tableData = []
      if (this.level === "全部社团") {
        this.$axios.post("/api/associations/GetAssociationList").then((res) => {
          if (res.data.state === 200) {
            this.tableData = res.data.data;
          }
        });
      } else {
        this.$axios
          .post(
            "/api/associations/GetAssociationLevelList",
            this.$qs.stringify({
              as_level: this.level,
            })
          )
          .then((res) => {
            if (res.data.state === 200) {
              this.tableData = res.data.data;
            }
          });
      }
    },
    associationDetail(as_name) {
      this.$router.push({
        name: "associationDetail",
        params: { as_name: as_name },
      });
    },
    showName(row, column, event){
      this.form.content = ''
      this.as_name = row.as_name
    },
    applyAssociation() {
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

<style lang="scss">
.association-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  .content-search {
    margin: 0 0 10px 0;
    height: 50px;
    text-align: center;
    padding: 10px;
    width: 80%;
    border-radius: 5px;
    box-shadow: 0 0 5px #e7e7e7;
    background-color: rgb(245, 245, 245);
  }
}

.content-list {
  text-align: center;
  max-width: 100%;
}

.apply-content .el-dialog__body {
  padding: 0 20px;
}


.el-tooltip__popper {
  max-width: 30%;
}

</style>
