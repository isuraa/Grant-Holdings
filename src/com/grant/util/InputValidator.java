
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.util;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Isura Amarasinghe
 */
public class InputValidator {

    // test if field is empty
    public boolean validateField(JTextField f, String errormsg) {
        if (f.getText().equals("")) {
            return failedMessage(f, errormsg);
        } else {
            return true; // validation successful
        }
    }

    public boolean validateInteger(JTextField f, String errormsg) {
        try {  // try to convert input to integer
            int i = Integer.parseInt(f.getText());

            // input must be greater then 0
            // if it is, success
            if (i > 0) {
                return true; // success, validation succeeded
            }
        } catch (Exception e) {
            // if conversion failed, or input was <= 0,
            // fall-through and do final return below
        }
        return failedMessage(f, errormsg);
    }
        public boolean validateInteger(JLabel f, String errormsg) {
        try {  // try to convert input to integer
            int i = Integer.parseInt(f.getText());

            // input must be greater then 0
            // if it is, success
            if (i > 0) {
                return true; // success, validation succeeded
            }
        } catch (Exception e) {
            // if conversion failed, or input was <= 0,
            // fall-through and do final return below
        }
        return failedMessage(f, errormsg);
    }

    public boolean validInvo(JLabel f, String errormsg) {
        if (f.getText().equals("") || f.getText().equals("Invoice No")) {
            return failedMessage(f, errormsg);
        } else {
            return true; // validation successful
        }

    }

    public boolean failedMessage(JTextField f, String errormsg) {
        JOptionPane.showMessageDialog(null, errormsg); // give user feedback
        f.requestFocus(); // set focus on field, so user can change
        return false; // return false, as validation has failed
    }

    public boolean failedMessage(JLabel f, String errormsg) {
        JOptionPane.showMessageDialog(null, errormsg); // give user feedback
        f.requestFocus(); // set focus on field, so user can change
        return false; // return false, as validation has failed
    }

}
