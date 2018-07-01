package cinling.admin.model;

import net.minidev.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseModel {

    private static ApiResponseModel shareInstance = null;
    private ApiResponseModel() {

    }
    public static ApiResponseModel GetInstance() {
        if (ApiResponseModel.shareInstance == null) {
            ApiResponseModel.shareInstance = new ApiResponseModel();
        }
        return ApiResponseModel.shareInstance;
    }

    public static final int SUCC_SUCC = 1;      // 成功
    public static final int SUCC_LOGIN = 2;     // 登录成功

    public static final int FAIL_FAIL = -1;     // 失败
    public static final int FAIL_EMPTY_USERNAME_OR_PASSWORD = -2;   // 账号或密码为空
    public static final int FAIL_ADMIN_USER_WAS_EXISTS = -3;    // 管理员已经存在


    Map<Integer, String> codeMsgMap = null;
    /**
     * @return code 对应 msg 的 map 集合
     */
    private Map<Integer, String> GetCodeMsgMap() {
        if (this.codeMsgMap == null) {
            this.codeMsgMap = new HashMap<>();

            this.codeMsgMap.put(SUCC_SUCC, "success");
            this.codeMsgMap.put(SUCC_LOGIN, "login success");

            this.codeMsgMap.put(FAIL_FAIL, "fail");
            this.codeMsgMap.put(FAIL_EMPTY_USERNAME_OR_PASSWORD, "username or password is empty");
            this.codeMsgMap.put(FAIL_ADMIN_USER_WAS_EXISTS, "admin user was exists");
        }
        return this.codeMsgMap;
    }

    /**
     * @param code 状态码
     * @return 状态消息
     */
    private String GetMsgByCode(int code) {
        Map<Integer, String> codeMsgMap = this.GetCodeMsgMap();

        if (!codeMsgMap.containsKey(code)) {
            return "";
        }
        return codeMsgMap.get(code);
    }


    /**
     * @param code 状态吗
     * @param data 返回的数据
     * @return json 消息的字符串
     */
    public String ReturnJson(int code, String data) {
        return "{\"code\":" + code + ",\"msg\":\"" + this.GetMsgByCode(code) + "\",\"data\":\"" + data + "\"}";
    }

    /**
     * @param code 状态吗
     * @param jsonObject 返回的数据
     * @return 消息的字符串
     */
    public String ReturnJson(int code, JSONObject jsonObject) {
        return this.ReturnJson(code, jsonObject.toJSONString());
    }

    /**
     * @param code 状态吗
     * @param map 返回的数据
     * @return 消息的字符串
     */
    public String ReturnJson(int code, Map<String, ?> map) {
        return ReturnJson(code, JSONObject.toJSONString(map));
    }

    /**
     *
     * @param code 状态吗
     * @return 消息字符串
     */
    public String ReturnJson(int code) {
        return this.ReturnJson(code, "");
    }
}
