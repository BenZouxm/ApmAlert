package com.oneapm.alter.service;


import com.oneapm.alter.entity.JobAndTrigger;
import com.github.pagehelper.PageInfo;

public interface IJobAndTriggerService {
	PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
