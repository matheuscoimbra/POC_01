package com.math.poc.poc_01.domain.merchant.infrastructure.mapper;

import com.math.poc.poc_01.domain.merchant.core.dto.MerchantDTO;
import com.math.poc.poc_01.domain.merchant.core.entity.Merchant;
import com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.cache.MerchantHash;
import com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.rdb.MerchantEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MerchantMapper {

    public MerchantHash toMerchantHash(Merchant merchant, Long id) {
        return  MerchantHash.builder()
                .id(id.toString())
                .name(merchant.getName())
                .email(merchant.getEmail())
                .build();
    }

    public MerchantEntity toMerchantEntity(Merchant merchant) {
        return MerchantEntity.builder()
                .name(merchant.getName())
                .email(merchant.getEmail())
                .build();
    }

    public MerchantDTO toMerchantDTO(MerchantEntity merchant) {
        return MerchantDTO.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .email(merchant.getEmail())
                .build();
    }

    public MerchantDTO toMerchantDTO(MerchantHash merchant) {
        return MerchantDTO.builder()
                .id(Long.parseLong(merchant.getId()))
                .name(merchant.getName())
                .email(merchant.getEmail())
                .build();
    }

    public MerchantHash toMerchantHash(MerchantEntity merchantEntity) {
        return MerchantHash.builder()
                .id(merchantEntity.getId().toString())
                .name(merchantEntity.getName())
                .email(merchantEntity.getEmail())
                .build();
    }
}
