// package com.example.demo.service;

// import com.example.demo.model.TimeTable;
// import com.example.demo.model.Course;
// import com.example.demo.model.Teacher;
// import com.example.demo.repository.TimeTableRepository;
// import com.example.demo.repository.CourseRepository;
// import com.example.demo.repository.TeacherRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class TimeTableService {

//     @Autowired
//     private TimeTableRepository timeTableRepository;

//     @Autowired
//     private CourseRepository courseRepository;

//     @Autowired
//     private TeacherRepository teacherRepository;

//     public TimeTable saveTimeTable(TimeTable timeTable, Long courseId, Long teacherId) {
//         Optional<Course> course = courseRepository.findById(courseId);
//         Optional<Teacher> teacher = teacherRepository.findById(teacherId);

//         if (course.isPresent() && teacher.isPresent()) {
//             timeTable.setCourse(course.get());
//             timeTable.setTeacher(teacher.get());
//             return timeTableRepository.save(timeTable);
//         } else {
//             throw new RuntimeException("Course or Teacher not found");
//         }
//     }

//     public Optional<TimeTable> getTimeTableById(Long id) {
//         return timeTableRepository.findById(id);
//     }

//     public List<TimeTable> getAllTimeTables() {
//         return timeTableRepository.findAll();
//     }

//     public TimeTable updateTimeTable(TimeTable timeTable, Long courseId, Long teacherId) {
//         Optional<Course> course = courseRepository.findById(courseId);
//         Optional<Teacher> teacher = teacherRepository.findById(teacherId);

//         if (course.isPresent() && teacher.isPresent()) {
//             timeTable.setCourse(course.get());
//             timeTable.setTeacher(teacher.get());
//             return timeTableRepository.save(timeTable);
//         } else {
//             throw new RuntimeException("Course or Teacher not found");
//         }
//     }

//     public void deleteTimeTableById(Long id) {
//         timeTableRepository.deleteById(id);
//     }
// }

package com.example.demo.service;

import com.example.demo.model.TimeTable;
import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TimeTableRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public TimeTable saveOrUpdateTimeTable(TimeTable timeTable, Long courseId, Long teacherId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (course.isPresent() && teacher.isPresent()) {
            // Check if there's already a TimeTable for the same day and time slot
            Optional<TimeTable> existingTimeTable = timeTableRepository.findByDayAndTimeSlot(timeTable.getDay(), timeTable.getTimeSlot());

            if (existingTimeTable.isPresent()) {
                // Update the existing entry
                TimeTable existing = existingTimeTable.get();
                existing.setCourse(course.get());
                existing.setTeacher(teacher.get());
                return timeTableRepository.save(existing);
            } else {
                // Save as a new entry
                timeTable.setCourse(course.get());
                timeTable.setTeacher(teacher.get());
                return timeTableRepository.save(timeTable);
            }
        } else {
            throw new RuntimeException("Course or Teacher not found");
        }
    }

    public Optional<TimeTable> getTimeTableById(Long id) {
        return timeTableRepository.findById(id);
    }

    public List<TimeTable> getAllTimeTables() {
        return timeTableRepository.findAll();
    }

    public void deleteTimeTableById(Long id) {
        timeTableRepository.deleteById(id);
    }
}

