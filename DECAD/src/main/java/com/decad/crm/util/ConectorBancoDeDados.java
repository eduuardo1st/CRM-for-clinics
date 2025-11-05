package com.decad.crm.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBancoDeDados {

    public static Connection conectar() {
        String url = null;
        String user = null;
        String password = null;

        try {
            Dotenv dotenv = Dotenv.load();

            url = dotenv.get("DB_URL");
            user = dotenv.get("DB_USER");
            password = dotenv.get("DB_PASSWORD");

            // Se qualquer variável for nula, o .env não foi lido corretamente.
            if (url == null || user == null || password == null) {
                throw new RuntimeException("Falha ao carregar variáveis do arquivo .env. Verifique se o arquivo .env está na raiz do projeto e os nomes das variáveis (DB_URL, DB_USER, DB_PASSWORD) estão corretos.");
            }

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            // --- ESTE É O NOVO BLOCO DE DIAGNÓSTICO ---

            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.err.println("!!!               ERRO DE CONEXÃO SQL                !!!");
            System.err.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.err.println("O código Java não conseguiu se conectar ao seu MySQL.");
            System.err.println("URL usada:     " + url);
            System.err.println("Usuário usado: " + user);
            System.err.println("--------------------------------------------------------");
            System.err.println("Mensagem da SQLException: " + e.getMessage());
            System.err.println("Código de Erro SQL:     " + e.getErrorCode());
            System.err.println("SQLState:               " + e.getSQLState());
            System.err.println("--------------------------------------------------------");

            // Lança a exceção para parar a aplicação, como antes
            throw new RuntimeException("Erro detalhado de SQL ao conectar ao banco de dados!", e);

        } catch (Exception e) {
            // Pega outros erros, como o de .env não encontrado
            throw new RuntimeException("Erro ao preparar a conexão: " + e.getMessage(), e);
        }
    }
}