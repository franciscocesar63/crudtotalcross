package br.com.sovisexample.crud.connection;

import java.sql.SQLException;
import java.util.HashMap;
import totalcross.io.File;
import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.sys.Vm;

public class SQLiteConnectionFactory {

    private static HashMap<String, Connection> connData = new HashMap<>();
    public static String VERSION = "";

    public static Connection getConnectionData() {
        if (!connData.containsKey(getNomeBanco())) {
            connData.put(getNomeBanco(), createConnectionData());
        }

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connData.get(getNomeBanco()).createStatement();
            stmt.executeUpdate("pragma busy_timeout = 9000;");

            if (VERSION.equalsIgnoreCase("")) {
                rs = stmt.executeQuery("select sqlite_version();");
                if (rs.next()) {
                    VERSION = rs.getString(1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            SQLiteConnectionFactory.closeStatement(stmt, rs);
        }

        return connData.get(getNomeBanco());
    }

    private static Connection createConnectionData() {
        Connection con = null;

        try {
            Class.forName("totalcross.db.sqlite.JDBC");

            String path = "jdbc:sqlite:" + getNomeBanco();
            Vm.debug("jdbc:sqlite:" + new File(".").getPath() + getNomeBanco());
            Vm.debug("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, getNomeBanco()));

            con = DriverManager.getConnection(path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return con;
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
                st = null;
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

    public static void closeStatement(totalcross.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

    }

    public static void closeStatement(Statement st, totalcross.sql.ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }

        closeStatement(st);

    }

    public static void closeConnectionData() {
        if (connData != null) {
            try {
                if (connData.get(getNomeBanco()) != null) {
                    connData.get(getNomeBanco()).close();
                    connData.remove(getNomeBanco());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String getNomeBanco() {
        return "data.db";

    }
}
