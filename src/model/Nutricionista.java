package model;

public class Nutricionista extends Profissional {

    private String planoAlimentar;

    public Nutricionista(String nome, String cpf) {
        super(nome, cpf, "nutricao");
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta);
        this.planoAlimentar = "";
    }

    public Nutricionista(String nome, String cpf, String registroProfissional,
                         double valorConsulta, String planoAlimentar) {
        super(nome, cpf, "nutricao", registroProfissional, valorConsulta);
        this.planoAlimentar = planoAlimentar;
    }

    public String getPlanoAlimentar() { 
        return planoAlimentar; 
    }
    public void setPlanoAlimentar(String planoAlimentar) { 
        this.planoAlimentar = planoAlimentar; 
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (!planoAlimentar.isEmpty()) {
            atendimento.getProntuario().adicionarProcedimento(
                    "Plano alimentar: " + planoAlimentar
            );
        }
    }

    @Override
    public String exibirResumo() {
        return "NUTRICIONISTA | " + getDadosBase()
                + " | Plano alimentar: " + (planoAlimentar.isEmpty() ? "nao definido" : planoAlimentar);
    }
}
