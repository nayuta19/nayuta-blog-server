package org.nayutablogserver.controller;
import org.nayutablogserver.entity.Schedule;
import org.nayutablogserver.pojo.Result;
import org.nayutablogserver.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public Result getScheduleList() {
        List<Schedule> allList =  scheduleService.selectSchedule();
        return  Result.success(allList);
    }

    @PostMapping
    public Result addSchedule(
            @RequestBody Schedule requestData) {
        try {
            String title = requestData.getTitle();
            Date startDate = requestData.getStartDate();
            Date endDate = requestData.getEndDate();
            String desc = requestData.getDesc();
            int result = scheduleService.addSchedule(title, startDate, endDate,desc);
            if (result > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } catch (Exception e) {
            return Result.error();
        }

    }

    @DeleteMapping("/{id}")
    public Result delSchedule(@PathVariable Integer id) {
        try {
            int result =  scheduleService.delSchedule(id);
            if (result > 0) {
                return Result.success();
            } else {
                return Result.error();
            }
        } catch (Exception e) {
            return Result.error();
        }
    }

}
