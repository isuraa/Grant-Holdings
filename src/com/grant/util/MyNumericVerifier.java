/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.util;

import javax.swing.InputVerifier;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Isura Amarasinghe
 */
public class MyNumericVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text = null;

        if (input instanceof JTextField) {
            text = ((JTextField) input).getText();
        } else if (input instanceof JComboBox) {
            text = ((JComboBox) input).getSelectedItem().toString();
        }

        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public boolean text(JComponent input, String inText) {
        String text = null;
        boolean result = false;

        if (input instanceof JTextField) {
            text = ((JTextField) input).getText();
        } else if (input instanceof JComboBox) {
            text = ((JComboBox) input).getSelectedItem().toString();
        }

        if (text == inText) {
            result = false;
        } else {
            result = text == null || text.trim().isEmpty();
        }

        return result;
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
        boolean valid = verify(input);

        return valid;
    }
}
