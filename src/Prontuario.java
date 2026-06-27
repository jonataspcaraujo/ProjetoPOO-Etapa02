import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Prontuario {

    // encapsulamento todos os atributos em private
    private String observacoes;
    private String diagnosticoDoPaciente;
    private List<String> procedimentoRealizados; //  colecao no lugar de arrays
    private LocalDate dataCriacao;

    Prontuario(String observacoes, String diagnosticoDoPaciente) {
        this.observacoes = observacoes;
        this.diagnosticoDoPaciente = diagnosticoDoPaciente;
        this.dataCriacao = LocalDate.now();// registra data atual do sistema
        this.procedimentoRealizados = new ArrayList<>();// comeca a lista vazia
    }

    //getters
    public String getObservacoes() {
        return observacoes;
    }

    public String getDiagnosticoDoPaciente() {
        return diagnosticoDoPaciente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

   public List<String> getProcedimentosRealizados() {
    return new ArrayList<>(procedimentoRealizados);
}

    //setters com validacao interna
    public void setObservacoes(String observacoes) {
        if (observacoes == null || observacoes.trim().isEmpty()) {
            System.out.println("Erro: A observação não pode estar vazia.");
            return;
        }
        this.observacoes = observacoes;
    }

    public void setDiagnosticoDoPaciente(String diagnosticoDoPaciente) {
        if (diagnosticoDoPaciente == null || diagnosticoDoPaciente.trim().isEmpty()) {
            System.out.println("Erro: O diagnóstico não pode estar vazia.");
            return;
        }
        this.diagnosticoDoPaciente = diagnosticoDoPaciente;
    }

    public void adicionarProcedimento (String procedimento) {
        if (procedimento != null && !procedimento.trim().isEmpty()) {
            this.procedimentoRealizados.add(procedimento);
        } else {
            System.out.println("Erro: Procedimento inválido.");
        }
    }
}
