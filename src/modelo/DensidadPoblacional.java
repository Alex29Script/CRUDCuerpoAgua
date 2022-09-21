
package modelo;

/**
 *
 * @author alex_
 */
public class DensidadPoblacional extends ObjetoGeografico {
    private double Densidad;

    public DensidadPoblacional( int id, String Municipio, double Densidad) {
        super(id, Municipio);
        this.Densidad = Densidad;
    }

    public double getDensidad() {
        return Densidad;
    }

    public void setDensidad(double Densidad) {
        this.Densidad = Densidad;
    }


   
    
    public int afección(){
    int resulafeccion;
    
            if (this.Densidad<10000) {
                resulafeccion=0;
            }else if (this.Densidad>= 10000 && this.Densidad<= 50000) {
                resulafeccion=1;
            }else {
                resulafeccion=2;
            }
    
    return resulafeccion;
    }
}
