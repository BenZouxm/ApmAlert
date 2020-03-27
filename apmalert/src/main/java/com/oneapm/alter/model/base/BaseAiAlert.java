package com.oneapm.alter.model.base;

import lombok.Data;

/**
 * Created by zou on 2020/3/24.
 */
@Data
public class BaseAiAlert {
    private long agentId;
    private String agentName;
    private long metricId;
    private long systemId;
    private String systemName;
    private long tierId;
    private String tierName;
}
