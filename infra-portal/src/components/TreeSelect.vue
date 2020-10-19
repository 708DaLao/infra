<!--
    结合elementui的select下拉选择器和tree树组件进行二次封装，实现下拉选择树
    使用示例：
    <tree-select
      :data="data // 树形数据
      :defaultProps="defaultProps" // 树节点默认属性，默认children和label
      :nodeKey="id" // 树节点唯一标识，默认id
      v-model=""  // 绑定字段值
      clearable // 清空选项，默认为true
    ></tree-select>
-->
<template>
  <el-select
    ref="select"
    :value="valueId"
    @input="$emit('input', $event)"
    :clearable="clearable"
    @clear="handleClear"
    placeholder="请选择"
  >
    <el-option
      :label="valueLabel"
      :value="valueId"
      style="height: auto; padding: 0"
    >
      <el-tree
        ref="tree"
        :data="data"
        :props="defaultProps"
        :node-key="nodeKey"
        :check-strictly="checkStrictly"
        :expand-on-click-node="false"
        :default-expanded-keys="defaultExpandedKey"
        @node-click="handleNodeClick"
      >
      </el-tree>
    </el-option>
  </el-select>
</template>

<script>
export default {
  name: "TreeSelect",
  props: {
    value: [Number, String], // 传递的值
    // 树形结构数据
    data: {
      type: Array,
      default: () => {
        return [];
      }
    },
    defaultProps: {
      type: Object,
      default: () => {
        return {
          children: "children",
          label: "label"
        };
      }
    },
    // 树节点唯一标识
    nodeKey: {
      type: String,
      default: () => {
        return "id";
      }
    },
    // 显示复选框情况下，是否严格遵循父子不互相关联
    checkStrictly: {
      type: Boolean,
      default: () => {
        return false;
      }
    },
    //可清空选项
    clearable: {
      type: Boolean,
      default: () => {
        return true;
      }
    }
  },
  data() {
    return {
      valueId: this.value, // 初始值
      valueLabel: "",
      defaultExpandedKey: [] // 默认展开
    };
  },
  mounted() {
    this.initData();
  },
  watch: {
    value() {
      this.valueId = this.value;
      this.initData();
    }
  },
  methods: {
    // 初始化展示数据
    initData() {
      if (this.valueId && this.valueId != "0") {
        // 初始化显示label
        this.valueLabel = this.$refs.tree.getNode(this.valueId).data[
          this.defaultProps.label
        ];
        this.$refs.tree.setCurrentKey(this.valueId); // 设置默认选中
        this.defaultExpandedKey = [this.valueId]; // 设置默认展开
      } else {
        this.valueLabel = "";
      }
    },
    // 树节点点击事件
    handleNodeClick(data) {
      this.valueLabel = data[this.defaultProps.label]; //获取label
      this.valueId = data[this.nodeKey]; //获取value
      this.$emit("input", this.valueId); // 传值给父组件
      this.$refs.select.blur(); // 失去焦点
    },
    // 清空选择
    handleClear() {
      this.valueLabel = "";
      this.valueId = null;
      this.defaultExpandedKey = [];
    }
  }
};
</script>

<style scoped></style>
