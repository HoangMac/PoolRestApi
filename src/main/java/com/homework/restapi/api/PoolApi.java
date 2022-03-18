package com.homework.restapi.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.homework.restapi.dto.request.PoolAppendRequest;
import com.homework.restapi.dto.request.PoolQueryRequest;
import com.homework.restapi.dto.result.PoolAppendResult;
import com.homework.restapi.dto.result.PoolQueryResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 *
 * @author hmm
 */
@RequestMapping(PoolApi.REQUEST_MAPPING)
public interface PoolApi {
    String REQUEST_MAPPING = "/api";

    @PostMapping(value = "/pools", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Append (if pool already exists) or insert (new pool) the values to the appropriate pool (as per the id)")
    @ApiResponses(value = @ApiResponse(responseCode = "200"))
    ResponseEntity<PoolAppendResult> append(@RequestBody PoolAppendRequest request);

    @PostMapping(value = "/pools/percentile", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(description = "Query a pool and calculate the quantile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid Pool-id or Quantile"),
            @ApiResponse(responseCode = "404", description = "The Pool not found"),
    })
    ResponseEntity<PoolQueryResult> calculatePercentile(@RequestBody PoolQueryRequest request);
}
