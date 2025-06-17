package com.sigis.prueba.incidency.controller;

import com.sigis.prueba.incidency.model.CategoryModel;
import com.sigis.prueba.incidency.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAll(){
        return categoryRepository.findAll();
    }

}
