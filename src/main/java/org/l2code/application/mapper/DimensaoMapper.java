package org.l2code.application.mapper;

import com.seumanoel.api.model.Dimensoes;
import org.l2code.application.dto.DimensoesDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DimensaoMapper {
    DimensoesDTO toDimensoesDTO(Dimensoes source);
}