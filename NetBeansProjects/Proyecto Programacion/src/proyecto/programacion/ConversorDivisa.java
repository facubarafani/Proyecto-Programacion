/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.programacion;

import javax.money.*;

/**
 *
 * @author facundo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class ConversorDivisa extends JFrame {

    JPanel panelprincipal = new JPanel();
    JPanel panel = new JPanel();
    JPanel panelconvertir = new JPanel();
    String[] dolpe = {"Peso(ARS)", "USD"};
    JComboBox comboconversor1 = new JComboBox(dolpe);
    JComboBox comboconversor2 = new JComboBox(dolpe);
    JTextField tf1 = new JTextField();
    JTextField tf2 = new JTextField();
    JPanel panelizq = new JPanel();
    JPanel panelmed = new JPanel();
    JPanel panelder = new JPanel();
    ImageIcon iconocam = new ImageIcon("/home/facundo/iconocambiar.png");
    JButton botoncambiar = new JButton(iconocam);
    JButton convertir1 = new JButton("Convertir");
    JButton convertir2 = new JButton("Convertir");

    public ConversorDivisa() {
        super("Conversor de Divisa");
        setSize(400, 120);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        convertir1.addActionListener(new RealizarConversion());
        //SE AGREGAN DATOS A LOS COMBOBOX
        //PANEL PRINCIPAL
        panelprincipal.setLayout(new BoxLayout(panelprincipal, BoxLayout.Y_AXIS));
        //PANEL ORIENTADO EN X
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        //PANEL LADO IZQUIERDO
        panelizq.setLayout(new BoxLayout(panelizq, BoxLayout.Y_AXIS));
        comboconversor1.setSelectedIndex(0);
        panelizq.add(comboconversor1);
        panelizq.add(tf1);


        //PANEL MEDIO
        panelmed.setLayout(new GridBagLayout());
        panelmed.add(panel, new GridBagConstraints());
        panelmed.add(botoncambiar);
        botoncambiar.addActionListener(new BotonCambiar());
        //PANEL DERECHO
        panelder.setLayout(new BoxLayout(panelder, BoxLayout.Y_AXIS));
        comboconversor2.setSelectedIndex(1);
        panelder.add(comboconversor2);
        panelder.add(tf2);

        //PANEL CONVERTIR
        panelconvertir.setLayout(new GridBagLayout());
        panelconvertir.add(panel, new GridBagConstraints());
        panelconvertir.add(convertir1);

        panelprincipal.add(panel);
        panel.add(panelizq);
        panel.add(panelmed);
        panel.add(panelder);
        panelprincipal.add(panelconvertir);
        add(panelprincipal);
    }

    public class BotonCambiar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (comboconversor1.getSelectedIndex() == 0 & comboconversor2.getSelectedIndex() == 1) {
                comboconversor1.setSelectedIndex(1);
                comboconversor2.setSelectedIndex(0);
            } else {
                comboconversor1.setSelectedIndex(0);
                comboconversor2.setSelectedIndex(1);
            }
        }
    }

    public class RealizarConversion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double factor = 17.11;
            String valor = tf1.getText();
            if (comboconversor1.getSelectedIndex() == 0 & comboconversor2.getSelectedIndex() == 1){
            double valorSalida = Double.parseDouble(valor) / factor;
            tf2.setText(String.valueOf(valorSalida));
            }else{
            double valorSalida = Double.parseDouble(valor) * factor;
            tf2.setText(String.valueOf(valorSalida));
            }
            if (comboconversor1.getSelectedIndex() == 0 & comboconversor2.getSelectedIndex() == 0 || comboconversor1.getSelectedIndex() == 1 & comboconversor2.getSelectedIndex() == 1){
                JOptionPane.showMessageDialog(null, "Error:\n"
                    + "Por favor seleccione dos divisas distintas", "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            Divisa divisa = new Divisa();
            if (comboconversor1.getSelectedIndex() == 0){
            divisa.setDolar(Double.parseDouble(tf2.getText()));
            divisa.setPeso(Double.parseDouble(tf1.getText()));
            }else{
                divisa.setDolar(Double.parseDouble(tf1.getText()));
                divisa.setPeso(Double.parseDouble(tf2.getText()));
            }
            System.out.println(divisa.toString());
        }

    }

}
