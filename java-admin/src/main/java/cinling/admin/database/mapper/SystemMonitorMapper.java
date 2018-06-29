package cinling.admin.database.mapper;

import cinling.admin.database.entity.SystemMonitorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemMonitorMapper {
    List<SystemMonitorEntity> SelectByLimit(@Param("skip") int skip, @Param("limit") int limit);

    void Insert(SystemMonitorEntity systemMonitorEntity);
}
