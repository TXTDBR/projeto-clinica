package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Medico;

public class MedicoDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public MedicoDAO() {
        con = new FabricaDeConexoes().getConnection();
    }

    public void registrarMedico(Medico medico) throws SQLException {
        String sql = "INSERT INTO medico (nome,crm,especialidade_id) VALUES(?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, medico.getNome());
        stmt.setInt(2, medico.getCrm());
        stmt.setInt(3, medico.getEspecialidade_id());

        stmt.execute();
        stmt.close();
        con.close();
    }

    public Medico pesquisarMedico(String nome) throws SQLException {
        String sql = "SELECT * FROM medico WHERE nome LIKE ?";
        stmt = con.prepareStatement(sql);

        stmt.setString(1, nome + "%");
        rs = stmt.executeQuery();

        Medico medico = null;
        if (rs.first()) {
            medico = new Medico();
            medico.setId(rs.getInt("id"));
            medico.setNome(rs.getString("nome"));
            medico.setCrm(rs.getInt("crm"));
            medico.setEspecialidade_id(rs.getInt("especialidade_id"));

        }
        
        stmt.close();
        rs.close();
        con.close();

        return medico;
    }

    public ArrayList buscarMedico(String nome) throws SQLException {
        String sql = "SELECT * FROM medico WHERE nome LIKE ? ORDER BY id DESC";
        stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);

        stmt.setString(1, nome + "%");
        rs = stmt.executeQuery();

        ArrayList<Medico> medico = new ArrayList<Medico>();

        while (rs.next()) {
            Medico m = new Medico();
            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getInt("crm"));
            m.setEspecialidade_id(rs.getInt("especialidade_id"));
            medico.add(m);
        }
        stmt.close();
        rs.close();
        con.close();

        return medico;
    }

    public void editarMedico(Medico m) throws SQLException {
        String sql = "UPDATE medico SET nome=?, crm=?, especialidade_id=? WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, m.getNome());
        stmt.setInt(2, m.getCrm());
        stmt.setInt(3, m.getEspecialidade_id());
        stmt.setInt(4, m.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void removerRegistro(int id) throws SQLException {
        String sql = "DELETE FROM medico WHERE id = ?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        
        stmt.close();
        con.close();
    }
    
    public String getNomeById(int id) throws SQLException{
        String nome = null;
        String slq = "SELECT * FROM especialidade WHERE id=?";
        stmt = con.prepareStatement(slq);
        stmt.setInt(1, id);
        rs = stmt.executeQuery();
        
        if(rs.next()){
            nome = rs.getString("especialidade");
        }
        return nome;
    }
    
    public int getIdByNome(String nome) throws SQLException{
        String sql = "SELECT * FROM medico WHERE nome = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        rs = stmt.executeQuery();
        
        int id = 0;
        
        while(rs.next()){
           id = rs.getInt("id");
        }
        return id;
        
    }
    
    public List listarMedico() throws SQLException {
        String sql = "SELECT * FROM medico";
        stmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);

        
        rs = stmt.executeQuery();

        List<Medico> medico = new ArrayList<Medico>();

        while (rs.next()) {
            Medico m = new Medico();
            m.setId(rs.getInt("id"));
            m.setNome(rs.getString("nome"));
            m.setCrm(rs.getInt("crm"));
            m.setEspecialidade_id(rs.getInt("especialidade_id"));
            medico.add(m);
        }
        stmt.close();
        rs.close();
        con.close();

        return medico;
    }
}
