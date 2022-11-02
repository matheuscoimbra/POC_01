package com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RedisHash("merchant")
public class MerchantEntity {

        private Long id;
        private String name;
        private String email;

        public MerchantEntity() {
        }
}
