package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade updateGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGradeById(Long id) {
        gradeRepository.deleteById(id);
    }
}
