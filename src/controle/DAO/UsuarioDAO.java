
package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public UsuarioDAO(){
        con = new FabricaDeConexoes().getConnection();
    }
    
    public void registrarUsuario(Usuario u) throws SQLException{
        String sql = "INSERT INTO usuario (usuario,login,perfil, senha) VALUES(?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getUsuario());
        stmt.setString(2, u.getLogin());
        stmt.setString(3, u.getTipo());
        stmt.setString(4, u.getSenha());
        
        stmt.executeUpdate();
        stmt.close();
        con.close();
        
    }
    
    public ArrayList<Usuario> listarUsuario() throws SQLException{
        String sql = "SELECT * FROM usuario";
        stmt = con.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
        rs = stmt.executeQuery();
        
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        
        while(rs.next()){
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setUsuario(rs.getString("usuario"));
            u.setLogin(rs.getString("login"));
            u.setSenha(rs.getString("senha"));
            u.setTipo(rs.getString("perfil"));
            lista.add(u);
        }
        stmt.close();
        rs.close();
        con.close();
        return lista;
    }
    
     public void editar(Usuario u) throws SQLException {
        String sql = "UPDATE usuario SET usuario=?, login=?, perfil=?, senha=? WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getUsuario());
        stmt.setString(2, u.getLogin());
        stmt.setString(3, u.getTipo());
        stmt.setString(4, u.getSenha());
        stmt.setInt(5, u.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
     public void remover(int id) throws SQLException{
         String sql = "DELETE FROM usuario WHERE id=?";
         stmt = con.prepareStatement(sql);
         stmt.setInt(1, id);
         stmt.execute();
         
         stmt.close();
         con.close();
     }
}
