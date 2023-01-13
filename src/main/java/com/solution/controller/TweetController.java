package com.solution.controller;


import com.solution.service.TweetService;
import com.solution.dto.TweetDto;
import com.solution.entity.request.TweetInfo;
import com.solution.entity.request.TweetRequest;
import com.solution.entity.response.LikeDetail;
import com.solution.payload.LikeResponse;
import com.solution.payload.PageResponse;
import com.solution.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tweets/")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/getAllTweets")
    ResponseEntity<PageResponse> getTweet(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        try {
            PageResponse tweetList = tweetService.getAllTweets(pageNo, pageSize);
            return new ResponseEntity<>(tweetList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addTweet")
    public ResponseEntity<TweetDto> addTweet(@RequestBody TweetInfo tweetInfo){
        return new ResponseEntity<>(tweetService.addTweet(tweetInfo.getUserId(), tweetInfo.getText()), HttpStatus.CREATED);
    }

    @PutMapping("/likeTweet")
    public ResponseEntity<LikeDetail> likeTweet(@RequestBody TweetRequest tweetRequest){
        return new ResponseEntity<>(tweetService.likeTweet(tweetRequest.getUserId(), tweetRequest.getTweetId()), HttpStatus.CREATED);
    }

    @PutMapping("/dislikeTweet")
    public ResponseEntity<LikeDetail> dislikeTweet(@RequestBody TweetRequest tweetRequest){
        return new ResponseEntity<>(tweetService.dislikeTweet(tweetRequest.getUserId(), tweetRequest.getTweetId()), HttpStatus.CREATED);
    }

    @GetMapping("/getTweetDetails/{tweetId}")
    ResponseEntity<LikeResponse> getTweet(@PathVariable("tweetId") Long tweetId) {
        try {
            LikeResponse tweetDetail = tweetService.getDetails(tweetId);
            return new ResponseEntity<>(tweetDetail, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
