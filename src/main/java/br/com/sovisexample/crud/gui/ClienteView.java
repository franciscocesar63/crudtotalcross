package br.com.sovisexample.crud.gui;

import br.com.sovisexample.crud.bean.Cliente;
import br.com.sovisexample.crud.main.App;
import static br.com.sovisexample.crud.main.App.primary_purple;
import br.com.sovisexample.crud.util.ImageUtil;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import static totalcross.ui.Control.BOTTOM;
import static totalcross.ui.Control.CENTER;
import static totalcross.ui.Control.FILL;
import static totalcross.ui.Control.LEFT;
import static totalcross.ui.Control.TOP;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.Window;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

/**
 *
 * @author francisco.silva
 */
public abstract class ClienteView extends Window {

    protected Button btnSalvar;
    protected Button btnVoltar;
    protected Container bar;
    protected Container footer;
    protected boolean isEdit = false;
    protected Cliente cliente;

    protected Edit nome;
    protected Edit cpfcnpj;
    protected App app;
    public static int secondary_text = 0x727272;

    public ClienteView(App app) {
        this.app = app;
        setRect(CENTER, CENTER, Settings.screenWidth, Settings.screenHeight);
    }

    public ClienteView(Cliente cliente, App app) {
        this.cliente = cliente;
        isEdit = true;
        this.app = app;
        setRect(CENTER, CENTER, Settings.screenWidth, Settings.screenHeight);
    }

    @Override
    public void initUI() {
        adicionaBarRodape();
        Container body = new Container();
        add(body, LEFT, AFTER, FILL, FIT, bar);

        nome = new Edit();
        nome.setText(isEdit ? this.cliente.getNome() : "");
        nome.caption = "Nome";
        nome.captionColor = secondary_text;
        body.add(nome, CENTER, TOP + 10, PARENTSIZE + 90, PREFERRED);

        cpfcnpj = new Edit();
        cpfcnpj.setText(isEdit ? this.cliente.getCpfcnpj() : "");
        cpfcnpj.caption = "CNPJ/CPF";
        cpfcnpj.captionColor = secondary_text;
        body.add(cpfcnpj, CENTER, AFTER + 10, PARENTSIZE + 90, PREFERRED);

        btnSalvar = new Button("Salvar");
        btnSalvar.transparentBackground = true;
        btnSalvar.setBorder(BORDER_NONE);
        btnSalvar.setForeColor(Color.WHITE);
        btnSalvar.setFont(Font.getFont((int) (fmH * 1.5)).asBold());

        footer.add(btnSalvar, RIGHT - fmH, CENTER, PREFERRED, fmH * 2);

        btnVoltar = new Button(ImageUtil.getImage("images/left.png", fmH * 2));
        btnVoltar.transparentBackground = true;
        btnVoltar.setBorder(BORDER_NONE);
        btnVoltar.setForeColor(Color.WHITE);
        btnVoltar.setFont(Font.getFont((int) (fmH * 1.5)).asBold());

        bar.add(btnVoltar, LEFT + fmH, CENTER, PREFERRED, fmH * 2);

    }

    private void adicionaBarRodape() {
        bar = new Container();
        bar.setBackColor(primary_purple);

        footer = new Container();
        footer.setBackColor(primary_purple);
        int barH = (int) (Settings.screenHeight * 0.1);

        add(bar, LEFT, TOP, FILL, barH);
        add(footer, LEFT, BOTTOM, FILL, barH);

        Label title = new Label(isEdit ? "Editar Cliente" : "Novo Cliente");
        title.setFont(Font.getFont(fmH * 2).asBold());
        title.setForeColor(Color.WHITE);

        bar.add(title, CENTER, CENTER);
    }

}
