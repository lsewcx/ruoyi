<template>
    <div>
        <el-form ref="basicInformation" :model="basicInformation" label-width="100px">
            <div style="border-left: 4px solid #39a9ff;padding-left: 8px;line-height: initial;font-size: initial;">
                <h4>开发工资</h4>
                <ul>总工时{{this.add}}</ul>
                <ul>总成本{{this.zuizhongjiage0}}</ul>
            </div>
            <el-row :gutter="20" v-for="(item,index) in basicInformation.purPeoList" :key="index">
                <el-row :gutter="20">
                    <el-col :span="5">
                        <el-form-item label="开发工程师" :prop="'purPeoList.'+index+'.xm'">
                            <select v-model="goodsId"
                                style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px">
                                <option value="">请选择开发工程师</option>
                                <option v-for="item in kindList" v-bind:value="item.id" v-text="item.name">
                                </option>
                            </select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时占比" :prop="'purPeoList.'+index+'.jtcygxdm'">
                            <el-input v-model="item.jtcygxdm"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="this.gs0"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工资" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="gongzi"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="5">
                        <el-form-item label="开发工程师" :prop="'purPeoList.'+index+'.xm'">
                            <select v-model="goodsId1"
                                style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px">
                                <option value="">请选择开发工程师</option>
                                <option v-for="item in kindList" v-bind:value="item.id" v-text="item.name"></option>
                            </select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时占比" :prop="'purPeoList.'+index+'.jtcygxdm1'">
                            <el-input v-model="item.jtcygxdm1"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="this.gs1"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工资" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="gongzi1"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row><el-row :gutter="20">
                    <el-col :span="5">
                        <el-form-item label="开发工程师" :prop="'purPeoList.'+index+'.xm'">
                            <select v-model="goodsId2"
                                style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px">
                                <option value="">请选择开发工程师</option>
                                <option v-for="item in kindList" v-bind:value="item.id" v-text="item.name"></option>
                            </select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时占比" :prop="'purPeoList.'+index+'.jtcygxdm2'">
                            <el-input v-model="item.jtcygxdm2"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="this.gs2"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工资" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="gongzi2"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="5">
                        <el-form-item label="开发工程师" :prop="'purPeoList.'+index+'.xm'">
                            <select v-model="goodsId3"
                                style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px">
                                <option value="">请选择开发工程师</option>
                                <option v-for="item in kindList" v-bind:value="item.id" v-text="item.name"></option>
                            </select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时占比" :prop="'purPeoList.'+index+'.jtcygxdm3'">
                            <el-input v-model="item.jtcygxdm3"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="this.gs3"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工资" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="gongzi3"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="5">
                        <el-form-item label="开发工程师" :prop="'purPeoList.'+index+'.xm'">
                            <select v-model="goodsId4"
                                style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px">
                                <option value="">请选择开发工程师</option>
                                <option v-for="item in kindList" v-bind:value="item.id" v-text="item.name"></option>
                            </select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时占比" :prop="'purPeoList.'+index+'.jtcygxdm4'">
                            <el-input v-model="item.jtcygxdm4"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="this.gs4"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工资" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="gongzi4"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="5">
                        <el-form-item label="开发工程师" :prop="'purPeoList.'+index+'.xm'">
                            <select v-model="goodsId5"
                                style="width:200px;height: 30px;margin-top: 5px;margin-bottom: 5px;margin-left: 10px">
                                <option value="">请选择开发工程师</option>
                                <option v-for="item in kindList" v-bind:value="item.id" v-text="item.name"></option>
                            </select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时占比" :prop="'purPeoList.'+index+'.jtcygxdm5'">
                            <el-input v-model="item.jtcygxdm5"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工时" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="this.gs5"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="5">
                        <el-form-item label="工资" :prop="'purPeoList.'+index+'.hklxdm'">
                            <el-input v-model="gongzi5"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>




                <el-form-item>
                    <el-button type="danger" v-if="basicInformation.purPeoList.length >1" size="small" plain
                        @click="removeRow(index)">
                        删除</el-button>
                </el-form-item>
            </el-row>
            <el-row :gutter="20">
                <el-col :span="6" :push="18">
                    <el-form-item>
                        <el-button size="small" @click="addBuyerInformation">新增开发工程师信息</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="20" style="margin-top:50px;">
                <el-col :span="6" :push="18">
                    <el-form-item label-width="0">
                        <!-- <el-button type="success" @click="lastStepHandler" style="width:100px;">上一步</el-button> -->
                        <el-button type="success" @click="submitHandler" style="width:100px;">保存</el-button>
                        <el-button type="success" style="width:100px;" @click="submitHandlerAndNextStep">下一步</el-button>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>



    </div>
</template>



