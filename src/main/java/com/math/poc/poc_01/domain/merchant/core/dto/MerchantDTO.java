package com.math.poc.poc_01.domain.merchant.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MerchantDTO {

    private Long id;
    private String name;
    private String email;


}

