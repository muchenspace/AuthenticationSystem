import request from "../utils/request.js"
import { useTokenStore } from "../stores/token.js"

function list() {
    const tokenstore = useTokenStore()
    return request.get("app/", { headers: { "Authorization": tokenstore.token } });
}
function add(data) {
    const tokenstore = useTokenStore()
    const params = new URLSearchParams()
    for (let key in data) {
        params.append(key, data[key])
    }
    return request.post("app/createApp", params, { headers: { "Authorization": tokenstore.token } })
}
function deleteApp(data) {
    const tokenstore = useTokenStore()
    return request.delete("app/", {params: {
        id: data
    },headers: {Authorization: tokenstore.token,} })
}
export { list, add, deleteApp }