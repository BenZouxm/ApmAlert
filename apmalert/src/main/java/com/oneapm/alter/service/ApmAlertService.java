package com.oneapm.alter.service;


import com.oneapm.alter.model.base.BaseRepEnt;

import java.util.HashMap;

public interface ApmAlertService {
	BaseRepEnt dealAiAlert(HashMap<String, Object> reqs);
	BaseRepEnt dealBiAlert(HashMap<String, Object> reqs);
}
