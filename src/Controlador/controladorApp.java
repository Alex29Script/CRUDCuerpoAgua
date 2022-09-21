/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.CuerpoDeAgua;
import modelo.DensidadPoblacional;
import Vista.formApp;
import java.util.Objects;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author alex_
 */
public class controladorApp implements ActionListener {
    private ArrayList<CuerpoDeAgua> Cuerpos ;
    private ArrayList<DensidadPoblacional> Dens;
    private formApp Form ;
        
     DefaultTableModel tablaCuerpos= new DefaultTableModel();
     DefaultTableModel tablaDensidad= new DefaultTableModel();
     DefaultTableModel tablaResultado1= new DefaultTableModel();
     DefaultTableModel tablaResultado2= new DefaultTableModel();
     DefaultComboBoxModel comboTipo1= new DefaultComboBoxModel();
     DefaultComboBoxModel comboTipo2= new DefaultComboBoxModel();

    public controladorApp(ArrayList<CuerpoDeAgua> Cuerpos, ArrayList<DensidadPoblacional> Dens, formApp Form) {
        this.Cuerpos = Cuerpos;
        this.Dens = Dens;
        this.Form = Form;
        this.Form.b_addCDA.addActionListener(this);
        this.Form.bn_addDP.addActionListener(this);
        this.Form.b_procesar.addActionListener(this);
        this.Form.comboTipo.addActionListener(this);
        
    }
    
      public void iniciar(){
        this.Form.setLocationRelativeTo(null);//CEntra el form en la pantalla
        this.Form.setTitle("Sistema de Manejo de Objetos Geograficos");//Establece el título al formulario
        modeloTablaCuerpo();
        modeloTablaDensidad();
        modeloTablaResultado( tablaResultado1);
        modeloTablaResultado2( tablaResultado2);
        ingDatos();
        modeloCombo1();
        modeloCombo2(this.Cuerpos);
        this.Form.comboID.setModel(comboTipo2);
        this.Form.comboTipo.setModel(comboTipo1);
        this.Form.tablaCuerpos.setModel(tablaCuerpos);
        this.Form.tablaDensidad.setModel( tablaDensidad);
       this.Form.tablaResultado.setModel(tablaResultado1);
       this.Form.tablaResultado2.setModel(tablaResultado2);
       
        
    }
      
      public void actionPerformed(ActionEvent e){
      
          if (e.getSource()== this.Form.b_addCDA) {
              System.out.println("Pulso Boton ingresar un Cuerpo de Agua");
              agregarCuerpo();
          
          }if(e.getSource()== this.Form.bn_addDP){
              System.out.println("Pulso Boton ingresar una Densidad Poblacional");
              agregarODensidad();
          }if (e.getSource()== this.Form.b_procesar) {
              System.out.println("Pulso Boton ingresar Procesar");
              this.tablaResultado1.setRowCount(0);
              this.tablaResultado2.setRowCount(0);
              nivelForm(this.Cuerpos, this.tablaResultado1);
              nBajos(this.Cuerpos,this.tablaResultado1);
              nMedios(this.Cuerpos,this.tablaResultado1);
              menor(this.Cuerpos,this.tablaResultado1);
              AfectacionForm(this.Dens, this.tablaResultado2);
          }
        
      
          
         
      
      }
      
      

    public void agregarCuerpo(){
        
        int id=Integer.parseInt(this.Form.d_id.getText());
        String Muni=this.Form.d_municipio.getText();
        String Name=this.Form.d_nombre.getText();
        String TCA=this.Form.d_tipoagua.getText();
        String TA=this.Form.d_tagua.getText();
        double irca=Double.parseDouble( this.Form.d_IRCA.getText());
    
        Cuerpos.add(new CuerpoDeAgua(id,Muni,Name,TCA,TA,irca));
        //Borrado de Datos
        for (int i = 0; i < Cuerpos.size(); i++) {
            System.out.println(Cuerpos.get(i).getId()+"|"+Cuerpos.get(i).getMunicipio());
        }
        actualizarTablaCuerpos( this.Cuerpos);
         borrarpanel1();
    
    }

