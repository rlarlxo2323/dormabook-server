package com.dormabook.web.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestIntroDto {
    private String memberIntroduce;
    private String memberValue;



    @Builder
    public RequestIntroDto(String memberIntroduce, String memberValue){
        this.memberIntroduce = memberIntroduce;
        this.memberValue = memberValue;
    }
}
