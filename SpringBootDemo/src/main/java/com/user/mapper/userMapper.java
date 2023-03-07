package com.user.mapper;

import com.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface userMapper {

    Integer userLogin(@Param("userName") String userName,@Param("password") String password );
    Integer userInsert(User user);
    List getUserInfo(User user);
    Integer updateUserInfo(User user);

    List addUser(User user);
}
