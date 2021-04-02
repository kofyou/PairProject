package com.pair.papercrawler.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Paper {
    private Integer paperId;
    private String title;
    private String link;
    private String abstracts;
    private String magazine;
}
