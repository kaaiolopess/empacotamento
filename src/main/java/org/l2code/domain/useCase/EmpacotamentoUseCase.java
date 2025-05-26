package org.l2code.domain.useCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.l2code.application.dto.PedidoDTO;
import org.l2code.application.dto.PedidosRequestDTO;
import org.l2code.application.dto.ProdutoDTO;
import org.l2code.domain.dto.CaixaDisponivelDTO;
import org.l2code.domain.dto.CaixaEmpacotada;
import org.l2code.domain.dto.PedidoEmpacotado;
import org.l2code.domain.dto.RespostaEmpacotamento;
import org.l2code.domain.properties.CaixasProperties;
import org.l2code.domain.useCase.interfaces.IEmpacotamentoUseCase;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmpacotamentoUseCase implements IEmpacotamentoUseCase {

    private final CaixasProperties properties;

    @Override
    public RespostaEmpacotamento executar(PedidosRequestDTO pedidosRequestDTO) {
        log.info("Iniciando processo de calculo de empacotamento");

        List<CaixaDisponivelDTO> caixasDisponiveis = getCaixasDisponiveis();

        var pedidosEmpacotados = pedidosRequestDTO.getPedidos().stream()
                .map(p -> empacotarPedido(p, caixasDisponiveis))
                .sorted(Comparator.comparing(PedidoEmpacotado::pedidoId))
                .toList();

        log.info("Processo de empacotamento finalizado");
        log.info("Pedidos empacotados: {}", pedidosEmpacotados.size());

        return new RespostaEmpacotamento(pedidosEmpacotados);
    }

    public List<CaixaDisponivelDTO> getCaixasDisponiveis() {
        return properties.getCaixas().stream()
                .map(c -> new CaixaDisponivelDTO(c.getId(), c.getDimensao()))
                .toList();
    }

    private PedidoEmpacotado empacotarPedido(PedidoDTO pedido, List<CaixaDisponivelDTO> caixasDisponiveis) {
        // Ordenar caixas por volume (menor para maior)
        List<CaixaDisponivelDTO> caixasOrdenadas = caixasDisponiveis.stream()
                .sorted(Comparator.comparing(c -> c.dimensao().volume()))
                .toList();

        // Usar Set para rastrear produtos alocados
        Set<String> alocados = new HashSet<>();

        // Mapear caixas para produtos que cabem, filtrando os já alocados
        List<CaixaEmpacotada> caixasUsadas = caixasOrdenadas.stream()
                .map(caixa -> {
                    List<ProdutoDTO> encaixam = pedido.getProdutos().stream()
                            .filter(p -> !alocados.contains(p.getProdutoId()))
                            .filter(p -> caixa.dimensao().comporta(p.getDimensoes()))
                            .toList();

                    if (!encaixam.isEmpty()) {
                        alocados.addAll(encaixam.stream()
                                .map(ProdutoDTO::getProdutoId)
                                .toList());
                        return new CaixaEmpacotada(
                                caixa.id(),
                                encaixam.stream()
                                        .map(ProdutoDTO::getProdutoId)
                                        .toList(),
                                null
                        );
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        // Produtos que não cabem em nenhuma caixa
        List<CaixaEmpacotada> produtosNaoAlocados = pedido.getProdutos().stream()
                .filter(p -> !alocados.contains(p.getProdutoId()))
                .map(p -> new CaixaEmpacotada(
                        null,
                        List.of(p.getProdutoId()),
                        "Produto não cabe em nenhuma caixa disponível."
                ))
                .toList();

        // Combina caixas usadas + não alocados
        List<CaixaEmpacotada> todasCaixasUsadas = Stream.concat(caixasUsadas.stream(), produtosNaoAlocados.stream())
                .toList();

        log.info("Pedido {} empacotado em {} caixas.", pedido.getPedidoId(), todasCaixasUsadas.size());

        return new PedidoEmpacotado(pedido.getPedidoId(), todasCaixasUsadas);
    }
}