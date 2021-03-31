<template>
    <div>
        <el-menu
            style="user-select:none;"
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1" @click="jumpSearch()">论文检索</el-menu-item>
            <el-menu-item index="2" @click="jumpTrend()">热度走势</el-menu-item>
            <el-menu-item index="3" @click="jumpKeyword()">热门领域</el-menu-item>
            <el-menu-item index="4" @click="jumpLogin()">个人中心</el-menu-item>
            <!-- <el-button type="warning" style="float:right; margin:10px 20px 0 0;">{{msg}}</el-button> -->
        </el-menu>
        <router-view></router-view>
        <!-- <v-thesissearch></v-thesissearch>
        <v-tableTrend></v-tableTrend> -->
    </div>
</template>


<script>
    import thesisSearch from '@/views/thesisSearch'
    import tableTrend from '@/components/tableTrend'

    export default {
        components:{
            'v-thesissearch':thesisSearch,
            'v-tabletrend':tableTrend
        },
        data() {
        return {
            activeIndex: '1',
            msg: '退出登录'
        };
        },
        methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        jumpSearch:function(){
            this.activeIndex='1';
            this.$router.push('/thesisSearch');
        },
        jumpTrend:function(){
            this.activeIndex='2';
            this.$router.push('/tableHistogram');
        },
        jumpKeyword:function(){
            this.activeIndex='3';
            this.$router.push('/tablePie');
        },
        jumpLogin:function(){
            this.activeIndex='4';
        }
        }
    }
</script>