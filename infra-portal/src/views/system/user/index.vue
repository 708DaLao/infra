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
    </el-row>

    <el-table
      ref="userTable"
      :data="userList"
      border
      stripe
      style="width: 100%"
    >
      <el-table-column label="ID" sortable prop="id"> </el-table-column>
      <el-table-column label="账号" prop="username"> </el-table-column>
      <el-table-column label="昵称" prop="nickname"> </el-table-column>
      <el-table-column label="真实姓名" prop="realname"> </el-table-column>
      <el-table-column label="手机" prop="phone"> </el-table-column>
      <el-table-column label="邮箱" prop="email"> </el-table-column>
      <el-table-column label="性别" prop="gender">
        <template slot-scope="scope">
          <span v-if="scope.row.gender === 0">保密</span>
          <span v-else-if="scope.row.gender === 1">男</span>
          <span v-else>女</span>
        </template>
      </el-table-column>
      <el-table-column label="出生日期" prop="birthday"> </el-table-column>
      <el-table-column label="地址" prop="addr"> </el-table-column>
      <el-table-column label="状态" prop="status" sortable width="73px">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.status ? 'success' : 'danger'"
            disable-transitions
            size="mini"
            >{{ scope.row.status ? "开启" : "锁定" }}</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="上次登录时间" prop="loginTime"> </el-table-column>
      <el-table-column label="上次登录地址" prop="loginIp"> </el-table-column>
      <el-table-column label="是否在线" prop="online" sortable>
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.online ? 'success' : 'danger'"
            disable-transitions
            size="mini"
            >{{ scope.row.online ? "是" : "否" }}</el-tag
          >
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="90">
        <template slot-scope="scope">
          <el-button
            type="success"
            icon="el-icon-edit"
            circle
            plain
            size="mini"
            @click="handleEdit(scope.row)"
          />
          <el-button
            type="danger"
            icon="el-icon-delete"
            circle
            plain
            size="mini"
            @click="handleDelete(scope.row)"
          />
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
      :visible.sync="dialogUser"
      width="30%"
      @close="closeDialog"
    >
      <el-form
        ref="userForm"
        :model="userForm"
        :rules="userFormRules"
        label-width="80px"
      >
        <el-form-item label="真实姓名" prop="realname">
          <el-input
            v-model="userForm.realname"
            clearable
            placeholder="请输入真实姓名"
            :disabled="userForm.id != ''"
          ></el-input>
        </el-form-item>
        <el-form-item label="手机" prop="phone">
          <el-input
            type="number"
            v-model="userForm.phone"
            clearable
            placeholder="请输入手机号"
          ></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input
            type="email"
            v-model="userForm.email"
            clearable
            placeholder="请输入邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="userForm.gender" placeholder="请选择性别">
            <el-option label="保密" :value="0"></el-option>
            <el-option label="男" :value="1"></el-option>
            <el-option label="女" :value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-date-picker
            v-model="userForm.birthday"
            type="date"
            placeholder="选择日期"
            value-format="yyyy-MM-dd"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="家庭地址">
          <el-input v-model="userForm.addr" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账户" prop="username">
          <el-input
            v-model="userForm.username"
            autocomplete="off"
            placeholder="账户默认输入和手机号一样"
            clearable
            :disabled="userForm.id != ''"
          ></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="userForm.status">
            <el-radio :label="true">开启</el-radio>
            <el-radio :label="false">锁定</el-radio>
          </el-radio-group>
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
import { getUserList, saveOrUpdateUser } from "../../../api/user";
import { isValidPhone } from "../../../utils/validate";

const defaultUser = {
  id: "",
  realname: "", // 真实姓名
  phone: "",
  email: "",
  gender: "",
  birthday: "",
  addr: "",
  username: "", // 账户/用户名
  status: false // 状态
};
// 自定义手机号校验规则
var validPhone = (rule, value, callback) => {
  if (!value) {
    callback(new Error("请输入手机号码"));
  } else if (!isValidPhone(value)) {
    callback(new Error("请输入正确的11位手机号码"));
  } else {
    callback();
  }
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
      userList: [], // 用户列表
      dialogTitle: "",
      dialogUser: false,
      userForm: Object.assign({}, defaultUser), // 用户表单
      userFormRules: {
        realname: [
          { required: true, message: "请填写真实姓名", trigger: "blur" }
        ],
        phone: [{ required: true, trigger: "blur", validator: validPhone }],
        email: [
          { required: true, message: "请输入邮箱地址", trigger: "blur" },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        username: [
          { required: true, message: "请填写用户账户", trigger: "blur" }
        ],
        status: [{ required: true, message: "请选择账户状态", trigger: "blur" }]
      }
    };
  },
  created() {
    this.initData();
  },
  methods: {
    // 初始化
    initData() {
      getUserList(this.query).then(res => {
        let data = res.data.page;
        this.userList = data.records;
        this.total = data.total;
      });
    },
    // 搜索
    search() {},
    // 添加用户
    handleAdd() {
      this.dialogTitle = "添加用户";
      this.dialogUser = true;
    },
    // 编辑
    handleEdit(row) {
      this.dialogTitle = "修改用户";
      this.dialogUser = true;
      this.userForm = JSON.parse(JSON.stringify(row));
    },
    handleSubmit() {
      this.$refs.userForm.validate(valid => {
        if (valid) {
          saveOrUpdateUser(this.userForm).then(res => {
            this.$message.success(res.message);
            this.initData();
            this.closeDialog();
          });
        } else {
          return false;
        }
      });
    },
    // 删除
    handleDelete() {},
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
      this.dialogUser = false;
      this.userForm = Object.assign({}, defaultUser);
      this.$refs.userForm.clearValidate();
    }
  },
  watch: {
    // 监听手机号变化，用户账户默认为手机号
    "userForm.phone"(val) {
      if (this.userForm.id === "") {
        this.userForm.username = val;
      }
    }
  }
};
</script>

<style scoped></style>
