package cn.cinling.javacommon.database.mapper;

import cn.cinling.javacommon.database.entity.AdminSystemMonitorEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminSystemMonitorMapper {
    List<AdminSystemMonitorEntity> SelectByLimit(@Param("skip") int skip, @Param("limit") int limit);

    void Insert(AdminSystemMonitorEntity systemMonitorEntity);
}
