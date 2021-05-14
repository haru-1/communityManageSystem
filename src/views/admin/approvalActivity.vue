<template>
  <div class="activity-content">
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
          <!-- <el-table-column prop="act_name" label="活动名称" width="150" v-else>
            <template slot-scope="scope">
              <el-link
                type="primary"
                @click="activityDetail(scope.row.act_name)"
                >{{ scope.row.act_name }}</el-link
              >
            </template>
          </el-table-column> -->
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
        <span>确定批准该活动吗？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="updateApplyState('批准')"
            >确 定</el-button
          >
        </span>
      </el-dialog>

      <el-dialog title="拒绝" :visible.sync="dialogVisible2" width="30%">
        <span>确定拒绝该活动吗？</span>
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
  name: "approvalActivity",
  data() {
    return {
      tableHeader: [
        {
          prop: "act_end",
          label: "活动截止时间",
          width: "180",
        },
        {
          prop: "act_name",
          label: "活动名称",
          width: "100",
        },
        {
          prop: "act_regStart",
          label: "报名开始时间",
          width: "180",
        },
        {
          prop: "act_regEnd",
          label: "报名截止时间",
          width: "180",
        },
        {
          prop: "act_start",
          label: "活动开始时间",
          width: "180",
        },
      ],
      tableData: [],
      act_id: "",
      dialogVisible1: false,
      dialogVisible2: false,
    };
  },
  created() {
    this.getActivityApplyList();
  },
  methods: {
    getId(row, column, event) {
      this.act_id = row.act_id;
    },
    getActivityApplyList() {
      this.tableData = []
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/admin/getActivityApplyList",
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
    // activityDetail(act_name) {
    //   this.$router.push({
    //     name: "activityDetail",
    //     params: { act_name: act_name },
    //   });
    // },
    updateApplyState(state) {
      if (state === "批准") {
        this.dialogVisible1 = true;
      } else {
        this.dialogVisible2 = true;
      }
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/admin/updateActivityMessage",
          this.$qs.stringify({
            act_id: this.act_id,
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
          this.getActivityApplyList();
        });
    },
  },
};
</script>
<style lang="scss" scoped>
.activity-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  .content-list {
    text-align: center;
    max-width: 100%;
    text-overflow: ellipsis;
  }
}
</style>
