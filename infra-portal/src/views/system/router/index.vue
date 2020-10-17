<template>
  <div>
    <el-row class="margin-bottom-10">
      <el-button
        type="primary"
        size="small"
        icon="el-icon-plus"
        @click="dialogRouter = true"
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
          <el-button type="primary" icon="el-icon-edit" size="mini"
            >编辑</el-button
          >
          <el-button type="danger" icon="el-icon-delete" size="mini"
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

    <el-dialog :title="dialogTitle" :visible.sync="dialogRouter" width="30%">
      <el-form ref="routerForm" :model="routerForm" label-width="100px">
        <el-form-item label="title">
          <el-input v-model="routerForm.title"></el-input>
        </el-form-item>
        <el-form-item label="icon">
          <el-input v-model="routerForm.icon"></el-input>
        </el-form-item>
        <el-form-item label="path">
          <el-input v-model="routerForm.path"></el-input>
        </el-form-item>
        <el-form-item label="name">
          <el-input v-model="routerForm.name"></el-input>
        </el-form-item>
        <el-form-item label="component">
          <el-input v-model="routerForm.component"></el-input>
        </el-form-item>
        <el-form-item label="title">
          <el-input v-model="routerForm.title"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="flex-row-center">
        <el-button @click="dialogRouter = false">取 消</el-button>
        <el-button type="primary" @click="dialogRouter = false"
          >确 定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getRouters } from "../../../api/role";
const defaultRouter = {
  id: "",
  title: "",
  icon: "",
  path: "",
  name: "",
  keepAlive: "",
  component: "",
  redirect: "",
  parentId: "",
  hidden: "",
  alwaysShow: "",
  sort: ""
};
export default {
  data() {
    return {
      // 查询条件
      query: {
        current: 1, // 当前页
        size: 10 // 每页大小
      },
      total: 0, // 数据总数
      routerList: [], // 路由列表
      dialogTitle: "添加路由",
      dialogRouter: false,
      routerForm: Object.assign({}, defaultRouter), // 路由表单
    };
  },
  created() {
    this.initData();
  },
  methods: {
    // 初始化数据
    initData() {
      getRouters(this.query).then(res => {
        this.routerList = res.data.page.records;
        this.total = res.data.page.total;
      });
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
    }
  }
};
</script>

<style scoped></style>
