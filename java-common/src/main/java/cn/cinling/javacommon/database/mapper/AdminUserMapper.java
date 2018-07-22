package cn.cinling.javacommon.database.mapper;

import cn.cinling.javacommon.database.entity.AdminUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    /**
     * @param adminUserEntity 管理员账号对象
     * @return 插入成功的数目
     */
    int Insert(AdminUserEntity adminUserEntity);

    /**
     * @return 管理员数据列表
     */
    List<AdminUserEntity> SelectAll();

    /**
     * @return 查询数据表中的数据条数
     */
    int SelectCount();

    /**
     * @param account 账号
     * @return 用户数据
     */
    AdminUserEntity SelectByAccount(String account);
}
