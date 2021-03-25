<template>
    <el-card class="box-card">
        <div class="card-header">
            <h1>Title</h1>
        </div>
        <div class="card-body">
            <a>Author</a>
            <div>
                <h3>Abstract</h3>
            </div>
            <p>abcdefghijklmn</p>
            <p>abcdefghijklmn</p>
            <p>abcdefghijklmn</p>
            <p>abcdefghijklmn</p>
        </div>
        <div>123</div>
    </el-card>
        
</template>
<script>
import { defineComponent } from "vue";
export default defineComponent({    
  name: "Paper",
});
</script>
<style>
    .box-card {
        width:1200px;
        height:100%;
        margin-bottom: 50px;
    }
    .card-header {
        display:flex;
        padding-left: 20px;
        justify-content:space-between;
        align-items:center;
    }
    .card-body {
        display: flex;
        padding: 30px;
        /* justify-content:space-between; */
        /* align-items:center; */
    }
</style>
