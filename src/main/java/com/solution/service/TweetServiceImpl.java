package com.solution.service;

import com.solution.dto.LikeDetailDto;
import com.solution.dto.TweetDto;
import com.solution.entity.response.LikeDetail;
import com.solution.entity.response.Tweet;
import com.solution.entity.response.UserDetail;
import com.solution.payload.LikeResponse;
import com.solution.payload.PageResponse;
import com.solution.repository.LikeDetailRepository;
import com.solution.repository.TweetDetailRepository;
import com.solution.repository.UserDetailRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetDetailRepository tweetDetailRepository;

    @Autowired
    private LikeDetailRepository likeDetailRepository;

    @Autowired
    private UserDetailRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PageResponse getAllTweets(int pageNo, int pageSize) {

        Sort sort = Sort.by("publishDate").descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Tweet> tweets = tweetDetailRepository.findAll(pageable);
        List<Tweet> listOfTweets = tweets.getContent();

        List<TweetDto> content = listOfTweets.stream().map(tweet -> mapTweetToDTO(tweet)).collect(Collectors.toList());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(content);
        pageResponse.setPageNo(tweets.getNumber());
        pageResponse.setPageSize(tweets.getSize());
        pageResponse.setTotalElements(tweets.getTotalElements());
        pageResponse.setTotalPages(tweets.getTotalPages());
        pageResponse.setLast(tweets.isLast());

        return pageResponse;
    }

    public TweetDto addTweet(Long userId, String text) {
        UserDetail userDetails = userRepository.findByUserId(userId).get();
        Tweet tweet= Tweet.builder().userDetail(userDetails).likeCount(0).dislikeCount(0).text(text).publishDate(LocalDate.now()).build();
        Tweet newTweet = tweetDetailRepository.save(tweet);
        TweetDto tweetResponse = mapTweetToDTO(newTweet);
        return tweetResponse;
    }

    public LikeDetail likeTweet(Long userId, Long tweetId) {
        UserDetail userDetails = userRepository.findByUserId(userId).get();
        Tweet tweetDetails = tweetDetailRepository.findByTweetId(tweetId).get();
        tweetDetails.setLikeCount(tweetDetails.getLikeCount() + 1);
        LikeDetail likeDetail = LikeDetail.builder().details(userDetails).tweet(tweetDetails).likes(true).build();
        if(likeDetailRepository.findByDetailsAndTweet(userDetails, tweetDetails).isEmpty()) {
            LikeDetail detail = likeDetailRepository.save(likeDetail);
            return detail;
        }
        return new LikeDetail();
    }

    public LikeDetail dislikeTweet(Long userId, Long tweetId) {
        UserDetail userDetails = userRepository.findByUserId(userId).get();
        Tweet tweetDetails = tweetDetailRepository.findByTweetId(tweetId).get();
        tweetDetails.setDislikeCount(tweetDetails.getDislikeCount() + 1);
        LikeDetail likeDetail = LikeDetail.builder().details(userDetails).tweet(tweetDetails).dislikes(true).build();
        if(likeDetailRepository.findByDetailsAndTweet(userDetails, tweetDetails).isEmpty()) {
            LikeDetail detail = likeDetailRepository.save(likeDetail);
            return detail;
        }
        return new LikeDetail();
    }

    public LikeResponse getDetails(Long tweetId) {
        Tweet tweetDetails = tweetDetailRepository.findByTweetId(tweetId).get();
        List<LikeDetail> likeDetails = likeDetailRepository.findByTweet(tweetDetails);
        LikeResponse likeResponse = new LikeResponse();
        if(!likeDetails.isEmpty()) {
            List<LikeDetailDto> detailsList = new ArrayList<>();
            for(LikeDetail detail : likeDetails) {
                detailsList.add(mapLikeDetailToDTO(detail));
            }
            likeResponse.setDetails(detailsList);
            likeResponse.setLikeCount(tweetDetails.getLikeCount());
            likeResponse.setDislikeCount(tweetDetails.getDislikeCount());
        }
        return likeResponse;
    }

    private TweetDto mapTweetToDTO(Tweet tweet){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TweetDto tweetDto;
        tweetDto = modelMapper.map(tweet, TweetDto.class);
        return tweetDto;
    }

    private LikeDetailDto mapLikeDetailToDTO(LikeDetail likeDetail){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        LikeDetailDto likeDetailDto;
        likeDetailDto = modelMapper.map(likeDetail, LikeDetailDto.class);
        return likeDetailDto;
    }

}
