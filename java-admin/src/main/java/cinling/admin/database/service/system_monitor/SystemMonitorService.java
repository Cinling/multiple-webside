package cinling.admin.database.service.system_monitor;

import cinling.admin.database.entity.SystemMonitorEntity;

import java.util.List;

public interface SystemMonitorService {
    /**
     * 根据页码查询
     * @param page 显示的页码
     * @param rows 每页的数据
     * @return 数据列表
     */
    List<SystemMonitorEntity> GetByPage(int page, int rows);

    /**
     * 插入一条数据
     */
    void Add(SystemMonitorEntity systemMonitorEntity);
}
