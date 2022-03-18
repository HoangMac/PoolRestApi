package com.homework.restapi.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hmm
 */
public interface PoolDAO {
    boolean containPoolId(Long poolId);

    void insertPoolMap(Long poolId, List<Long> poolValues);

    List<Long> getListValuesByPoolId(Long poolId);
}
