package com.oneapm.alter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneapm.alter.dao.JobAndTriggerMapper;
import com.oneapm.alter.entity.JobAndTrigger;
import com.oneapm.alter.service.IJobAndTriggerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class JobAndTriggerImpl implements IJobAndTriggerService{

	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;
	
	public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
		PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
		return page;
	}

}