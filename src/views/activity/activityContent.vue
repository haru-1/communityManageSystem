<template>
  <div class="activity-content">
    <div class="content-search">
      <el-form :inline="true" :model="activity">
        <el-form-item label="活动名称">
          <el-input v-model="activity.name" placeholder="活动名称"></el-input>
        </el-form-item>
        <!-- <el-form-item label="活动状态">
            <el-input v-model="activity.status" placeholder="活动状态"></el-input>
          </el-form-item> -->
        <el-form-item>
          <el-button type="primary" @click="searchActivity">查询</el-button>
        </el-form-item>
      </el-form>
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
      </el-table>
      <div class="content-list-page">
        <el-pagination layout="prev, pager, next" :total="1000">
        </el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "activityContent",
  props: ["level"],
  data() {
    return {
      activity: {
        name: "",
        status: "",
      },
      tableHeader: [
        {
          prop: "as_name",
          label: "发布机构",
          width: "150",
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
          prop: "act_end",
          label: "活动截止时间",
          width: "180",
        },
      ],
      tableData: [],
      level: "",
    };
  },
  created() {
    this.getActivityList();
  },
  watch: {
    level(value) {
      this.level = value;
      this.getActivityList()
    },
  },
  methods: {
    searchActivity() {
      this.tableData = [];
      let self = this;
      this.$axios
        .post(
          "/api/activity/GetActivityForSearch",
          this.$qs.stringify({
            act_name: self.activity.name,
          })
        )
        .then((res) => {
            this.tableData = res.data.data;
          }
        );
    },
    getActivityList() {
      this.tableData = []
      if (this.level === "全部活动") {
        this.$axios.post("/api/activity/GetActivityList").then((res) => {
          if (res.data.state === 200) {
            this.tableData = res.data.data;
          }
        });
      }
      else{
        this.$axios.post(
          "/api/activity/GetActivityLevelList",this.$qs.stringify({
            act_level:this.level
          })
        )
        .then(res=>{
          if (res.data.state === 200) {
            this.tableData = res.data.data;
          }
        })
      }
    },
    activityDetail(act_name) {
      this.$router.push({ name: "activityDetail",params:{act_name:act_name}});
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
  .content-search {
    text-align: center;
    padding: 10px;
    width: 80%;
    border-radius: 5px;
    box-shadow: 0 0 5px #e7e7e7;
    background-color: rgb(245, 245, 245);
  }
  .content-list {
    text-align: center;
    max-width: 100%;
    text-overflow: ellipsis;
  }
}

</style>
