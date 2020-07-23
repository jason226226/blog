package com.jee.service;

import com.jee.pojo.Type;

import java.util.List;

public interface TypeService {

    int saveType(Type type);
    Type getType(Long id);
    List<Type> getUseType();
    List<Type> getAllType();
    Type getTypeByName(String name);
    int updateType(Type type);
    int deleteType(Long id);
}
