package dev.patika.veterinary.core.config.modelMapper;

import dev.patika.veterinary.dto.response.animal.AnimalResponse;
import dev.patika.veterinary.entity.Animal;
import org.modelmapper.ModelMapper;

public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
