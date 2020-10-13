<template>
  <div>
    <el-row class="margin-bottom-10">
      <el-input
        v-model="query.name"
        size="medium"
        placeholder="请输入角色名称"
        clearable
        style="width: 300px;margin-right: 10px"
        @keyup.enter.native="search()"
      >
        <el-button
          size="small"
          slot="append"
          icon="el-icon-search"
          @click="search"
        ></el-button>
      </el-input>
      <el-button
        type="primary"
        size="small"
        icon="el-icon-plus"
        @click="handleAdd"
        >添加</el-button
      >
    </el-row>

    <el-table
      ref="roleTable"
      :data="roleList"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column prop="name" label="名称"> </el-table-column>
      <el-table-column prop="description" label="描述"> </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button
            type="success"
            size="mini"
            @click="handlePermission(scope.row.id)"
            >权限分配</el-button
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
      :title="dialogRoleTitle"
      :visible.sync="dialogRole"
      width="30%"
      @close="closeDialogRole"
    >
      <el-form
        ref="roleForm"
        :model="roleForm"
        :rules="roleRules"
        label-width="80px"
      >
        <el-form-item label="角色名称" prop="name">
          <el-input
            v-model="roleForm.name"
            :disabled="disabledRoleName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input
            v-model="roleForm.description"
            type="textarea"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="flex-row-center">
        <el-button @click="closeDialogRole">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="权限配置" :visible.sync="dialogPermission" @close="closeDialogPermission" width="40%">
      <el-tabs v-model="activeTab" stretch>
        <el-tab-pane label="路由配置" name="first">
          <el-tree
            ref="routerTree"
            :props="defaultProps"
            :data="routerTreeData"
            show-checkbox
            default-expand-all
            node-key="id"
          >
          </el-tree>
        </el-tab-pane>
        <el-tab-pane label="接口配置" name="second">接口配置</el-tab-pane>
        <el-tab-pane label="用户配置" name="third">用户配置</el-tab-pane>
        <el-tab-pane label="其他配置" name="fourth">扩展。。。</el-tab-pane>
      </el-tabs>
      <span slot="footer" class="flex-row-center">
        <el-button size="small" @click="dialogPermission = false"
          >取 消</el-button
        >
        <el-button size="small" type="primary" @click="submitPermission"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getRoles, saveRole, deleteRole, getRouters, getPermissionByRoleId } from "../../../api/role";
const defaultRole = {
  id: "",
  name: "",
  description: ""
};
export default {
  data() {
    return {
      // 查询条件
      query: {
        name: "", // 角色名称
        current: 1, // 当前页
        size: 10 // 每页大小
      },
      total: 0, // 数据总数
      roleList: [], // 角色列表
      dialogRole: false, // 角色弹窗
      dialogRoleTitle: "添加角色", // 角色弹窗标题
      roleForm: Object.assign({}, defaultRole), // 角色表单
      disabledRoleName: false, // 禁止角色名
      roleRules: {
        // 角色表单校验
        name: [{ required: true, message: "请输入角色名称", trigger: "blur" }]
      },
      dialogPermission: false, // 权限配置弹窗
      activeTab: "first",
      defaultProps: {
        children: "children",
        label: "label"
      },
      routerTreeData: [], // 路由树
    };
  },
  created() {
    this.initData();
  },
  methods: {
    // 初始化数据
    initData() {
      getRoles(this.query).then(res => {
        this.roleList = res.data.list.records;
        this.total = res.data.list.total;
      });
      // 页面初始化时只去获取一次路由数据，减少数据库请求
      getRouters().then(res => {
        this.routerTreeData = res.data.tree;
      });
    },
    // 搜索
    search() {
      this.initData();
    },
    // 添加角色信息
    handleAdd() {
      this.dialogRoleTitle = "添加角色信息";
      this.dialogRole = true;
    },
    // 角色修改
    handleEdit(data) {
      this.dialogRoleTitle = "修改角色信息";
      this.dialogRole = true;
      this.roleForm = Object.assign({}, data);
      this.disabledRoleName = true;
    },
    // 确认
    handleSubmit() {
      this.$refs.roleForm.validate(valid => {
        if (valid) {
          saveRole(this.roleForm).then(res => {
            this.$message.success(res.message);
            this.closeDialogRole();
            this.initData();
          });
        } else {
          return false;
        }
      });
    },
    // 删除角色
    handleDelete(id) {
      this.$confirm("确定删除该角色吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteRole({ id: id }).then(res => {
            this.$message.success(res.message);
            this.initData();
          });
        })
        .catch(() => {});
    },
    // 权限配置
    handlePermission(roleId) {
      this.dialogPermission = true;
      // 获取角色权限
      getPermissionByRoleId({roleId:roleId}).then(res => {
        this.$refs.routerTree.setCheckedKeys(res.data.routerIds);
      })
    },
    // 确定分配权限
    submitPermission() {

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
    // 关闭角色弹窗的回调
    closeDialogRole() {
      this.dialogRole = false;
      this.roleForm = Object.assign({}, defaultRole);
      this.$refs.roleForm.clearValidate();
      this.disabledRoleName = false;
    },
    // 关闭权限配置弹窗回调
    closeDialogPermission() {
      // 清空选中状态
      this.$refs.routerTree.setCheckedKeys([]);
    }
  }
};
</script>

<style scoped></style>
