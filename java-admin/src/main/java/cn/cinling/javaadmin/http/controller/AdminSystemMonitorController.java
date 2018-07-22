package cn.cinling.javaadmin.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统监控的控制器
 */
@RequestMapping("/admin-system-monitor")
@Controller
public class AdminSystemMonitorController extends BaseController {
//    @Autowired
//    AdminSystemMonitorService systemMonitorService;

    @RequestMapping("/")
    public String Home() {
        return "admin-system-monitor/home";
    }

    @RequestMapping("/home")
    public String HomeWithSuffix() {
        return this.Home();
    }

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
}
