package cinling.admin.database.service.user;

import cinling.admin.database.entity.UserEntity;

import java.util.List;

public interface UserService {
    int addUser(UserEntity user);

    List<UserEntity> findAllUser(int pageNum, int pageSize);
}
