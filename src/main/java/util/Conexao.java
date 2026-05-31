package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    static final String url = "jdbc:postgresql://localhost:5432/gif"; 
    static final String usuario = "gif"; 
    static final String senha = "gif"; 

    public static Connection obterConexao()
        throws SQLException {
            return DriverManager.getConnection(url, usuario, senha);
    }
}
