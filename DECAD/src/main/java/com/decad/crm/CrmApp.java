package com.decad.crm;

import com.decad.crm.controller.IAutenticacaoController;
import com.decad.crm.controller.implement.AutenticacaoController;
import com.decad.crm.dao.IUsuarioDAO;
import com.decad.crm.dao.implement.UsuarioDAO;
import com.decad.crm.view.MenuAgenda;
import com.decad.crm.view.MenuPaciente;
import com.decad.crm.view.MenuPrincipal;
import com.decad.crm.view.MenuProfissional;

import java.util.Scanner;

public class CrmApp {

    private final Scanner scanner;
    private final MenuPrincipal menuPrincipal;
    private final MenuPaciente menuPaciente;
    private final MenuProfissional menuProfissional;
    private final MenuAgenda menuAgenda;
    private final IAutenticacaoController autenticador;

    public CrmApp() {

        this.scanner = new Scanner(System.in);

        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        this.autenticador = new AutenticacaoController(usuarioDAO);

        this.menuPrincipal = new MenuPrincipal(this.scanner);
        this.menuPaciente = new MenuPaciente(this.scanner);
        this.menuProfissional = new MenuProfissional(this.scanner);
        this.menuAgenda = new MenuAgenda(this.scanner);

    }

    private boolean realizarLogin() {
        System.out.println("\n=================================");
        System.out.println("   BEM-VINDO AO DECAD CRM");
        System.out.println("=================================");
        System.out.println("Por favor, faça o login para continuar.");

        boolean logado = false;
        while (!logado) {
            System.out.print("Login: ");
            String login = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            if (this.autenticador.login(login, senha)) {
                logado = true;
            }
            else{
                System.out.println("Credenciais de login ou senha incorretos. Tente novamente");
            }
        }

        return logado;
    }

    public void run() {

        boolean executando = true;

        while (executando) {
            int escolha = menuPrincipal.mostrarMenuPrincipal();

            switch (escolha) {
                case 1:
                    menuPaciente.exibirMenu();
                    break;
                case 2:
                    menuProfissional.exibirMenu();
                    break;
                case 3:
                    menuAgenda.mostrarConsultarAgenda();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.err.println("Opção inválida retornada ao CrmApp.");
            }
        }

        System.out.println("\nObrigado por usar o DECAD CRM. Encerrando...");
        this.scanner.close();
    }

    public static void main(String[] args) {
        CrmApp app = new CrmApp();
        app.realizarLogin();
        app.run();
    }
}