package com.solution.payload;

import com.solution.dto.LikeDetailDto;
import com.solution.dto.TweetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {

    private int likeCount;
    private int dislikeCount;
    private List<LikeDetailDto> details;

}
