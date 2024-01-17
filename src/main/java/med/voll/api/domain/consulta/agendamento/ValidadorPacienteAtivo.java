package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements  ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidationExcepetion("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
