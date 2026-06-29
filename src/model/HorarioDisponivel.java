package model;

public class HorarioDisponivel {

    private String diaSemana;
    private String turno;

    public HorarioDisponivel(String diaSemana, String turno) {
        this.diaSemana = diaSemana;
        this.turno = turno;
    }

    public HorarioDisponivel(String diaSemana) {
        this.diaSemana = diaSemana;
        this.turno = "manha";
    }

    public String getDiaSemana() { 
        return diaSemana; 
    }
    public String getTurno() { 
        return turno; 
    }

    public void setDiaSemana(String diaSemana) { 
        this.diaSemana = diaSemana; 
    }
    public void setTurno(String turno) { 
        this.turno = turno; 
    }

    public String exibirResumo() {
        return diaSemana + " (" + turno + ")";
    }
}