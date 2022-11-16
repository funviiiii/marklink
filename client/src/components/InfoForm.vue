<template>
  <el-form
      label-width="100"
      class="el-col-offset-6"
  >
    <el-form-item
        label="真实姓名："
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
          value-format="YYYY-MM-DD"
      >
      </el-date-picker>
    </el-form-item>
    <el-checkbox
        label="是否已婚"
        class="el-col-offset-4"
        v-model="user.is_married"
    ></el-checkbox>
  </el-form>
</template>

<script setup>
import {reactive, watch} from "vue";

const user = reactive({
  name: "",
  gender: "保密",
  birthday: "",
  is_married: false
})

let emit = defineEmits(["report-data"]);

watch(user, () => {
  emit("report-data", {
    name: user.name,
    gender: user.gender,
    birthday: user.birthday,
    isMarried: user.is_married ? 1 : 0
  })
})
</script>

<style scoped>

</style>