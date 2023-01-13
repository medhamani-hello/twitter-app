package com.solution.dto;

import com.solution.entity.response.UserDetail;
import lombok.Builder;
import lombok.Data;

@Data
public class LikeDetailDto {
    private UserDetail details;
    private boolean likes;
    private boolean dislikes;
}
