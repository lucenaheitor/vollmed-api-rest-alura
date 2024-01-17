package med.voll.api.domain.consulta.agendamento;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.consulta.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioDeFuncionamentoClinica implements  ValidadorAgendamentoDeConsulta {

    public void validar(AgendamentoConsultaDTO dados){
        LocalDateTime dataHoraConsulta = dados.data();
        LocalDateTime agora = LocalDateTime.now();

        if(dataHoraConsulta.getDayOfWeek() == DayOfWeek.SUNDAY){
            throw  new ValidationExcepetion("A clinica só funciona de segunda a sabado");
        }
        var horaDeFuncionamento =  dataHoraConsulta.getHour();
        if(horaDeFuncionamento <7 ||  horaDeFuncionamento>= 18){
            throw  new ValidationExcepetion("A clínica só funciona das 7 da manhã às 19h.");
        }

        // Verifica se a consulta é agendada com pelo menos 30 minutos de antecedência
//        Duration diferenca = Duration.between(agora, dataHoraConsulta);
//        if (diferenca.toMinutes() < 30) {
//            throw new RuntimeException("A consulta deve ser agendada com pelo menos 30 minutos de antecedência.");
//        }

    }
}
