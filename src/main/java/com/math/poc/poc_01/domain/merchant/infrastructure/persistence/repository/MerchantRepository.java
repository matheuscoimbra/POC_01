package com.math.poc.poc_01.domain.merchant.infrastructure.persistence.repository;

import com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.rdb.MerchantEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MerchantRepository extends ReactiveCrudRepository<MerchantEntity, Long> {
}

