package org.l2code.application.port.rest;

import com.seumanoel.api.controller.ApiApi;
import com.seumanoel.api.model.RequisicaoEmpacotamento;
import com.seumanoel.api.model.RespostaEmpacotamento;
import org.l2code.application.mapper.EmpacotamentoMapper;
import org.l2code.application.mapper.RequisicaoEmpacotamentoMapper;
import org.l2code.domain.useCase.EmpacotamentoUseCase;
import org.l2code.domain.useCase.interfaces.IEmpacotamentoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController implements ApiApi {

    private final IEmpacotamentoUseCase empacotamentoUseCase;
    private final EmpacotamentoMapper empacotamentoMapper;

    public PedidoController(EmpacotamentoUseCase empacotamentoUseCase, EmpacotamentoMapper empacotamentoMapper) {
        this.empacotamentoUseCase = empacotamentoUseCase;
        this.empacotamentoMapper = empacotamentoMapper;
    }

    @Override
    public ResponseEntity<RespostaEmpacotamento> empacotarPedidos(RequisicaoEmpacotamento pedidosRequest) {
        org.l2code.domain.dto.RespostaEmpacotamento respostaEmpacotamento =
                empacotamentoUseCase.executar(RequisicaoEmpacotamentoMapper.toDTO(pedidosRequest));
        return ResponseEntity.ok(empacotamentoMapper.toRespostaEmpacotamento(respostaEmpacotamento));
    }
}
