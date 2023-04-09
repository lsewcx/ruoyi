<template>
   <div>
      <div class="echart" id="mychart" :style="myChartStyle">
      </div>
      <div class="button">
         <el-button class="button" type="primary" @click="push1">返回 </el-button>
      </div>
   </div>
</template>

<script>
   import * as echarts from "echarts";
   var xiangsidu
   export default {
      data() {
         return {
            xiangsidu: 0,
            myChart: {},
            pieData: [
               {
                  value: this.getfileID(),
                  name: "开源代码占比"
               },
               {
                  value: 1 - this.getfileID(),
                  name: "个人代码占比"
               }
            ],
            pieName: [],
            myChartStyle: { float: "left", width: "100%", height: "400px" } //图表样式
         };
      },
      mounted() {
         this.initDate(); //数据初始化
         this.initEcharts();
         this.getfileID();
      },




      methods: {
         getfileID() {
            var xiangsidu = this.$route.query.formdata
            return xiangsidu
         },

         push1() {
            this.$router.push({ path: "/info1" });
         },
         initDate() {
            for (let i = 0; i < this.pieData.length; i++) {
               // this.xData[i] = i;
               // this.yData =this.xData[i]*this.xData[i];
               this.pieName[i] = this.pieData[i].name;
            }
            console.log(xiangsidu)
         },
         initEcharts() {
            // 饼图
            const option = {
               legend: {
                  // 图例
                  data: this.pieName,
                  right: "10%",
                  top: "30%",
                  orient: "vertical"
               },
               title: {
                  // 设置饼图标题，位置设为顶部居中
                  text: "代码相似度百分比",
                  top: "0%",
                  left: "center"
               },
               series: [
                  {
                     type: "pie",
                     label: {
                        show: true,
                        formatter: "{b} {d}%" // b代表名称，c代表对应值，d代表百分比
                     },
                     radius: ["40%", "70%"], //饼图半径
                     data: this.pieData
                  }
               ]
            };
            console.log(this.seriesData);
            const optionFree = {
               xAxis: {},
               yAxis: {},
               series: [
                  {
                     data: this.seriesData,
                     type: "line",
                     smooth: true
                  }
               ]
            };
            this.myChart = echarts.init(document.getElementById("mychart"));
            this.myChart.setOption(option);
            //随着屏幕大小调节图表
            window.addEventListener("resize", () => {
               this.myChart.resize();
            });
         }
      }
   };
</script>

<style scope>
   .button {
      text-align: center;
   }
</style>