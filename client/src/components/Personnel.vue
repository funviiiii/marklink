<template>
</template>

<script setup>
import {useRouter} from "vue-router";
import {ElMessage} from "element-plus";
import {onMounted} from "vue";

const props = defineProps(["right"]);
const router = useRouter();

onMounted(() => {
  if (!props.right) {
    ElMessage.warning("当前账户没有权限访问该页面")
    router.push("/index")
  }
})
</script>

<style scoped>

</style>