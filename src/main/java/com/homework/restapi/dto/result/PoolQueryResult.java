package com.homework.restapi.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hmm
 */
@Getter
@Setter
@Schema(description = "the response from the query")
public class PoolQueryResult {
    @Schema(description = "the calculated quantile")
    private Long quantile;
    @Schema(description = "the total count of elements in the pool")
    private Integer totalCount;
}
