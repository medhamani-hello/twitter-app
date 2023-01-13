package com.solution.entity.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TweetRequest {
    private Long userId;
    private Long tweetId;
}
