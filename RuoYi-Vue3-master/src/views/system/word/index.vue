<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:word:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:word:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>





    <!-- 添加或修改word上传对话框 -->
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="文字" prop="fileWord">
        <el-input :rows="20" v-model="form.fileWord" type="textarea" placeholder="请输入内容" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="button">
      <el-button type="primary" @click="submitForm" calss="button">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>
  </div>
</template>

<script>
  import { listWord, getWord, delWord, addWord, updateWord } from "@/api/system/word";

  export default {
    name: "Word",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // word上传表格数据
        wordList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          fileWord: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
    },
    mounted() {
      this.getrows()
      this.getxiangsidu()
      this.getid()
    },
    methods: {
      getrows() {
        var rows = this.$route.query.rows
        return rows
      },
      getxiangsidu() {
        var xiangsidu = this.$route.query.xiangsidu
        return xiangsidu
      },
      getid() {
        var id = this.$route.query.id
        return id
      },

      /** 查询word上传列表 */
      getList() {
        this.loading = true;
        listWord(this.queryParams).then(response => {
          this.wordList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          fileId: null,
          fileWord: null
        };
        this.resetForm("form");
      },


      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.fileId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加word上传";
      },

      /** 提交按钮 */
      submitForm() {
        console.log(this.getid())
        console.log(this.getxiangsidu())
        console.log(this.getrows())
        // this.$refs["form"].validate(valid => {
        //   if (valid) {
        //     if (this.form.fileId != null) {
        //       updateWord(this.form).then(response => {
        //         this.$modal.msgSuccess("修改成功");
        //         this.open = false;
        //         this.getList();
        //       });
        //     } else {
        //       addWord(this.form).then(response => {
        //         this.$modal.msgSuccess("新增成功");
        //         this.open = false;
        //         this.getList();
        //       });
        //     }
        //   }
        // });
        // this.$router.push({ path: "/jiage" })
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/word/export', {
          ...this.queryParams
        }, `word_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>

<style scope>
  .button {
    text-align: center;
  }

  .input {
    height: 500px;
  }
</style>