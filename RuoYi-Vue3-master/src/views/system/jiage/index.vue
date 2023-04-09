<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <ul>总工时:{{this.add}}</ul>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:jiage:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:jiage:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:jiage:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['system:jiage:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="jiageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件id" align="center" prop="fileId" />
      <el-table-column label="价格" align="center" prop="fileJiage" />
      <el-table-column label="实际代码" align="center" prop="fileShijidaima" />
      <el-table-column label="最终价格" align="center" prop="fileZuozhongjiage" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['system:jiage:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['system:jiage:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改最终价格计算对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body v-model="open">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="价格" prop="fileJiage">
          <el-input v-model="form.fileJiage" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="实际代码量" prop="fileShijidaima">
          <el-input v-model="form.fileShijidaima" placeholder="请输入实际代码量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listJiage, getJiage, delJiage, addJiage, updateJiage } from "@/api/system/jiage";
  import { ref } from 'vue'
  import { ElMessage, ElMessageBox } from 'element-plus'
  var jgyz
  export default {
    name: "Jiage",
    data() {
      return {
        number: 0,
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
        // 最终价格计算表格数据
        jiageList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          fileJiage: null,
          fileShijidaima: null,
          fileZuozhongjiage: null
        },
        // 表单参数
        form: {
        },
        // 表单校验
        rules: {
        }
      };
    },
    computed: {
      add() {
        this.jgyz = this.getjiageyinzi()
        return this.jgyz
      },
    },
    created() {
      this.getList()
    },
    mounted() {
      this.getrow();
      this.getjiage();
      this.getxiangsidu();
      this.getjiageyinzi()
      this.open1();
    },
    methods: {
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
      change() {
        if (location.href.indexOf("#reloaded") == -1) {
          location.href = location.href + "#reloaded";
          location.reload();
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
      getjiage() {
        var jiage = this.$route.query.jiage
        return jiage
      },
      getjiageyinzi() {
        var jiageyinizi = this.$route.query.jiageyinzi
        return jiageyinizi
      },



      /** 查询最终价格计算列表 */
      getList() {
        this.jgyz = this.getjiageyinzi()
        console.log(this.jgyz)
        this.number = 1
        console.log(this.getjiage())
        this.loading = true;
        listJiage(this.queryParams).then(response => {
          this.jiageList = response.rows;
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
          fileJiage: null,
          fileShijidaima: null,
          fileZuozhongjiage: null
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
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加最终价格计算(点击确定自动填充)";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const fileId = row.fileId || this.ids
        getJiage(fileId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改最终价格计算";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.fileId != null) {
              updateJiage(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              this.form.fileJiage = this.getjiage()
              this.form.fileShijidaima = parseInt(this.getrow()) - parseInt(this.getrow() * parseInt(this.getxiangsidu() * 100) / 100)
              addJiage(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除最终价格计算编号为"' + fileIds + '"的数据项？').then(function () {
          return delJiage(fileIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => { });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/jiage/export', {
          ...this.queryParams
        }, `jiage_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>

<style scoped>
  .dialog-footer {
    text-align: center;
  }
</style>