package com.decad.crm.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBancoDeDados {
    private static Dotenv dotenv = Dotenv.load();
    private static String url = dotenv.get("DB_URL");
    private static String usuario = dotenv.get("DB_USER");
    private static String senha = dotenv.get("DB_PASSWORD");

    public static Connection getConexao(){
        try{
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.err.println("Erro ao se conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();

            if(usuario == null || senha == null || url == null){
                System.out.println("Provavelmente é o arquivo .env, que não está configurado. Verifique o arquivo que deve estar na raiz do projeto.");
            }
            return null;
        }
    }
}
