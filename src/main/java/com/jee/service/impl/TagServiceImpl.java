package com.jee.service.impl;

import com.jee.dao.TagDao;
import com.jee.pojo.Tag;
import com.jee.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public Tag getById(Long id) {
        return tagDao.getById(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagDao.getByName(name);
    }

    @Override
    public List<Tag> getUseTag() {
        return tagDao.getUseTag();
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long along : longs) {
            tags.add(tagDao.getById(along));
        }
        return tags;
    }

    private List<Long> convertToList(String text) {
        List<Long> list = new ArrayList<>();
        if(!"".equals(text) && text!=null){
            String[] idarray = text.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }
}
