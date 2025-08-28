package com.base.demo;

import java.util.Scanner;

import com.base.demo.model.Acao;
import com.base.demo.service.BrapiService;

public class Main {
    public static void main(String[] args) {
        BrapiService service = new BrapiService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nDigite o ticker da ação (ex: PETR4) ou 'sair' para terminar:");
            String ticker = scanner.nextLine();

            if (ticker.equalsIgnoreCase("sair")) {
                break;
            }

            Acao acao = service.consultarAcao(ticker);

            if (acao != null) {
                System.out.println("------------------------------------------");
                System.out.println("Dados da Ação:");
                System.out.println("Código: " + acao.getCodigo());
                System.out.println("Nome: " + acao.getNomeLongo());
                System.out.printf("Preço: R$ %.2f\n", acao.getPreco());
                System.out.println("------------------------------------------");
            } else {
                System.out.println("Não foi possível encontrar dados para o ticker informado.");
            }
        }

        System.out.println("Programa finalizado.");
        scanner.close();
    }
}