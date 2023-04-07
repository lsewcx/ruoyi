<template>
    <div class="container">
        <div class="left">
            <div class="height"></div>
            <li>未调整的工作量(UE):</li>
            <div class="height"></div>
            <li>应用领域调整因子(A)</li>
            <div class="height"></div>
            <li>软件完整性级别(IL)</li>
            <div class="height"></div>
            <li>开发语言调整因子</li>
            <div class="height2"></div>
            <li>最大团队规模调整因子</li>
            <div class="height2"></div>
            <li>调整后的工作量(AE)</li>
        </div>
        <div class="middle">
            <div class="height1"></div>
            <li>{{this.UE}}(p*h)</li>
            <div class="height1"></div>
            <el-input type="number" min="0.8" max="1.2" step="0.1" @input="(v)=>(input=v.replace(/[^\d.]/g,''))"
                maxlength="3" v-model="input" placeholder="0.8~1.2" style="display: inline;" />
            <div class="height1"></div>
            <el-input type="number" min="0.8" max="1.8" step="0.1" @input="(v)=>(input1=v.replace(/[^\d.]/g,''))"
                maxlength="3" v-model="input1" placeholder="0.8~1.8" />
            <div class="height1"></div>
            <el-input type="number" min="0.8" max="1.2" step="0.1" @input="(v)=>(input2=v.replace(/[^\d.]/g,''))"
                maxlength="3" v-model="input2" placeholder="0.8~1.2" />
            <div class="height1"></div>
            <el-input type="number" min="0.8" max="1.2" step="0.1" @input="(v)=>(input3=v.replace(/[^\d.]/g,''))"
                maxlength="3" v-model="input3" placeholder="0.8~1.2" />
            <div class="height1"></div>
            <li>{{this.AE1}}</li>
            <div class="height0"></div>
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="push">下一步</el-button>
        </div>
        <div class="right">
            <div class="height"></div>
            <div class="height"></div>
            <li>取值范围(0.8~1.2)</li>
            <div class="height"></div>
            <li>取值范围(0.8~1.8)</li>
            <div class="height"></div>
            <li>取值范围(0.8~1.2)</li>
            <div class="height"></div>
            <li>取值范围(0.8~1.2)</li>
            <div class="height"></div>
            <li>AE=UE*A*IL*L*T</li>
        </div>
    </div>

</template>

<script>
    export default {
        name: "jiageyinzi",
        data() {
            return {
                UE: 50510.5,
                input: 1,
                input1: 1,
                input2: 1,
                input3: 1,
                AE1: 50510.5
            }
        },
        methods: {
            push() {
                console.log(this.input3)
                // this.$router.push({ path: "/jiage" })
            },
        },
    }
</script>
<style scoped>
    .container {
        display: flex;
    }

    .left {
        width: 600px;
        height: 300px;
    }

    .middle {
        height: 200px;
        flex: auto;
    }

    .right {
        height: 300px;
        width: 600px;
    }

    .height {
        height: 100px;
    }

    .el-input {
        width: auto;
        height: auto;
    }

    .height1 {
        height: 90px;
    }

    .height2 {
        height: 100px;
    }

    .height3 {
        height: 140px;
    }

    .height0 {
        height: 60px;
    }
</style>