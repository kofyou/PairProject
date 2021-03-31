<template>
  <div>
    <!-- search -->
        <div class="w-search" style="margin-top:20px;">
        <div style="width:300px;margin-left:0px;">
            <el-input
            placeholder="请输入内容"
            v-model="input"
            clearable
            @keyup.enter.native="convert()"
            style="width:300px;">
            </el-input>
        </div>

        <el-button type="primary" icon="el-icon-search" style="margin-left:20px;" @click="convert()" id="searchBtn">搜索</el-button>
        </div>

        <!-- table -->
        <el-table
            :data="tableData"
            :default-sort = "{prop: 'date', order: 'descending'}"
            style="width: 100%; margin-top:20px;">
            <el-table-column type="expand">
            <template slot-scope="props">
                <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="摘要">
                    <span>{{ props.row.abstract }}</span>
                </el-form-item>
                </el-form>
            </template>
            </el-table-column>
            <el-table-column
            label="论文标题"
            sortable
            prop="name">
            </el-table-column>
            <el-table-column
            label="日期"
            sortable
            prop="date">
            </el-table-column>
            <el-table-column
            label="关键词"
            prop="keyword">
            </el-table-column>
            <el-table-column
            label="会议"
            sortable
            prop="meeting">
            </el-table-column>

            <el-table-column
                fixed="right"
                label="操作"
                width="120">
                <template slot-scope="scope">
                    <el-button
                    @click.native.prevent="deleteRow(scope.$index, tableDataAll)"
                    type="text"
                    size="small">
                    移除
                    </el-button>
                </template>
            </el-table-column>

        </el-table>

        <div class="block p-center">
          <el-pagination
            layout="prev, pager, next"
            :total="this.tableDataAll.length"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :current-size="pageSize">
          </el-pagination>
        </div>
    </div>
</template>

<style scoped>
  .w-search{
    display: flex;
    justify-content: center;
  }
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .p-center{
      display: flex;
      justify-content: center;
      margin-top: 20px;
  }
</style>

<script>
  import Vue from 'vue'
  import $ from 'jquery'
  import axios from 'axios'

  export default {
    data() {
      return {
        //search
        input: '',
        //axios result
        res:{},

        //table
        tableData:[],
        tableDataAll:[],
        total:0,
        currentPage:1,
        pageSize:10
      }
    },
    mounted:function(){
        this.convert();
    },
    methods: {
        handleCurrentChange:function(currentPage){
            console.log("handleCurrentChange()\n");
            this.tableData=[];
            this.currentPage=currentPage;
            console.log("currentPage="+currentPage+'\n');
            var i;
            for(var i=(currentPage-1)*10,j=0;j<10 && this.tableDataAll.length!=0 && (i+j)<=this.tableDataAll.length-1;j++,i++){
                
                console.log("i="+i+'\n');
                this.tableData.push(this.tableDataAll[i]);
                console.log("this.tableDataAll[i]"+JSON.stringify(this.tableDataAll[i])+'\n');
            }
            if(this.tableDataAll.length==0){
                this.tableData=[];
            }
        },
        deleteRow:function(index, rows){
            console.log("deleteRow()\n");
            rows.splice(index, 1);
            this.tableData.splice(index%10,1);
        },
      //search
      //取消异步，以免按两次键
      convert:async function(){
          console.log("convert()\n");
        //测试post传入字段
        //使前后端数据类型一致
        let search=JSON.stringify(this.input);
        console.log("this.input="+this.input+'\n');
        console.log("this.search="+search+'\n');

        //axios
        await axios.post('/api/search.php',search).then(res=>{
          console.log('res=>',res.data);
          this.res=res;
          // console.log('this.res=>',this.res.data);
        })

        //转换成数组
        let snsArr;
        snsArr=[];
        //判断是否为空，非空则split()
        if(this.res.data){
          // snsArr=this.res.data.split(/[(\n)\n]+/);
          snsArr=this.res.data.split("***;%4");
        }
        //删除空项
        for(var i=0;i<snsArr.length;i++){
          if(!snsArr[i]){
            console.log("delete:"+i+'\n');
            snsArr.splice(i,5);
          }
        }

        for(var i=0;i<snsArr.length;i++){
          console.log("snsArr["+i+"]="+snsArr[i]+'\n');
        }

        // //删除空项
        // snsArr.forEach((item,index)=>{
        //       if(!item){
        //           snsArr.splice(index,1);
        //       }
        // })
        
        this.tableData.length=0;
        this.tableDataAll.length=0;
        this.handleCurrentChange(1);

        //需要删除空项
        for(var i=0;i<snsArr.length;i+=5){
            let dateFormate=snsArr[i].split(' ');
            let nameFormate=snsArr[i+1].replace("\\(\\ell ^{0}\\)-","");
            let keywordFormate=snsArr[i+2].replace('[','').replace(']','');
            this.tableDataAll.push({date:dateFormate[0],
                            name:nameFormate,
                            keyword:keywordFormate,
                            meeting:snsArr[i+3],
                            abstract:snsArr[i+4]});
        }
        this.handleCurrentChange(1);
      }
    }
  }
</script>