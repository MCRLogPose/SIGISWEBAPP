package com.sigis.prueba.incidency.mapper;

import com.sigis.prueba.incidency.dto.CategoryResponse;
import com.sigis.prueba.incidency.model.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(CategoryModel model) {
        if (model == null) return null;

        CategoryResponse dto = new CategoryResponse();
        dto.setId(model.getId());
        dto.setTypeCategory(model.getTypeCategory());
        return dto;
    }
}
