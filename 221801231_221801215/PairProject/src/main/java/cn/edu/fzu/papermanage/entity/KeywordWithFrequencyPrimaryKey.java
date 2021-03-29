package cn.edu.fzu.papermanage.entity;

import java.io.Serializable;

/**
 * keyword_with_frequency视图的“主键”
 */
public class KeywordWithFrequencyPrimaryKey implements Serializable {
    private String keyword;
    private String publishYear;
    private String source;
}
