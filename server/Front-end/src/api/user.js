import request from "../utils/request.js"

function userRegisterService(registerData)
{
    const params = new URLSearchParams()
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post("user/register", params)
}

function userLoginService(registerData)
{
    const params = new URLSearchParams()
    for (let key in registerData) {
        params.append(key, registerData[key])
    }
    return request.post("user/login", params)
}

export{userRegisterService,userLoginService}