package com.jee.service;

import com.jee.pojo.Tag;

import java.util.List;

public interface TagService {

    int saveTag(Tag tag);
    int deleteTag(Long id);
    int updateTag(Tag tag);
    Tag getById(Long id);
    Tag getByName(String name);
    List<Tag> getUseTag();
    List<Tag> getTagByString(String text);
    List<Tag> getAllTag();
}
