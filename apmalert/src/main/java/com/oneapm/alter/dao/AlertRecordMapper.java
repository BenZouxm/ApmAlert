package com.oneapm.alter.dao;

import com.oneapm.alter.entity.AlertRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AlertRecordMapper {
	List<AlertRecord> getUnsentAlertRecord(@Param("beginDate") Date beginDate,@Param("reSendTimes") int reSendTimes);

	int saveAlterRecord(AlertRecord alertRecord);

	int updateAlterRecord(AlertRecord alertRecord);

	List<AlertRecord> getAllAlertRecord();

}
