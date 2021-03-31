<template>
  <el-container v-if="ifReload">
    <myheader
      :userName="this.Username"
      :loginStatus="this.loginStatus"
    ></myheader>
    <el-main>
      <sidebar
        @ResetPage="ResetPage"
        @GetPagePaperList="GetPagePaperList"
        @ChangeToFullResult="ChangeToFullResult"
      ></sidebar>
      <div class="returnbutton">
        <router-link to="/index"
          ><i
            class="fa fa-arrow-circle-left"
            aria-hidden="true"
            style="font-size: 80px; color: #133382"
          ></i
        ></router-link>
      </div>
      <el-dialog :visible.sync="dialogVisible" style="width:100%">
        <div style="height: 1000px;width:800px">
          <div class="paperdetailstitle">论文详情:</div>
          <div class="papertitle">
            <strong>论文题目:</strong>《{{ this.dialogDetail.title }}》
          </div>
          <div class="papersource">
            <strong>论文来源:</strong>{{ this.dialogDetail.source }}
          </div>
          <div class="paperyear">
            <strong>论文年份:</strong>{{ this.dialogDetail.publishYear }}
          </div>
          <div class="paperprecis">
            <strong>论文摘要: </strong>{{ this.dialogDetail._abstract }}
          </div>
          <div class="paperkeyword">
            <strong>论文关键词:</strong
            ><span
              v-for="(item, index) in this.dialogDetail.keywords"
              :key="index"
              style="margin-left: 10px"
              ><el-tag>{{ item.keyword }}</el-tag></span
            >
          </div>
          <a :href="this.dialogDetail.url" class="paperurl">原文链接</a>
        </div>
      </el-dialog>
      <el-tabs
        type="border-card"
        :stretch="true"
        style="position: relative"
        @tab-click="ShowPagenation"
      >
        <el-tab-pane >
          <span slot="label"><i class="el-icon-tickets"></i> 爬取结果显示</span>
          <el-collapse-transition>
            <ul style="list-style: none" v-show="this.showpaperList">
              <li
                v-for="(item, index) in paperDetailList"
                style="position: relative"
                :key="index"
                @click="showDetails(index)"
              >
                <mycard :paperDetail="item"> </mycard>
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
                  @click="deleteCard(index, item.id, 0)"
                ></i>
              </li></ul
          ></el-collapse-transition>
        </el-tab-pane>
        <el-tab-pane >
          <span slot="label"><i class="el-icon-s-order"></i> 关键词图谱</span>
          <el-card shadow="hover" class="keymap">
            <div
              class="keyword"
              style="font-size: 30px; top: 100px; left: 300px; color: red"
            >
              <el-tooltip :content="keywordList[0]" effect="light"
                ><span @click="showKeywordPapers(0)" class="keywordpointer">{{
                  keywordList[0]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="font-size: 20px; top: 30px; left: 350px; color: #d9c880"
            >
              <el-tooltip :content="keywordList[1]" effect="light">
                <span @click="showKeywordPapers(1)" class="keywordpointer">{{
                  keywordList[1]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="font-size: 25px; top: 60px; left: 100px; color: #b1a9c1"
            >
              <el-tooltip :content="keywordList[2]" effect="light"
                ><span @click="showKeywordPapers(2)" class="keywordpointer">{{
                  keywordList[2]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="
                font-size: 26px;
                top: 60px;
                left: 400px;
                color: #93aab8;
                width: 350px;
              "
            >
              <el-tooltip :content="keywordList[3]" effect="light">
                <span @click="showKeywordPapers(3)" class="keywordpointer">{{
                  keywordList[3]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="
                font-size: 15px;
                top: 90px;
                left: 150px;
                color: #81a027;
                width: 150px;
              "
              @click="showKeywordPapers(4)"
            >
              <el-tooltip :content="keywordList[4]" effect="light"
                ><span @click="showKeywordPapers(4)" class="keywordpointer">{{
                  keywordList[4]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="
                font-size: 29px;
                top: 120px;
                left: 50px;
                color: #ffa634;
                width: 250px;
              "
            >
              <el-tooltip :content="keywordList[5]" effect="light"
                ><span @click="showKeywordPapers(5)" class="keywordpointer">{{
                  keywordList[5]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="
                font-size: 20px;
                top: 90px;
                left: 550px;
                color: #fcb1c0;
                width: 250px;
              "
            >
              <el-tooltip :content="keywordList[6]" effect="light"
                ><span @click="showKeywordPapers(6)" class="keywordpointer">{{
                  keywordList[6]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="font-size: 23px; top: 130px; left: 500px; color: #adacab"
            >
              <el-tooltip :content="keywordList[7]" effect="light"
                ><span @click="showKeywordPapers(7)" class="keywordpointer">{{
                  keywordList[7]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="font-size: 26px; top: 160px; left: 400px; color: #d2ff4d"
            >
              <el-tooltip :content="keywordList[8]" effect="light"
                ><span @click="showKeywordPapers(8)" class="keywordpointer">{{
                  keywordList[8]
                }}</span></el-tooltip
              >
            </div>
            <div
              class="keyword"
              style="font-size: 35px; top: 160px; left: 100px; color: #8c0000"
            >
              <el-tooltip :content="keywordList[9]" effect="light">
                <span @click="showKeywordPapers(9)" class="keywordpointer">{{
                  keywordList[9]
                }}</span></el-tooltip
              >
            </div>
          </el-card>
          <div
            style="
              height: 40px;
              line-height: 50px;
              text-align: left;
              width: 400px;
              margin-left: 20px;
            "
          >
            请从以上选择你的热词:{{ this.currentKeyword }}
          </div>
          <ul style="list-style: none">
            <li
              v-for="(item, index) in keywordPaperList"
              style="position: relative"
              :key="index"
            >
              <mycard :paperDetail="item"> </mycard>
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
                @click="deleteCard(index, item.id, 1)"
              ></i>
            </li>
          </ul>
        </el-tab-pane>
        <el-tab-pane >
          <span slot="label"><i class="el-icon-data-line"></i> 热度走势</span>
          <el-form :model="statics">
            <div class="meetingchoosebox">
              <el-radio-group
                v-model="statics.meetingValue"
                @change="ChangeMeetingFrequency"
              >
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
              <el-button
                type="primary"
                style="margin-left: 30px; width: 120px"
                @click="RefreshFrequency"
                >确定</el-button
              >
            </div></el-form
          >
          <div id="myChart" :style="{ width: '800px', height: '600px' }"></div>
        </el-tab-pane>
      </el-tabs>
      <el-pagination
        small
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        layout="prev, pager, next"
        :total="this.papersNum"
        style="position: absolute; left: 50%"
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
        style="position: absolute; left: 50%"
        v-show="pagePagination2"
      >
      </el-pagination>
    </el-main>
    <el-footer>copyright &copy; 2021 puffer&&tars 结对作业二</el-footer>
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
      ifReload: true,
      Username: "",
      loginStatus: false,
      currentPage: 1,
      pagesize: 3,
      keywordPage: 1,
      keywordsize: 2,
      keywordPapersNum: 0,
      showpaperList: false,
      papersNum: 10,
      pagePagination1: true,
      pagePagination2: false,
      currentKeyword: "",
      paperDetailList: [],
      dialogVisible: false,
      dialogDetail: {},
      keywordList: [],
      keywordPaperList: [],
      statics: {
        meetingValue: "全部顶会",
        startYearOptions: {
          options: [
            {
              value: "1",
              label: "2000",
            },
            {
              value: "2",
              label: "2001",
            },
            {
              value: "3",
              label: "2002",
            },
            {
              value: "4",
              label: "2003",
            },
            {
              value: "5",
              label: "2004",
            },
            {
              value: "6",
              label: "2005",
            },
            {
              value: "7",
              label: "2006",
            },
            {
              value: "8",
              label: "2007",
            },
            {
              value: "9",
              label: "2008",
            },
            {
              value: "10",
              label: "2009",
            },
            {
              value: "11",
              label: "20010",
            },
            {
              value: "12",
              label: "2011",
            },
            {
              value: "13",
              label: "2012",
            },
            {
              value: "14",
              label: "2013",
            },
            {
              value: "15",
              label: "2014",
            },
            {
              value: "16",
              label: "2015",
            },
            {
              value: "17",
              label: "2016",
            },
            {
              value: "18",
              label: "2017",
            },
            {
              value: "19",
              label: "2018",
            },
            {
              value: "20",
              label: "2019",
            },
            {
              value: "21",
              label: "2020",
            },
          ],
          value: "1",
        },
        endYearOptions: {
          options: [
            {
              value: "1",
              label: "2000",
            },
            {
              value: "2",
              label: "2001",
            },
            {
              value: "3",
              label: "2002",
            },
            {
              value: "4",
              label: "2003",
            },
            {
              value: "5",
              label: "2004",
            },
            {
              value: "6",
              label: "2005",
            },
            {
              value: "7",
              label: "2006",
            },
            {
              value: "8",
              label: "2007",
            },
            {
              value: "9",
              label: "2008",
            },
            {
              value: "10",
              label: "2009",
            },
            {
              value: "11",
              label: "20010",
            },
            {
              value: "12",
              label: "2011",
            },
            {
              value: "13",
              label: "2012",
            },
            {
              value: "14",
              label: "2013",
            },
            {
              value: "15",
              label: "2014",
            },
            {
              value: "16",
              label: "2015",
            },
            {
              value: "17",
              label: "2016",
            },
            {
              value: "18",
              label: "2017",
            },
            {
              value: "19",
              label: "2018",
            },
            {
              value: "20",
              label: "2019",
            },
            {
              value: "21",
              label: "2020",
            },
          ],
          value: "21",
        },
      },
      frequencyDatas: [],
      newfrequencyDatas: [],
      frequencyKeywords: [],
    };
  },
  mounted() {
    this.drawLine();
    this.showpaperList = true;
    this.papersNum = parseInt(sessionStorage.getItem("papernum"));
    this.Username = sessionStorage.getItem("username");
    this.loginStatus = sessionStorage.getItem("loginstatus");
    this.GetPagePaperList(this.currentPage, this.pagesize, true);
    this.GetKeyword();
    this.ShowTotalFrequency();
  },
  watch: {
    // newfrequencyDatas: function (val, oldval) {
    //   this.drawLine();
    // },
    keywordList: function (val, oldval) {
      this.drawLine();
    },
    deep: true,
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let seriesData = this.newfrequencyDatas;
      let myChart = this.$echarts.init(document.getElementById("myChart"));
      myChart.resize();
      // 绘制图表
      myChart.setOption({
        title: { text: "近年热度走势对比" },
        legend: {
          show: true,
          top: "0%",
          left: "20%",
          data: this.frequencyKeywords,
        },
        grid: {
          bottom: "5%",
        },
        tooltip: {},
        xAxis: {
          data: [
            "2000",
            "2001",
            "2002",
            "2003",
            "2004",
            "2005",
            "2006",
            "2007",
            "2008",
            "2009",
            "2010",
            "2011",
            "2012",
            "2013",
            "2014",
            "2015",
            "2016",
            "2017",
            "2018",
            "2019",
            "2020",
          ].splice(
            this.statics.startYearOptions.value - 1,
            this.statics.endYearOptions.value -
              this.statics.startYearOptions.value +
              1
          ),
        },
        yAxis: {},
        series: seriesData.splice(
        this.statics.startYearOptions.value - 1,
        this.statics.endYearOptions.value -
          this.statics.startYearOptions.value +
          1
      ),
      });
    },
    ShowPagenation: function (tab, event) {//标签页切换时执行的操作
      if (tab.index == 0) {
        this.pagePagination1 = true;
        this.pagePagination2 = false;
      } else if (tab.index == 1) {
        this.pagePagination2 = true;
        this.pagePagination1 = false;
        this.GetKeyword();
        this.keywordPaperList.length = 0;
        this.currentKeyword = "";
      } else {
        this.pagePagination2 = false;
        this.pagePagination1 = false;
        this.ShowTotalFrequency();
        this.drawLine();
      }
    },
    deleteCard: function (value, id, index) {//根据点击的事件判断并删除卡片
      if (index == 0) {
        this.paperDetailList.splice(value, 1);
      } else index == 1;
      {
        this.keywordPaperList.splice(value, 1);
      }
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/delete", {
          params: {
            paperId: id,
          },
        })
        .then(function (response) {
          _this.$message({
            message: "删除成功",
            type: "success",
          });
          _this.refresh();
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("删除失败");
        });
    },
    GetPagePaperList: function (topagenum, topagesize, value) {//获取当页论文列表
      let _this = this;
      let searchContent = sessionStorage.getItem("searchContent");
      if (searchContent == "") {
        if (value == true) {
          this.$axios
            .get(_this.$api.globalUrl + "/userPaper/contentsPage", {
              params: {
                pageNum: topagenum,
                pageSize: topagesize,
              },
            })
            .then(function (response) {
              _this.paperDetailList = response.data.data;
            })
            .catch(function (error) {
              console.log(error);
              _this.$message.error("用户论文列表加载失败");
            });
        } else {
          this.$axios
            .get(_this.$api.globalUrl + "/userPaper/keyword", {
              params: {
                keyword: _this.currentKeyword,
                pageNum: topagenum,
                pageSize: topagesize,
              },
            })
            .then(function (response) {
              // console.log(response);
              _this.keywordPaperList = response.data.data;
              // console.log(_this.keywordPaperList);
            })
            .catch(function (error) {
              console.log(error);
              _this.$message.error("用户论文列表加载失败");
            });
        }
      } else {
        this.GetSearchResultPaperList(searchContent);
      }
    },
    handleCurrentChange: function (currentpage) {//分页项切换时执行刷新论文列表
      this.currentPage = currentpage;
      this.GetPagePaperList(this.currentPage, this.pagesize, true);
    },
    handlekeywordChange: function (keywordpage) {//热词页切换分页时执行刷新论文列表
      this.keywordPage = keywordpage;
      this.GetPagePaperList(this.keywordPage, this.keywordsize, false);
    },
    showDetails: function (value) {//展示论文详情
      this.dialogVisible = true;
      this.dialogDetail = this.paperDetailList[value];
    },
    showKeywordPaperDetails: function (value) {//展示关键词论文详情
      this.dialogVisible = true;
      this.dialogDetail = this.keywordPaperList[value];
    },
    showKeywordPapers: function (value) {//根据关键词刷新论文显示列表
      let _this = this;
      _this.currentKeyword = _this.keywordList[value];
      this.GetPagePaperList(1, this.keywordsize, false);
      this.$axios
        .get(_this.$api.globalUrl + "/userPaper/countByKeyword", {
          params: {
            keyword: _this.keywordList[value],
          },
        })
        .then(function (response) {
          // console.log(response);
          _this.keywordPapersNum = response.data.data;
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("关键词论文列表加载失败");
        });
    },
    getpagenum: function () {//获取论文数量
      this.paperNum = sessionStorage.getItem("papernum");
    },
    GetKeyword: function () {
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/keyword/userTopTen", {
          params: {},
        })
        .then(function (response) {
          // console.log(response);
          _this.keywordList = response.data.data;
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("获取关键词失败");
        });
    },
    refresh: function () //刷新界面
    {
      this.ifReload = false;
      this.ifReload = true;
    },
    ShowTotalFrequency: function () {//获取热度走势并渲染页面
      this.frequencyKeywords.length = 0;
      this.frequencyDatas.length = 0;
      this.newfrequencyDatas.length = 0;
      let _this = this;
      this.$axios
        .get(_this.$api.globalUrl + "/keywordWithFrequency/all", {
          params: {},
        })
        .then(function (response) {
          // console.log(response);
          _this.frequencyDatas = response.data.data;
          let index = 0;
          _this.frequencyDatas.forEach((element) => {
            let singleArray = [];
            for (let i = 0; i < 21; i++) {
              singleArray[i] = "0";
            }
            element.forEach((ele) => {
              let year = parseInt(ele.publishYear) - 2000;
              singleArray[year] = ele.frequency.toString();
            });
            _this.frequencyKeywords.push(element[0].keyword);
            if (index == 4) {
              _this.frequencyKeywords.push("");
            }
            _this.newfrequencyDatas.push({
              name: element[0].keyword,
              type: "line",
              data: singleArray,
            });
          });
          _this.drawLine();
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("热度走势加载失败");
        });
    },
    GetSearchResultPaperList: function (value) {//通过侧边栏结果刷新论文页面
      let _this = this;
      let content = sessionStorage.getItem("searchContent");
      this.$axios
        .get(_this.$api.globalUrl + "/paper/searchPage", {
          params: {
            content: content,
            pageNum: _this.pageNum,
            pageSize: _this.pagesize,
          },
        })
        .then(function (response) {
          // console.log(response);
          _this.paperDetailList = response.data.data;
        })
        .catch(function (error) {
          console.log(error);
          _this.$message.error("结果论文列表加载失败");
        });
      sessionStorage.setItem("searchContent", "");
    },
    RefreshFrequency: function () {//通过筛选年份刷新热度走势
      if (
        parseInt(this.statics.startYearOptions.value) >
        parseInt(this.statics.endYearOptions.value)
      ) {
        this.$message({
          message: "年份范围有误，请重新选择",
          type: "warning",
        });
        this.statics.startYearOptions.value = "1";
        this.statics.endYearOptions.value = "21";
      } else this.drawLine();
    },
    ChangeMeetingFrequency: function (value) {//根据议会变化刷新热度走势
      this.frequencyKeywords.length = 0;
      this.frequencyDatas.length = 0;
      this.newfrequencyDatas.length = 0;
      let _this = this;
      if (value != "全部顶会") {
        this.$axios
          .get(_this.$api.globalUrl + "/keywordWithFrequency/source", {
            params: {
              source: value,
            },
          })
          .then(function (response) {
            // console.log(response);
            _this.frequencyDatas = response.data.data;
            let index = 0;
            _this.frequencyDatas.forEach((element) => {
              let singleArray = [];
              for (let i = 0; i < 21; i++) {
                singleArray[i] = "0";
              }
              // console.log(element);
              element.forEach((ele) => {
                let year = parseInt(ele.publishYear) - 2000;
                singleArray[year] = ele.frequency.toString();
              });
              _this.frequencyKeywords.push(element[0].keyword);
              if (index == 4) {
                _this.frequencyKeywords.push("");
              }
              _this.newfrequencyDatas.push({
                name: element[0].keyword,
                type: "line",
                data: singleArray,
              });
            });
            _this.drawLine();
          })
          .catch(function (error) {
            console.log(error);
            _this.$message.error("会议切换失败");
          });
      } else {
        this.ShowTotalFrequency();
      }
    },
    ResetPage: function () {//更新分页项
      this.papersNum = parseInt(sessionStorage.getItem("papernum"));
    },
    ChangeToFullResult() {
      this.GetPagePaperList(1, 3, true);
    },
  },
};
</script>

<style scoped>
@import "../css/CrawResult.css";
</style>
