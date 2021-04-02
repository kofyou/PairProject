package com.zmt.service.impl;

import com.zmt.NotFoundException;
import com.zmt.dao.BlogRepository;
import com.zmt.po.Blog;
import com.zmt.po.Type;
import com.zmt.service.BlogService;
import com.zmt.util.MarkdownUtils;
import com.zmt.util.MyBeanUtils;
import com.zmt.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @Auther: 南迪叶先生:https://www.cnblogs.com/ye888/
 * @Date: 2019/10/3
 * @Description: com.yrp.service.impl
 * @version: 1.0
 */
@Service
public class BlogServiceImpl implements BlogService
{

    @Autowired
    private BlogRepository blogRepository;


    @Override
    public com.zmt.po.Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public com.zmt.po.Blog getAadConvertBlog(Long id) {
        com.zmt.po.Blog blog = blogRepository.findOne(id);
        if (blog == null){
            throw new com.zmt.NotFoundException("该博客不存在！");
        }
        com.zmt.po.Blog b = new com.zmt.po.Blog();
        BeanUtils.copyProperties(blog,b);
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(b.getContent()));
        blogRepository.updateViews(id);
        return b;
    }

    @Override
    public Page<com.zmt.po.Blog> ListBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<com.zmt.po.Blog>() {
            /**
             *
             * @param root 要查询的对象
             * @param cq   查询的一个条件容器
             * @param cb    设置具体某一个条件的表达式(模糊查询)
             * @return
             */
            @Override
            public Predicate toPredicate(Root<com.zmt.po.Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<com.zmt.po.Blog> ListBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<com.zmt.po.Blog> ListBlog(Long tagId, Pageable pageable) {

        return blogRepository.findAll(new Specification<com.zmt.po.Blog>() {
            @Override
            public Predicate toPredicate(Root<com.zmt.po.Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        }, pageable);
    }

    @Override
    public Page<com.zmt.po.Blog> ListBlog(Pageable pageable, String query) {
        return blogRepository.findByQuery(query,pageable);
    }

    @Override
    public List<com.zmt.po.Blog> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<com.zmt.po.Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<com.zmt.po.Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    /**
     * 发布博客
     *          (如何是新增博客,需要添加创建时间和修改时间、以及初始化浏览量view = 0)
     *          (如果是修改博客,需要添加修改时间)
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public com.zmt.po.Blog saveBlog(com.zmt.po.Blog blog) {
        //博客新增
        return blogRepository.save(blog);
    }

    /**
     * 所谓的修改其实是查询+保存
     * @param id
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public com.zmt.po.Blog updateBlog(Long id, com.zmt.po.Blog blog) {
        Blog b = blogRepository.findOne(id);
        //判断是否存在这条数据
        if(b == null){
            throw new NotFoundException("该博客不存在");
        }
        b.setUpdateTime(new Date());
        //copy有值属性 不覆盖
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }
}
