<template>
  <div>
    <el-row class="margin-bottom-10">
      <el-button
        type="primary"
        size="small"
        icon="el-icon-plus"
        @click="handleAdd"
      >
        添加
      </el-button>
    </el-row>

    <el-table
      ref="roleTable"
      :data="routerList"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="id" sortable width="60">
      </el-table-column>
      <el-table-column prop="title" label="title"> </el-table-column>
      <el-table-column label="icon" width="55">
        <template slot-scope="scope">
          <i :class="scope.row.icon"></i>
        </template>
      </el-table-column>
      <el-table-column prop="path" label="path"> </el-table-column>
      <el-table-column prop="name" label="name"> </el-table-column>
      <el-table-column label="keepAlive" width="61">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.keepAlive ? 'success' : 'danger'"
            disable-transitions
            size="mini"
            >{{ scope.row.keepAlive }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="component" label="component"> </el-table-column>
      <el-table-column prop="redirect" label="redirect"> </el-table-column>
      <el-table-column prop="parentId" label="parentId" width="67">
      </el-table-column>
      <el-table-column label="hidden" width="70">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.hidden ? 'success' : 'danger'"
            disable-transitions
            size="mini"
            >{{ scope.row.hidden }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="alwaysShow" width="68">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.alwaysShow ? 'success' : 'danger'"
            disable-transitions
            size="mini"
            >{{ scope.row.alwaysShow }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="sort" sortable width="73">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="177">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      class="flex-row-center margin-top-10"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[10, 20, 30, 40, 50]"
      :page-size="query.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
    >
    </el-pagination>

    <el-dialog
      :title="dialogTitle"
      :visible.sync="dialogRouter"
      width="30%"
      @close="closeDialog"
    >
      <el-form
        class="routerForm"
        ref="routerForm"
        :model="routerForm"
        :rules="routerFormRules"
        label-width="110px"
      >
        <el-form-item label="标题">
          <el-input v-model="routerForm.title" clearable></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input
            v-model="routerForm.icon"
            placeholder="elementUI图标或fontawesome图标"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="路径" prop="path">
          <el-input v-model="routerForm.path" clearable></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input
            v-model="routerForm.name"
            placeholder="缓存页面时必填且唯一"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="组件" prop="component">
          <el-input
            v-model="routerForm.component"
            placeholder="eg: Layout或views目录下的页面路径"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="重定向">
          <el-input v-model="routerForm.redirect" clearable></el-input>
        </el-form-item>
        <el-form-item label="上级路由">
          <tree-select
            v-model="routerForm.parentId"
            :data="routerTree"
          ></tree-select>
        </el-form-item>
        <el-form-item label="排序">
          <el-input
            v-model="routerForm.sort"
            placeholder="建议每个大分类相隔至少10"
          ></el-input>
        </el-form-item>
        <el-form-item label="是否缓存">
          <el-radio-group v-model="routerForm.keepAlive">
            <el-radio :label="true">true</el-radio>
            <el-radio :label="false">false</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否隐藏">
          <el-radio-group v-model="routerForm.hidden">
            <el-radio :label="true">true</el-radio>
            <el-radio :label="false">false</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否显示根路由">
          <el-radio-group v-model="routerForm.alwaysShow">
            <el-radio :label="true">true</el-radio>
            <el-radio :label="false">false</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="flex-row-center">
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
        <el-button @click="closeDialog">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getRouters } from "../../../api/role";
import { saveOrUpdateRouter, deleteRouter } from "../../../api/router";
import TreeSelect from "../../../components/TreeSelect";
const defaultRouter = {
  id: "",
  title: "",
  icon: "",
  path: "",
  name: "",
  keepAlive: false,
  component: "",
  redirect: "",
  parentId: "",
  hidden: false,
  alwaysShow: false,
  sort: ""
};
export default {
  components: {
    TreeSelect
  },
  data() {
    return {
      // 查询条件
      query: {
        current: 1, // 当前页
        size: 10 // 每页大小
      },
      total: 0, // 数据总数
      routerList: [], // 路由列表
      dialogTitle: "",
      dialogRouter: false,
      routerForm: Object.assign({}, defaultRouter), // 路由表单
      routerTree: [], // 路由树
      routerFormRules: {
        // 表单规则
        path: [{ required: true, message: "请输入请求路径", trigger: "blur" }],
        component: [{ required: true, message: "请输入组件", trigger: "blur" }]
      }
    };
  },
  created() {
    this.initData();
  },
  methods: {
    // 初始化数据
    initData() {
      // 分页数据
      getRouters(this.query).then(res => {
        this.routerList = res.data.page.records;
        this.total = res.data.page.total;
      });
      // 树形数据
      getRouters().then(res => {
        this.routerTree = res.data.tree;
      });
    },
    // 添加路由
    handleAdd() {
      this.dialogRouter = true;
      this.dialogTitle = "添加路由";
    },
    // 编辑
    handleEdit(data) {
      this.dialogTitle = "修改路由";
      this.dialogRouter = true;
      this.routerForm = data;
    },
    // 确定
    handleSubmit() {
      this.$refs.routerForm.validate(valid => {
        if (valid) {
          this.routerForm.parentId = this.routerForm.parentId
            ? this.routerForm.parentId
            : 0;
          saveOrUpdateRouter(this.routerForm).then(res => {
            this.closeDialog();
            this.initData();
            this.$message.success(res.message);
          });
        } else {
          return false;
        }
      });
    },
    // 删除
    handleDelete(id) {
      this.$confirm("确定删除该路由吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteRouter({ id: id }).then(res => {
            this.$message.success(res.message);
            this.initData();
          });
        })
        .catch(() => {});
    },
    // 每页大小发生变化时执行
    handleSizeChange(val) {
      this.query.size = val;
      this.initData();
      // console.log(`每页 ${val} 条`);
    },
    // 当前页发生变化时执行
    handleCurrentChange(val) {
      this.query.current = val;
      this.initData();
      // console.log(`当前页: ${val}`);
    },
    // 关闭弹窗回调事件
    closeDialog() {
      this.dialogRouter = false;
      this.routerForm = Object.assign({}, defaultRouter); // 路由表单
      this.$refs.routerForm.clearValidate(); // 移除表单校验
    }
  }
};
</script>

<style scoped>
.routerForm {
  height: 60vh;
  overflow: auto;
}
</style>
