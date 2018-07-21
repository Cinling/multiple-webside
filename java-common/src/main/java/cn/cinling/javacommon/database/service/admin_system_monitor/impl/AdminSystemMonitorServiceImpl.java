package cn.cinling.javacommon.database.service.admin_system_monitor.impl;

import cn.cinling.javacommon.database.entity.AdminSystemMonitorEntity;
import cn.cinling.javacommon.database.mapper.AdminSystemMonitorMapper;
import cn.cinling.javacommon.database.service.admin_system_monitor.AdminSystemMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("systemMonitorService")
public class AdminSystemMonitorServiceImpl implements AdminSystemMonitorService {
    @Autowired
    AdminSystemMonitorMapper systemMonitorMapper;

    @Override
    public List<AdminSystemMonitorEntity> GetByPage(int page, int rows) {
        int skip = (page - 1) * rows;
        return systemMonitorMapper.SelectByLimit(skip, rows);
    }

    @Override
    public void Add(AdminSystemMonitorEntity systemMonitorEntity) {
        if (systemMonitorEntity.getTime() == null) {
            systemMonitorEntity.setTime(Integer.valueOf(String.valueOf(System.currentTimeMillis() / 1000)));
        }

        systemMonitorEntity.setDiskTotal( Math.round(systemMonitorEntity.getDiskTotal() * 1000) / 1000.0 );
        systemMonitorEntity.setDiskUse( Math.round(systemMonitorEntity.getDiskUse() * 1000) / 1000.0 );

        systemMonitorMapper.Insert(systemMonitorEntity);
    }
}
