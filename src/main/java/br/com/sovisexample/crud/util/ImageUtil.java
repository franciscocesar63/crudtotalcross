/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sovisexample.crud.util;

import totalcross.io.IOException;
import totalcross.ui.image.Image;
import totalcross.ui.image.ImageException;

/**
 *
 * @author francisco.silva
 */
public class ImageUtil {

    public static Image getImage(String nome, int fmH) {
        Image img = null;
        try {
            if (nome != null) {
                img = new Image(nome);
                if (fmH > 0) {
                    img = img.getSmoothScaledInstance(img.getWidth() * fmH / img.getHeight(), fmH);
                }
            }
            return img;
        } catch (ImageException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            img = null;
        }

        return null;
    }
}
