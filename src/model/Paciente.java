package model;

public class Paciente extends Pessoa {

    private int idade;
    private Convenio convenio;
    private boolean ativo;

    public Paciente(String nome, String cpf) {
        super(nome, cpf);
        this.idade = 0;
        this.convenio = null;
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone) {
        super(nome, cpf, telefone, "");
        this.idade = idade;
        this.convenio = null;
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio) {
        super(nome, cpf, telefone, "");
        this.idade = idade;
        this.convenio = convenio;
        this.ativo = true;
    }

    public Paciente(String nome, String cpf, int idade, String telefone, Convenio convenio, String dataNascimento) {
        super(nome, cpf, telefone, dataNascimento);
        this.idade = idade;
        this.convenio = convenio;
        this.ativo = true;
    }

    public int getIdade() { 
        return idade; 
    }
    public Convenio getConvenio() { 
        return convenio; 
    }
    public boolean isAtivo() { 
        return ativo; 
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade nao pode ser negativa.");
        }
        this.idade = idade;
    }

    public void setConvenio(Convenio convenio) { 
        this.convenio = convenio; 
    }
    public void setAtivo(boolean ativo) { 
        this.ativo = ativo; 
    }

    public void complementar(int idade, String telefone) {
        setIdade(idade);
        setTelefone(telefone);
    }

    public void complementar(int idade, String telefone, Convenio convenio) {
        setIdade(idade);
        setTelefone(telefone);
        this.convenio = convenio;
    }

    public void desativar() {
        this.ativo = false;
    }

    @Override
    public String exibirResumo() {
        String statusAtivo = ativo ? "Sim" : "Nao";
        String nomeConvenio = (convenio != null) ? convenio.getNome() : "Nenhum";
        return "PACIENTE | Nome: " + nome + " | CPF: " + cpf
                + " | Idade: " + idade + " | Tel: " + telefone
                + " | Convenio: " + nomeConvenio + " | Ativo: " + statusAtivo;
    }
}
