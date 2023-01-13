package com.solution.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private Long tweetId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id")
    private UserDetail userDetail;
    @Column(name = "like_count", columnDefinition = "integer default 0")
    private Integer likeCount;
    @Column(name = "dislike_count", columnDefinition = "integer default 0")
    private Integer dislikeCount;
    private String text;
    @Column(name = "publish_date")
    private LocalDate publishDate;

}
