
package com.mycompany.automovil.persistencia;

import com.mycompany.automovil.igu.ConsultaAutomovil;
import com.mycompany.automovil.logica.AutoMovil;
import com.mycompany.automovil.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {
    
    AutoMovilJpaController autoJpa = new AutoMovilJpaController();
   

    public void agregarAutomovil(AutoMovil auto) {
         autoJpa.create(auto);
    }

    public List<AutoMovil> traerAutos() {
       return autoJpa.findAutoMovilEntities();
    }

    public void borrarAuto(int idAuto) {
        try {
            autoJpa.destroy(idAuto);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public AutoMovil traerAutos(int idAuto) {
        return autoJpa.findAutoMovil(idAuto);
    }

    public void modificarAuto(AutoMovil auto) {
        try {
            autoJpa.edit(auto);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    
}
