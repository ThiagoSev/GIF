package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.Conexao;

import dao.*;
import ui.*;

public class Main {

    public static void main(String[] args) {
        new LoginForm().setVisible(true);
        
        //teste
        Usuario admin = new Usuario(
        1,
        "admin",
        "123",
        true
    );

    }
}
