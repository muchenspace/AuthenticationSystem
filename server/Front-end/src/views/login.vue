<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { ref } from 'vue'
import * as user from "../api/user.js"
import { useRouter } from "vue-router"
import { useTokenStore } from "../stores/token.js"
import { ElMessage } from 'element-plus';

const tokenStore = useTokenStore()
const router = useRouter()
//控制注册与登录表单的显示， 默认显示注册
const isRegister = ref(false)
const RegisterData = ref({
    name: "",
    passwd: ""
})

const loginData = ref({
    name: "",
    passwd: ""
})

const rules = {
    name: [
        { required: true, message: "请输入用户名", trigger: "blur" },
        { min: 6, max: 16, message: "用户名不合法", trigger: "blur" }
    ],
    passwd: [
        { required: true, message: "请输入密码", trigger: "blur" },
        { min: 5, max: 16, message: "密码不合法", trigger: "blur" }
    ]
}

const login = async () => {
    let result = await user.userLoginService(loginData.value)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    tokenStore.setToken(result.data)
    console.log(result.data)
    ElMessage.success("登录成功")
    router.push("/")
}

const register = async () => {
    let result = await user.userRegisterService(RegisterData.value)
    if (result.code != 0) {
        ElMessage.error(result.message);
        return;
    }
    ElMessage.success("注册成功")
}

</script>

<template>
    <div class="login">
        <!-- 注册表单 -->
        <div class="loginFrom">
            <el-form ref="form" size="large" autocomplete="off" :model=RegisterData v-if="isRegister" :rules="rules">
                <el-form-item>
                    <h1>注册</h1>
                </el-form-item>
                <el-form-item prop="name">
                    <el-input :prefix-icon="User" placeholder="请输入用户名" v-model=RegisterData.name></el-input>
                </el-form-item>
                <el-form-item prop="passwd">
                    <el-input :prefix-icon="Lock" type="password" placeholder="请输入密码"
                        v-model=RegisterData.passwd></el-input>
                </el-form-item>
                <!-- 注册按钮 -->
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        注册
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false">← 返回</el-link>
                </el-form-item>
            </el-form>
            <!-- 登录表单 -->
            <el-form ref="form" size="large" autocomplete="off" :model=loginData v-else>
                <el-form-item>
                    <h1>登录</h1>
                </el-form-item>
                <el-form-item>
                    <el-input v-model=loginData.name :prefix-icon="User" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input name="password" v-model=loginData.passwd :prefix-icon="Lock" type="password"
                        placeholder="请输入密码"></el-input>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button @click="login" class="button" type="primary" auto-insert-space>登录</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true">
                        注册 →
                    </el-link>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<style lang="scss" scoped>
/* 样式 */
.login {
    height: 100vh;
    background: #d6d6d685;
    display: flex;
    justify-content: center;
    align-items: center;

    .loginFrom {
        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%;
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }
}
</style>