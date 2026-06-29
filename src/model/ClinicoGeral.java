package model;

public class ClinicoGeral extends Profissional {

    private String encaminhamento;

    public ClinicoGeral(String nome, String cpf) {
        super(nome, cpf, "clinica geral");
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        this.encaminhamento = "";
    }

    public ClinicoGeral(String nome, String cpf, String registroProfissional,
                        double valorConsulta, String encaminhamento) {
        super(nome, cpf, "clinica geral", registroProfissional, valorConsulta);
        this.encaminhamento = encaminhamento;
    }

    public String getEncaminhamento() { 
        return encaminhamento; 
    }
    public void setEncaminhamento(String encaminhamento) { 
        this.encaminhamento = encaminhamento; 
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (!encaminhamento.isEmpty()) {
            atendimento.getProntuario().adicionarProcedimento(
                    "Encaminhamento para: " + encaminhamento
            );
        }
    }

    @Override
    public String exibirResumo() {
        return "CLINICO GERAL | " + getDadosBase()
                + " | Encaminhamento: " + (encaminhamento.isEmpty() ? "nenhum" : encaminhamento);
    }
}