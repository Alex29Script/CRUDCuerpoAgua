
package modelo;

/**
 *
 * @author alex_
 */
public class CuerpoDeAgua extends ObjetoGeografico{
    private String Nombre;
    private String TCuerpoAgua, TAgua;
    private double IRCA;

    public CuerpoDeAgua(int id, String Municipio,String Nombre, String TCuerpoAgua, String TAgua, double IRCA ) {
        super(id, Municipio);
        this.Nombre = Nombre;
        this.TCuerpoAgua = TCuerpoAgua;
        this.TAgua = TAgua;
        this.IRCA = IRCA;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTCuerpoAgua() {
        return TCuerpoAgua;
    }

    public void setTCuerpoAgua(String TCuerpoAgua) {
        this.TCuerpoAgua = TCuerpoAgua;
    }

    public String getTAgua() {
        return TAgua;
    }

    public void setTAgua(String TAgua) {
        this.TAgua = TAgua;
    }

    public double getIRCA() {
        return IRCA;
    }

    public void setIRCA(double IRCA) {
        this.IRCA = IRCA;
    }
    
    

    
    public String nivel(){
            String strIrca;
            if (80<this.IRCA && this.IRCA<=100) {
                strIrca="INVIABLE SANITARIAMENTE";
            }else if (35<this.IRCA && this.IRCA<=80) {
                strIrca="ALTO";
            }else if (14<this.IRCA && this.IRCA<=35) {
                strIrca="MEDIO";
            }else if (5<this.IRCA && this.IRCA<=14) {
                strIrca="BAJO";
            }else if (0<=this.IRCA && this.IRCA<=5) {
               strIrca="SIN RIESGO";
            }else {
                strIrca="ERROR, VARIABLE FUERA DE PARAMETROS";
            }
        
    return strIrca;
    }
    
    
}
