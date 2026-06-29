package model;

public class Psicologo extends Profissional {

    private String abordagem;

    public Psicologo(String nome, String cpf) {
        super(nome, cpf, "psicologia");
        this.abordagem = "";
    }

    public Psicologo(String nome, String cpf, String registroProfissional,
                     double valorConsulta, String abordagem) {
        super(nome, cpf, "psicologia", registroProfissional, valorConsulta);
        this.abordagem = abordagem;
    }

    public String getAbordagem() { return abordagem; }
    public void setAbordagem(String abordagem) { this.abordagem = abordagem; }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (!abordagem.isEmpty()) {
            atendimento.getProntuario().adicionarProcedimento(
                    "Abordagem terapeutica: " + abordagem
            );
        }
    }

    @Override
    public String exibirResumo() {
        return "PSICOLOGO | " + getDadosBase()
                + " | Abordagem: " + (abordagem.isEmpty() ? "nao informada" : abordagem);
    }
}