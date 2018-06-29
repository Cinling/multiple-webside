package cinling.admin.database.mapper;

import cinling.admin.database.entity.UserEntity;

import java.util.List;

public interface UserMapper {


    int insert(UserEntity record);



    List<UserEntity> selectUsers();
}