package model;

public class Fisioterapeuta extends Profissional {

    private int totalSessoesPrevistas;

    public Fisioterapeuta(String nome, String cpf) {
        super(nome, cpf, "fisioterapia");
        this.totalSessoesPrevistas = 10;
    }

    public Fisioterapeuta(String nome, String cpf, String registroProfissional, double valorConsulta) {
        super(nome, cpf, "fisioterapia", registroProfissional, valorConsulta);
        this.totalSessoesPrevistas = 10;
    }

    public Fisioterapeuta(String nome, String cpf, String registroProfissional,
                          double valorConsulta, int totalSessoesPrevistas) {
        super(nome, cpf, "fisioterapia", registroProfissional, valorConsulta);
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    public int getTotalSessoesPrevistas() { 
        return totalSessoesPrevistas; 
    }
    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) { 
        this.totalSessoesPrevistas = totalSessoesPrevistas; 
    }

    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        atendimento.getProntuario().adicionarProcedimento(
                "Plano de fisioterapia: " + totalSessoesPrevistas + " sessoes previstas"
        );
    }

    @Override
    public String exibirResumo() {
        return "FISIOTERAPEUTA | " + getDadosBase()
                + " | Sessoes previstas: " + totalSessoesPrevistas;
    }
}
