//package cn.cinling.javacommon.http.controller;
//
//import cn.cinling.javacommon.database.entity.AdminSystemMonitorEntity;
//import cn.cinling.javacommon.database.service.admin_system_monitor.AdminSystemMonitorService;
//import net.minidev.json.JSONArray;
//import net.minidev.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
///**
// * 系统监控的控制器
// */
//@RequestMapping("/admin-system-monitor")
//@Controller
//public class AdminSystemMonitorController extends BaseController {
//    @Autowired
//    AdminSystemMonitorService systemMonitorService;
//
//    @RequestMapping("/")
//    public String Home() {
//        return "admin-system-monitor/home";
//    }
//
//    @RequestMapping("/home")
//    public String HomeWithSuffix() {
//        return this.Home();
//    }
//
//    @ResponseBody
//    @PostMapping("/get-system-info")
//    public String GetSystemInfo() {
//        List<AdminSystemMonitorEntity> systemMonitorEntityList = systemMonitorService.GetByPage(1, 1440 * 7);
//        JSONArray jsonArray = new JSONArray();
//        int count = systemMonitorEntityList.size();
//        for (int i = 0; i < count; ++i) {
//            AdminSystemMonitorEntity systemMonitorEntity = systemMonitorEntityList.get(0);
//
//            JSONObject systemMonitorObject = new JSONObject();
//            systemMonitorObject.put("mt", systemMonitorEntity.getMemoryTotal());
//            systemMonitorObject.put("mu", systemMonitorEntity.getMemoryUse());
//            systemMonitorObject.put("dt", systemMonitorEntity.getDiskTotal());
//            systemMonitorObject.put("du", systemMonitorEntity.getDiskUse());
//            systemMonitorObject.put("t", systemMonitorEntity.getTime());
//
//            jsonArray.appendElement(systemMonitorObject);
//
//            // 制空对象，让 jvm 可释放内存
//            systemMonitorEntityList.remove(0);
//        }
//
//        JSONObject retJsonObject = new JSONObject();
//        retJsonObject.put("count", count);
//        retJsonObject.put("data", jsonArray);
//
//        return retJsonObject.toJSONString();
//    }
//}
