package com.jee.dao;

import com.jee.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {

    int saveTag(Tag tag);
    int deleteTag(Long id);
    int updateTag(Tag tag);
    Tag getById(Long id);
    Tag getByName(String name);
    List<Tag> getUseTag();
    List<Tag> getAllTag();
}
