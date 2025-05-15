package com.jobPrize.repository.company.schedule;

import java.util.List;
import java.util.Optional;

import com.jobPrize.entity.company.QSchedule;
import com.jobPrize.entity.company.Schedule;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> findAllByCompanyId(Long id) {
        QSchedule schedule = QSchedule.schedule;

        return queryFactory.selectFrom(schedule)
        		.select(schedule)
        		.from(schedule)
                .where(schedule.company.id.eq(id))
                .orderBy(schedule.date.desc())
                .fetch();
    }

    @Override
    public Optional<Schedule> findByScheduleId(Long Id) {  
        QSchedule schedule = QSchedule.schedule;

        return Optional.ofNullable(
                queryFactory.selectFrom(schedule)
                .select(schedule)
        		.from(schedule)
                .where(schedule.id.eq(Id))
                .distinct()
                .fetchOne()
        );
    }
}