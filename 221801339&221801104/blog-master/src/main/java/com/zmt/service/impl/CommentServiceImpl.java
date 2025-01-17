package com.zmt.service.impl;

import com.zmt.dao.CommentRepository;
import com.zmt.po.Comment;
import com.zmt.service.CommentService;
import com.zmt.util.CommentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Auther: 南迪叶先生:https://www.cnblogs.com/ye888/
 * @Date: 2019/10/10
 * @Description: com.yrp.service.impl
 * @version: 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<com.zmt.po.Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort("createTime");
        List<com.zmt.po.Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return CommentUtil.eachComment(comments);
    }

    @Transactional
    @Override
    public com.zmt.po.Comment saveComment(Comment comment) {
        //回复: 如果有父级,需要将父级set进来,之后在保存
        Long parentCommentId = comment.getParentComment().getId();
        System.out.println(parentCommentId);
        if(parentCommentId != -1){
            comment.setParentComment(commentRepository.findOne(parentCommentId));
        } else {
            //发布评论:
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

}
