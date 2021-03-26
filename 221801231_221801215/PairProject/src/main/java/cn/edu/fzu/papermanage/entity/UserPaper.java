package cn.edu.fzu.papermanage.entity;

import javax.persistence.*;

/**
 * user_paper表对应实体类，有两个主键：userid，paperid
 */
@Table(name = "user_paper")
@Entity
@IdClass(UserPaperPrimaryKey.class)
public class UserPaper {
    @Id
    @Column(name = "userid")
    private Integer userId;
    @Id
    @Column(name = "paperid")
    private Integer paperId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}
