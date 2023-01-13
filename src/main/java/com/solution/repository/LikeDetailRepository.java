package com.solution.repository;

import com.solution.entity.response.LikeDetail;
import com.solution.entity.response.Tweet;
import com.solution.entity.response.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDetailRepository extends JpaRepository<LikeDetail, Long> {
    List<LikeDetail> findByDetailsAndTweet(UserDetail userDetail, Tweet tweet);
    List<LikeDetail> findByTweet(Tweet tweet);
}
