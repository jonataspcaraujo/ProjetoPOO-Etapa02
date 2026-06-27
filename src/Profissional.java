import java.util.ArrayList;
import java.util.List;


public class Profissional extends Pessoa {
    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;
    // AGREGAÇÃO: Profissional possui horários, mas horários sobrevivem sem o profissional
    private List<HorarioDisponivel> horariosDisponiveis = new ArrayList<>();

    
    public Profissional(String nome, String cpf, String telefone, int idade, String especialidade) {
        super(nome, cpf, telefone, idade); // Chama o construtor da classe Pessoa
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String telefone, int idade, 
                        String especialidade, String registroProfissional,
                        double valorConsulta, List<HorarioDisponivel> horarios) {
        super(nome, cpf, telefone, idade);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.horariosDisponiveis = new ArrayList<>();
        if (horarios != null) {
            this.horariosDisponiveis.addAll(horarios);
        }
        }

    public void adicionarHorario(HorarioDisponivel horario) {
        if (horario != null) {
            this.horariosDisponiveis.add(horario);
        }
    }

    public void atualizar(String registro, double valor) {
        this.registroProfissional = registro;
        this.valorConsulta = valor;
    }

    public void atualizar(String registro, double valor, List<HorarioDisponivel> horarios) {
        this.registroProfissional = registro;
        this.valorConsulta = valor;
        this.horariosDisponiveis = new ArrayList<>();
        if (horarios != null) {
            this.horariosDisponiveis.addAll(horarios);
        }
        }


    
    public boolean atendeNoDia(String dia) {
        for (HorarioDisponivel h : horariosDisponiveis) {
            if (h.getDiaSemana().equals(dia)) {
                return true;
            }
        }
        return false;
    }

    public boolean atendeNoHorario(String dia, String turno) {
        if (dia == null || turno == null) return false;
        for (HorarioDisponivel h : horariosDisponiveis) {
            if (h.getDiaSemana().equals(dia) && h.getTurno().equals(turno)) {
                return true;
            }
        }
        return false;
    }

    public static boolean especialidadeValida(String esp) {
        if (esp.equals("clinica geral")) return true;
        if (esp.equals("fisioterapia")) return true;
        if (esp.equals("psicologia")) return true;
        if (esp.equals("nutricao")) return true;
        return false;
    }

    // SOBRESCRITA
    @Override
    public String exibirResumo() {
        return "Nome: " + getNome() + " | Espec: " + especialidade + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta + " | Horários: " + horariosDisponiveis.toString();
    }

    
    public void registrarEspecifico(Atendimento atendimento) {
        // Vazio na classe base
    }

    

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(double valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    public List<HorarioDisponivel> getHorariosDisponiveis() { 
        return horariosDisponiveis; 
    }

}
