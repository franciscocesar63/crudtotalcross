package br.com.sovisexample.crud.bo;

import br.com.sovisexample.crud.bean.Cliente;
import br.com.sovisexample.crud.persistence.ClientePersistence;
import java.util.List;

/**
 *
 * @author francisco.silva
 */
public class ClienteBO {

    private ClientePersistence persistence;

    public ClienteBO() {
        persistence = new ClientePersistence();
    }

    public boolean insert(Cliente c) {
        return persistence.insert(c);
    }

    public boolean update(Cliente c) {
        return persistence.update(c);
    }

    public boolean delete(Cliente c) {
        return persistence.delete(c);
    }

    public List<Cliente> findAll() {
        return this.persistence.findAll();
    }
}
