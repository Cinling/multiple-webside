package cn.cinling.admin.database.mapper;

import cn.cinling.admin.database.entity.AdminSystemMonitorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminSystemMonitorMapper {
    List<AdminSystemMonitorEntity> SelectByLimit(@Param("skip") int skip, @Param("limit") int limit);

    void Insert(AdminSystemMonitorEntity systemMonitorEntity);
}
