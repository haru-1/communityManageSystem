<template>
  <div class="associationIntroduce">
    <div class="introduce-content">&nbsp;&nbsp;{{associationList.as_introduce}}</div>
    <div class="introduce-number">
      <span class="number-title">社团人数：<span class="number-item">{{associationList.as_number}}</span></span>
      <span class="number-title">活动数量：<span class="number-item">{{associationList.as_actnumber}}</span></span>
    </div>
  </div>
</template>

<script>
export default {
  name: "associationIntroduce",
  props: ["as_name"],
  data() {
    return {
      associationList: [],
      as_name: "",
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
            this.associationList = res.data.data
          }
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.introduce-content{
    padding: 20px;
    text-align: left;
    text-indent: 2em;
    font-weight: 600;
    font-size: 14px;
}
.introduce-number{
    padding: 20px;
    .number-title{
        margin: 0 40px;
        font-weight: 600;
    }
    .number-item{
        color: #abc6e2;
        font-size: 20px;
    }
}
</style>