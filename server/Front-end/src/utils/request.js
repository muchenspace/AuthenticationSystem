//定制请求的实例

//导入axios  npm install axios
import axios from 'axios';
import { ElMessage } from 'element-plus';
import router from "@/router"

//定义一个变量,记录公共的前缀  ,  baseURL
const baseURL = '/api';
const instance = axios.create({ baseURL })


//添加响应拦截器
instance.interceptors.response.use(
    result => {
        return result.data;
    },
    err => {
        if (err.response.status == 401) {
            ElMessage.error("登录失败!!!")
            router.push("/login")
        }
        else {
            ElMessage.error('服务异常');
            return Promise.reject(err);//异步的状态转化成失败的状态
        }
    }
)

export default instance;