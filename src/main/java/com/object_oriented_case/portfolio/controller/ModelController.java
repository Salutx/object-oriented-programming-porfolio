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

import com.object_oriented_case.portfolio.dto.ModelCreateRequest;
import com.object_oriented_case.portfolio.dto.ModelUpdateRequest;
import com.object_oriented_case.portfolio.model.Model;
import com.object_oriented_case.portfolio.service.ModelService;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<Model>> getAllModels() {
        List<Model> models = modelService.getAllModels();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Model> getModelById(@RequestParam Long id) {
        Model model = modelService.getModelById(id);

        if (model == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(model);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Model> updateModel(@RequestParam Long id, @RequestBody ModelUpdateRequest modelDetails) {
        Model updatedModel = modelService.updateModel(id, modelDetails);

        if (updatedModel == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedModel);
    }

    @PostMapping("/create")
    public ResponseEntity<Model> createModel(@RequestBody ModelCreateRequest model) {
        Model createdModel = modelService.createModel(model);
        return ResponseEntity.ok(createdModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteModel(@RequestParam Long id) {
        boolean isDeleted = modelService.deleteModel(id);

        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(isDeleted);
    }

}
