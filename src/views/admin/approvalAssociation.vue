<template>
  <div class="association-content">
    <div class="content-list">
      <el-table
        :data="tableData"
        style="width: 100%"
        cell-style="text-align:center;"
        header-cell-style="text-align:center;"
        @row-click="getAs_id"
      >
        <div v-for="(item, index) in tableHeader" :key="index">
          <el-table-column
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
            show-overflow-tooltip
          >
          </el-table-column>
        </div>
        <el-table-column prop="operate" label="操作" width="150">
          <template>
            <el-button size="mini" type="primary" @click="dialogVisible1 = true"
              >批准</el-button
            >
            <el-button size="mini" type="primary" @click="dialogVisible2 = true"
              >拒绝</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="批准" :visible.sync="dialogVisible1" width="30%">
        <span>确定批准此社团建立申请吗？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="updateApplyState('批准')"
            >确 定</el-button
          >
        </span>
      </el-dialog>

      <el-dialog title="拒绝" :visible.sync="dialogVisible2" width="30%">
        <span>确定拒绝此社团建立申请吗？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible2 = false">取 消</el-button>
          <el-button type="primary" @click="updateApplyState('拒绝')"
            >确 定</el-button
          >
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "associationContent",
  data() {
    return {
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
      dialogVisible1: false,
      dialogVisible2: false,
      as_id: "",
    };
  },
  created() {
    this.getAssociationApplyList();
  },
  methods: {
    getAssociationApplyList() {
      this.tableData = []
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/admin/getAssociationApplyList",
          {},
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
    getAs_id(row, column, event) {
      this.as_id = row.as_id;
    },
    updateApplyState(state) {
      if (state === "批准") {
        this.dialogVisible1 = true;
      } else {
        this.dialogVisible2 = true;
      }
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/admin/updateAssociationMessage",
          this.$qs.stringify({
            as_id: this.as_id,
            state: state,
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
              message: "审批成功",
              type: "success",
            });
          } else {
            this.$notify({
              title: "失败",
              message: "审批失败",
            });
          }
          this.dialogVisible1 = false;
          this.dialogVisible2 = false;
          this.getAssociationApplyList();
        });
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
