package com.object_oriented_case.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.object_oriented_case.portfolio.dto.ModelUpdateRequest;
import com.object_oriented_case.portfolio.model.Mark;
import com.object_oriented_case.portfolio.model.Model;
import com.object_oriented_case.portfolio.repository.ModelRepository;

@Service
public class ModelService {

    private final MarkService markService;
    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository, MarkService markService) {
        this.modelRepository = modelRepository;
        this.markService = markService;
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Model getModelById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found with id: " + id));
    }

    public Model createModel(Model model) {
        return modelRepository.save(model);
    }

    public Model updateModel(Long id, ModelUpdateRequest modelDetails) {
        Model model = getModelById(id);
        model.setName(modelDetails.getName());

        Mark mark = markService.getMarkById(modelDetails.getMarkId());
        model.setMark(mark);
        return modelRepository.save(model);
    }

    public boolean deleteModel(Long id) {
        Model model = getModelById(id);
        modelRepository.delete(model);
        return true;
    }

}
