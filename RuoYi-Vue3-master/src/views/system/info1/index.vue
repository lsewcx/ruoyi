<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件名称" prop="fileName">
        <el-input v-model="queryParams.fileName" placeholder="请输入文件名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:info1:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:info1:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:info1:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:info1:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="push">代码分析</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-download" size="mini" @click="jiage">价格计算</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <!-- 新增 -->

    <el-table v-loading="loading" :data="info1List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件id" align="center" prop="fileId" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件路径" align="center" prop="filePath" :show-overflow-tooltip="true" />
      <el-table-column label="开源项目版本" align="center" prop="fileVersion" />
      <el-table-column label="托管地址" align="center" prop="fileGithub" :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column label="代码行数" align="center" prop="fileRows" />
      <el-table-column label="代码相似度" align="center" prop="fileXinagsidu" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:info1:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:info1:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改文件信息1对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body v-model="open">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名称" />
        </el-form-item>
        <el-form-item label="文件路径" prop="filePath">
          <file-upload id="file" v-model="form.filePath" placeholder="请输入文件路径" />
        </el-form-item>
        <el-form-item label="项目版本" prop="fileVersion">
          <el-input v-model="form.fileVersion" placeholder="请输入开源项目版本" />
        </el-form-item>
        <el-form-item label="托管地址" prop="fileGithub">
          <el-input v-model="form.fileGithub" placeholder="请输入托管地址" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="button">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import { listInfo1, getInfo1, delInfo1, addInfo1, updateInfo1 } from "@/api/system/info1";
  import { ref, watch, computed } from 'vue'
  var res;
  var fileID;
  var a;
  export default {
    name: "Info1",
    data() {
      return {

        fileID: null,
        number: "",
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
        // 文件信息1表格数据
        info1List: [],
        list: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        param: {
          id: 0,
          fileID: 0,
          xiangsidu: 0,
          fileRows: 0,
        },
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          fileName: null,
          filePath: null,
          fileVersion: null,
          fileGithub: null,
          fileRows: null,
          fileXinagsidu: null
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
    methods: {

      jiage() {
        this.$router.push({
          path: "/word", query: {
            ID: this.param.id,
            xiangsidu: this.param.xiangsidu,
            rows: this.param.fileRows,
          }
        })
      },

      push() {
        console.log(this.param.fileID)
        this.$router.push({ path: "/tool/build", query: { formdata: this.param.fileID } })
      },


      /** 查询文件信息1列表 */
      getList() {
        this.loading = true;
        listInfo1(this.queryParams).then(response => {
          var list = response.rows
          var filterList = list.filter(val => val.fileId === fileID)
          res = filterList.map(item => item.fileXinagsidu);
          this.info1List = response.rows;
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
          fileName: null,
          filePath: null,
          fileVersion: null,
          fileGithub: null,
          fileRows: null,
          fileXinagsidu: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.fileId)
        var a = this.ids[0]
        listInfo1(this.queryParams).then(response => {
          var list = response.rows
          var filterList = list.filter(val => val.fileId === a)
          res = filterList.map(item => item.fileXinagsidu)
          var c = filterList.map(item => item.fileRows)
          var b = filterList.map(item => item.fileId)
          var number = parseInt(res)
          number = number / 100
          this.param.id = b
          this.param.xiangsidu = number
          this.param.fileRows = c
          this.param.fileID = number
        });
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加文件信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const fileId = row.fileId || this.ids
        getInfo1(fileId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改文件信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        console.log(this.form.filePath)
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.fileId != null) {
              updateInfo1(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addInfo1(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const fileIds = row.fileId || this.ids;
        this.$modal.confirm('是否确认删除文件信息1编号为"' + fileIds + '"的数据项？').then(function () {
          return delInfo1(fileIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => { });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/info1/export', {
          ...this.queryParams
        }, `info1_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>

<style scope>
  .button {
    text-align: center;
  }
</style>