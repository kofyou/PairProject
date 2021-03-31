
var Main = {
    data() {
        return {
            restaurants: [],
            state: '',
            timeout: null
        };
    },
    methods: {
        querySearchAsync(queryString, cb) {
            var restaurants = this.restaurants;
            var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                cb(results);
            }, 3000 * Math.random());
        },
        createStateFilter(queryString) {
            return (state) => {
                return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
            };
        },
        handleSelect(item) {
            console.log(item);
        }
    },
    mounted() {
        this.restaurants = this.loadAll();
    }
};
var Ctor = Vue.extend(Main);
new Ctor().$mount('#app');
function search(){
    var i=document.getElementById("title");
    /*var paperview=document.getElementById("paperview");
    var paperanalysis=document.getElementById("paperanalysis");
    paperview.setAttribute("href","../index/Paperview");
    paperanalysis.setAttribute("href","..public/index/Paperanalysis");*/
    window.location="../public/index/Paperview?data="+i.value+"+1";
}