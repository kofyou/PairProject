<template>
  <div class="header">
    <div class="logo">
      <img
        src="https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4090351118,2973307229&fm=26&gp=0.jpg"
        alt=""
      />
      <div class="text">论文系统</div>
    </div>
    <div class="nav">
      <router-link to="/show">论文列表</router-link>
      <router-link to="/echar">热词分析</router-link>
      <!-- <a href="">论文导入</a> -->
      <a href="">趋势图</a>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
.header {
  height: 80px;
  background-image: url(https://images.cnblogs.com/cnblogs_com/lzklzk/1952300/o_210325063128u=3078691788,1881157151&fm=26&gp=0.jpg);
  display: flex;
  justify-content: space-between;
  padding: 0 30px;
  align-items: center;
  .logo {
    display: flex;
    align-items: center;
    cursor: pointer;
    img {
      height: 60px;
    }
    .text {
      color: white;
      font-size: 30px;
      padding-left: 10px;
    }
  }
  .nav{
      display: flex;
      margin-right: 200px;
      a{
          display: block;
          border-radius: 20px;
          padding: 10px 20px;
          color: white;
          font-size: 25px;
          margin-left: 10px;
          &:hover,&.active{
              color:white;
              background:black;
          }
      }
  }
}
</style>