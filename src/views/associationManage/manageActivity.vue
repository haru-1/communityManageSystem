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
            v-if="item.prop != 'act_name'"
          >
          </el-table-column>
          <el-table-column prop="act_name" label="活动名称" width="150" v-else>
            <template slot-scope="scope">
              <el-link
                type="primary"
                @click="activityDetail(scope.row.act_name)"
                >{{ scope.row.act_name }}</el-link
              >
            </template>
          </el-table-column>
        </div>
        <el-table-column prop="operate" label="操作" width="180">
          <template>
            <el-button size="mini" type="primary" @click="dialogVisible = true"
              >取消活动</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="取消活动" :visible.sync="dialogVisible" width="30%">
        <span>确定取消活动吗？</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="deleteActivity">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  name: "manageActivity",
  props: ["as_name"],
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
      as_name: "",
      act_id: "",
      dialogVisible: false,
    };
  },
  created() {
    this.getAssociationActivityList();
  },
  watch: {
    as_name(value) {
      this.as_name = value;
    },
  },
  methods: {
    getId(row, column, event) {
      this.act_id = row.act_id;
    },
    getAssociationActivityList() {
      this.tableData = []
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/president/getAssociationActivityList",
          this.$qs.stringify({
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
            this.tableData = res.data.data;
          }
        });
    },
    activityDetail(act_name) {
      this.$router.push({
        name: "activityDetail",
        params: { act_name: act_name },
      });
    },
    deleteActivity() {
      const token = localStorage.getItem("token");
      this.$axios
        .post(
          "/api/president/deleteActivity",
          this.$qs.stringify({
            act_id: this.act_id,
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
            this.getAssociationActivityList();
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
