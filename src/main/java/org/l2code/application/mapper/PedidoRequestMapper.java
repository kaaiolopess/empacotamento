package org.l2code.application.mapper;

import com.seumanoel.api.model.RequisicaoEmpacotamento;

import org.l2code.application.dto.PedidosRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PedidoMapper.class})
public interface PedidoRequestMapper {
    PedidosRequestDTO toPedidoDTO(RequisicaoEmpacotamento source);
}
