package com.solution.repository;

import com.solution.entity.response.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TweetDetailRepository extends JpaRepository<Tweet, Long> {
    Optional<Tweet> findByTweetId(Long tweetId);
}
