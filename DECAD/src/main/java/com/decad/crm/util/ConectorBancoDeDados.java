package com.decad.crm.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorBancoDeDados {
    public static Connection conectar(){
        System.out.println("Conectando ao banco de dados...");
        try{
            Dotenv dotenv = Dotenv.load();

            String url = dotenv.get("DB_URL");
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");

            System.out.println("Banco de dados conectado");
            return DriverManager.getConnection(url, user, password);
        } catch(SQLException e){
            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
        }
    }
}
