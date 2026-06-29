package jornadas;

import repositorio.ClinicaRepositorio;
import model.*;
import exceptions.*;
import java.util.Scanner;

public class ProcessamentoPagamentos {
    private ClinicaRepositorio repositorio;
    private Scanner sc = new Scanner(System.in);

    public ProcessamentoPagamentos(ClinicaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void executar() {
        System.out.println("\nPROCESSAR PAGAMENTO");
        System.out.print("Indice da Consulta realizada: ");
        int indice;
        try { indice = Integer.parseInt(sc.nextLine()); } catch (Exception e) { System.out.println("Indice invalido."); return; }
        
        try {
            Consulta c = repositorio.getConsulta(indice);
            if (!c.getStatus().equals("realizada")) {
                throw new OperacaoInvalidaException("Pagamento so pode ser feito para consultas realizadas.");
            }
            
            Profissional prof = repositorio.buscarProfissional(c.getNomeProfissional());
            double valorBase = prof.getValorConsulta();
            
            System.out.println("Valor base da consulta: R$" + valorBase);
            System.out.println("Tipo de Pagamento (dinheiro / cartao / convenio): ");
            String tipo = sc.nextLine().toLowerCase();
            
            Pagamento p = null;
            if (tipo.equals("dinheiro") || tipo.equals("pix")) {
                p = new PagamentoDinheiro(indice, valorBase);
            } else if (tipo.equals("cartao")) {
                System.out.print("Numero de parcelas (1 a 6): ");
                int parc = Integer.parseInt(sc.nextLine());

                if (parc < 1 || parc > 6) 
                    throw new PagamentoInvalidoException("Parcelas devem ser de 1 a 6.");
                p = new PagamentoCartao(indice, valorBase, parc);

            } else if (tipo.equals("convenio")) {
                Paciente pac = repositorio.buscarPaciente(c.getCpfPaciente());
                Convenio conv = pac.getConvenio();

                if (conv == null) 
                    throw new PagamentoInvalidoException("Paciente sem convenio cadastrado.");
                
                if (!conv.cobre(prof.getEspecialidade())) {
                    throw new ConvenioNaoCobreException("O convenio " + conv.getNome() + " nao cobre " + prof.getEspecialidade());
                }
                
                p = new PagamentoConvenio(indice, valorBase, conv);
            } else {
                throw new PagamentoInvalidoException("Tipo invalido.");
            }
            
            repositorio.getPagamentos().add(p);
            System.out.println("Pagamento registrado! Valor final: R$" + p.calcularValorFinal());
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            System.out.println("Operação de pagamento finalizada");
        }
    }
}
