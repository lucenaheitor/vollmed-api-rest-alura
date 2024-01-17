package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.consulta.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(AgendamentoConsultaDTO dados);

}
