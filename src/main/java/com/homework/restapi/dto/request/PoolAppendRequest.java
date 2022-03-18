package com.homework.restapi.dto.request;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hmm
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class PoolAppendRequest {
    @Schema(description = "a pool-id (numeric)", required = true)
    private Long poolId;
    @Schema(description = "a pool-values (array of values)", required = true)
    private List<Long> poolValues;
}
