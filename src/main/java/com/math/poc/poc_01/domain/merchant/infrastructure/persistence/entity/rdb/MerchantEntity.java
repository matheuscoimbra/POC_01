package com.math.poc.poc_01.domain.merchant.infrastructure.persistence.entity.rdb;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("merchant")
public class MerchantEntity {

    @Id
    private Long id;
    private String name;
    private String email;

    @Column("created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column("created_by")
    @CreatedBy
    private String createdBy;
    @Column("updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column("updated_by")
    @LastModifiedBy
    private String updatedBy;
    @Column("version")
    @Version
    private Long version;


}
