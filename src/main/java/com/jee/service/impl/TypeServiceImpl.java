package com.jee.service.impl;

import com.jee.dao.TypeDao;
import com.jee.pojo.Type;
import com.jee.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public List<Type> getUseType() {
        return typeDao.getUseType();
    }

    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Override
    public int deleteType(Long id) {
        return 0;
    }
}
