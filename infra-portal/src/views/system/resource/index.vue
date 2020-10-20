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
      :data="
        resourceList.slice(
          (query.current - 1) * query.size,
          query.current * query.size
        )
      "
      border
      stripe
      style="width: 100%"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="资源" sortable prop="key"> </el-table-column>
      <el-table-column label="拥有者" prop="value"> </el-table-column>
      <el-table-column fixed="right" label="操作">
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

    <el-dialog :title="dialogTitle" :visible.sync="dialogResource" width="30%">
      <el-form
        class="resourceForm"
        ref="resourceForm"
        :model="resourceForm"
        :rules="resourceFormRules"
        label-width="70px"
      >
        <el-form-item label="接口路径">
          <el-input v-model="resourceForm.key" clearable></el-input>
        </el-form-item>
        <el-form-item label="拥有者">
          <el-checkbox-group v-model="resourceForm.value">
            <el-checkbox
              v-for="name in roleList"
              :label="name"
              :key="name"
            ></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="flex-row-center">
        <el-button type="primary">确 定</el-button>
        <el-button>取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getResource } from "../../../api/resource";
import { getRoles } from "../../../api/role";

export default {
  data() {
    return {
      // 查询条件
      query: {
        current: 1, // 当前页
        size: 10 // 每页大小
      },
      total: 0, // 数据总数
      resourceList: [], // 资源列表
      dialogTitle: "",
      dialogResource: false,
      resourceForm: {
        // 资源表单
        key: "",
        value: []
      },
      resourceFormRules: {}, // 检验规则
      roleList: [] // 角色列表
    };
  },
  created() {
    this.initData();
  },
  methods: {
    // 初始化数据
    initData() {
      getResource().then(res => {
        this.resourceList = res.data.resource;
        this.total = res.data.resource.length;
      });
      getRoles().then(res => {
        let data = res.data.list;
        for (let i = 0; i < data.length; i++) {
          this.roleList.push(data[i].name);
        }
      });
    },
    handleAdd() {
      this.dialogResource = true;
      this.dialogTitle = "添加资源";
    },
    // 每页大小发生变化时执行
    handleSizeChange(val) {
      this.query.size = val;
      // console.log(`每页 ${val} 条`);
    },
    // 当前页发生变化时执行
    handleCurrentChange(val) {
      this.query.current = val;
      // console.log(`当前页: ${val}`);
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
