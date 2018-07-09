package cinling.admin.database.mapper;

import cinling.admin.database.entity.AdminUserEntity;

import java.util.List;

public interface AdminUserMapper {
    /**
     * 插入一条数据
     * @param adminUserEntity 管理员账号对象
     * @return 插入成功的数目
     */
    int Insert(AdminUserEntity adminUserEntity);

    /**
     * 查询所有数据
     * @return 管理员数据列表
     */
    List<AdminUserEntity> SelectAll();

    /**
     * 查询所有数据的条数
     * @return 查询数据表中的数据条数
     */
    int SelectCount();

    /**
     * 根据账号查 管理员用户信息
     * @param account
     * @return
     */
    List<AdminUserEntity> SearchByAccount(String account);
}
