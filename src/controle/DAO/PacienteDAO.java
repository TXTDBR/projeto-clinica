
package controle.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import controle.FabricaDeConexoes;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Paciente;
public class PacienteDAO {
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public PacienteDAO(){
        this.con = new FabricaDeConexoes().getConnection();
    }
    
    public int RegistrarPaciente(Paciente p)throws SQLException{
        int retorno = 0;
        String sql = "INSERT INTO paciente (nome,nascimento,rg,telefone,cep,cidade,bairro,rua,num,complemento) VALUES(?,?,?,?,?,?,?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getNascimento());
        stmt.setString(3, p.getRg());
        stmt.setString(4, p.getTelefone());
        stmt.setString(5, p.getCep());
        stmt.setString(6, p.getCidade());
        stmt.setString(7, p.getBairro());
        stmt.setString(8, p.getRua());
        stmt.setInt(9, p.getNum());
        stmt.setString(10, p.getComplemento());
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
       return retorno; 
    }
    
    public int editarRegistro(Paciente p)throws SQLException{
        int retorno = 0;
        String sql = "UPDATE paciente SET nome=?, nascimento=?,rg=?, telefone=?, cep=?, cidade=?, bairro=?, rua=?, num=?, complemento=? WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt = con.prepareStatement(sql);
        stmt.setString(1, p.getNome());
        stmt.setString(2, p.getNascimento());
        stmt.setString(3, p.getRg());
        stmt.setString(4, p.getTelefone());
        stmt.setString(5, p.getCep());
        stmt.setString(6, p.getCidade());
        stmt.setString(7, p.getBairro());
        stmt.setString(8, p.getRua());
        stmt.setInt(9, p.getNum());
        stmt.setString(10, p.getComplemento());
        stmt.setInt(11, p.getId());
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }   
    
    public int removerRegistro(int id) throws SQLException{
        int retorno = 0;
        String sql = "DELETE FROM paciente WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        
        return retorno;
    }
    
    public ArrayList listarPaciente(String nome) throws SQLException {
      String sql = "SELECT * FROM paciente WHERE nome LIKE ?";
      stmt = con.prepareStatement(sql);
      stmt.setString(1, nome + "%");
      rs = stmt.executeQuery();
      
      ArrayList<Paciente> paciente = new ArrayList<>();
      
      while(rs.next()){
          Paciente p = new Paciente();
          p.setId(rs.getInt("id"));
          p.setNome(rs.getString("nome"));
          p.setNascimento(rs.getString("nascimento"));
          p.setRg(rs.getString("rg"));
          p.setTelefone(rs.getString("telefone"));
          p.setCep(rs.getString("cep"));
          p.setCidade(rs.getString("cidade"));
          p.setBairro(rs.getString("bairro"));
          p.setRua(rs.getString("rua"));
          p.setNum(rs.getInt("num"));
          p.setComplemento(rs.getString("complemento"));
          paciente.add(p);
      }
        stmt.close();
        rs.close();
        con.close();
        return paciente;
    }
    
    public Paciente buscarPaciente(String rg) throws SQLException{
        String sql = "SELECT * FROM paciente WHERE rg=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, rg);
        rs = stmt.executeQuery();
        
        Paciente p = new Paciente();
        
        if(rs.next()){
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setRg(rs.getString("rg"));
            p.setTelefone(rs.getString("telefone"));
        }
        stmt.close();
        rs.close();
        con.close();
        return p;
    }
}
