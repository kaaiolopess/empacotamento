package org.l2code.domain.dto;

import java.util.List;

public record RespostaEmpacotamento(
        List<PedidoEmpacotado> pedidos
) {}

