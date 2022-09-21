/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package App;

import Vista.formApp;
import modelo.CuerpoDeAgua;
import modelo.DensidadPoblacional;
import Controlador.controladorApp;
import java.util.ArrayList;

/**
 *
 * @author alex_
 */
public class lanzadorApp {public static void main(String[] args) {
    
    ArrayList<CuerpoDeAgua> CuerpoLanzador = new  ArrayList();
    ArrayList<DensidadPoblacional> DensidadLanzador= new ArrayList();
    formApp FormularioLanzador = new formApp();
    
    controladorApp ControladorLanzador= new controladorApp(CuerpoLanzador,DensidadLanzador,FormularioLanzador);
    
    ControladorLanzador.iniciar();
    FormularioLanzador.setVisible(true);
    
    
    }
    
}
