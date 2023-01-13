package com.solution.Service;

import com.solution.dto.TweetDto;
import com.solution.entity.response.LikeDetail;
import com.solution.payload.LikeResponse;
import com.solution.payload.PageResponse;
public interface TweetService {
    PageResponse getAllTweets(final int pageNo, final int pageSize);
    TweetDto addTweet(final Long userId, final String text);
    LikeDetail likeTweet(final Long userId, final Long tweetId);
    LikeDetail dislikeTweet(final Long userId, final Long tweetId);
    LikeResponse getDetails(final Long tweetId);
}
