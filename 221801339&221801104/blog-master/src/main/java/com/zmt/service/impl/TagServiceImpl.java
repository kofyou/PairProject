package com.zmt.service.impl;


import com.zmt.NotFoundException;
import com.zmt.dao.TagRepository;
import com.zmt.po.Tag;
import com.zmt.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签实现
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;


    @Transactional
    @Override
    public com.zmt.po.Tag saveTag(com.zmt.po.Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public com.zmt.po.Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Page<com.zmt.po.Tag> ListTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<com.zmt.po.Tag> ListTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    @Override
    public List<com.zmt.po.Tag> ListTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<com.zmt.po.Tag> ListTag(String ids) {
        return tagRepository.findAll(convertToList(ids));
    }

    @Override
    public com.zmt.po.Tag updateTag(Long id, com.zmt.po.Tag tag) {
        com.zmt.po.Tag t = tagRepository.findOne(id);
        if(t==null){
            throw new NotFoundException("不存在该标签");
        }

        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    /*将字符串转化为集合*/
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
