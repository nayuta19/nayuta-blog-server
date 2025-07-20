package org.nayutablogserver.service.impl;

import org.nayutablogserver.entity.Schedule;
import org.nayutablogserver.mapper.ScheduleMapper;
import org.nayutablogserver.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImlp implements ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public List<Schedule> selectSchedule() {
        return scheduleMapper.selectSchedule();
    }

    @Override
    public int addSchedule(String title, Date startDate,Date endDate,String desc ) {
        Schedule schedule = new Schedule();
        schedule.setTitle(title);
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
        schedule.setDesc(desc);
        return scheduleMapper.addSchedule(schedule);
    }

    @Override
    public int delSchedule(Integer id) {
        return scheduleMapper.delSchedule(id);
    }
}
