package br.com.sovisexample.crud.gui;

import br.com.sovisexample.crud.bean.Cliente;
import br.com.sovisexample.crud.bo.ClienteBO;
import br.com.sovisexample.crud.main.App;
import br.com.sovisexample.crud.util.ImageUtil;
import java.util.List;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Label;
import totalcross.ui.ScrollContainer;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

/**
 *
 * @author francisco.silva
 */
public class ListClienteViewModel extends ScrollContainer {

    protected ClienteBO bo = new ClienteBO();
    private App app;

    public ListClienteViewModel(App app) {
        this.app = app;
    }

    @Override
    public void initUI() {

        carregarItens();
    }

    public void carregarItens() {
        List<Cliente> clientes = new ClienteBO().findAll();
        removeAll();
        repaint();
        boolean first = true;
        for (int i = 0; i < clientes.size(); i++) {
            first = i == 0;
            Container c = new Container();
            c.setBorderStyle(Container.BORDER_SIMPLE);
            c.borderColor = Color.BLACK;

            Cliente get = clientes.get(i);

            add(c, LEFT, first ? TOP : AFTER, FILL, fmH * 4);
            Label label = new Label("ID: " + get.getId());
            c.add(label, LEFT, TOP);
            label = new Label("Nome: " + get.getNome());
            c.add(label, LEFT, AFTER + 1);
            label = new Label("CPF/CNPJ: " + get.getCpfcnpj());
            c.add(label, LEFT, AFTER + 1);
            Image image = ImageUtil.getImage("images/trash.png", fmH * 2);
            image.applyColor2(Color.BLACK);

            Button btnExcluir = new Button(image);
            btnExcluir.transparentBackground = true;
            btnExcluir.setBorder(Button.BORDER_NONE);
            c.add(btnExcluir, RIGHT, CENTER);
            btnExcluir.addPressListener(btnExcluirListener(get));

            image = ImageUtil.getImage("images/edit.png", fmH * 2);
            image.applyColor2(Color.BLACK);
            Button btnEditar = new Button(image);
            btnEditar.transparentBackground = true;
            btnEditar.setBorder(Button.BORDER_NONE);
            c.add(btnEditar, BEFORE, SAME, btnExcluir);
            btnEditar.addPressListener(btnEditarListener(get));

        }
    }

    private PressListener btnExcluirListener(Cliente get) {
        return new PressListener() {
            @Override
            public void controlPressed(ControlEvent ce) {
                ClienteBO clienteBO = new ClienteBO();

                boolean sucesso = clienteBO.delete(get);

                if (sucesso) {
                    ListClienteViewModel.this.carregarItens();

                    MessageBox mb = new MessageBox("Sucesso!", "Cliente excluido com sucesso!");
                    mb.setBackForeColors(Color.GREEN, Color.BLACK);
                    mb.popup();
                } else {
                    MessageBox mb = new MessageBox("Erro!", "Houve algum erro ao excluir o cliente!");
                    mb.popup();
                }
            }
        };
    }

    private PressListener btnEditarListener(Cliente get) {
        return new PressListener() {
            @Override
            public void controlPressed(ControlEvent ce) {
                ClienteViewModel model = new ClienteViewModel(get, app);
                model.popup();
            }
        };
    }

}
