package com.object_oriented_case.portfolio.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.object_oriented_case.portfolio.dto.MarkUpdateRequest;
import com.object_oriented_case.portfolio.model.Mark;
import com.object_oriented_case.portfolio.service.MarkService;

@RestController
@RequestMapping("/api/marks")
public class MarkController {

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping
    public ResponseEntity<List<Mark>> getAllMarks() {
        List<Mark> marks = markService.getAllMarks();
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mark> getMarkById(@RequestParam Long id) {
        Mark mark = markService.getMarkById(id);

        if (mark == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mark);
    }

    @PostMapping("/create")
    public ResponseEntity<Mark> createMark(@RequestBody Mark mark) {
        Mark createdMark = markService.createMark(mark);
        return ResponseEntity.ok(createdMark);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mark> updateMark(@RequestParam Long id, @RequestBody MarkUpdateRequest markDetails) {
        Mark updatedMark = markService.updateMark(id, markDetails);

        if (updatedMark == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMark);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteMark(@RequestParam Long id) {
        boolean isDeleted = markService.deleteMark(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
