package cn.cinling.javaadmin.model;

import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

    public static final int SUCC_SUCC = 1;      // 成功
    public static final int SUCC_LOGIN = 2;     // 登录成功

    public static final int FAIL_FAIL = -1;     // 失败
    public static final int FAIL_EMPTY_ACCOUNT_OR_PASSWORD = -2;   // 账号或密码为空
    public static final int FAIL_ADMIN_USER_WAS_EXISTS = -3;    // 管理员已经存在
    public static final int FAIL_USER_NOT_EXISTS = -4;  // 账号不存在
    public static final int FAIL_PASSWORD_ERROR = -5;   // 密码错误


    /**
     * 返回的状态码
     */
    private int code;

    /**
     * 返回的数据
     */
    private String data = "";

    public ApiResponse(int code) {
        this.code = code;
    }

    public ApiResponse(int code, String data) {
        this.code = code;
        this.data = data;
    }

    private static Map<Integer, String> codeMsgMap = null;
    /**
     * @return code 对应 msg 的 map 集合
     */
    private Map<Integer, String> GetCodeMsgMap() {
        if (ApiResponse.codeMsgMap == null) {
            ApiResponse.codeMsgMap = new HashMap<>();

            ApiResponse.codeMsgMap.put(SUCC_SUCC, "success");
            ApiResponse.codeMsgMap.put(SUCC_LOGIN, "login success");

            ApiResponse.codeMsgMap.put(FAIL_FAIL, "fail");
            ApiResponse.codeMsgMap.put(FAIL_EMPTY_ACCOUNT_OR_PASSWORD, "username or password is empty");
            ApiResponse.codeMsgMap.put(FAIL_ADMIN_USER_WAS_EXISTS, "admin user was exists");
            ApiResponse.codeMsgMap.put(FAIL_USER_NOT_EXISTS, "user not exists");
            ApiResponse.codeMsgMap.put(FAIL_PASSWORD_ERROR, "password error");
        }
        return ApiResponse.codeMsgMap;
    }

    /**
     * @param code 状态码
     * @return 状态消息
     */
    private String GetMsgByCode(int code) {
        Map<Integer, String> codeMsgMap = this.GetCodeMsgMap();

        if (!codeMsgMap.containsKey(code)) {
            return "unknown";
        }
        return codeMsgMap.get(code);
    }

    /**
     * @return json 消息的字符串
     */
    public String ReturnJson() {
        return "{\"code\":" + this.code + ",\"msg\":\"" + this.GetMsgByCode(code) + "\",\"data\":\"" + this.data + "\"}";
    }

//    /**
//     * @param code 状态吗
//     * @param data 返回的数据
//     * @return json 消息的字符串
//     */
//    public String ReturnJson(int code, String data) {
//        this.code = code;
//        this.data = data;
//        return this.ReturnJson();
//    }
//
//    /**
//     * @param code 状态吗
//     * @param jsonObject 返回的数据
//     * @return 消息的字符串
//     */
//    public String ReturnJson(int code, JSONObject jsonObject) {
//        this.code = code;
//        this.data = jsonObject.toJSONString();
//        return this.ReturnJson();
//    }
//
//    /**
//     * @param code 状态吗
//     * @param map 返回的数据
//     * @return 消息的字符串
//     */
//    public String ReturnJson(int code, Map<String, ?> map) {
//        this.code = code;
//        this.data = JSONObject.toJSONString(map);
//        return this.ReturnJson();
//    }
}
