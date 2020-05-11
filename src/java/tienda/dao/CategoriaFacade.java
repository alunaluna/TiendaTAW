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
import tienda.entity.Categoria;

/**
 *
 * @author eugenio
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> {

    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public Categoria findByNombre (String categoria) {
        Query q;
        Categoria categoriaC;

        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Usuario":
        // @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
        q = this.getEntityManager().createNamedQuery("Categoria.findByNombre");
        q.setParameter("nombre", categoria); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        try{
            categoriaC = (Categoria) q.getSingleResult();
        } catch(Exception e){
            categoriaC = null;
        }
        return categoriaC;
    }
    
}
