package com.homework.restapi.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author hmm
 */
@Schema(description = "The response from the append is a status field confirming \"appended\" or \"inserted\"")
public enum PoolAppendResult {
    APPENDED,
    INSERTED
}
