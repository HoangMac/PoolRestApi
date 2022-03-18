package com.homework.restapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author hmm
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoolQueryRequest {
    @Schema(description = "a pool-id (numeric) identifying the queried pool", required = true)
    private Long poolId;
    @Schema(description = "a quantile (in percentile form)", required = true)
    private Double percentile;
}
