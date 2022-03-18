package com.homework.restapi.service.impl;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.homework.restapi.dao.PoolDAO;
import com.homework.restapi.dto.request.PoolAppendRequest;
import com.homework.restapi.dto.request.PoolQueryRequest;
import com.homework.restapi.dto.result.PoolAppendResult;
import com.homework.restapi.dto.result.PoolQueryResult;
import com.homework.restapi.service.PoolService;

/**
 *
 * @author hmm
 */
@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDAO poolDAO;

    @Override
    public ResponseEntity<PoolAppendResult> append(PoolAppendRequest request) {
        PoolAppendResult result = poolDAO.containPoolId(request.getPoolId())
                ? PoolAppendResult.APPENDED
                : PoolAppendResult.INSERTED;

        poolDAO.insertPoolMap(request.getPoolId(), request.getPoolValues());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PoolQueryResult> calculatePercentile(PoolQueryRequest request) {
        if (request.getPoolId() == null || request.getPercentile() == null ||
                request.getPercentile() < 0 || request.getPercentile() > 100) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Long> values = poolDAO.getListValuesByPoolId(request.getPoolId());
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
