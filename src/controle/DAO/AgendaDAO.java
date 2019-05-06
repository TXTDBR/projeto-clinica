package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Agenda;

public class AgendaDAO {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    public AgendaDAO() {
        con = new FabricaDeConexoes().getConnection();
    }

    public int RegistrarAgenda(Agenda a) throws SQLException {
        int i = 0;
        String sql = "INSERT INTO agenda (data, idpaciente,idmedico,motivo) VALUES(?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(a.getDataAgenda().getTime()));
        stmt.setInt(2, a.getIdPaciente());
        stmt.setInt(3, a.getIdMedico());
        stmt.setString(4, a.getMotivo());

        i = stmt.executeUpdate();

        stmt.close();
        con.close();
        return i;

    }
}
