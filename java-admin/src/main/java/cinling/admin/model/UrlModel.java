package cinling.admin.model;

import cinling.admin.model.exception.UrlModelException;

public class UrlModel {
    private static UrlModel shareInstance = null;
    private UrlModel () {
        this.prefix = "java-admin";
        this.fileVersion = String.valueOf(System.currentTimeMillis());
    }
    public static UrlModel GetInstance() {
        if (UrlModel.shareInstance == null) {
            UrlModel.shareInstance = new UrlModel();
        }
        return UrlModel.shareInstance;
    }

    private String prefix;
    private String fileVersion;

    /**
     * @param projectUri 目标相对路径。 如：admin/login
     * @return http 请求实际的绝对路径。如：/java-admin/admin/login
     */
    public String To(String projectUri) {
        return this.ToClientRequestUrl(projectUri);
    }

    /**
     * @param projectUri 目标相对路径。 如：admin/login
     * @return http 请求实际的绝对路径。如：/java-admin/admin/login
     */
    public String ToClientRequestUrl(String projectUri) {
        String absUri = "/" + this.prefix;

        if (!projectUri.startsWith("/")) {
            absUri += "/";
        }
        absUri += projectUri;

        if (projectUri.endsWith(".css") || projectUri.endsWith(".js") || projectUri.endsWith(".jpg")) {
            String fileVersion;
            if ("DEBUG".endsWith("DEBUG")) {
                fileVersion = String.valueOf(System.currentTimeMillis());
            } else {
                fileVersion = this.fileVersion;
            }
            absUri += "?v=" + fileVersion;
        }

        return absUri;
    }

    /**
     *
     * @param uri 除域名外的绝对路径。如：/java-admin/admin/login 或 java-admin/admin/login
     * @return 项目的基本路径。如：admin/login
     */
    public String ToProjectUri(String uri) throws UrlModelException {
        if (uri.startsWith("/" + this.prefix)) {
            return uri.substring(this.prefix.length() + 2);
        } else if (uri.startsWith(this.prefix)) {
            return uri.substring(this.prefix.length() + 1);
        } else if (uri.equals("/") || uri.equals("")) {
            return uri;
        } else {
            return uri;
        }

//        throw new UrlModelException("uri路径不正确");
    }
}
