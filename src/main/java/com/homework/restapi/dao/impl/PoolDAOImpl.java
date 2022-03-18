package com.homework.restapi.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.homework.restapi.dao.PoolDAO;

/**
 *
 * @author hmm
 */
@Service
public class PoolDAOImpl implements PoolDAO {
    private Map<Long, List<Long>> poolsMap = new HashMap<>();

    @Override
    public boolean containPoolId(Long poolId) {
        return poolsMap.containsKey(poolId);
    }

    @Override
    public void insertPoolMap(Long poolId, List<Long> poolValues) {
        poolsMap.computeIfAbsent(poolId, v -> new LinkedList<>())
                .addAll(Optional.ofNullable(poolValues).orElse(new ArrayList<>()));
    }

    @Override
    public List<Long> getListValuesByPoolId(Long poolId) {
        return poolsMap.get(poolId);
    }
}
