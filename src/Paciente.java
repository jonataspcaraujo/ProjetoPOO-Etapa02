public class Paciente extends Pessoa{
    // ASSOCIAÇÃO: Paciente conhece Convenio, mas ambos existem independentemente
    private Convenio convenio;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.convenio = null;
        this.ativo = true;
    }
    
    public Paciente(String nome, String cpf, String telefone, int idade) {
        super(nome, cpf, telefone, idade);
        this.convenio = null;
        this.ativo = true;
    }

    // construtor com todos os dados
    public Paciente(String nome, String cpf, String telefone, int idade, Convenio convenio) {
        super(nome, cpf, telefone, idade);
        this.convenio = convenio;
        this.ativo = true;
    }

    // atualiza so idade e telefone
    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    // atualiza tudo incluindo convenio
    public void complementar(int idade, String telefone, Convenio convenio) {
        setIdade(idade);
        setTelefone(telefone);
        this.convenio = convenio;
    }

    public void desativar() {
        this.ativo = false;
    }

    public Convenio getConvenioNome() {
        return convenio;
    }

    public void setConvenioNome(Convenio convenio) {
        this.convenio = convenio;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String exibirResumo() {
        String status = "Sim";
        if (!ativo) {
            status = "Nao";
        }
        return "Nome: " + getNome() + " | CPF: " + getCpf() + " | Idade: " + getIdade()
                + " | Tel: " + getTelefone() + " | Convenio: " + (convenio != null ? convenio.getNome() : "Nenhum")
                + " | Ativo: " + status;
    }
}
