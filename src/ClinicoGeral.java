import java.util.List;

public class ClinicoGeral extends Profissional {
    private String encaminhamento;

    
    public ClinicoGeral(String nome, String cpf, String telefone, int idade, String encaminhamento) {
        super(nome, cpf, telefone, idade, "clinica geral"); 
        this.encaminhamento = encaminhamento ;
    }

    
    
    public ClinicoGeral(String nome, String cpf, String telefone, int idade, String registroProfissional, double valorConsulta, List<HorarioDisponivel> horarios, String encaminhamento) {
        super(nome, cpf, telefone, idade, "clinica geral", registroProfissional, valorConsulta, horarios);
        this.encaminhamento = encaminhamento;
    }
    // SOBRESCRITA
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null && atendimento.getProntuario() != null) {
            String obsAtual = atendimento.getProntuario().getObservacoes();
            // Injeta o encaminhamento nas observações do prontuário 
            atendimento.getProntuario().setObservacoes(obsAtual + " | Encaminhamento: " + this.encaminhamento);
        }
    }
    // SOBRESCRITA
    @Override
    public String exibirResumo() {
        return "Clinico Geral: " + getNome() + " | Reg: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta() + " | Dias: " + getHorariosDisponiveis().toString();
    }

    
    public String getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(String encaminhamento) {
        this.encaminhamento = encaminhamento;
    }
}