package br.com.sovisexample.crud.main;

import br.com.sovisexample.crud.bean.Cliente;
import br.com.sovisexample.crud.bo.ClienteBO;
import br.com.sovisexample.crud.util.ImageUtil;
import br.com.sovisexample.crud.connection.CreateDataBase;
import br.com.sovisexample.crud.gui.ClienteViewModel;
import br.com.sovisexample.crud.gui.ListClienteViewModel;
import java.util.List;
import totalcross.ui.MainWindow;
import totalcross.ui.Label;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;

public class App extends MainWindow {

    public static int primary_purple = 0x4F2584;

    private Button btnAdd;
    public ListClienteViewModel listModel;
    public App() {
        setUIStyle(Settings.MATERIAL_UI);
    }

    @Override
    public void initUI() {
        Container bar = new Container();
        bar.setBackColor(primary_purple);

        Container footer = new Container();
        footer.setBackColor(primary_purple);
        int barH = (int) (Settings.screenHeight * 0.1);

        add(bar, LEFT, TOP, FILL, barH);
        add(footer, LEFT, BOTTOM, FILL, barH);

        Label title = new Label("CRUD CLIENTE!");
        title.setFont(Font.getFont(fmH * 2).asBold());
        title.setForeColor(Color.WHITE);

        bar.add(title, CENTER, CENTER);

        btnAdd = new Button(ImageUtil.getImage("images/mais.png", fmH * 2));
        btnAdd.transparentBackground = true;

        footer.add(btnAdd, RIGHT, CENTER);

        // realiza os CREATE TABLE
        CreateDataBase data = new CreateDataBase();
        data.createDataBase();


        listModel = new ListClienteViewModel(this);

        add(listModel, LEFT, AFTER, FILL, FIT, bar);

    }

    @Override
    public void onEvent(Event event) {
        switch (event.type) {
            case ControlEvent.PRESSED:
                if (event.target == btnAdd) {
                    ClienteViewModel model = new ClienteViewModel(this);
                    model.popup();
                }
                break;
        }
    }

}
