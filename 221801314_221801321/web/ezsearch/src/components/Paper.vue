<template>
    <el-card class="box-card">
        <div class="paper-title">
            <h1>Visual Attention Consistency Under Image Transforms for Multi-Label Image Classification</h1>
        </div>
        <div class="paper-body">
            <a class="table-cell">Author:</a>
            <span class="table-cell blue">123</span>
            <br/>
            <div>
                <h2 class="table-cell">Abstract</h2>
                <p class="table-col">123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890</p>
            </div>
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
    .paper-title {
        display:flex;
        padding-top: 10px;
        padding-left: 20px;
        justify-content:flex-start;
    }
    .paper-body {
        padding: 30px;
        /* justify-content:space-between; */
        /* align-items:center; */
    }
    .table-cell {
        display:inline-flexbox;
    }
    .table-col {
        display: table-row;
    }
    .block {
        display: block;
    }
    .blue {
        color: rgb(139, 175, 218);
    }

</style>
