<template>
    <el-dialog v-model="DialogVisible" title="创建key" width="500" >
        <el-form label-width="auto" style="max-width: 600px">
            <div style="margin-bottom: 20px">
                <span>数量: </span>
                <el-input v-model="createKeyInfo.count" style="width: 350px" placeholder="数量" />
            </div>
            <el-dropdown @command="setKeyType">
                <el-button type="primary"><span>卡密类型</span><el-icon><arrow-down /></el-icon></el-button>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item command="1">天卡</el-dropdown-item>
                        <el-dropdown-item command="2">周卡</el-dropdown-item>
                        <el-dropdown-item command="3">月卡</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="DialogVisible = false">取消</el-button>
                <el-button type="primary" @click="createKey">确定</el-button>
            </div>
        </template>
    </el-dialog>
    <!----------------------------------------------------------->

    <!--app选择-->
    <el-dropdown @command="setApp">
        <el-button type="primary">
            <span>选择app</span><el-icon class="el-icon--right"><arrow-down /></el-icon>
        </el-button>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item v-for="(item) in apps" :command="item.id">{{ item.name }}</el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
    <!--创建卡密-->
    <el-button type="primary" @click="DialogVisible = true">创建卡密</el-button>
    <!--卡密搜索-->
    <el-form :inline="true" style="margin-top: 20px;">
        <el-form-item label="搜索卡密">
      <el-input  v-model="searchInfo" placeholder="请输入"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="search">搜索</el-button>
    </el-form-item>
    </el-form>
    <!--key表-->
    <el-table :data="keys" style="width: 100%">
        <el-table-column label="序号" width="100" type="index"> </el-table-column>
        <el-table-column label="卡密" prop="content"></el-table-column>
        <el-table-column label="到期时间" prop="maturityTime"></el-table-column>
        <el-table-column label="操作" width="100">
            <template #default="{ row }">
                <el-button @click="deleteKey(row)" :icon="Delete" circle plain type="danger"></el-button>
            </template>
        </el-table-column>
        <template #empty>
            <el-empty description="没有数据" />
        </template>
    </el-table>
</template>

<script setup>
import { ref } from 'vue'
import * as app from "../../api/app.js"
import * as key from "../../api/key.js"
import { ElMessage } from 'element-plus'
import { ArrowDown } from '@element-plus/icons-vue'
import { onMounted } from 'vue';
import {
    Delete
} from '@element-plus/icons-vue'

const searchInfo = ref()
const apps = ref()
const theApp = ref(0)
const keys = ref()
const DialogVisible = ref()
const createKeyInfo = ref({
    count: "1",
    appId: theApp,
    type: 1
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


const getKey = async () => {
    const result = await key.findByAppId(theApp.value)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    keys.value = result.data
    ElMessage.success("key获取成功")
}

const createKey = async () => {
    if (createKeyInfo.value.appId <= 0) {
        ElMessage.error("请选择应用！！");
        return;
    }

    const result = await key.add(createKeyInfo.value)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    ElMessage.success("key创建成功")
    getKey();
}

const deleteKey = async(row)=>
{
    const result = await key.deleteKey(row.id)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    ElMessage.success("删除成功")
    getKey();
}
const search = async()=>
{
    const result = await key.findByKey(searchInfo.value)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    ElMessage.success("搜索成功");
    console.log(result.data)
    keys.value = [result.data];
}
/////////////////////////////////////////////////////////////////////////
const setApp = (command) => {
    ElMessage.success(`当前app为 ${command}`);
    theApp.value = command;
    getKey();
}

const setKeyType = (command) => {
    createKeyInfo.value.type = command;
}

onMounted(() => {
    getApp();
})

</script>
