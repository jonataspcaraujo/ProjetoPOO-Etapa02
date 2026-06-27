import java.util.List;

public class Atendimento implements Exportavel {

    private Consulta consulta;
    // COMPOSIÇÃO: Prontuário só existe dentro de Atendimento
    private Prontuario prontuario;

    // registro basico - so observacoes
    public Atendimento(Consulta consulta, String observacoes) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(observacoes,"Não informado" );
    }
    // registro com diagnostico, 3 parâmetros
    public Atendimento(Consulta consulta, String observacoes, String diagnostico) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(observacoes, diagnostico);
    }
    // registro completo com procedimentos ja definidos, 4 parâmetros
     public Atendimento(Consulta consulta, String observacoes, String diagnostico,
                       List<String> procedimentos) {
        this.consulta = consulta;
        this.prontuario = new Prontuario(observacoes, diagnostico);

         if (procedimentos != null){
             for (String proc : procedimentos) {
                 this.prontuario.adicionarProcedimento(proc);
             }
         }
     }

    // adiciona um por vez, 1 parâmetro
    public void adicionarProcedimento(String procedimento) {
            this.prontuario.adicionarProcedimento(procedimento);
            }
    // adiciona varios ed ima vez, 2 parâmetro
    public void adicionarProcedimento(List<String> procs) {
            if (procs == null) return;
           for (int i = 0; i < procs.size(); i++) {
                this.prontuario.adicionarProcedimento(procs.get(i));
}
    }

    public String exibirResumo() {
        String resumo = "Observacoes: " + prontuario.getObservacoes();

        String diag = prontuario.getDiagnosticoDoPaciente();
        if (diag != null &&!diag.trim().isEmpty() && !diag.equals("Não informado")) {
            resumo = resumo + "\nDiagnostico: " + diag;
        }

        List<String> listaProcs = prontuario.getProcedimentosRealizados();

        if (listaProcs != null && !listaProcs.isEmpty()) {
            resumo = resumo + "\nProcedimentos " + String.join(", ", listaProcs);
        }

        return resumo;
    }

    public Consulta getConsulta() {
        return consulta;
    }
    public Prontuario getProntuario() {
        return prontuario;
    }
    // SOBRESCRITA
    @Override
    public String exportarDados() {
        String procs = prontuario.getProcedimentosRealizados() != null ? 
                       String.join(",", prontuario.getProcedimentosRealizados()) : "Nenhum";
                       
        return "TIPO:Atendimento | CPF_PACIENTE:" + consulta.getCpfPaciente() +
               " | DATA:" + consulta.getData() +
               " | OBSERVACOES:" + prontuario.getObservacoes() +
               " | DIAGNOSTICO:" + prontuario.getDiagnosticoDoPaciente() +
               " | PROCEDIMENTOS:" + procs;
    }
}
