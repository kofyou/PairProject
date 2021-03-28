<template>
    <div class="box-card" align="left">
        <div class="paper title">
            <h1>Visual Attention Consistency Under Image Transforms for Multi-Label Image Classification</h1>
            <a>Author:</a>
            <span class="blue">Hao Guo, Kang Zheng, Xiaochuan Fan, Hongkai Yu, Song Wang</span>
        </div>
        <el-divider></el-divider>
        <div class="paper body">
            <h2>Abstract</h2>
            <p> Human visual perception shows good consistency for many multi-label image classification tasks under certain spatial transforms, such as scaling, rotation, flipping and translation. This has motivated the data augmentation strategy widely used in CNN classifier training -- transformed images are included for training by assuming the same class labels as their original images....</p>
            <p>abcdefghijklmn</p>
            <p>abcdefghijklmn</p>
        </div>
        <div class="right">
            <el-button class="btns"></el-button>
            <el-button class="btns"></el-button>
            <el-button class="btns"></el-button>
        </div>
    </div>
        
</template>
<script>
import { defineComponent } from "vue";
export default defineComponent({    
  name: "Paper",
});
</script>
<style>
    .box-card {
        max-width:1200px;
        width:100%;
        height:100%;
        /* margin: 20px; */
        margin-bottom: 50px;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        float: left;
        border-radius: 10px;
        margin-right: 10px;
        padding: 5px;
        padding-top: 15px;
    }
    .paper {
        /* width: 80%; */
        height:100%;
    }
    .title {
        background-color: #f0a091de;
        /* filter: blur(2px); */
        border-radius: 7px;
        padding-top: 10px;
        padding-bottom: 10px;
        padding-left: 20px;
        padding-right: 20px;
        margin-top: 5px;
        margin-left: 20px;
        margin-right: 20px;
        justify-content:flex-start;
    }
    .body {
        width: 80%;
        float: left;
        padding-left:40px;
        padding-bottom: 20px;
    }
    .right {
        float: right;
        padding-bottom: 10px;
    }
    .btns {
        clear: both;
        display: block;
        margin-bottom:5px;
        margin-left: 0px !important;
        margin-right: 20px;
        
    }
    .block {
        display: block;
    }
    .blue {
        color: rgb(139, 175, 218);
    }

</style>
