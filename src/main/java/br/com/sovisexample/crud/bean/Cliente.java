package br.com.sovisexample.crud.bean;

/**
 *
 * @author francisco.silva
 */
public class Cliente {

    private int id;
    private String nome;
    private String cpfcnpj;

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", cpfcnpj=" + cpfcnpj + '}';
    }

}
