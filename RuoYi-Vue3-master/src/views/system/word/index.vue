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
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:word:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:word:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:word:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="push">最终价格</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="wordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件id" align="center" prop="fileId" />
      <el-table-column label="word行数" align="center" prop="fileRows" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:word:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:word:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />


    <!-- 添加或修改word上传对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body v-model="open">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文字" prop="fileWord">
          <el-input :rows="20" v-model="form.fileWord" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="button">
        <el-button type="primary" @click="submitForm" calss="button">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listWord, getWord, delWord, addWord, updateWord } from "@/api/system/word";
  var res
  export default {
    name: "Word",
    data() {
      return {
        param: {
          c: 0,
          xiangsidu: 0,
          rows: 0,
        },
        res: null,
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
          fileWord: null,
          fileRows: null
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

      push() {
        console.log(this.param.rows)
        this.$router.push({
          path: "/jiageyinzi",
          query: {
            rows: this.param.rows,
            xiangsidu: this.param.xiangsidu,
            jiage: this.param.c
          }
        })
      },
      getrows() {
        var rows = this.$route.query.rows
        return rows
      },
      getxiangsidu() {
        var xiangsidu = this.$route.query.xiangsidu
        return xiangsidu
      },
      getid() {
        var id = this.$route.query.ID
        return id
      },

      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.fileId)
        var a = this.ids[0]
        listWord(this.queryParams).then(response => {
          var word = response.rows
          var filterList = word.filter(val => val.fileId === a)
          var c = filterList.map(item => item.fileRows)
          this.param.c = c
          this.param.xiangsidu = this.getxiangsidu()
          this.param.rows = this.getrows()
          console.log(this.param.c)
        });
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },

      /** 查询word上传列表 */
      getList() {
        this.loading = true;
        listWord(this.queryParams).then(response => {
          var word = response.rows
          var filterList = word.filter(val => val.fileId === val.fileId)
          res = filterList.map(item => item.fileRows)
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
          fileWord: null,
          fileRows: null
        };
        this.resetForm("form");
      },
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const fileId = row.fileId || this.ids
        getWord(fileId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改word上传";
        });
      },
      handleDelete(row) {
        const fileIds = row.fileId || this.ids;
        this.$modal.confirm('是否确认删除word上传编号为"' + fileIds + '"的数据项？').then(function () {
          return delWord(fileIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => { });
      },


      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加word上传";
      },

      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.fileId != null) {
              updateWord(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addWord(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
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