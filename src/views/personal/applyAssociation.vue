<template>
  <div class="applyAssociation">
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
          >申请社团</el-button
        >
      </el-form-item>
    </el-form>

    <el-dialog title="申请社团" :visible.sync="dialogVisible" width="30%">
      <span>确定申请社团吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="applyAssociationMessage"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "applyAssociation",
  data() {
    return {
      dialogVisible: false,
      association: {
        as_name: "",
        as_introduce: "",
        as_number: "",
        as_level: "",
      },
    };
  },
  methods: {
    applyAssociationMessage() {
      const token = window.localStorage.getItem("token");
      this.$axios
        .post(
          "/api/personal/addAssociation",
          this.$qs.stringify({
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
              message: res.data.msg,
              type: "success",
            });
          } else {
            this.$notify.error({
              title: "失败",
              message: res.data.msg,
            });
          }
          this.dialogVisible = false;
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.applyAssociation {
  padding: 30px 20px;
  text-align: left;
}
</style>