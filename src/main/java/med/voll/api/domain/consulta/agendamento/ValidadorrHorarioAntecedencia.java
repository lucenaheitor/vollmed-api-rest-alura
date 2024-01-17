package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidadorrHorarioAntecedencia implements  ValidadorAgendamentoDeConsulta {

    public void validar(AgendamentoConsultaDTO dados){
        LocalDateTime dataConsulta =  dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw  new ValidationExcepetion("possivel marcar  consulta com  30 minutos de antecedencia apenas ");
        }

    }

}
