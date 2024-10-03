import request from "../utils/request.js"
import { useTokenStore } from "../stores/token.js"

function findByAppId(AppId) {
    const tokenstore = useTokenStore()
    return request.get("key/", {
        params: {
            appId: AppId
        }, headers: { "Authorization": tokenstore.token }
    });
}

function add(data) {
    const tokenstore = useTokenStore()
    const params = new URLSearchParams()
    for (let key in data) {
        params.append(key, data[key])
    }
    return request.post("key/createKey", params, { headers: { "Authorization": tokenstore.token } })
}

function deleteKey(keyId)
{
    const tokenstore = useTokenStore()
    return request.delete("key/", {params: {
        id: keyId
    },headers: {Authorization: tokenstore.token,} })
}

function findByKey(content)
{
    const tokenstore = useTokenStore()
    return request.get("key/findByKey", {
        params: {
            key: content
        }, headers: { "Authorization": tokenstore.token }
    });
}

export { findByAppId,add,deleteKey,findByKey }