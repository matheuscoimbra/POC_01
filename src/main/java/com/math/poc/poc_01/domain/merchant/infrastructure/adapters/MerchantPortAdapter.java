package com.math.poc.poc_01.domain.merchant.infrastructure.adapters;

import com.math.poc.poc_01.domain.merchant.core.dto.MerchantDTO;
import com.math.poc.poc_01.domain.merchant.core.entity.Merchant;
import com.math.poc.poc_01.domain.merchant.core.ports.output.SaveMerchantPort;
import com.math.poc.poc_01.domain.merchant.infrastructure.mapper.MerchantMapper;
import com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.cache.MerchantHash;
import com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.rdb.MerchantEntity;
import com.math.poc.poc_01.domain.merchant.infrastructure.persistence.repository.MerchantRepository;
import com.math.poc.poc_01.infrastructure.config.web.ReactiveRequestContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MerchantPortAdapter implements SaveMerchantPort {

    private static final String KEY = "merchant";


    private final ReactiveRedisOperations<String, MerchantHash> reactiveRedisOperations;
    private final ReactiveHashOperations<String, String, MerchantHash> reactiveHashOperations;

    private final MerchantRepository merchantRepository;


    private final MerchantMapper mapper;


    public MerchantPortAdapter(ReactiveRedisOperations<String, MerchantHash> reactiveRedisOperations, MerchantRepository merchantRepository, MerchantMapper mapper) {
        this.reactiveRedisOperations = reactiveRedisOperations;
        this.reactiveHashOperations = reactiveRedisOperations.opsForHash();
        this.merchantRepository = merchantRepository;
        this.mapper = mapper;
    }


    @Transactional
    @Override
    public Mono<MerchantDTO> save(Merchant merchant) {
        var rdb = mapper.toMerchantEntity(merchant);

        return merchantRepository.save(rdb)
                .flatMap(merchantEntity -> saveInCache(merchant, merchantEntity))
                .doOnError(throwable -> log.error("Error saving merchant in database {}", rdb));

    }

    private Mono<MerchantDTO> saveInCache(Merchant merchant, MerchantEntity merchantEntity) {
        var cache = mapper.toMerchantHash(merchant, merchantEntity.getId());

        log.info("Saving merchant in cache {}", cache);
        return reactiveHashOperations.put(KEY, cache.getId(), cache).
                flatMap(saved -> Mono.just(mapper.toMerchantDTO(merchantEntity)))
                .doOnError(throwable -> log.error("Error saving merchant in cache {}", cache));
    }

    @Override
    public Mono<MerchantDTO> findMerchantById(Long id) {
        return reactiveHashOperations.get(KEY, id.toString())
                .flatMap(merchantHash -> {
                    log.info("Merchant found in cache {}", merchantHash);
                    return Mono.just(mapper.toMerchantDTO(merchantHash));
                })
                .switchIfEmpty(Mono.defer(()-> getFromCache(id)));
    }

    private Mono<MerchantDTO> getFromCache(Long id) {
        return merchantRepository.findById(id)
                .flatMap(merchantEntity -> {
                    var cache = mapper.toMerchantHash(merchantEntity);
                    log.info("Saving merchant in cache {}", cache);
                    return reactiveHashOperations.put(KEY, merchantEntity.getId().toString(), cache).
                            flatMap(saved -> {
                                log.info("Merchant saved in cache {}", cache);
                                return Mono.just(mapper.toMerchantDTO(merchantEntity));
                            });
                });
    }
}

