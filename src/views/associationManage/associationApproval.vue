<template>
  <div class="approval-message">
    <div class="message-header">
      <span class="header-title">社团人员审批</span>
    </div>
    <div class="content-list">
      <el-table
        :data="tableData"
        cell-style="text-align:center;"
        header-cell-style="text-align:center;"
        @row-click="getAp_id"
      >
        <div v-for="(item, index) in tableHeader" :key="index">
          <el-table-column
            :prop="item.prop"
            :label="item.label"
            :width="item.width"
            v-if="item.prop != 'ap_passDate'"
          >
          </el-table-column>
          <el-table-column
            label="审核时间"
            prop="ap_passDate"
            width="180"
            v-else
          >
            <template slot-scope="scope">
              <div v-if="scope.row.ap_passDate != null">
                {{ scope.row.ap_passDate }}
              </div>
              <div v-else>暂无日期</div>
            </template>
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
        <span>确定让他加入社团吗？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="updateApplyState('批准')"
            >确 定</el-button
          >
        </span>
      </el-dialog>

      <el-dialog title="拒绝" :visible.sync="dialogVisible2" width="30%">
        <span>确定拒绝他加入社团吗？</span>
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
  name: "associationApproval",
  props: ["as_name"],
  data() {
    return {
      as_name: "",
      ap_id: "",
      tableHeader: [
        {
          prop: "ap_state",
          label: "审核状态",
          width: "80",
        },

        {
          prop: "stu_id",
          label: "学号",
          width: "70",
        },
        {
          prop: "stu_name",
          label: "姓名",
          width: "70",
        },
        {
          prop: "ap_content",
          label: "申请理由",
          width: "160",
        },
        {
          prop: "ap_applyDate",
          label: "申请时间",
          width: "170",
        },
        {
          prop: "ap_passDate",
          label: "审核时间",
          width: "170",
        },
      ],
      tableData: [],
      dialogVisible1: false,
      dialogVisible2: false,
    };
  },
  created() {
    this.getAllApplyMessage();
  },
  watch: {
    as_name(value) {
      this.as_name = value;
    },
  },
  methods: {
    getAllApplyMessage() {
      this.tableData = []
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/apply/getAllApplyMessage",
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
    getAp_id(row, column, event) {
      this.ap_id = row.ap_id;
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
          "/api/apply/updateApplyState",
          this.$qs.stringify({
            ap_id: this.ap_id,
            ap_state: state,
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
          this.getAllApplyMessage();
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