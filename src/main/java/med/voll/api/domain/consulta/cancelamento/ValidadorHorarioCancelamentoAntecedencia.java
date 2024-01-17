package med.voll.api.domain.consulta.cancelamento;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioCancelamentoAntecedencia implements  ValidadorCancelamentoConsulta {

    @Autowired
    private ConsultaRepository  repository;

    @Override
    public void validar(DadosCancelamentoConsultaDTO dados){
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if(diferencaEmHoras < 24){
            throw new ValidationExcepetion("Cancelamento só  é possivel com 30 minutos de antecedencia");
        }

    }

}