<script>
    import { ref } from 'vue'
    import { ElMessage, ElMessageBox } from 'element-plus'
    var jiageyinizi
    var gs
    var gs12
    var gs22
    var gs32
    var gs42
    var gs52
    var gz
    var gz1
    var gz2
    var gz3
    var gz4
    var gz5
    var zuizhongjiage
    export default {
        name: 'gcs',
        data() {
            return {
                param: {
                    rows: 0,
                    xiangsidu: 0,
                    jiage: 0,
                    jiageyinzi: 0,
                },
                goodsId: 1,
                goodsId1: 2,
                goodsId2: 3,
                goodsId3: 4,
                goodsId4: 5,
                goodsId5: 6,
                kindList: [{ name: "前端工程师", id: 1 }, { name: "UI设计师", id: 2 }, { name: "软件开发师", id: 3 }, { name: "测试工程师", id: 4 }, { name: "系统架构师", id: 5 }, { name: "大数据分析师", id: 6 }],
                basicInformation: {
                    purPeoList: [
                        {
                            xm: "",
                            jtcygxdm: "5%",
                            jtcygxdm1: "10%",
                            jtcygxdm2: "10%",
                            jtcygxdm3: "20%",
                            jtcygxdm4: "15%",
                            jtcygxdm5: "5%",
                            hklxdm: "",
                            zjlxdm: "",
                            zjhm: "",
                            sfgfrdm: "",
                            hjdjrq: "",
                            wsqrdm: "",
                            lxdh: "",
                            lxdz: "",
                        },
                    ],
                },
            }
        },
        computed: {
            add() {
                this.jgyz = this.getjiageyinzi()
                return this.jgyz
            },
            gs0() {
                this.gs = parseInt(this.basicInformation.purPeoList.map(item => item.jtcygxdm)) / 100 * this.getjiageyinzi()
                this.gs = this.gs.toFixed(0)
                return this.gs
            },
            gs1() {
                this.gs12 = parseInt(this.basicInformation.purPeoList.map(item => item.jtcygxdm1)) / 100 * this.getjiageyinzi()
                this.gs12 = this.gs12.toFixed(0)
                return this.gs12
            },
            gs2() {
                this.gs22 = parseInt(this.basicInformation.purPeoList.map(item => item.jtcygxdm2)) / 100 * this.getjiageyinzi()
                this.gs22 = this.gs22.toFixed(0)
                return this.gs22
            },
            gs3() {
                this.gs32 = parseInt(this.basicInformation.purPeoList.map(item => item.jtcygxdm3)) / 100 * this.getjiageyinzi()
                this.gs32 = this.gs32.toFixed(0)
                return this.gs32
            },
            gs4() {
                this.gs42 = parseInt(this.basicInformation.purPeoList.map(item => item.jtcygxdm4)) / 100 * this.getjiageyinzi()
                this.gs42 = this.gs42.toFixed(0)
                return this.gs42
            },
            gs5() {
                this.gs52 = parseInt(this.basicInformation.purPeoList.map(item => item.jtcygxdm5)) / 100 * this.getjiageyinzi()
                this.gs52 = this.gs52.toFixed(0)
                return this.gs52
            },
            gongzi() {
                this.gz = 100 * this.gs0
                return this.gz
            },
            gongzi1() {
                this.gz1 = 100 * this.gs1
                return this.gz1
            },
            gongzi2() {
                this.gz2 = 100 * this.gs2
                return this.gz2
            },
            gongzi3() {
                this.gz3 = 100 * this.gs3
                return this.gz3
            },
            gongzi4() {
                this.gz4 = 100 * this.gs4
                return this.gz4
            },
            gongzi5() {
                this.gz5 = 100 * this.gs5
                return this.gz5
            },
            zuizhongjiage0() {
                this.zuizhongjiage = this.gongzi + this.gongzi1 + this.gongzi2 + this.gongzi3 + this.gongzi4 + this.gongzi5
                return this.zuizhongjiage
            },
        },
        mounted() {
            this.getjiageyinzi()
            this.getrow()
            this.getxiangsidu()
            this.open1()
        },
        methods: {
            change() {
                if (location.href.indexOf("#reloaded") == -1) {
                    location.href = location.href + "#reloaded";
                    location.reload();
                }
            },
            open1() {
                if (this.getjiageyinzi() == null) {
                    ElMessageBox.alert('没有选择数据', '错误', {
                    }).then((action) => {
                        this.$router.push({ path: "/jiageyinzi" })
                    })
                }
                else {
                    this.change()
                }
            },
            getrow() {
                var rows = this.$route.query.rows
                return rows
            },
            getxiangsidu() {
                var xiangsidu = this.$route.query.xiangsidu
                return xiangsidu
            },
            getjiageyinzi() {
                var jiageyinizi = this.$route.query.jiageyinzi
                return jiageyinizi
            },
            //新增购房人基本信息
            addBuyerInformation() {
                const that = this;
                that.basicInformation.purPeoList.push({});
            },
            //删除购房人基本信息
            removeRow(index) {
                if (index >= 0) {
                    this.basicInformation.purPeoList.splice(index, 1);
                }
            },
            submitHandlerAndNextStep() {
                this.param.rows = this.getrow()
                this.param.xiangsidu = this.getxiangsidu()
                this.param.jiageyinizi = this.getjiageyinzi()
                this.param.jiage = this.zuizhongjiage0
                this.$router.push({
                    path: "/jiage",
                    query: {
                        rows: this.param.rows,
                        xiangsidu: this.param.xiangsidu,
                        jiage: this.param.jiage,
                        jiageyinzi: this.param.jiageyinzi,
                    }
                })
            },
        }
    }
</script>


<style scope>

</style>