/*
 * CLASSE ATENDIMENTO
 *
 * Esta classe representa um atendimento realizado
 * para uma consulta da clínica.
 *
 * Conceitos aplicados:
 * - Encapsulamento
 * - Composição
 * - Sobrecarga
 * - Interface
 */

public class Atendimento implements Exportavel {

    /*
     * Mantidos públicos para não quebrar
     * Main.java e Relatorio.java.
     */
    public int indiceConsulta;
    public String observacoes;
    public String diagnostico;
    public String[] procedimentos;
    public int totalProcedimentos;

    /*
     * COMPOSIÇÃO
     * O prontuário pertence ao atendimento.
     */
    private Prontuario prontuario;

    /*
     * Atendimento especializado.
     */
    private String observacaoEspecializada;

    // registro básico

    public Atendimento(int indiceConsulta,
                       String observacoes) {

        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = "";

        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;

        this.prontuario =
                new Prontuario(
                        observacoes,
                        "");

        this.observacaoEspecializada = "";
    }

    // registro com diagnóstico

    public Atendimento(int indiceConsulta,
                       String observacoes,
                       String diagnostico) {

        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;

        this.procedimentos = new String[10];
        this.totalProcedimentos = 0;

        this.prontuario =
                new Prontuario(
                        observacoes,
                        diagnostico);

        this.observacaoEspecializada = "";
    }

    // registro completo

    public Atendimento(int indiceConsulta,
                       String observacoes,
                       String diagnostico,
                       String[] procedimentos,
                       int totalProcedimentos) {

        this.indiceConsulta = indiceConsulta;
        this.observacoes = observacoes;
        this.diagnostico = diagnostico;

        this.procedimentos = new String[10];
        this.totalProcedimentos = totalProcedimentos;

        for (int i = 0; i < totalProcedimentos; i++) {

            this.procedimentos[i] =
                    procedimentos[i];
        }

        this.prontuario =
                new Prontuario(
                        observacoes,
                        diagnostico);

        for (int i = 0; i < totalProcedimentos; i++) {

            this.prontuario
                    .adicionarProcedimento(
                            procedimentos[i]);
        }

        this.observacaoEspecializada = "";
    }

    // adiciona um procedimento

    public void adicionarProcedimento(
            String procedimento) {

        if (totalProcedimentos < 10) {

            procedimentos[totalProcedimentos] =
                    procedimento;

            totalProcedimentos++;

            prontuario
                    .adicionarProcedimento(
                            procedimento);
        }
    }

    // sobrecarga

    public void adicionarProcedimento(
            String[] procs,
            int quantidade) {

        for (int i = 0; i < quantidade; i++) {

            adicionarProcedimento(
                    procs[i]);
        }
    }

    /*
     * Atendimento especializado
     * Jornada 25.
     */
    public void registrarObservacaoEspecializada(
            String texto) {

        observacaoEspecializada = texto;
    }

    public String getObservacaoEspecializada() {

        return observacaoEspecializada;
    }

    public Prontuario getProntuario() {

        return prontuario;
    }

    public String exibirResumo() {

        String resumo =
                "Observacoes: " + observacoes;

        if (!diagnostico.equals("")) {

            resumo +=
                    "\nDiagnostico: "
                            + diagnostico;
        }

        if (totalProcedimentos > 0) {

            resumo += "\nProcedimentos: ";

            for (int i = 0;
                 i < totalProcedimentos;
                 i++) {

                resumo += procedimentos[i];

                if (i < totalProcedimentos - 1) {

                    resumo += ", ";
                }
            }
        }

        if (!observacaoEspecializada.equals("")) {

            resumo +=
                    "\nObservacao Especializada: "
                            + observacaoEspecializada;
        }

        return resumo;
    }

    /*
     * Interface Exportavel
     * Jornada 26.
     */
    @Override
    public String exportarDados() {

        return "ATENDIMENTO|"
                + indiceConsulta
                + "|"
                + diagnostico
                + "|"
                + totalProcedimentos;
    }
}
