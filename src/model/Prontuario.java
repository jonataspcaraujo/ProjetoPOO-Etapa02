package model;

import java.util.ArrayList;
import java.util.List;

class Prontuario {

    private String observacoes;
    private String diagnostico;

    private List<String> procedimentos;
    private String dataRegistro;

    Prontuario(String observacoes, String dataRegistro) {
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = dataRegistro;
    }

    Prontuario(String observacoes, String diagnostico, String dataRegistro) {
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = dataRegistro;
    }

    public String getObservacoes() { return observacoes; }
    public String getDiagnostico() { return diagnostico; }
    public List<String> getProcedimentos() { return procedimentos; }
    public String getDataRegistro() { return dataRegistro; }

    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public void setDataRegistro(String dataRegistro) { this.dataRegistro = dataRegistro; }

    public void adicionarProcedimento(String procedimento) {
        procedimentos.add(procedimento);
    }

    public void adicionarProcedimento(List<String> procs) {
        procedimentos.addAll(procs);
    }

    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Observacoes: ").append(observacoes);
        sb.append(" | Data: ").append(dataRegistro);
        
        if (!diagnostico.isEmpty()) {
            sb.append("\nDiagnostico: ").append(diagnostico);
        }
        if (!procedimentos.isEmpty()) {
            sb.append("\nProcedimentos: ").append(String.join(", ", procedimentos));
        }
        return sb.toString();
    }
}
