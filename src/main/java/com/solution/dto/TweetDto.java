package com.solution.dto;

import com.solution.entity.response.UserDetail;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TweetDto {
    private Long tweetId;
    private UserDetail userDetail;
    private int likeCount;
    private int dislikeCount;
    private String text;
    private LocalDate publishDate;
}
