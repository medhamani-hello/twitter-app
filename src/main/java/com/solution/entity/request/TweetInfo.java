package com.solution.entity.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TweetInfo {
    private Long userId;
    private String text;
}
