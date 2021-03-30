<template>
    <div class="box-card" align="left">
        <div class="paper title">
            <h1>Visual Attention Consistency Under Image Transforms for Multi-Label Image Classification</h1>
            <a>作者: </a>
            <span class="blue">Hao Guo, Kang Zheng, Xiaochuan Fan, Hongkai Yu, Song Wang</span>
        </div>
        <el-divider></el-divider>
        <div class="paper body">
            <h2>摘要：</h2>
            <div style="height: 80px; overflow:hidden">
                <span>In this paper we propose a new technique to perform figure-ground segmentation in image sequences of moving objects under varying illumination conditions. Unlike most of the algorithms some of these hypotheses take into uccessfully track nonrigid contours. Reciprocally, the contour estimation is used to correct the color model. The integration of color and shape is done in a stage denominated 'sample concentration', that has been introduced as a final step to the well-known CONDENSATION algorithm. </span>
            </div>
            <div style="height:10px;"></div>
            <h3>关键词:</h3>
            <div style="height: 32px; overflow:hidden">
                <span>Deformable models,Image segmentation,Image sequences,Lighting,Object segmentation,Colored noise,Multi-stage noise shaping,Noise generators,Fitting,Color</span>
            </div>
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
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        float: left;
        border-radius: 10px;
        padding: 5px;
        padding-top: 15px;
    }
    .paper {
        /* width: 80%; */
        height:100%;
    }
    .title {
        background-color: #ccadbb;
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
        overflow: hidden;
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
        color: rgb(98, 123, 155);
    }
    .paper-details {
        widows: 80%;;
    }

</style>
