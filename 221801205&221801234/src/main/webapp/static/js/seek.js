let url = turl+'download';

let vm = new Vue({
    el: ".container",
    data: {
        meeting: "ICCV",
        year: 2020,
        pwd: "",
        count: 100,
        list: [],
    },

    methods: {
        getInfo() {
            this.tp = this.list;
            var that = this;

            if(this.count<0||this.count>400)
                alert("次数必须0~400");
            let data = JSON.stringify({
                meeting: this.meeting,
                year: this.year,
                pwd: this.pwd,
                count: this.count,
            });

            if(this.pwd==''){
                alert("密码不为空");
            } else{
                axios.post(url,data,{
                    responseType:'blob',
                }).then(function (response) {
                    alert(url);

                    alert(response.data);
                    // that.list = response.data;

                    const fileName = response.headers['content-disposition'].match(
                        /filename=(.*)/
                    )[1];
                    //alert(fileName);
                    // 将二进制流转为blob
                    const blob = new Blob([response.data], { type: 'application/octet-stream' });
                    if (typeof window.navigator.msSaveBlob !== 'undefined') {
                        // 兼容IE，window.navigator.msSaveBlob：以本地方式保存文件
                        window.navigator.msSaveBlob(blob, decodeURI(fileName))
                    } else {

                        // 创建新的URL并指向File对象或者Blob对象的地址
                        const blobURL = window.URL.createObjectURL(blob);
                        // 创建a标签，用于跳转至下载链接
                        const tempLink = document.createElement('a');
                        tempLink.style.display = 'none';
                        tempLink.href = blobURL;
                        tempLink.setAttribute('download', decodeURI(fileName));
                        // 兼容：某些浏览器不支持HTML5的download属性
                        if (typeof tempLink.download === 'undefined') {
                            tempLink.setAttribute('target', '_blank')
                        }
                        // 挂载a标签
                        document.body.appendChild(tempLink);
                        tempLink.click();
                        document.body.removeChild(tempLink);
                        // 释放blob URL地址
                        window.URL.revokeObjectURL(blobURL);
                    }
                }).catch(function (error) {
                    console.log(error);
                });
            }

        },
    },
});
