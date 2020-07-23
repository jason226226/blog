package com.jee.dao;

import com.jee.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    User queryByUsername(@Param("username") String username);
}
