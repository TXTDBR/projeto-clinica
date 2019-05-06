
package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FabricaDeConexoes {

    public Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/projetoclinica","root", "");
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
    }
}
