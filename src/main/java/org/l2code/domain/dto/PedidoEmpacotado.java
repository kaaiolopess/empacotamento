package org.l2code.domain.dto;

import java.util.List;

public record PedidoEmpacotado(
        Integer pedidoId,
        List<CaixaEmpacotada> caixas
) {}
