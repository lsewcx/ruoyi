<template>
  <div>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="queryParams.fileName" placeholder="请输入文件名称" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <div>先选择你想要比对的文件是哪个点击文件id前面的框</div>
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
          <el-button type="primary" plain icon="el-icon-download" size="mini" @click="difference">代码差异分析</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-download" size="mini" @click="go">文件详细信息</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-download" size="mini" @click="diff">代码溯源</el-button>
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

      <el-dialog width="1000px" append-to-body v-model="open1">
        <code-diff :old-string="this.str123" :new-string="this.str124" output-format="side-by-side" :context="100" />
      </el-dialog>

    </div>
  </div>
</template>

<script>
  import { listInfo1, getInfo1, delInfo1, addInfo1, updateInfo1 } from "@/api/system/info1";
  import { ref, watch, computed } from 'vue'
  import { CodeDiff } from 'v-code-diff'
  var res;
  var fileID;
  var a;
  var d
  var e
  export default {
    name: "Info1",
    data() {
      return {
        url: "http://localhost:9000",
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
        title1: "",
        // 是否显示弹出层
        open: false,
        open1: false,
        param: {
          id: 0,
          fileID: 0,
          xiangsidu: 0,
          fileRows: 0,
          filename: '',
          path: '',
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
          fileXinagsidu: null,
          abc: null,
          abc1: null,
        },
        // 表单参数
        form: {},
        form1: {},
        // 表单校验
        rules: {
        }
      };
    },

    computed: {
      str123() {
        this.str1 = 'package com.ruoyi.system.controller;\
    import com.hankcs.hanlp.HanLP;\
    import org.springframework.web.bind.annotation.RestController;\
    import java.util.ArrayList;\
    import java.util.Collections;\
    import java.util.List;\
    import java.util.Map;\
    import java.util.stream.Collectors;\
    @RestController\
    public class gonneng {\
    public static String getSimilarity(String sentence1, String sentence2) {\
        List<String> sent1Words = getSplitWords(sentence1);\
        List<String> sent2Words = getSplitWords(sentence2);\
        List<String> allWords = mergeList(sent1Words, sent2Words);\
        int[] statistic1 = statistic(allWords, sent1Words);\
        int[] statistic2 = statistic(allWords, sent2Words);\
        // 向量A与向量B的点乘\
        double dividend = 0;\
        // 向量A所有维度值的平方相加\
        double divisor1 = 0;\
        // 向量B所有维度值的平方相加\
        double divisor2 = 0;\
        // 余弦相似度 算法\
        for (int i = 0; i < statistic1.length; i++) {\
            dividend += statistic1[i] * statistic2[i];\
            divisor1 += Math.pow(statistic1[i], 2);\
            divisor2 += Math.pow(statistic2[i], 2);\
        }\
        // 向量A与向量B的点乘 / （向量A所有维度值的平方相加后开方 * 向量B所有维度值的平方相加后开方）\
        double A=dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2))*100;\
        A= Math.round(A);\
        String B= String.valueOf(A);\
        B+="% ";\
        return B;\
    }\
    // 2. 取并集\
    private static List<String> mergeList(List<String> list1, \List<String> list2) {\
        List<String> result = new ArrayList<>();\
        result.addAll(list1);\
        result.addAll(list2);\
        return result.stream().distinct().collect(Collectors.toList());}'
        return this.str1
      },
      str124() {
        this.str2 = 'package com.ruoyi.system.controller;\
    import com.hankcs.hanlp.HanLP;\
    import org.springframework.web.bind.annotation.RestController;\
    import java.util.ArrayList;\
    import java.util.Collections;\
    import java.util.List;\
    import java.util.Map;\
    import java.util.stream.Collectors;\
    @RestController\
    public class gonneng {\
    public static String getSimilarity(String sentence1, String sentence2) {\
        List<String> sent1Words = getSplitWords(sentence1);\
        List<String> sent2Words = getSplitWords(sentence2);\
        List<String> allWords = mergeList(sent1Words, sent2Words);\
        int[] statistic1 = statistic(allWords, sent1Words);\
        int[] statistic2 = statistic(allWords, sent2Words);\
        // 向量A与向量B的点乘\
        double dividend = 0;\
        // 向量A所有维度值的平方相加\
        double divisor1 = 0;\
        // 向量B所有维度值的平方相加\
        double divisor2 = 0;\
        // 余弦相似度 算法\
        for (int i = 0; i < statistic1.length; i++) {\
            dividend += statistic1[i] * statistic2[i];\
            divisor1 += Math.pow(statistic1[i], 2);\
            divisor2 += Math.pow(statistic2[i], 2);\
        }\
        // 向量A与向量B的点乘 / （向量A所有维度值的平方相加后开方 * 向量B所有维度值的平方相加后开方）\
        double A=dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2))*100;\
        A= Math.round(A);\
        String B= String.valueOf(A);\
        B+="% ";\
        return B;\
    }\
    // 3. 计算词频\
    private static int[] statistic(List<String> allWords, List<String> sentWords) {\
        int[] result = new int[allWords.size()];\
        for (int i = 0; i < allWords.size(); i++) {\
            // 返回指定集合中指定对象出现的次数\
            result[i] = Collections.frequency(sentWords, allWords.get\(i));\
        }\
        return result;\
    }\
    // 2. 取并集\
    private static List<String> mergeList(List<String> list1, \List<String> list2) {\
        List<String> result = new ArrayList<>();\
        result.addAll(list1);\
        result.addAll(list2);\
        return result.stream().distinct().collect(Collectors.toList());}'
        return this.str2
      },
    },

    created() {
      this.getList();
    },
    methods: {
      go() {
        this.$router.push({
          path: "/xinxi",
          query: {
            filename: this.param.filename,
            filePath: this.param.path,
          }
        })
      },
      difference() {
        this.open1 = true
        this.str1 = ""
        this.str2 = ""
      },
      diff() {
        window.open(this.url)
      },
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
        this.$router.push({
          path: "/build", query: {
            formdata: this.param.fileID,
            rows: this.param.fileRows
          }
        })
      },
      change() {
        if (location.href.indexOf("#reloaded") == -1) {
          location.href = location.href + "#reloaded";
          location.reload();
        }
      },


      // 取消按钮
      cancel() {
        this.open = false;
        this.open1 = fasle;
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
          d = filterList.map(item => item.filePath)//文件路径
          var name = filterList.map(item => item.fileName)
          var path = filterList.map(item => item.filePath)
          this.param.filename = name
          this.param.path = path
          console.log(path)
          this.form.filenewpath = d
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
        this.form.filenewpath = d[0]
        console.log(d[0])
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
                this.open = true;
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