   public void agregarODensidad(){
   
       int id=Integer.parseInt(this.Form.dp_id.getText());
       String Muni=this.Form.dp_municipio.getText();
       double densidad=Double.parseDouble( this.Form.dp_densidad.getText());
       Dens.add(new DensidadPoblacional(id,Muni,densidad));
       
       for (int i = 0; i < Dens.size(); i++) {
           System.out.println(Dens.get(i).getId()+"|"+Dens.get(i).getMunicipio());
          
       }
        actualizarTablaDensidad(this.Dens);
        borrarpanel2();
   }
    
    //Para las tablas y Mostrar los datos
   public void modeloTablaCuerpo(){
        this.tablaCuerpos.addColumn("Nombre");
        this.tablaCuerpos.addColumn("ID");
        this.tablaCuerpos.addColumn("Municipio");
        this.tablaCuerpos.addColumn("TipoCuerpoAgua");
        this.tablaCuerpos.addColumn("TipoAgua");
        this.tablaCuerpos.addColumn("IRCA");
        
    }
   public void modeloTablaDensidad(){
        this.tablaDensidad.addColumn("ID");
        this.tablaDensidad.addColumn("Municipio");
        this.tablaDensidad.addColumn("Densidad");
   
   }
   public void modeloTablaResultado(DefaultTableModel tablaResultado){
       tablaResultado.addColumn("Resultados");
   }
    public void modeloTablaResultado2(DefaultTableModel tablaResultado){
       tablaResultado.addColumn("Identificación");
       tablaResultado.addColumn("Afección");
   }
   
   // Modelos para combo box
   
   public void modeloCombo1(){
       Object Tipos[]= new Object[2];
       Tipos[0]="CuerpoDeAgua";
       Tipos[1]="DensidadPoblacional";
       String[] g= new String[2];
       g[0]="CuerpoDeAgua";
       g[1]="Densidad";
       this.comboTipo1.addElement(g[0]);
       this.comboTipo1.addElement(g[1]);
  
   
   }
   
   public void modeloCombo2(ArrayList<CuerpoDeAgua> Cuerpos){
       
       
       for (int i = 0; i < this.Cuerpos.size(); i++) {
           this.comboTipo2.addElement(Cuerpos.get(i).getId());
       }
            
       
       
  
   
   }
     
   
   
   public void actualizarTablaCuerpos(ArrayList<CuerpoDeAgua> Cuerpos){
       this.tablaCuerpos.setRowCount(0);
       for (int i = 0; i < this.Cuerpos.size(); i++) {
           this.tablaCuerpos.addRow( new Object[]{this.Cuerpos.get(i).getNombre(),this.Cuerpos.get(i).getId(),this.Cuerpos.get(i).getMunicipio(),this.Cuerpos.get(i).getTCuerpoAgua()
           ,this.Cuerpos.get(i).getTAgua(),this.Cuerpos.get(i).getIRCA()});
           
       }
       
   }
   
    public void actualizarTablaDensidad(ArrayList<DensidadPoblacional> Dens){
       this.tablaDensidad.setRowCount(0);
       for (int i = 0; i < this.Dens.size(); i++) {
           this.tablaDensidad.addRow( new Object[]{this.Dens.get(i).getId(),this.Dens.get(i).getMunicipio(),this.Dens.get(i).getDensidad()});
           
       }
       
   }
    /// Funciones para OBTENER RESULTADOS ///
    
    // 1. Obtener resultados de Objetos Geograficos
     public static void nivelForm(ArrayList<CuerpoDeAgua> Data, DefaultTableModel modelotabla2){
    
        String texto="";
        for (int j = 0; j < Data.size(); j++) {
        texto= Data.get(j).nivel();
        modelotabla2.addRow(new Object[]{texto});
        }
     }
     
