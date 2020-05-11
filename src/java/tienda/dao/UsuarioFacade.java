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
import tienda.entity.Usuario;

/**
 *
 * @author eugenio
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
     public Usuario findByNombre (String user) {
        Query q;
        Usuario usuario;
        
        // Las "Named Query" son consultas predefinidas que se ubican antes de la declaración
        // de la clase entidad, en este caso, "Usuario":
        // @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
        q = this.getEntityManager().createNamedQuery("Usuario.findByNombre");
        q.setParameter("nombre", user); // Los parámetros son aquellas cadenas de caracteres que van precedidas de los dos puntos.
        try{
            usuario = (Usuario) q.getSingleResult();
        } catch(Exception e){
            usuario = null;
        }
        return usuario;
    }
       
}
