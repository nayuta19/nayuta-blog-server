package org.nayutablogserver.mapper;
import org.apache.ibatis.annotations.*;
import org.nayutablogserver.entity.Schedule;
import java.util.List;

@Mapper
public interface ScheduleMapper {
    // 查询日程
    @Select("SELECT * FROM schedule WHERE status = 1 ")
    List<Schedule> selectSchedule();

    @Insert("INSERT INTO schedule(title, status, start_date, end_date) " +
            "VALUES(#{title}, 1, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 获取自增ID
    int addSchedule(Schedule schedule);

    //删除日程
    @Update("UPDATE schedule SET status = 0 where id = #{id} ")
    int delSchedule(Integer id);
}
