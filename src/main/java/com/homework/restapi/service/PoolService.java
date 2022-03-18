package com.homework.restapi.service;

import org.springframework.http.ResponseEntity;
import com.homework.restapi.dto.request.PoolAppendRequest;
import com.homework.restapi.dto.request.PoolQueryRequest;
import com.homework.restapi.dto.result.PoolAppendResult;
import com.homework.restapi.dto.result.PoolQueryResult;

/**
 *
 * @author hmm
 */
public interface PoolService {
    ResponseEntity<PoolAppendResult> append(PoolAppendRequest request);

    ResponseEntity<PoolQueryResult> calculatePercentile(PoolQueryRequest request);
}
