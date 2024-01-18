package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidationExcepetion;
import med.voll.api.domain.consulta.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public  DadosDetalhamentoConsulta agendar(AgendamentoConsultaDTO dados){
        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidationExcepetion("Id do paciente invalido!");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw  new ValidationExcepetion("Id  do médico é invalido");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente =  pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw  new ValidationExcepetion("Nenhum mnédico disponivel nessa data");

        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        return  new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null){
            return  medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null){
            throw  new ValidationExcepetion("Especialidade obrigatoria quando  o médidco n for especioficado");

        }
        return  medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelarConsulta(DadosCancelamentoConsultaDTO dados) {
        if(!consultaRepository.existsById(dados.idConsulta())){
            throw  new ValidationExcepetion("Id da consulta  invalido!");
        }
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelarConsulta(dados.motivo());
    }
}
