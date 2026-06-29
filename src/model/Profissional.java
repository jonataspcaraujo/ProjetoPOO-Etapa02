package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Profissional extends Pessoa {

    private String especialidade;
    private String registroProfissional;
    private double valorConsulta;

    private List<HorarioDisponivel> horariosDisponiveis;

    public Profissional(String nome, String cpf, String especialidade) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = "";
        this.valorConsulta = 0;
        this.horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String especialidade,
                        String registroProfissional, double valorConsulta) {
        super(nome, cpf);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.horariosDisponiveis = new ArrayList<>();
    }

    public Profissional(String nome, String cpf, String telefone, String dataNascimento,
                        String especialidade, String registroProfissional, double valorConsulta) {
        super(nome, cpf, telefone, dataNascimento);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
        this.valorConsulta = valorConsulta;
        this.horariosDisponiveis = new ArrayList<>();
    }

    public String getEspecialidade() { 
        return especialidade; 
    }
    
    public String getRegistroProfissional() { 
        return registroProfissional; 
    }
    
    public double getValorConsulta() { 
        return valorConsulta; 
    }
    
    public List<HorarioDisponivel> getHorariosDisponiveis() { 
        return horariosDisponiveis; 
    }

    public void setEspecialidade(String especialidade) { 
        this.especialidade = especialidade; 
    }
    public void setRegistroProfissional(String registroProfissional) { this.registroProfissional = registroProfissional; }

    public void setValorConsulta(double valorConsulta) {
        if (valorConsulta < 0) {
            throw new IllegalArgumentException("Valor da consulta nao pode ser negativo.");
        }
        this.valorConsulta = valorConsulta;
    }

    public void atualizar(String registro, double valor) {
        this.registroProfissional = registro;
        setValorConsulta(valor);
    }

    public void atualizar(String registro, double valor, List<HorarioDisponivel> horarios) {
        this.registroProfissional = registro;
        setValorConsulta(valor);
        this.horariosDisponiveis = horarios;
    }

    public void adicionarHorario(HorarioDisponivel horario) {
        horariosDisponiveis.add(horario);
    }

    public boolean atendeNoDia(String dia) {
        for (HorarioDisponivel h : horariosDisponiveis) {
            if (h.getDiaSemana().equalsIgnoreCase(dia)) return true;
        }
        return false;
    }

    protected String getDadosBase() {
        StringBuilder dias = new StringBuilder();

        for (int i = 0; i < horariosDisponiveis.size(); i++) {
            if (i > 0) dias.append(", ");
            dias.append(horariosDisponiveis.get(i).exibirResumo());
        }
        return "Nome: " + nome + " | Espec: " + especialidade
                + " | Reg: " + registroProfissional
                + " | Valor: R$" + valorConsulta
                + " | Horarios: " + (dias.length() == 0 ? "nenhum" : dias.toString());
    }

    public static boolean especialidadeValida(String esp) {
        if (esp == null) return false;
        return esp.equals("clinica geral") || esp.equals("fisioterapia")
                || esp.equals("psicologia") || esp.equals("nutricao");
    }

    public abstract void registrarEspecifico(Atendimento atendimento);

    @Override
    public abstract String exibirResumo();
}