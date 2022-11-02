package com.math.poc.poc_01.domain.merchant.core.ports.output;

import com.math.poc.poc_01.domain.merchant.core.dto.MerchantDTO;
import com.math.poc.poc_01.domain.merchant.core.entity.Merchant;
import reactor.core.publisher.Mono;


public interface SaveMerchantPort {

     Mono<MerchantDTO> save(Merchant merchant);

     Mono<MerchantDTO> findMerchantById(Long id);
}
