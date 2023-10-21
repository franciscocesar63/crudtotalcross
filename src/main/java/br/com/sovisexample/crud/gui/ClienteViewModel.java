package br.com.sovisexample.crud.gui;

import br.com.sovisexample.crud.bean.Cliente;
import br.com.sovisexample.crud.bo.ClienteBO;
import br.com.sovisexample.crud.main.App;
import totalcross.ui.MainWindow;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

/**
 *
 * @author francisco.silva
 */
public class ClienteViewModel extends ClienteView {

    public ClienteViewModel(App app) {
        super(app);
    }

    public ClienteViewModel(Cliente cliente, App app) {
        super(cliente, app);
    }

    @Override
    public void initUI() {
        super.initUI();

        btnSalvar.addPressListener(salvarEvent());
        btnVoltar.addPressListener(voltarListener());
    }

    private PressListener salvarEvent() {
        return new PressListener() {
            @Override
            public void controlPressed(ControlEvent ce) {
                if (nome == null || nome.getText().trim().isEmpty()) {
                    nome.repaintNow();
                    new MessageBox("", "O Nome do cliente é obrigatório").popup();
                    return;
                }
                if (cpfcnpj == null || cpfcnpj.getText().trim().isEmpty()) {
                    cpfcnpj.repaintNow();
                    new MessageBox("", "O CPF/CNPJ do cliente é obrigatório").popup();
                    return;
                }

                Cliente c = new Cliente();
                c.setId(isEdit ? cliente.getId() : -1);
                c.setNome(nome.getText().trim());
                c.setCpfcnpj(cpfcnpj.getText().trim());

                ClienteBO bo = new ClienteBO();

                boolean sucesso = isEdit ? bo.update(c) : bo.insert(c);

                if (sucesso) {
                    MessageBox mb = new MessageBox("Sucesso!", "Cliente " + (isEdit ? "atualizado" : "inserido") + " com sucesso!");
                    mb.setBackForeColors(Color.GREEN, Color.BLACK);
                    back();
                    app.listModel.carregarItens();
                    mb.popup();
                } else {
                    MessageBox mb = new MessageBox("Erro!", "Houve algum erro ao " + (isEdit ? "atualizar" : "inserir") + " o cliente!");
                    mb.popup();
                }

            }
        };
    }

    private PressListener voltarListener() {
        return new PressListener() {
            @Override
            public void controlPressed(ControlEvent ce) {
                back();
            }
        };
    }

    private void back() {
        this.unpop();
    }

}
