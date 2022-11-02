package com.math.poc.poc_01.domain.merchant.core;

import com.math.poc.poc_01.domain.merchant.core.dto.MerchantDTO;
import com.math.poc.poc_01.domain.merchant.core.entity.Merchant;
import com.math.poc.poc_01.domain.merchant.core.ports.input.CreateMerchantUseCase;
import com.math.poc.poc_01.domain.merchant.core.ports.output.SaveMerchantPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MerchantFacade implements CreateMerchantUseCase {

    private final SaveMerchantPort saveMerchantPort;


    @Override
    public Mono<MerchantDTO> createMerchant(Merchant merchant) {
        return saveMerchantPort.save(merchant);
    }

    @Override
    public Mono<MerchantDTO> findMerchantById(Long id) {
        return saveMerchantPort.findMerchantById(id);
    }


}

