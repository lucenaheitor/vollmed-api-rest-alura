package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMédicoAtivio implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        //escolha do medico opcional
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidationExcepetion("Consulta não pode ser agendada com médico excluído");
        }
    }

}
