package cinling.admin.database.mapper;

import cinling.admin.database.entity.AdminSystemMonitorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminSystemMonitorMapper {
    List<AdminSystemMonitorEntity> SelectByLimit(@Param("skip") int skip, @Param("limit") int limit);

    void Insert(AdminSystemMonitorEntity systemMonitorEntity);
}
