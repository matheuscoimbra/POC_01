package com.math.poc.poc_01.domain.merchant.application;

import com.math.poc.poc_01.domain.merchant.core.MerchantFacade;
import com.math.poc.poc_01.domain.merchant.core.dto.MerchantDTO;
import com.math.poc.poc_01.domain.merchant.core.entity.Merchant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/merchant")
@RequiredArgsConstructor
public class MerchantResource {

    private final MerchantFacade merchantFacade;

    @PostMapping
    public Mono<MerchantDTO> createMerchant(@RequestBody Merchant merchant) {
        return merchantFacade.createMerchant(merchant);
    }

    @GetMapping("/{id}")
    public Mono<MerchantDTO> findMerchantById(@PathVariable Long id) {
        return merchantFacade.findMerchantById(id);
    }
}
