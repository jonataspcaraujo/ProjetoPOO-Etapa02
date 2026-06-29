package relatorios;

import repositorio.ClinicaRepositorio;
import model.*;

public class RelatorioFinanceiro {
    private ClinicaRepositorio repositorio;

    public RelatorioFinanceiro(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nRESUMO FINANCEIRO");
        int realizadas = 0, canceladas = 0;
        double totalFaturado = 0, totalMultas = 0;

        for (Consulta c : repositorio.getConsultas()) {
            if (c.getStatus().equals("realizada")) 
                realizadas++;
            if (c.getStatus().equals("cancelada")) 
                canceladas++;
        }

        for (Pagamento p : repositorio.getPagamentos()) 
            totalFaturado += p.calcularValorFinal();
            
        for (Double m : repositorio.getMultas()) 
            totalMultas += m;

        System.out.println("Atendimentos realizados: " + realizadas);
        System.out.println("Total faturado: R$" + Math.round(totalFaturado * 100.0) / 100.0);
        System.out.println("Cancelamentos: " + canceladas);
        System.out.println("Total em multas: R$" + Math.round(totalMultas * 100.0) / 100.0);
    }
}
