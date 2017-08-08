/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.programacion;

/**
 *
 * @author facundo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SistemaLogin extends JFrame{
    JPanel panel = new JPanel();
    JPanel loginp = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel nusuario = new JLabel("Usuario");
    JTextField usuarios = new JTextField();
    JLabel contra = new JLabel("Contraseña");
    JPasswordField psw = new JPasswordField(10);
    JButton log = new JButton("Ingresar");
    ImageIcon iconologin = new ImageIcon("/home/facundo/iconologin.png");
    JLabel etiquetalogin = new JLabel(iconologin);
    public SistemaLogin(){
        super("Login");
        setSize(250,240);
        setResizable(false);
        
        log.addActionListener(new BotonApretado());
        //PANEL
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        //PANEL ICONOLOGIN
        loginp.setLayout(new FlowLayout(FlowLayout.CENTER));
        loginp.add(etiquetalogin);
        //PANEL 1
        panel1.setLayout(new GridLayout(4,1));
        panel1.add(nusuario);
        panel1.add(usuarios);
        panel1.add(contra);
        panel1.add(psw);
        //PANEL 2
        panel2.add(log);
        
        panel.add(loginp);
        panel.add(panel1);
        panel.add(panel2);

        add(panel);
    }
    public class BotonApretado implements ActionListener{
        @Override
    public void actionPerformed(ActionEvent e){
        char clave[]=psw.getPassword();
        String clavedef=new String(clave);
         if (usuarios.getText().equals("Facundo") && clavedef.equals("12345")){
                    JOptionPane.showMessageDialog(null, "Bienvenido\n"
                    + "Has ingresado satisfactoriamente al sistema",   "Mensaje de bienvenida",
                    JOptionPane.INFORMATION_MESSAGE);
                    Ventana ventana1 = new Ventana();
                    ventana1.setVisible(true);
                    dispose();
            }else {
                    JOptionPane.showMessageDialog(null, "Acceso denegado:\n"
                    + "Por favor ingrese un usuario válido", "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
