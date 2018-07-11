package cn.cinling.admin.model.exception;

import cn.cinling.admin.model.ApiResponse;

public class AdminUserModelException extends Exception {
    private ApiResponse apiResponse;

    public AdminUserModelException (String msg, ApiResponse apiResponse) {
        super(msg);
        this.apiResponse = apiResponse;
    }

    public ApiResponse GetApiResponse() {
        if (this.apiResponse == null) {
            return new ApiResponse(ApiResponse.FAIL_FAIL, "没有创建出现异常的返回消息");
        }

        return this.apiResponse;
    }
}
