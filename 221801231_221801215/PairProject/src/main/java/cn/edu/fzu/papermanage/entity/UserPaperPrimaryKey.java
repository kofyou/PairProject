package cn.edu.fzu.papermanage.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * {@link UserPaper}的双主键userid，paperid对应构造的主键类
 */
public class UserPaperPrimaryKey implements Serializable {
    private Integer userId;
    private Integer paperId;
}
