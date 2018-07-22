//package cn.cinling.javaadmin.manager;
//
//import org.springframework.util.ResourceUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//
//public class AssetManager {
//    private static AssetManager shareInstance = null;
//    private AssetManager() {
//        this.prefix = "java-admin";
//    }
//    public static  AssetManager GetInstance() {
//        if (AssetManager.shareInstance == null) {
//            AssetManager.shareInstance = new AssetManager();
//        }
//        return AssetManager.shareInstance;
//    }
//
//
//    /**
//     * web访问路径的前缀
//     */
//    private String prefix;
//
//    /**
//     * @param projectUri 目标相对路径。 如：admin/login
//     * @return http 请求实际的绝对路径。如：/java-admin/admin/login
//     */
//    public String To(String projectUri) {
//        return this.ToClientRequestUrl(projectUri);
//    }
//
//    /**
//     * @param projectUri 目标相对路径。 如：admin/login
//     * @return http 请求实际的绝对路径。如：/java-admin/admin/login
//     */
//    public String ToClientRequestUrl(String projectUri) {
//        String absUri = "/" + this.prefix;
//
//        if (!projectUri.startsWith("/")) {
//            projectUri = "/" + projectUri;
//        }
//        absUri += projectUri;
//
//        if (projectUri.endsWith(".css") || projectUri.endsWith(".js") || projectUri.endsWith(".jpg")) {
//            String fileVersion;
//
//            String relativePath = "static" + projectUri;
//            try {
//                File staticFile = ResourceUtils.getFile("classpath:" + relativePath);
//                fileVersion = String.valueOf(staticFile.lastModified());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                fileVersion = "error" + System.currentTimeMillis();
//            }
//
//            absUri += "?v=" + fileVersion;
//        }
//
//        return absUri;
//    }
//
//    /**
//     *
//     * @param uri 除域名外的绝对路径。如：/java-admin/admin/login 或 java-admin/admin/login
//     * @return 项目的基本路径。如：admin/login
//     */
//    public String ToProjectUri(String uri) {
//        if (uri.startsWith("/" + this.prefix)) {
//            return uri.substring(this.prefix.length() + 2);
//        } else if (uri.startsWith(this.prefix)) {
//            return uri.substring(this.prefix.length() + 1);
//        } else if (uri.equals("/") || uri.equals("")) {
//            return uri;
//        } else {
//            return uri;
//        }
//    }
//
//
//
//
//
//
//
//    /**
//     * @return bootstrap 的 css 链接
//     */
//    public String BootstrapCssLink() {
//        return "https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css";
//    }
//
//    /**
//     * @return bootstrap 的 js 链接
//     */
//    public String BootstrapJsLink() {
//        return "https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js";
//    }
//
//    /**
//     * @return popper 的 js 链接
//     */
//    public String PopperJsLink() {
//        return "https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js";
//    }
//
//    /**
//     * @return Vue.js 的 js 链接
//     */
//    public String VueJsLink() {
//        return "https://cdn.bootcss.com/vue/2.5.16/vue.min.js";
//    }
//
//    /**
//     * @return awesome 的 css 链接
//     */
//    public String AwesomeCssLink() {
//        return "https://use.fontawesome.com/releases/v5.1.0/css/all.css";
//    }
//
//    /**
//     * @return jquery 的 js 链接
//     */
//    public String JqueryJsLink() {
//        return "https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js";
//    }
//}
