<template>
  <div>
    <myheader></myheader>
    <el-main>
      <sidebar></sidebar>
      <div class="returnbutton">
        <router-link to="/index"><i class="fa fa-arrow-circle-left" aria-hidden="true" style="font-size:80px;color: #133382"></i></router-link>
      </div>
      <el-dialog :visible.sync="dialogVisible">
            <div style="height: 700px">
              <div class="paperdetailstitle">论文详情:</div>
              <div class="papertitle">论文题目:{{this.dialogDetail.paperTitle}}</div>
              <div class="papersource">论文来源:{{this.dialogDetail.paperSource}}</div>
              <div class="paperyear">论文年份:{{this.dialogDetail.paperYear}}</div>
              <div class="paperprecis">论文摘要: {{this.dialogDetail.paperAbstract}}</div>
              <div class="paperkeyword">论文关键词:<span v-for="(item,index) in this.dialogDetail.paperKeyword" :key="index">{{item}}</span></div>
              <a :href="this.dialogDetail.paperUrl" class="paperurl">原文链接</a>
            </div>
          </el-dialog>
      <el-tabs type="border-card" stretch="true" style="position: relative">
        <el-tab-pane>
          <span slot="label"><i class="el-icon-date"></i> 爬取结果显示</span>
          <ul style="list-style: none">
            <li
              v-for="(item, index) in paperDetailList.slice(
                (currentPage - 1) * pagesize,
                currentPage * pagesize
              )"
              style="position: relative"
              :key="index"
            >
              <mycard :paperDetail='item'>
              </mycard>
              <i
                class="fa fa-eye"
                aria-hidden="true"
                style="
                  position: absolute;
                  bottom: 20px;
                  right: 40px;
                  cursor: pointer;
                "
                @click="showDetails(index)"
              ></i>
              <i
                class="fa fa-trash"
                style="
                  position: absolute;
                  bottom: 20px;
                  right: 20px;
                  cursor: pointer;
                "
                @click="deleteCard(index)"
              ></i>
            </li>
          </ul>
          <el-pagination
            small
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pagesize"
            layout="prev, pager, next"
            :total="100"
            style="position: relative; bottom: -50px"
          >
          </el-pagination>
        </el-tab-pane>
        <el-tab-pane label="关键词图谱">
          <el-card shadow="hover" class="keymap">
            <div
              class="keyword"
              style="font-size: 50px; top: 100px; left: 350px; color: red"
              @click="showPapers(0)"
            >
              热词一
            </div>
            <div
              class="keyword"
              style="font-size: 20px; top: 50px; left: 350px; color: #d9c880"
              @click="showPapers(1)"
            >
              热词二
            </div>
            <div
              class="keyword"
              style="font-size: 25px; top: 60px; left: 250px; color: #b1a9c1"
              @click="showPapers(2)"
            >
              热词三
            </div>
            <div
              class="keyword"
              style="font-size: 26px; top: 60px; left: 450px; color: #93aab8"
              @click="showPapers(3)"
            >
              热词四
            </div>
            <div
              class="keyword"
              style="font-size: 15px; top: 80px; left: 150px; color: #81a027"
              @click="showPapers(4)"
            >
              热词五
            </div>
            <div
              class="keyword"
              style="font-size: 29px; top: 120px; left: 150px; color: #ffa634"
              @click="showPapers(5)"
            >
              热词六
            </div>
            <div
              class="keyword"
              style="font-size: 20px; top: 90px; left: 550px; color: #fcb1c0"
              @click="showPapers(6)"
            >
              热词七
            </div>
            <div
              class="keyword"
              style="font-size: 23px; top: 120px; left: 500px; color: #adacab"
              @click="showPapers(7)"
            >
              热词八
            </div>
            <div
              class="keyword"
              style="font-size: 26px; top: 150px; left: 400px; color: #d2ff4d"
              @click="showPapers(8)"
            >
              热词九
            </div>
            <div
              class="keyword"
              style="font-size: 35px; top: 160px; left: 250px; color: #8c0000"
              @click="showPapers(9)"
            >
              热词十
            </div>
          </el-card>
          <div
            style="
              height: 40px;
              line-height: 50px;
              text-align: left;
              width: 150px;
              margin-left: 20px;
            "
          >
            请选择你的热词:
          </div>
          <ul style="list-style: none">
            <li
              v-for="(item, index) in keywordPaperList.slice(
                (keywordPage - 1) * keywordsize,
                keywordPage * keywordsize
              )"
              style="position: relative"
              :key="index"
            >
              <mycard :paperDetail="item">
              </mycard>
              <i
                class="fa fa-eye"
                aria-hidden="true"
                style="
                  position: absolute;
                  bottom: 20px;
                  right: 40px;
                  cursor: pointer;
                "
                @click="showKeywordPaperDetails(index)"
              ></i>
              <i
                class="fa fa-trash"
                style="
                  position: absolute;
                  bottom: 20px;
                  right: 20px;
                  cursor: pointer;
                "
                @click="deleteCard(index)"
              ></i>
            </li>
          </ul>
          <el-pagination
            small
            @current-change="handlekeywordChange"
            :current-page="keywordPage"
            :page-size="keywordsize"
            layout="prev, pager, next"
            :total="100"
            style="text-align: center"
          >
          </el-pagination>
        </el-tab-pane>
        <el-tab-pane label="热度走势">
           <el-form :model="statics">
          <div class="meetingchoosebox">
            <el-radio-group v-model="statics.meetingValue">
              <el-radio-button label="全部顶会"></el-radio-button>
              <el-radio-button label="CVPR"></el-radio-button>
              <el-radio-button label="ICCV"></el-radio-button>
              <el-radio-button label="ECCV"></el-radio-button>
            </el-radio-group>
          </div>
          <div class="rulebox">
            <span>年份:</span>
            <el-select
              v-model="statics.startYearOptions.value"
              placeholder="请选择"
              size="small"
            >
              <el-option
                v-for="item in statics.startYearOptions.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            --
            <el-select
              v-model="statics.endYearOptions.value"
              placeholder="请选择"
              size="small"
            >
              <el-option
                v-for="item in statics.endYearOptions.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
            <el-button type="primary" style="margin-left: 30px; width: 120px"
              >确定</el-button
            >
          </div></el-form>
          <div id="myChart" :style="{ width: '500px', height: '500px' }"></div>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </div>
