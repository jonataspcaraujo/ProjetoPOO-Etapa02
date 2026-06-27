import java.util.List;

public class Psicologo extends Profissional {
    private String abordagem;

    
    public Psicologo(String nome, String cpf, String telefone, int idade, String abordagem) {
        super(nome, cpf, telefone, idade, "psicologia"); 
        this.abordagem = abordagem ;
    }

    
    
    public Psicologo(String nome, String cpf, String telefone, int idade, String registroProfissional, double valorConsulta, List<HorarioDisponivel> horarios, String abordagem) {
        super(nome, cpf, telefone, idade, "psicologia", registroProfissional, valorConsulta, horarios);
        this.abordagem = abordagem;
    }
    // SOBRESCRITA
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null && atendimento.getProntuario() != null) {
            String obsAtual = atendimento.getProntuario().getObservacoes();
            // Injeta a abordagem terapêutica nas observações do prontuário 
            atendimento.getProntuario().setObservacoes(obsAtual + " | Abordagem Terapêutica: " + this.abordagem);
        }
    }
    // SOBRESCRITA
    @Override
    public String exibirResumo() {
        return "Psicologo: " + getNome() + " | Reg: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta() + " | Horários: " + getHorariosDisponiveis().toString()
                + " | Abordagem: " + getAbordagem();
    }

    
    public String getAbordagem() {
        return abordagem;
    }

    public void setAbordagem(String abordagem) {
        this.abordagem = abordagem;
    }
}