package br.com.sovisexample.crud.persistence;

import br.com.sovisexample.crud.bean.Cliente;
import br.com.sovisexample.crud.connection.SQLiteConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;

/**
 *
 * @author francisco.silva
 */
public class ClientePersistence {

    private Connection conn;

    public ClientePersistence() {
        conn = SQLiteConnectionFactory.getConnectionData();
    }

    public boolean insert(Cliente c) {
        StringBuilder sb = new StringBuilder();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sb.append("INSERT INTO CLIENTE (NOME, CPFCNPJ) VALUES(?,?)");

            ps = conn.prepareStatement(sb.toString());

            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpfcnpj());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            SQLiteConnectionFactory.closeStatement(ps, rs);
            sb.setLength(0);
            sb = null;
        }

        return false;
    }

    public boolean delete(Cliente c) {
        StringBuilder sb = new StringBuilder();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sb.append("DELETE FROM CLIENTE  WHERE ID = ?");

            ps = conn.prepareStatement(sb.toString());
            ps.setInt(1, c.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            SQLiteConnectionFactory.closeStatement(ps, rs);
            sb.setLength(0);
            sb = null;
        }

        return false;

    }

    public boolean update(Cliente c) {
        StringBuilder sb = new StringBuilder();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            sb.append("UPDATE CLIENTE SET NOME = ?, CPFCNPJ = ? WHERE ID = ?");

            ps = conn.prepareStatement(sb.toString());

            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpfcnpj());
            ps.setInt(3, c.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            SQLiteConnectionFactory.closeStatement(ps, rs);
            sb.setLength(0);
            sb = null;
        }

        return false;
    }

    public List<Cliente> findAll() {
        StringBuilder sb = new StringBuilder();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<>();

        try {
            sb.append(" SELECT ID, NOME, CPFCNPJ FROM CLIENTE");

            ps = conn.prepareStatement(sb.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setCpfcnpj(rs.getString(3));

                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            SQLiteConnectionFactory.closeStatement(ps, rs);
            sb.setLength(0);
            sb = null;
        }
        return lista;
    }

}
