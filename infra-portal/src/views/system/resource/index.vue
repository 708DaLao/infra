<template>
  <div>
    <el-row class="margin-bottom-10">
      <el-input
        v-model="query.content"
        size="medium"
        placeholder="请输入搜索内容"
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
      >
        添加
      </el-button>
      <el-button
        type="danger"
        size="small"
        icon="el-icon-close"
        @click="handleMoreDelete()"
      >
        批量删除
      </el-button>
    </el-row>

    <el-table
      ref="roleTable"
      :data="resourceList"
      border
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="ID" sortable prop="id" width="70px">
      </el-table-column>
      <el-table-column label="资源" sortable prop="name"> </el-table-column>
      <el-table-column label="拥有者" prop="owner"> </el-table-column>
      <el-table-column label="资源描述" prop="description"> </el-table-column>
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
            type="danger"
            icon="el-icon-delete"
            size="mini"
            @click="handleDelete(scope.row.id, scope.row.name)"
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
      :visible.sync="dialogResource"
      width="30%"
      @close="closeDialog"
    >
      <el-form
        class="resourceForm"
        ref="resourceForm"
        :model="resourceForm"
        :rules="resourceFormRules"
        label-width="80px"
      >
        <el-form-item label="接口路径" prop="name">
          <el-input
            v-model="resourceForm.name"
            :disabled="isDisabled"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="资源描述">
          <el-input
            v-model="resourceForm.description"
            type="textarea"
          ></el-input>
        </el-form-item>
        <el-form-item label="拥有者">
          <el-checkbox-group v-model="resourceForm.owner">
            <el-checkbox
              v-for="name in roleList"
              :label="name"
              :key="name"
            ></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="flex-row-center">
        <el-button type="primary" @click="handleSubmit">确 定</el-button>
        <el-button>取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getResource,
  saveOrUpdateResource,
  deleteResource
} from "../../../api/resource";
import { getRoles } from "../../../api/role";
const defaultResource = {
  id: "",
  name: "",
  owner: [],
  description: ""
};
export default {
  data() {
    return {
      // 查询条件
      query: {
        current: 1, // 当前页
        size: 10, // 每页大小
        content: "" // 搜索内容
      },
      total: 0, // 数据总数
      resourceList: [], // 资源列表
      dialogTitle: "",
      dialogResource: false,
      resourceForm: Object.assign({}, defaultResource),
      isDisabled: false, //是否禁止编辑接口
      resourceFormRules: {
        name: [{ required: true, message: "请输入资源路径", trigger: "blur" }]
      }, // 检验规则
      roleList: [], // 角色列表
      multipleSelectionId: [], // 多选时的id
      multipleSelectionName: [] // 多选时的name
    };
  },
  created() {
    this.initData();
    this.initData2();
  },
  methods: {
    // 初始化数据
    initData() {
      getResource(this.query).then(res => {
        let data = res.data.page;
        this.resourceList = data.records;
        this.total = data.total;
      });
    },
    initData2() {
      getRoles().then(res => {
        let data = res.data.list;
        for (let i = 0; i < data.length; i++) {
          this.roleList.push(data[i].name);
        }
      });
    },
    search() {
      this.initData();
    },
    handleAdd() {
      this.dialogResource = true;
      this.dialogTitle = "添加资源";
    },
    handleEdit(row) {
      this.isDisabled = true;
      this.dialogTitle = "修改资源";
      this.dialogResource = true;
      // 深拷贝，避免影响表格数据
      let data = JSON.parse(JSON.stringify(row));
      data.owner = data.owner.split(",");
      this.resourceForm = data;
    },
    handleSubmit() {
      this.resourceForm.owner = this.resourceForm.owner.toString();
      saveOrUpdateResource(this.resourceForm).then(res => {
        this.initData();
        this.$message.success(res.message);
        this.closeDialog();
      });
    },
    // 删除资源
    handleDelete(ids, names) {
      this.$confirm("确定删除该资源吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          deleteResource({ id: ids, name: names }).then(res => {
            this.$message.success(res.message);
            this.initData();
          });
          this.multipleSelectionId = [];
          this.multipleSelectionName = [];
        })
        .catch(() => {});
    },
    // 批量删除
    handleMoreDelete() {
      if (!this.multipleSelectionId.length > 0) {
        this.$message.warning("请勾选要删除的资源");
      } else {
        this.handleDelete(
          this.multipleSelectionId.toString(),
          this.multipleSelectionName.toString()
        );
      }
    },
    handleSelectionChange(val) {
      for (let i = 0, len = val.length; i < len; i++) {
        this.multipleSelectionId.push(val[i].id);
        this.multipleSelectionName.push(val[i].name);
      }
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
    closeDialog() {
      this.isDisabled = false;
      this.dialogResource = false;
      this.resourceForm = Object.assign({}, defaultResource);
      this.$refs.resourceForm.clearValidate();
    }
  }
};
</script>

<style scoped>
.resourceForm {
  max-height: 60vh;
  overflow: auto;
}
</style>
