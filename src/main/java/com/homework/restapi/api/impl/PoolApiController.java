package com.homework.restapi.api.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.homework.restapi.api.PoolApi;
import com.homework.restapi.dto.request.PoolAppendRequest;
import com.homework.restapi.dto.request.PoolQueryRequest;
import com.homework.restapi.dto.result.PoolAppendResult;
import com.homework.restapi.dto.result.PoolQueryResult;

/**
 *
 * @author hmm
 */
@RestController
public class PoolApiController implements PoolApi {

    private Map<Long, List<Long>> poolsMap = new HashMap<>();

    @Override
    public ResponseEntity<PoolAppendResult> append(PoolAppendRequest request) {

        PoolAppendResult result = poolsMap.containsKey(request.getPoolId())
                ? PoolAppendResult.APPENDED
                : PoolAppendResult.INSERTED;

        poolsMap.computeIfAbsent(request.getPoolId(), v -> new LinkedList<>())
                .addAll(Optional.ofNullable(request.getPoolValues()).orElse(new ArrayList<>()));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PoolQueryResult> calculatePercentile(PoolQueryRequest request) {

        if (request.getPoolId() == null || request.getPercentile() == null ||
                request.getPercentile() < 0 || request.getPercentile() > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> values = poolsMap.get(request.getPoolId());
        if (values == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Collections.sort(values);

        PoolQueryResult result = new PoolQueryResult();
        result.setQuantile(percentile(values, request.getPercentile()));
        result.setTotalCount(values.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private long percentile(List<Long> values, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * values.size());
        return values.get(index - 1);
    }
}
