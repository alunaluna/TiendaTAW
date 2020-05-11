/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tienda.entity.PalabraClave;

/**
 *
 * @author eugenio
 */
@Stateless
public class PalabraClaveFacade extends AbstractFacade<PalabraClave> {

    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PalabraClaveFacade() {
        super(PalabraClave.class);
    }
    
    public PalabraClave findByPalabra (String palabra) {
        Query q;
        PalabraClave palabraC;
        
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Usuario":
        // @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
        q = this.getEntityManager().createNamedQuery("PalabraClave.findByPalabra");
        q.setParameter("palabra", palabra); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        try{
            palabraC = (PalabraClave) q.getSingleResult();
        } catch(Exception e){
            palabraC = null;
        }
        return palabraC;
    }
    
}
