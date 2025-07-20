package org.nayutablogserver.service;

import org.nayutablogserver.entity.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleService {
    List<Schedule> selectSchedule();

    int addSchedule(String title, Date startDate, Date endDate,String desc);

    int delSchedule(Integer id);
}
