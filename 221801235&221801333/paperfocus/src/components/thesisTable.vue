<template>
    <div>
        <!-- search -->
        <div class="w-search">
          <div style="width:500px;margin-left:210px;">
            <el-input
              placeholder="请输入内容"
              v-model="input"
              clearable
              @keyup.enter.native="convert()"
              style="width:200px;">
            </el-input>
          </div>

          <el-button type="primary" icon="el-icon-search" style="margin-left:20px;" @click="convert()">搜索</el-button>
        </div>

        <!-- table -->
        <el-button @click="resetDateFilter">清除日期过滤器</el-button>
        <el-button @click="clearFilter">清除所有过滤器</el-button>
        <el-table
            ref="filterTable"
            :data="tableData"
            style="width: 100%">
            <el-table-column
            prop="date"
            label="日期"
            sortable
            width="180"
            column-key="date"
            :filters="[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]"
            :filter-method="filterHandler"
            >
            </el-table-column>
            <el-table-column
            prop="name"
            label="论文标题"
            width="180">
            </el-table-column>
            <el-table-column
            prop="keyword"
            label="关键词"
            :formatter="formatter">
            </el-table-column>
            <el-table-column
            prop="tag"
            label="标签"
            width="100"
            :filters="[{ text: 'nlp', value: 'nlp' }, { text: 'cv', value: 'cv' }]"
            :filter-method="filterTag"
            filter-placement="bottom-end">
            <template slot-scope="scope">
                <el-tag
                :type="scope.row.tag === 'nlp' ? 'primary' : 'success'"
                disable-transitions>{{scope.row.tag}}</el-tag>
            </template>
            </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </div>
    </div>
</template>

<style scoped>
.w-search{
  display: flex;
  justify-content: space-around;
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
        tableData:[{}]
        // tableData: [{
        //   date: '2021-05-02',
        //   name: '计算机视觉和深度学习',
        //   address: 'ICCV',
        //   tag: 'nlp'
        // }, {
        //   date: '2021-05-04',
        //   name: '自然语言处理',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-01',
        //   name: '计算机视觉和深度学习',
        //   address: 'ICCV',
        //   tag: 'nlp'
        // }, {
        //   date: '2021-05-03',
        //   name: '自然语言处理',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '计算机视觉和深度学习',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '自然语言处理',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '计算机视觉和深度学习',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '自然语言处理',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '计算机视觉和深度学习',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '自然语言处理',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }, {
        //   date: '2021-05-03',
        //   name: '计算机视觉和深度学习',
        //   address: 'ICCV',
        //   tag: 'cv'
        // }]
      }
    },
    mounted:function(){
        this.convert();
    },
    methods: {
      //search
      convert:function(){
        //测试post传入字段
        let search=this.input;
        console.log("this.input="+this.input+'\n');

        //axios
        axios.post('/api/search.php',search).then(res=>{
          // console.log('res=>',res.data);
          this.res=res;
          // console.log('this.res=>',this.res.data);
        })

        //转换成数组
        let snsArr;
        //判断是否为空，非空则split()
        if(this.res.data){
          // snsArr=this.res.data.split(/[(\n)\n]+/);
          snsArr=this.res.data.split("*");
        }
        //需要删除空项
        for(var i=0;i<snsArr.length;i++){
          if(!snsArr[i]){
            console.log("delete:"+i+'\n');
            snsArr.splice(i,3);
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
        
        //需要删除空项
        for(var i=0;i<snsArr.length;i+=4){
          this.tableData.push({date:snsArr[i],
                            name:snsArr[i+1],
                            keyword:snsArr[i+2],
                            tag:snsArr[i+3]});
        }
        
      },

      //table
      resetDateFilter() {
        this.$refs.filterTable.clearFilter('date');
      },
      clearFilter() {
        this.$refs.filterTable.clearFilter();
      },
      formatter(row, column) {
        return row.keyword;
      },
      filterTag(value, row) {
        return row.tag === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      }
    }
  }
</script>