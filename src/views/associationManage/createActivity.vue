<template>
  <div class="createActivity">
    <el-form ref="form" :model="activity" label-width="120px">
      <el-form-item label="活动名称">
        <el-input v-model="activity.act_name"></el-input>
      </el-form-item>
      <el-form-item label="活动内容">
        <el-input
          type="textarea"
          :autosize="{ minRows: 4 }"
          v-model="activity.act_content"
        ></el-input>
      </el-form-item>
      <el-form-item label="活动形式">
        <el-radio-group v-model="activity.act_form">
          <el-radio label="线上"></el-radio>
          <el-radio label="线下"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="活动地点">
        <el-input v-model="activity.act_address"></el-input>
      </el-form-item>
      <el-form-item label="报名开始时间">
        <el-date-picker
          v-model="activity.act_regStart"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="报名结束时间">
        <el-date-picker
          v-model="activity.act_regEnd"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动开始时间">
        <el-date-picker
          v-model="activity.act_start"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动结束时间">
        <el-date-picker
          v-model="activity.act_end"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss"
          placeholder="选择日期时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="需要的人数">
        <el-input v-model="activity.act_number"></el-input>
      </el-form-item>
      <el-form-item label="活动类型">
        <el-radio-group v-model="activity.act_level">
          <el-radio label="校级活动"></el-radio>
          <el-radio label="院级活动"></el-radio>
          <el-radio label="社团活动"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true"
          >提交申请</el-button
        >
      </el-form-item>
    </el-form>

    <el-dialog title="提交申请" :visible.sync="dialogVisible" width="30%">
      <span>确定提交该活动申请吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addApplyActivity">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "createActivity",
  props: ["as_name"],
  data() {
    return {
      as_name: "",
      dialogVisible: false,
      activity: {
        act_name: "",
        act_content: "",
        act_form: "",
        act_address: "",
        act_regStart: "",
        act_regEnd: "",
        act_start: "",
        act_end: "",
        act_number: "",
        act_level: "",
      },
    };
  },
  watch: {
    as_name(value) {
      this.as_name = value;
    },
  },
  methods: {
    addApplyActivity() {
      const token = window.localStorage.getItem("token");
      this.$axios
        .post(
          "/api/president/addApplyActivity",
          this.$qs.stringify({
            as_name: this.as_name,
            act_name: this.activity.act_name,
            act_content: this.activity.act_content,
            act_form: this.activity.act_form,
            act_address: this.activity.act_address,
            act_regStart: this.activity.act_regStart,
            act_regEnd: this.activity.act_regEnd,
            act_start: this.activity.act_start,
            act_end: this.activity.act_end,
            act_number: this.activity.act_number,
            act_level: this.activity.act_level,
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
              message: "递交活动申请成功",
              type: "success",
            });
          } else {
            this.$notify.error({
              title: "失败",
              message: "递交活动申请失败",
            });
          }
          Object.keys(this.activity).forEach((key) => (this.activity[key] = ""));
          this.dialogVisible = false;
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.createActivity {
  padding: 30px 20px;
  text-align: left;
}
</style>