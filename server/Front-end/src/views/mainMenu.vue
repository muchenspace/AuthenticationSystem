
<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom
} from '@element-plus/icons-vue'
import avatar from '@/assets/default.png'
</script>

<template>
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                router>
                <el-menu-item index="/app">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>应用</span>
                </el-menu-item>
                <el-menu-item index="/key" >
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>卡密</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
      
            <!-- 中间区域 -->
            <el-main>
               <router-view>

               </router-view>
            </el-main>
            <!-- 底部区域 -->
            <el-footer>网络验证  by 沐辰</el-footer>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

    

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #a79e9e;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>