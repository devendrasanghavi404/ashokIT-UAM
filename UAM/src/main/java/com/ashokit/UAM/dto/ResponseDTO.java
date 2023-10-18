package com.ashokit.UAM.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ResponseDTO<T>{
    private T result;
}
