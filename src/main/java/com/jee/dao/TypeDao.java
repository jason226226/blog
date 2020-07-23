package com.jee.dao;

import com.jee.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeDao {

    int saveType(Type type);
    Type getTypeById(Long id);
    Type getTypeByName(String name);
    //目前已使用的类型
    List<Type> getUseType();
    List<Type> getAllType();
    int deleteType(Long id);
    int updateType(Type type);
}
