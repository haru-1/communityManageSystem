<template>
  <div class="updateAssociation">
    <el-form ref="form" :model="association" label-width="80px">
      <el-form-item label="社团名称">
        <el-input v-model="association.as_name"></el-input>
      </el-form-item>
      <el-form-item label="社团简介">
        <el-input
          type="textarea"
          :autosize="{ minRows: 4 }"
          v-model="association.as_introduce"
        ></el-input>
      </el-form-item>
      <el-form-item label="社团类型">
        <el-radio-group v-model="association.as_level">
          <el-radio label="校级社团"></el-radio>
          <el-radio label="院级社团"></el-radio>
          <el-radio label="二级社团"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="招聘人数">
        <el-input v-model="association.as_number"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true"
          >修改信息</el-button
        >
      </el-form-item>
    </el-form>

    <el-dialog title="修改信息" :visible.sync="dialogVisible" width="30%">
      <span>确定修改信息吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateAssociationMessage"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "updateAssociation",
  props: ["as_name"],
  data() {
    return {
      as_name: "",
      dialogVisible: false,
      association: {
        as_name: "",
        as_introduce: "",
        as_number: "",
        as_level: "",
      },
    };
  },
  created() {
    this.getAssociationDetail();
  },
  watch: {
    as_name(value) {
      this.as_name = value;
    },
  },
  methods: {
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
            this.association = res.data.data;
          }
        });
    },
    updateAssociationMessage() {
      const token = window.localStorage.getItem("token");
      this.$axios
        .post(
          "/api/president/updateAssociationMessage",
          this.$qs.stringify({
            oldName: this.as_name,
            as_name: this.association.as_name,
            as_introduce: this.association.as_introduce,
            as_number: this.association.as_number,
            as_level: this.association.as_level,
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
              message: "修改社团信息成功",
              type: "success",
            });
          } else {
            this.$notify.error({
              title: "失败",
              message: "修改社团信息失败",
            });
          }
          this.dialogVisible = false;
          this.getAssociationDetail();
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.updateAssociation {
  padding: 30px 20px;
  text-align: left;
}
</style>