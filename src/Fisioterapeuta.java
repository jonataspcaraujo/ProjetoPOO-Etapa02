import java.util.List;

public class Fisioterapeuta extends Profissional {
    private int totalSessoesPrevistas;

    
    public Fisioterapeuta(String nome, String cpf, String telefone, int idade, int totalSessoesPrevistas) {
        super(nome, cpf, telefone, idade, "fisioterapia"); 
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }

    
    
    public Fisioterapeuta(String nome, String cpf, String telefone, int idade, String registroProfissional, double valorConsulta, List<HorarioDisponivel> horarios, int totalSessoesPrevistas) {
        super(nome, cpf, telefone, idade, "fisioterapia", registroProfissional, valorConsulta, horarios);
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }
    // SOBRESCRITA
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null && atendimento.getProntuario() != null) {
            String obsAtual = atendimento.getProntuario().getObservacoes();
            // Injeta o total de sessões previstas nas observações do prontuário 
            atendimento.getProntuario().setObservacoes(obsAtual + " | Total de Sessões Previstas: " + this.totalSessoesPrevistas);
        }
    }
    // SOBRESCRITA
    @Override
    public String exibirResumo() {
        return "Fisioterapeuta: " + getNome() + " | Reg: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta() + " | Horários: " + getHorariosDisponiveis().toString()
                + " | Sessões Previstas: " + getTotalSessoesPrevistas();
    }

    
    public int getTotalSessoesPrevistas() {
        return totalSessoesPrevistas;
    }

    public void setTotalSessoesPrevistas(int totalSessoesPrevistas) {
        if (totalSessoesPrevistas <= 0) {
            System.out.println("O total de sessões deve ser maior que zero.");
        }
        this.totalSessoesPrevistas = totalSessoesPrevistas;
    }
}
