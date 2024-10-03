<template>
    <el-dialog v-model="addDialogVisible" title="添加应用" width="500" :before-close="handleClose">
        <el-input v-model="addAppData.name" style="width: 240px" placeholder="请输入" />
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="addDialogVisible = false">取消</el-button>
                <el-button type="primary" @click="addApp">确定</el-button>
            </div>
        </template>
    </el-dialog>

   
<!----------------------------------------------------------->
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>应用</span>
                <div class="extra">
                    <el-button @click="addDialogVisible = true" type="primary">添加应用</el-button>
                </div>
            </div>
        </template>
        <el-table :data="apps" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="id" width="100" prop="id"> </el-table-column>
            <el-table-column label="名称" prop="name"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button @click="deleteApp(row)" :icon="Delete" circle plain type="danger"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>

<script setup>
import { ref } from 'vue'
import * as app from "../../api/app.js"
import { ElMessage } from 'element-plus';
import { onMounted } from 'vue';
import {
    Delete
} from '@element-plus/icons-vue'


const addDialogVisible = ref(false)//添加app
const apps = ref(null)
const addAppData = ref({
    name: ""
})

const getApp = async () => {
    const result = await app.list()
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    apps.value = result.data
    ElMessage.success("app获取成功")
}
const deleteApp = async(row)=>
{
    const result = await app.deleteApp(row.id)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    ElMessage.success("删除成功")
    getApp();
}
const addApp = async ()=>
{
    const result = await app.add(addAppData.value)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    ElMessage.success("添加成功")
    addDialogVisible.value = false
    getApp();
}

onMounted(() => {
    getApp();
})
</script>