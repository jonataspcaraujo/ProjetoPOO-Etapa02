import java.util.List;

public class Nutricionista extends Profissional {
    private String planoAlimentar;

    
    public Nutricionista(String nome, String cpf, String telefone, int idade, String planoAlimentar) {
        super(nome, cpf, telefone, idade, "nutricao"); 
        this.planoAlimentar = planoAlimentar ;
    }

    
    
    public Nutricionista(String nome, String cpf, String telefone, int idade, String registroProfissional, double valorConsulta, List<HorarioDisponivel> horarios, String planoAlimentar) {
        super(nome, cpf, telefone, idade, "nutricao", registroProfissional, valorConsulta, horarios);
        this.planoAlimentar = planoAlimentar;
    }
    // SOBRESCRITA
    @Override
    public void registrarEspecifico(Atendimento atendimento) {
        if (atendimento != null && atendimento.getProntuario() != null) {
            String obsAtual = atendimento.getProntuario().getObservacoes();
            // Injeta o plano alimentar nas observações do prontuário 
            atendimento.getProntuario().setObservacoes(obsAtual + " | Plano Alimentar: " + this.planoAlimentar);
        }
    }
    // SOBRESCRITA
    @Override
    public String exibirResumo() {

        return "Nutricionista: " + getNome() + " | Reg: " + getRegistroProfissional()
                + " | Valor: R$" + getValorConsulta() + " | Horários: " + getHorariosDisponiveis().toString();
    }

    
    public String getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(String planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
    }
}