</template>

<script>
import Myheader from "../components/myheader";
import Sidebar from "../components/sidebar";
import Mycard from "../components/mycard.vue";

export default {
  name: "CrawlResult",
  components: { Sidebar, Myheader, Mycard },
  data() {
    return {
      currentPage: 1,
      pagesize: 3,
      keywordPage: 1,
      keywordsize: 2,

      paperDetailList: [
        {
          paperId: 7,
          paperTitle: "2001_A curve evolution approach for image segmentation using adaptive flows",
          paperSource: "ICCV",
          paperUrl: "https://doi.org/10.1109/ICCV.2001.937666",
          paperYear: "2001",
          paperAbstract: "In this paper, we develop a new active contour model for image segmentation using adaptive flows. This active contour model can be derived from minimizing a limiting form of the Mumford-Shah functional, where the segmented image is assumed to consist of piecewise constant regions. This paper is an extension of an active contour model developed by Chan-Vese. The segmentation method proposed in this paper adaptively estimates mean intensities for each separated region and uses a single curve to capture multiple regions with different intensities. The class of imagery that our new active model can handle is greater than the bimodal images. In particular, our method segments images with an arbitrary number of intensity levels and separated regions while avoiding the complexity of solving a full Mumford-Shah problem. The adaptive flow developed in this paper is easily formulated and solved using level set methods. We illustrate the performance of our segmentation methods on images generated by different modalities.",
          paperKeyword:['关键词1','关键词2'],
        }
      ],
      dialogVisible: false,
      dialogDetail:{
          paperTitle: "",
          paperSource: "",
          paperUrl: "",
          paperYear: "",
          paperAbstract: "",
          paperKeyword:[],
      },
      keywordPaperList:[
        {
          paperId: 6,
          paperTitle: "2001_A curve evolution approach for image segmentation using adaptive flows",
          paperSource: "ICCV",
          paperUrl: "https://doi.org/10.1109/ICCV.2001.937666",
          paperYear: "2001",
          paperAbstract: "In this paper, we develop a new active contour model for image segmentation using adaptive flows. This active contour model can be derived from minimizing a limiting form of the Mumford-Shah functional, where the segmented image is assumed to consist of piecewise constant regions. This paper is an extension of an active contour model developed by Chan-Vese. The segmentation method proposed in this paper adaptively estimates mean intensities for each separated region and uses a single curve to capture multiple regions with different intensities. The class of imagery that our new active model can handle is greater than the bimodal images. In particular, our method segments images with an arbitrary number of intensity levels and separated regions while avoiding the complexity of solving a full Mumford-Shah problem. The adaptive flow developed in this paper is easily formulated and solved using level set methods. We illustrate the performance of our segmentation methods on images generated by different modalities.",
          paperKeyword:['关键词1','关键词2'],
        }
      ],


      statics:{
        meetingValue: "全部顶会",
        startYearOptions: {
        options: [
          {
            value: "1",
            label: "黄金糕",
          },
          {
            value: "2",
            label: "双皮奶",
          },
          {
            value: "3",
            label: "蚵仔煎",
          },
          {
            value: "4",
            label: "龙须面",
          },
          {
            value: "5",
            label: "北京烤鸭",
          },
        ],
        value: "",
      },
      endYearOptions: {
        options: [
          {
            value: "1",
            label: "黄金糕",
          },
          {
            value: "2",
            label: "双皮奶",
          },
          {
            value: "3",
            label: "蚵仔煎",
          },
          {
            value: "4",
            label: "龙须面",
          },
          {
            value: "5",
            label: "北京烤鸭",
          },
        ],
        value: "",
      },
      },
    };
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("myChart"));
      myChart.resize();
      // 绘制图表
      myChart.setOption({
        title: { text: "近年热度走势对比" },
        legend:{data:['销量']},
        tooltip: {},
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "line",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      });
    },
    deleteCard: function (value) {
      this.paperDetailList.splice(value, 1);
    },
    handleCurrentChange: function (currentpage) {
      this.currentPage = currentpage;
    },
    handlekeywordChange: function (keywordpage) {
      this.keywordPage = keywordpage;
    },
    showDetails: function (value) {
      this.dialogVisible = true;
      this.dialogDetail=this.paperDetailList[value];
    },
    showKeywordPaperDetails: function (value) {
      this.dialogVisible = true;
      this.dialogDetail=this.keywordPaperList[value];
    },
  },
};
</script>

