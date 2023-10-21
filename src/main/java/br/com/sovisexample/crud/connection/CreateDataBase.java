package br.com.sovisexample.crud.connection;

import java.sql.SQLException;

import totalcross.sql.Connection;
import totalcross.sql.Statement;

/**
 * Classe responsalvel por criar banco de dados no aparelho
 *
 * @author Francisco César
 */
public class CreateDataBase {

    public CreateDataBase() {
    }

    public void createDataBase() {
        Statement stmt = null;
        try {
            Connection connection = SQLiteConnectionFactory.getConnectionData();
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS `CLIENTE` (\n"
                    + "  `ID` INTEGER  PRIMARY KEY AUTOINCREMENT,\n"
                    + "  `NOME` VARCHAR(100) NOT NULL,\n"
                    + "  `CPFCNPJ` VARCHAR(18)NOT NULL\n"
                    + ")");

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            SQLiteConnectionFactory.closeStatement(stmt);
        }
    }
}
