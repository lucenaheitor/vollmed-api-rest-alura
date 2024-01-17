package med.voll.api.domain.consulta.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;

public interface ValidadorCancelamentoConsulta {

    void validar(DadosCancelamentoConsultaDTO dados);

}