<style scoped>
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: 160px;
  height: 900px !important;
  margin: 0 !important;
  padding: 0 !important;
}
.mainpaperlist {
  position: absolute;
  background-color: transparent;
  height: 500px;
  width: 660px;
  top: 230px;
  left: 700px;
  list-style: none;
}
.delete {
  position: absolute;
  bottom: 20px;
  right: 20px;
  font-size: 20px;
  cursor: pointer;
}
.el-tabs {
  width: 800px;
  height: 800px;
  position: absolute;
  left: 600px;
  top: 50px;
}
.keymap {
  width: 770px;
  height: 200px;
  margin: 0 auto;
  background-color: #f2f3f9;
}
.keyword {
  width: 200px;
  height: 50px;
  position: absolute;
  font-weight: bold;
}
.meetingchoosebox {
  width: 400px;
  height: 50px;
  line-height: 50px;
  margin-left: 10px;
}
.selectyear {
  width: 200px;
}
.rulebox {
  height: 100px;
  line-height: 100px;
  margin-left: -30px;
  margin-top: 0px;
}
#myChart {
  margin: 0 auto;
}
.paperdetailstitle {
  position: absolute;
  width: 200px;
  height: 40px;
  top: 20px;
  left: 20px;
  line-height: 20px;
  font-weight: bold;
  font-size: 30px;
}
.papertitle {
  position: absolute;
  width: 750px;
  height: 40px;
  top: 60px;
  left: 60px;
  line-height: 40px;
  text-align: left;
}
.papersource{
   position: absolute;
  width: 750px;
  height: 20px;
  top: 140px;
  left: 60px;
  line-height: 40px;
  text-align: left;
}
.paperyear{
    position: absolute;
  width: 750px;
  height: 20px;
  top: 160px;
  left: 60px;
  line-height: 40px;
  text-align: left;
}
.paperprecis {
  position: absolute;
  width: 750px;
  height: 500px;
  top: 180px;
  left: 60px;
  line-height: 40px;
  text-align: left;
}
.paperkeyword {
  position: absolute;
  width: 750px;
  height: 100px;
  bottom: 100px;
  left: 65px;
  text-align: left;
}
.paperurl{
  display: block;
  position: absolute;
  height: 40px;
  width: 100px;
  bottom: 50px;
  left: 65px;
  text-align: left;
  line-height: 40px;
  text-decoration: none;
  color: darkgrey;
}
.returnbutton{
  position:absolute;
  bottom: 20px;
  left: 400px;
}
</style>
