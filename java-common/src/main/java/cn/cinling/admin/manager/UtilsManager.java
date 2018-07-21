package cn.cinling.admin.manager;

public class UtilsManager {
    private static UtilsManager shareInstance = null;
    private UtilsManager() {

    }

    private static UtilsManager GetInstance() {
        if (UtilsManager.shareInstance == null) {
            UtilsManager.shareInstance = new UtilsManager();
        }
        return UtilsManager.shareInstance;
    }
}