    public static void nBajos(ArrayList<CuerpoDeAgua> Data,DefaultTableModel modelotabla ){
    int nBajos=0;
    
                        
            for (int i = 0; i < Data.size(); i++) {
                if (Data.get(i).getIRCA()>=0 && Data.get(i).getIRCA()<= 35) {
                nBajos++;
                }  
            }
            modelotabla.addRow(new Object[]{nBajos});
    //return nBajos;   
    }
     public static void nMedios(ArrayList<CuerpoDeAgua> Data,DefaultTableModel modelotabla ){
    int nMedios=0;
    String textMedios="", inter, inter2;
        for (int i = 0; i < Data.size(); i++) {
                if (Data.get(i).getIRCA()>14 && Data.get(i).getIRCA()<= 35) {
                    nMedios++;
                    inter2=Data.get(i).getNombre();
                    //inter=(Data.get(i).getNombre()+"\n");
                    modelotabla.addRow(new Object[]{inter2});
                    //textMedios=textMedios+inter;
                }
                
            }
            if (nMedios==0) {
                //textMedios="NA"+"\n";
                inter2="NA";
                modelotabla.addRow(new Object[]{inter2});
            }
        //return textMedios;
    }
     public static String menor(ArrayList<CuerpoDeAgua> Data,DefaultTableModel modelotabla ){
    double menor=Data.get(0).getIRCA();
    String textoMenor;
        int imenor=0;
            for (int i = 0; i < Data.size(); i++) {
            if (Data.get(i).getIRCA()<menor){
                menor=Data.get(i).getIRCA();
                imenor=i;
            }    
            }
            //System.out.println("");
            
        textoMenor=Data.get(imenor).getNombre()+" "+Data.get(imenor).getId();
        modelotabla.addRow(new Object[]{textoMenor});
    return textoMenor;    
    }
     // 2. Obtener Resultados para Densidad Poblacional
     
     public static void AfectacionForm(ArrayList<DensidadPoblacional> Data, DefaultTableModel modelotabla2){
    
        String texto;
        int afec;
        for (int j = 0; j < Data.size(); j++) {
        texto=Data.get(j).getId()+"-"+Data.get(j).getMunicipio();
        afec=Data.get(j).afección();
        modelotabla2.addRow(new Object[]{texto,afec});
        }
     }
     
    
     
     
     public void ingDatos(){
         
         //Cuerpos.add(new CuerpoDeAgua(id,Muni,Name,TCA,TA,irca));
     this.Cuerpos.add(new CuerpoDeAgua(1,"Valledupar", "Cacique", "Rio", "Dulce", 20.5));
     this.Cuerpos.add(new CuerpoDeAgua(2,"SantaMarta", "LaSierra", "Rio", "Dulce", 10));
     this.Cuerpos.add(new CuerpoDeAgua(3,"SantaMarta", "Rodadero", "Mar", "Salado", 50));
     actualizarTablaCuerpos( this.Cuerpos);
     //Dens.add(new DensidadPoblacional(id,Muni,densidad));
     this.Dens.add(new DensidadPoblacional (1,"Valledupar", 9000));
     this.Dens.add(new DensidadPoblacional (2,"SantaMarta", 40000));
     this.Dens.add(new DensidadPoblacional (3,"SantaMarta", 100000));
     actualizarTablaDensidad(Dens);
     }
     
     public void borrarpanel1(){//limpia las casillas
         this.Form.d_id.setText("");
         this.Form.d_nombre.setText("");
         this.Form.d_municipio.setText("");
         this.Form.d_tipoagua.setText("");
         this.Form.d_tagua.setText("");
         this.Form.d_IRCA.setText("");
         
     }
     public void borrarpanel3(){
     this.Form.dp_id.setText("");
     this.Form.dp_densidad.setText("");
     this.Form.dp_municipio.setText("");
     }
     public void borrarpanel2(){
         this.Form.d_id.setText("");
         this.Form.e_nombre.setText("");
         this.Form.e_municipio.setText("");
         this.Form.e_ta.setText("");
         this.Form.e_tca.setText("");
         this.Form.e_irca.setText("");
         
     }
     
     
   
    
}
