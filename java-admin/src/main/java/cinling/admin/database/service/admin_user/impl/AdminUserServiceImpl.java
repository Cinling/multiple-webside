package cinling.admin.database.service.admin_user.impl;

import cinling.admin.database.entity.AdminUserEntity;
import cinling.admin.database.mapper.AdminUserMapper;
import cinling.admin.database.service.admin_user.AdminUserService;
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
    public List<AdminUserEntity> GetAll() {
        return this.adminUserMapper.SelectAll();
    }

    @Override
    public int GetCount() {
        return this.adminUserMapper.SelectCount();
    }
}
