package com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.cache;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value ="merchant")
public class MerchantHash {

        @Id
        private String id;
        @Indexed
        private String name;
        @Indexed
        private String email;
        @Indexed

        @TimeToLive
        private Long ttl;





}
