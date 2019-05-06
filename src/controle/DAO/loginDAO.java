package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class loginDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public loginDAO() {
        con = new FabricaDeConexoes().getConnection();
    }

    public boolean logarSistema(String login, String senha) throws SQLException {
        boolean status = false;
        String sql = "SELECT * FROM usuario WHERE login=? AND senha=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);

        rs = stmt.executeQuery();

        if (rs.next()) {
            status = true;
        } else {
            status = false;
        }

        stmt.close();
        rs.close();
        con.close();
        return status;
    }

    public Usuario pegarUsuario(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE login=? AND senha=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        rs = stmt.executeQuery();
        
        if (rs.next()) {
            Usuario u;
            u = new Usuario();
            u.setId(rs.getInt("id"));
            //u.setNome(rs.getString("nome"));
            u.setUsuario(rs.getString("usuario"));
            u.setSenha(rs.getString("senha"));
            u.setTipo(rs.getString("perfil"));
            stmt.close();
            rs.close();
            con.close();
            return u;
        } else {
            stmt.close();
            rs.close();
            con.close();
            return null;
        }

    }

}
