<template>
  <div class="approval-message">
    <div class="message-header">
      <span class="header-title">社团人员管理</span>
    </div>
    <div class="content-list">
      <el-table
        :data="tableData"
        cell-style="text-align:center;"
        header-cell-style="text-align:center;"
        @row-click="getId"
      >
        <div v-for="(item, index) in tableHeader" :key="index">
          <el-table-column
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
          >
          </el-table-column>
        </div>
        <el-table-column prop="operate" label="操作" width="80">
          <template>
            <el-button size="mini" type="primary" @click="dialogVisible = true"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="删除" :visible.sync="dialogVisible" width="30%">
        <span>确定将他移出社团吗？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="deleteMember">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "associationMember",
  props: ["as_name"],
  data() {
    return {
      as_name: "",
      ap_id: "",
      stu_id: "",
      tableHeader: [
        {
          prop: "stu_tel",
          label: "手机号码",
          width: "140",
        },
        {
          prop: "stu_id",
          label: "学号",
          width: "100",
        },
        {
          prop: "stu_name",
          label: "姓名",
          width: "100",
        },
        {
          prop: "stu_sex",
          label: "性别",
          width: "80",
        },
        {
          prop: "stu_major",
          label: "专业",
          width: "100",
        },
        {
          prop: "stu_grade",
          label: "年级",
          width: "100",
        },
        {
          prop: "stu_college",
          label: "学院",
          width: "140",
        },
      ],

      tableData: [],
      dialogVisible: false,
    };
  },
  created() {
    this.getMember();
  },
  watch: {
    as_name(value) {
      this.as_name = value;
    },
  },
  methods: {
    getMember() {
      this.tableData = []
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/president/getAssociationMember",
          this.$qs.stringify({ as_name: this.as_name }),
          {
            headers: {
              token: token,
            },
          }
        )
        .then((res) => {
          if (res.data.state === 200) {
            this.tableData = res.data.data;
          }
        });
    },
    getId(row, column, event) {
      this.ap_id = row.ap_id;
      this.stu_id = row.stu_id;
    },
    deleteMember() {
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/president/deleteAssociationMember",
          this.$qs.stringify({
            stu_id: this.stu_id,
            as_name: this.as_name,
          }),
          {
            headers: {
              token: token,
            },
          }
        )
        .then((res) => {
          if (res.data.state === 200) {
            this.$notify({
              title: "成功",
              message: "删除成功",
              type: "success",
            });
          } else {
            this.$notify({
              title: "失败",
              message: "删除失败",
            });
          }
          this.dialogVisible = false;
          this.getMember();
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.approval-message {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  .message-header {
    text-align: center;
    padding: 0 0 10px 0;
    .header-title {
      font-weight: 600;
      font-size: 18px;
      color: #abc6e2;
    }
  }
  .content-list {
    text-align: center;
    max-width: 100%;
  }
}
</style>