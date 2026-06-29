package model;

public class Atendimento implements Exportavel {

    private int indiceConsulta;

    private Prontuario prontuario;

    public Atendimento(int indiceConsulta, String observacoes, String dataRegistro) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, dataRegistro);
    }

    public Atendimento(int indiceConsulta, String observacoes, String diagnostico, String dataRegistro) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, diagnostico, dataRegistro);
    }

    public int getIndiceConsulta() { 
        return indiceConsulta; 
    }
    public Prontuario getProntuario() { 
        return prontuario; 
    }

    public String getDiagnostico() { 
        return prontuario.getDiagnostico(); 
    }
    public String getObservacoes() { 
        return prontuario.getObservacoes(); 
    }
    public String getDataRegistro() { 
        return prontuario.getDataRegistro(); 
    }

    public void adicionarProcedimento(String procedimento) {
        prontuario.adicionarProcedimento(procedimento);
    }

    public void setIndiceConsulta(int indiceConsulta) { 
        this.indiceConsulta = indiceConsulta; 
    }

    @Override
    public String exportarDados() {
        return "[ATENDIMENTO] Consulta #" + indiceConsulta + " | " + prontuario.exibirResumo();
    }

    public String exibirResumo() {
        return "Atendimento da Consulta #" + indiceConsulta + "\n" + prontuario.exibirResumo();
    }
}