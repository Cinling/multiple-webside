package cn.cinling.javaadmin.manager.exception;

import cn.cinling.javaadmin.model.ApiResponse;

public class AuthManagerException extends BaseManagerException {

    public ApiResponse getApiResponse() {
        return apiResponse;
    }

    private ApiResponse apiResponse;

    public AuthManagerException(String message, ApiResponse apiResponse) {
        super(message);
    }
}
