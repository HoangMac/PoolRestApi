package com.homework.restapi.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.homework.restapi.api.PoolApi;
import com.homework.restapi.dto.request.PoolAppendRequest;
import com.homework.restapi.dto.request.PoolQueryRequest;
import com.homework.restapi.dto.result.PoolAppendResult;
import com.homework.restapi.dto.result.PoolQueryResult;
import com.homework.restapi.service.PoolService;

/**
 *
 * @author hmm
 */
@RestController
public class PoolApiImpl implements PoolApi {

    @Autowired
    private PoolService poolService;

    @Override
    public ResponseEntity<PoolAppendResult> append(PoolAppendRequest request) {
        return poolService.append(request);
    }

    @Override
    public ResponseEntity<PoolQueryResult> calculatePercentile(PoolQueryRequest request) {
        return poolService.calculatePercentile(request);
    }
}
