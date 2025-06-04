package com.object_oriented_case.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.object_oriented_case.portfolio.dto.MarkUpdateRequest;
import com.object_oriented_case.portfolio.model.Mark;
import com.object_oriented_case.portfolio.repository.MarkRepository;

@Service
public class MarkService {

    private final MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    public Mark getMarkById(Long id) {
        return markRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mark not found with id: " + id));
    }

    public Mark createMark(Mark mark) {
        return markRepository.save(mark);
    }

    public Mark updateMark(Long id, MarkUpdateRequest markDetails) {
        Mark mark = getMarkById(id);
        mark.setName(markDetails.getName());
        return markRepository.save(mark);
    }

    public boolean deleteMark(Long id) {
        Mark mark = getMarkById(id);
        markRepository.delete(mark);
        return true;
    }

}
