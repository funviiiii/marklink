<template>
  <el-form
      label-width="100"
      class="el-col-offset-6"
  >
    <el-form-item label="真实姓名："
                  required
    >
      <el-input
          v-model="user.name"
          clearable
          class="el-col-10"
      ></el-input>
    </el-form-item>
    <el-form-item label="性别：">
      <el-select
          v-model="user.gender"
          class="el-col-10"
      >
        <el-option value="保密"></el-option>
        <el-option value="男"></el-option>
        <el-option value="女"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item
        label="生日："
    >
      <el-date-picker
          v-model="user.birthday"
          value-format="YYYY-M-D"
      >
      </el-date-picker>
    </el-form-item>
    <el-checkbox
        label="是否已婚"
        class="el-col-offset-4"
    ></el-checkbox>
  </el-form>
</template>

<script setup>
import {reactive} from "vue";

const user = reactive({
  name: "",
  gender: "保密",
  birthday: "",
  is_married: false
})
</script>

<style scoped>

</style>