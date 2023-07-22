
package com.mycompany.automovil.logica;

import com.mycompany.automovil.persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    AutoMovil automovil = null;
    
    public void agregarAutomovil(String modelo, String marca, String motor, String color, String patente, int cantdPuertas) {
       automovil = new AutoMovil();
        
        automovil.setModelo(modelo);
        automovil.setMarca(marca);
        automovil.setMotor(motor);
        automovil.setColor(color);
        automovil.setPatente(patente);
        automovil.setCantdPuertas(cantdPuertas);
        
        controlPersis.agregarAutomovil(automovil);
    }


    public List<AutoMovil> traerAutos() {
        return controlPersis.traerAutos();
    }

    public void borrarAuto(int idAuto)  {
        controlPersis.borrarAuto(idAuto);
    }

    public AutoMovil traerAuto(int idAuto) {
       return controlPersis.traerAutos(idAuto);
    }

    public void modificarAuto(AutoMovil auto, String modelo, String marca, String motor, String color, String patente, int cantdPuertas) {
        auto.setModelo(modelo);
        auto.setMarca(marca);
        auto.setMotor(motor);
        auto.setColor(color);
        auto.setPatente(patente);
        auto.setCantdPuertas(cantdPuertas);
        
        controlPersis.modificarAuto(auto);
    }

    
}
