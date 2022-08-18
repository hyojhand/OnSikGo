package com.ssafy.onsikgo.dto;

import com.ssafy.onsikgo.entity.Notice;
import com.ssafy.onsikgo.entity.NoticeState;
import com.ssafy.onsikgo.entity.Order;
import com.ssafy.onsikgo.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {


    private Long noticeId;
    private String content;
    private Boolean state;
    private UserDto userDto;
    private OrderDto orderDto;
    private Long receivedId;
    private NoticeState noticeState;

}
