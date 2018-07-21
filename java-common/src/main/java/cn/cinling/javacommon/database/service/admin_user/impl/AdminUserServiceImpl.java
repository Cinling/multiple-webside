package cn.cinling.javacommon.database.service.admin_user.impl;

import cn.cinling.javacommon.database.entity.AdminUserEntity;
import cn.cinling.javacommon.database.mapper.AdminUserMapper;
import cn.cinling.javacommon.database.service.admin_user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "adminUserService")
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public int AddAdminUser(AdminUserEntity adminUserEntity) {
        return this.adminUserMapper.Insert(adminUserEntity);
    }

    @Override
    public AdminUserEntity GetByAccount(String account) {
        List<AdminUserEntity> adminList = adminUserMapper.SearchByAccount(account);
        if (adminList.size() == 0) {
            return null;
        }

        return adminList.get(0);
    }

    @Override
    public List<AdminUserEntity> GetAll() {
        return this.adminUserMapper.SelectAll();
    }

    @Override
    public int GetCount() {
        return this.adminUserMapper.SelectCount();
    }
}
