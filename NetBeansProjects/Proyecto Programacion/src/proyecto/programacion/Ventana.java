package proyecto.programacion;

/*
 * @author facundo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;

public class Ventana extends JFrame {

    //CREACION DE PANELES
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panelb = new JPanel();
    //CREACION JCOMPONENTS
    JLabel producto = new JLabel("Nombre del Producto:");
    JTextField tp = new JTextField(10);
    JLabel st = new JLabel("Categoria:");
    JTextField tfstock = new JTextField(10);
    JLabel stock = new JLabel("Cantidad de Stock:");
    JTextField s = new JTextField(10);
    //BOTONES PARA AGREGAR,EDITAR Y BORRAR ELEMENTOS DE LA TABLA
    JButton nuevo = new JButton("Agregar");
    JButton modificar = new JButton("Editar");
    JButton eliminar = new JButton("Borrar");
    //COMPONENTES DE LA TABLA
    String[] columnNames = {"Producto", "Categoria", "Stock"};
    Object[][] data = {};
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
    private JTable tabla = new JTable(model);
    boolean bandera = true;
    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(tabla.getModel());
    JLabel buscar = new JLabel("Ingrese la busqueda:");
    JTextField bus = new JTextField(10);
    ImageIcon iconobusqueda = new ImageIcon("/home/facundo/iconobusqueda.png");
    JLabel etiquetaicono = new JLabel(iconobusqueda);
    //COMPONENTES MENU
    JMenuBar menubar = new JMenuBar();
    JMenu archivo = new JMenu("Archivo");
    JMenu ayuda = new JMenu("Ayuda");
    ImageIcon iconoabrir = new ImageIcon("/home/facundo/abrir.png");
    JMenuItem abrir = new JMenuItem("Abrir documento", iconoabrir);
    ImageIcon iconoguardar = new ImageIcon("/home/facundo/guardar.png");
    JMenuItem guardar = new JMenuItem("Guardar como...",iconoguardar);
    ImageIcon iconoayuda = new ImageIcon("/home/facundo/iconoayuda.png");
    JMenuItem acerca = new JMenuItem("Acerca de...",iconoayuda);
    

    public Ventana() {
        super("Sistema de Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);
        //EVENTOS MENUBAR
        archivo.setMnemonic(KeyEvent.VK_F);
        abrir.setMnemonic(KeyEvent.VK_E);
        abrir.setToolTipText("Cargar archivo .csv en la tabla");
        guardar.setMnemonic(KeyEvent.VK_E);
        guardar.setToolTipText("Guardar tabla en un archivo .csv");
     //SE AÑADE ACTIONLISTENER AL BOTON GUARDAR   
        guardar.addActionListener(new BotonGuardar());
     //SE AÑADE ACTIONLISTENER AL BOTON ABRIR
        abrir.addActionListener(new BotonAbrir());
     //SE AÑADE ACTIONLISTENER AL BOTON ACERCA
        acerca.addActionListener(new Ayuda());
        //SISTEMA PARA AÑADIR ELEMENTOS A LA TABLA
        Object[] row = new Object[3];
        nuevo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = tp.getText();
                row[1] = tfstock.getText();
                row[2] = s.getText();

                model.addRow(row);
            }
        });
        //SISTEMA PARA ELIMINAR ELEMENTOS A LA TABLA
           eliminar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = numero seleccionado de la tabla
                int i = tabla.getSelectedRow();
                if(i >= 0){
                    // Eliminar de la lista de la tabla
                    model.removeRow(i);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Error al Eliminar"); 
                }
            }
        });
        // LLEVAR LOS DATOS SELECCIONADOS A LOS TEXTFIELDS
        tabla.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            
            // i = elemento seleccionado en la tabla
            int i = tabla.getSelectedRow();
            
            tp.setText(model.getValueAt(i, 0).toString());
            tfstock.setText(model.getValueAt(i, 1).toString());
            s.setText(model.getValueAt(i, 2).toString());
        }
        });
        
        // SISTEMA PARA EDITAR ELEMENTOS DE LA TABLA
        modificar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                // i = numero de la fila seleccionada
                int i = tabla.getSelectedRow();
                
                if(i >= 0) 
                {
                   model.setValueAt(tp.getText(), i, 0);
                   model.setValueAt(tfstock.getText(), i, 1);
                   model.setValueAt(s.getText(), i, 2);
                }
                else{
                   JOptionPane.showMessageDialog(null,"Error al Modificar"); 
                }
            }
        });

        //SISTEMA PARA FILTRAR INFO EN LA JTABLE
        tabla.setRowSorter(rowSorter);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        bus.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = bus.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = bus.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        
        //PANEL PRINCIPAL   
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //PANEL1
        panel1.setLayout(new GridLayout(3, 2));
        panel1.add(producto);
        panel1.add(tp);
        panel1.add(st);
        panel1.add(tfstock);
        panel1.add(stock);
        panel1.add(s);
        //PANEL2
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel2.add(nuevo);
        panel2.add(modificar);
        panel2.add(eliminar);
        //PANEL BUSCAR
        panelb.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelb.add(buscar);
        panelb.add(bus);
        panelb.add(etiquetaicono);

        //PANEL3
        panel3.setLayout(new BorderLayout());
        panel3.add(tabla.getTableHeader(), BorderLayout.PAGE_START);
        panel3.add(tabla);
        //SCROLLBAR A LA TABLA
        panel3.add(new JScrollPane(tabla));
        //MENUBAR AL PROGRAMA
        menubar.add(archivo);
        menubar.add(ayuda);
        archivo.add(abrir);
        archivo.add(guardar);
        ayuda.add(acerca);
        setJMenuBar(menubar);
        //AÑADIR PANELES
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panelb);
        panel.add(panel3);
        add(panel);

    }
    //ACTION LISTENER AL BOTON ACERCA
    public class Ayuda implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(null,"Proyecto de Porgramacion II\n              "
                    + "ITS Villada\nAlumnos:\nBarafani,Facundo\nSavino,Juan Cruz","Acerca de...",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //SISTEMA PARA GUARDAR 
        public String [] [] ConseguirInfo() {
    int numfilas = model.getRowCount();
    int numcolumnas = model.getColumnCount();
    boolean isCapturedTheTitles = false;
    String matriz [][] = new String[numfilas + 1][numcolumnas];
    for (int rowIndex = 0;rowIndex<numfilas;rowIndex++){
        for (int colIndex = 0;colIndex<numcolumnas;colIndex++){
            if (!isCapturedTheTitles){
                matriz[0][colIndex] = model.getColumnName(colIndex);
                isCapturedTheTitles=(rowIndex>0)?true:false;
            }
            matriz[rowIndex+1][colIndex] = (String) model.getValueAt((rowIndex),colIndex);
        }
    }
    return matriz;
}
        //ACTION LISTENER DEL BOTON GUARDAR
    public class BotonGuardar implements ActionListener{    
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt){
            JFileChooser selectorarchivo = new JFileChooser();
            selectorarchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int resultado = selectorarchivo.showSaveDialog(panel);
            if (resultado == JFileChooser.CANCEL_OPTION){
                return;
            }
            File archivo = selectorarchivo.getSelectedFile();
            try {
                PrintWriter salida = new PrintWriter (new FileWriter(archivo+".csv"));
                String data [][] = ConseguirInfo();
                for(int i = 0; i < data.length;i++){
                    salida.print(data[i][0]);
                    for (int j = 1;j < data[i].length; j++){
                        String word  = data[i][j];
                        if (word != null) {
                            salida.print(","+word);
                        }else{
                            salida.print(",");
                        }
                    }
                    salida.println();
                }
                salida.close();
            }catch (IOException io ){               
            }
        }
    }
    //SISTEMA PARA ABRIR ARCHIVO .CSV
    private File obtenerArchivo(){
        JFileChooser selectorarchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Excel .csv","csv");
        selectorarchivo.setFileFilter(filtro);
        selectorarchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultado = selectorarchivo.showOpenDialog(this);
        if(resultado == JFileChooser.CANCEL_OPTION){
            return null;
        }else {
            File archivoSeleccionado=selectorarchivo.getSelectedFile();
            return archivoSeleccionado;
        }
    }
    //PROCESO DEL ARCHIVO .CSV A LA JTABLE
    private void procesarCsv(File archivoSeleccionado) throws IOException{
        BufferedReader input = new BufferedReader(new FileReader(archivoSeleccionado));
        String linea;
        String campo;
        ArrayList<String> columnas = new ArrayList<String>();
        while ((linea = input.readLine())!= null){
            ArrayList<String> contenidofilas = new ArrayList<String>();
            int tamaño = linea.length();
            int intAux = 0;
            char arregloCadena [] = new char[tamaño];
            arregloCadena = linea.toCharArray();
            for (int i = 0;i < tamaño; i++){
                if (arregloCadena[i] == ','){
                    campo = linea.substring(intAux,i);
                    intAux = i+1;
                    if (bandera){
                        columnas.add(campo);
                    }else {
                        contenidofilas.add(campo);
                    }
                    
                }else if (i == tamaño - 1){
                    campo = linea.substring(intAux,i+1);
                    if (bandera){
                        columnas.add(campo);
                        for(int x=0;x<columnas.size();x++){
                            //model.addColumn(columnas.get(x));
                        }
                    }else{
                        contenidofilas.add(campo);
                        model.addRow(contenidofilas.toArray());
                    }
                    bandera = false;
                }
            }
            
            
        }
        input.close();
    }
    //CREAMOS UN ACTIONLISTENER PARA EL BOTON ABRIR
    public class BotonAbrir implements ActionListener{
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt){
            File archivoSeleccionado = obtenerArchivo();
            if (archivoSeleccionado != null){
                try{
                    procesarCsv(archivoSeleccionado);
                }catch(IOException io){
                    System.out.println(io);
                }
            }
        }
    }
}