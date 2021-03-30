<template>

    <el-container>
    <myheader></myheader>
    <el-main>
      <sidebar></sidebar>
      <div class="returnbutton">
        <router-link to="/index"><i class="fa fa-arrow-circle-left" aria-hidden="true" style="font-size:80px;color: #133382"></i></router-link>
      </div>
      <el-dialog :visible.sync="dialogVisible" class="detaildialog">
            <div style="height: 700px">
              <div class="paperdetailstitle">论文详情:</div>
              <div class="papertitle"><strong>论文题目:</strong>《{{this.dialogDetail.title}}》</div>
              <div class="papersource"><strong>论文来源:</strong>{{this.dialogDetail.source}}</div>
              <div class="paperyear"><strong>论文年份:</strong>{{this.dialogDetail.publishYear}}</div>
              <div class="paperprecis"><strong>论文摘要: </strong>{{this.dialogDetail._abstract}}</div>
              <div class="paperkeyword"><strong>论文关键词:</strong><span v-for="(item,index) in this.dialogDetail.keywords" :key="index">[{{item.keyword}}]</span></div>
              <a :href="this.dialogDetail.url" class="paperurl">原文链接</a>
            </div>
          </el-dialog>
      <el-tabs type="border-card" stretch="true" style="position: relative" @tab-click="ShowPagenation">
        <el-tab-pane style="">
          <span slot="label"><i class="el-icon-date"></i> 爬取结果显示</span>
          <el-collapse-transition>
            <ul style="list-style: none" v-show="this.showpaperList">
            <li
              v-for="(item, index) in paperDetailList.slice(
                (currentPage - 1) * pagesize,
                currentPage * pagesize
              )"
              style="position: relative"
              :key="index"
              @click="showDetails(index)"
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
          </ul></el-collapse-transition>
        </el-tab-pane >
        <el-tab-pane label="关键词图谱">
          <el-card shadow="hover" class="keymap">
            <div
              class="keyword"
              style="font-size: 30px; top: 100px; left: 300px; color: red"

            >
              <el-tooltip :content="keywordList[0]" effect="light"><span @click="showKeywordPapers(0)" class="keywordpointer">{{keywordList[0]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 20px; top: 30px; left: 350px; color: #d9c880"
            >
              <el-tooltip :content="keywordList[1]" effect="light"> <span @click="showKeywordPapers(1)" class="keywordpointer">{{keywordList[1]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 25px; top: 60px; left: 100px; color: #b1a9c1"
            >
               <el-tooltip :content="keywordList[2]" effect="light"><span @click="showKeywordPapers(2)" class="keywordpointer">{{keywordList[2]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 26px; top: 60px; left: 400px; color: #93aab8;width:350px"
            >
             <el-tooltip :content="keywordList[3]" effect="light"> <span @click="showKeywordPapers(3)" class="keywordpointer">{{keywordList[3]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 15px; top: 90px; left: 150px; color: #81a027;width:150px"
              @click="showKeywordPapers(4)"
            >
               <el-tooltip :content="keywordList[4]" effect="light"><span @click="showKeywordPapers(4)" class="keywordpointer">{{keywordList[4]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 29px; top: 120px; left: 50px; color: #ffa634;width:250px"
            >
               <el-tooltip :content="keywordList[5]" effect="light"><span @click="showKeywordPapers(5)" class="keywordpointer">{{keywordList[5]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 20px; top: 90px; left: 550px; color: #fcb1c0;width:250px"
            >
              <el-tooltip :content="keywordList[6]" effect="light"><span @click="showKeywordPapers(6)" class="keywordpointer">{{keywordList[6]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 23px; top: 130px; left: 500px; color: #adacab"
            >
              <el-tooltip :content="keywordList[7]" effect="light"><span @click="showKeywordPapers(7)" class="keywordpointer">{{keywordList[7]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 26px; top: 160px; left: 400px; color: #d2ff4d"
            >
               <el-tooltip :content="keywordList[8]" effect="light"><span @click="showKeywordPapers(8)" class="keywordpointer">{{keywordList[8]}}</span></el-tooltip>
            </div>
            <div
              class="keyword"
              style="font-size: 35px; top: 160px; left: 100px; color: #8c0000"
            >
          <el-tooltip :content="keywordList[9]" effect="light"> <span @click="showKeywordPapers(9)" class="keywordpointer">{{keywordList[9]}}</span></el-tooltip>
            </div>
          </el-card>
          <div
            style="
              height: 40px;
              line-height: 50px;
              text-align: left;
              width: 200px;
              margin-left: 20px;
            "
          >
            请从以上选择你的热词:{{this.currentKeyword}}
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
                @click="deleteCard(index,item.id,1)"
              ></i>
            </li>
          </ul>
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
          <div id="myChart" :style="{ width: '600px', height: '600px' }"></div>
        </el-tab-pane>
      </el-tabs>
         <el-pagination
            small
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="pagesize"
            layout="prev, pager, next"
            :total="100"
            style="position:absolute;left:50%"
            v-show="pagePagination1"
          >
          </el-pagination>
           <el-pagination
            small
            @current-change="handlekeywordChange"
            :current-page="keywordPage"
            :page-size="keywordsize"
            layout="prev, pager, next"
            :total="this.keywordPapersNum"
            style="position:absolute;left:50%"
            v-show="pagePagination2"
          >
          </el-pagination>
    </el-main>
    <el-footer>Footer</el-footer>
    </el-container>
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
      keywordPapersNum:0,
      showpaperList:false,
      paperNum:10,
      pagePagination1:true,
      pagePagination2:false,
      currentKeyword:"",
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
      },
      keywordList:[

      ],
      keywordPaperList:[
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
      xdata:["2011","2022"],
    };
  },
  mounted() {
    this.drawLine();
    this.showpaperList=true;
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("myChart"));
      myChart.resize();
      // 绘制图表
      myChart.setOption({
        title: { text: "近年热度走势对比" },
        legend:{data:['销量','数据']},
        tooltip: {},
        xAxis: {
          data: this.xdata,
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "line",
            data: [5, 20, 36, 10, 10, 20],
          },
          {
            name: "数据",
            type: "line",
            data: [20, 5, 36, 10, 10, 20],
          },
        ],
      });
    },
    ShowPagenation:function (tab,event) {
      if(tab.index==0)
      {
        this.pagePagination1=true;
        this.pagePagination2=false;

      }
      else if(tab.index==1)
      {
        this.pagePagination2=true;
        this.pagePagination1=false;
        this.GetKeyword();
        this.keywordPapersNum=this.keywordPaperList.length;
      }
      else{
        this.pagePagination2=false;
        this.pagePagination1=false;
      }
    },
    deleteCard: function (value,id,index) {
      if(index==0)
      {this.paperDetailList.splice(value, 1);}
      else(index==1)
      {
        this.keywordPaperList.splice(value,1);
      }
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/delete", {
          params: {
            paperId: id,
          },
        })
        .then(function (response) {
          console.log(response);
           _this.$message({
          message:'删除成功',
          type:'success'
        });
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    handleCurrentChange: function (currentpage) {
      this.currentPage = currentpage;
      let _this=this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/contentsPage", {
          params: {
           pageNum:_this.currentPage,
          },
        })
        .then(function (response) {
          console.log(response);

        })
        .catch(function (error) {
          console.log(error);
        });
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
    showKeywordPapers:function (value) {
       let _this=this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/keyword", {
          params: {
            keyword:_this.keywordList[value],
            pageNum:_this.keywordPage,
            pageSize:_this.keywordsize
          },
        })
        .then(function (response) {
          _this.keywordPaperList=response.data.data;
          _this.keywordPapersNum=response.data.data.length
        })
        .catch(function (error) {
          console.log(error);
        });
      },
    getpagenum:function () {
      this.paperNum=sessionStorage.getItem('papernum');
      },
    GetKeyword:function(){
      let _this=this;
      this.$axios
        .get(_this.$api.globalUrl + "/keyword/userTopTen", {
          params: {
          },
        })
        .then(function (response) {
          console.log(response);
         _this.keywordList=response.data.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    }
  },

};
</script>

<style scoped>
.el-footer {
  background-color: #133382;
  color: #d3dce6;
  text-align: center;
  line-height: 80px;
  height: 80px !important;
}
::-webkit-scrollbar-track {
  background: rgb(255, 255, 255);
  border-radius: 8px;
}
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
  width: 300px;
  height: 50px;
  position: absolute;
  font-weight: bold;
}
.keywordpointer
{
   cursor: pointer;
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
  line-height: 20px;
  text-align: left;
}
.papersource{
   position: absolute;
  width: 750px;
  height: 20px;
  top: 120px;
  left: 60px;
  line-height: 20px;
  text-align: left;
}
.paperyear{
    position: absolute;
  width: 750px;
  height: 20px;
  top: 140px;
  left: 60px;
  line-height: 20px;
  text-align: left;
}
.paperprecis {
  position: absolute;
  width: 750px;
  height: 500px;
  top: 160px;
  left: 60px;
  line-height: 40px;
  text-align: left;
}
.paperkeyword {
  position: absolute;
  width: 750px;
  height: 100px;
  bottom: 70px;
  left: 60px;
  line-height: 20px;
  text-align: left;
}
.paperurl{
  display: block;
  position: absolute;
  height: 40px;
  width: 100px;
  top: 10px;
  right: 65px;
  text-align: left;
  line-height: 40px;
  text-decoration: none;
  color: darkgrey;
}
.returnbutton{
  position:absolute;
  bottom: -50px;
  left: 450px;
}
</style>
