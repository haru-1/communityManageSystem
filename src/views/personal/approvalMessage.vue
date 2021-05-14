<template>
  <div class="approval-message">
    <div class="message-header">
      <span class="header-title">我的审批（社团）</span>
    </div>
    <div class="content-list">
      <el-table
        :data="tableData"
        cell-style="text-align:center;"
        header-cell-style="text-align:center;"
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
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "approvalMessage",
  data() {
    return {
      tableHeader: [
        {
          prop: "ap_passDate",
          label: "审核时间",
          width: "180",
        },
        {
          prop: "as_name",
          label: "社团名称",
          width: "150",
        },
        {
          prop: "ap_content",
          label: "申请理由",
          width: "200",
        },
        {
          prop: "ap_state",
          label: "审核状态",
          width: "100",
        },
        {
          prop: "ap_applyDate",
          label: "申请时间",
          width: "180",
        },
      ],
      tableData: [],
    };
  },
  created() {
    this.getMyApplyMessage();
  },
  methods: {
    getMyApplyMessage() {
      this.tableData = []
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/personal/getMyApplyMessage",
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