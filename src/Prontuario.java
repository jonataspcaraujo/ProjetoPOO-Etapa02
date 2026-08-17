/*
 * COMPOSIÇÃO
 *
 * O prontuário pertence ao atendimento.
 * Ele é criado junto com o atendimento
 * e representa os dados clínicos registrados.
 */

public class Prontuario {

    private String observacoes;
    private String diagnostico;
    private String[] procedimentos;
    private int totalProcedimentos;

    public Prontuario(String observacoes, String diagnostico) {

        this.observacoes = observacoes;
        this.diagnostico = diagnostico;

        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;
    }

    public void adicionarProcedimento(String procedimento) {

        if (totalProcedimentos < 10) {

            procedimentos[totalProcedimentos] = procedimento;
            totalProcedimentos++;
        }
    }

    public String getObservacoes() {
        return observacoes;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String[] getProcedimentos() {
        return procedimentos;
    }

    public int getTotalProcedimentos() {
        return totalProcedimentos;
    }
}
