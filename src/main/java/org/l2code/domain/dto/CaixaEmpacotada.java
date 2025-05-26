package org.l2code.domain.dto;

import java.util.List;

public record CaixaEmpacotada(
        String caixaId,
        List<String> produtos,
        String observacao
) {}
