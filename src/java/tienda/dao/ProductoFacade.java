/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tienda.entity.Producto;

/**
 *
 * @author eugenio
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "TiendaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> findByNameAndDescriptionAndPriceAndDate(String busqueda, int minimo, int maximo, Date fecha) {
        List<Producto> productos;

        Query q;

        q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN p.palabraClaveList pcl WHERE "
                + "(LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) "
                + "or LOWER(pcl.palabra) like LOWER(:busqueda)) and p.precio >= :minimo and p.precio <= :maximo and p.fechaCreacion = :fechaBusqueda");


        q.setParameter("busqueda", "%" + busqueda + "%");
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        q.setParameter("fechaBusqueda",fecha);

        productos =  q.getResultList();

        return productos;
    }
    
    public List<Producto> findByNameAndDescriptionAndPriceAndDateAndHour(String busqueda, int minimo, int maximo, Date fecha,Date hora) {
        List<Producto> productos;

        Query q;

        q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN p.palabraClaveList pcl WHERE "
                + "(LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) "
                + "or LOWER(pcl.palabra) like LOWER(:busqueda)) and p.precio >= :minimo and p.precio <= :maximo and p.fechaCreacion = :fechaBusqueda "
                + "and p.horaCreacion = :horaBusqueda"  );


        q.setParameter("busqueda", "%" + busqueda + "%");
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        q.setParameter("fechaBusqueda",fecha);
        q.setParameter("horaBusqueda",hora);

        productos =  q.getResultList();

        return productos;
    }
    
    
    
    
    

    public List<Producto> findByCategoryAndNameAndDescriptionAndPriceAndDate(String busqueda, int categoriaId, int minimo, int maximo, Date fecha) {
    List<Producto> productos;

        Query q;

         q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN  p.categoriaId cl JOIN p.palabraClaveList pcl"
                + " WHERE (LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) or LOWER(pcl.palabra) like LOWER(:busqueda)) "
                + "and (cl.id = :categoriaId or cl.categoriaPadre.id = :categoriaId) and p.precio >= :minimo and p.precio <= :maximo and p.fechaCreacion = :fechaBusqueda");


        q.setParameter("busqueda", "%" + busqueda + "%");

        q.setParameter("categoriaId", categoriaId);
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        q.setParameter("fechaBusqueda",fecha);

        productos =  q.getResultList();

        return productos;
    }
    
    
    public List<Producto> findByCategoryAndNameAndDescriptionAndPriceAndDateAndHour(String busqueda, int categoriaId, int minimo, int maximo, Date fecha,Date hora) {
    List<Producto> productos;

        Query q;

         q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN  p.categoriaId cl JOIN p.palabraClaveList pcl"
                + " WHERE (LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) or LOWER(pcl.palabra) like LOWER(:busqueda)) "
                + "and (cl.id = :categoriaId or cl.categoriaPadre.id = :categoriaId) and p.precio >= :minimo and p.precio <= :maximo and p.fechaCreacion = :fechaBusqueda "
                + "and p.horaCreacion = :horaBusqueda"  );


        q.setParameter("busqueda", "%" + busqueda + "%");

        q.setParameter("categoriaId", categoriaId);
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        q.setParameter("fechaBusqueda",fecha);
        q.setParameter("horaBusqueda",hora);

        productos =  q.getResultList();

        return productos;
    }
    
   

    public List<Producto> findByNameAndDescriptionAndPrice(String busqueda,int minimo, int maximo) {
        List<Producto> productos;
        
        Query q;
              
        q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN p.palabraClaveList pcl WHERE "
                + "(LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) or LOWER(pcl.palabra) like LOWER(:busqueda))"
                + " and p.precio >= :minimo and p.precio <= :maximo  ");
        

        q.setParameter("busqueda", "%" + busqueda + "%");
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
                
        
        productos =  q.getResultList();
        
        return productos;
    }
    
    
    public List<Producto> findByNameAndDescriptionAndPriceAndHour(String busqueda,int minimo, int maximo,Date hora) throws ParseException {
        List<Producto> productos;
        
        Query q;
              
        q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN p.palabraClaveList pcl WHERE "
                + "(LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) or LOWER(pcl.palabra) like LOWER(:busqueda)) "
                + " and p.precio >= :minimo and p.precio <= :maximo  "
                + " and p.horaCreacion = :horaBusqueda");
        

        q.setParameter("busqueda", "%" + busqueda + "%");
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        q.setParameter("horaBusqueda",hora);
              
        
        productos =  q.getResultList();
        
        return productos;
    }
    
    
     public List<Producto> findByCategoryAndNameAndDescriptionAndPrice(String busqueda,int categoriaId, int minimo, int maximo){
        List<Producto> productos;
        
        Query q;
              
         q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN  p.categoriaId cl JOIN p.palabraClaveList pcl"
                + " WHERE (LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) or LOWER(pcl.palabra) like LOWER(:busqueda)) "
                + "and (cl.id = :categoriaId or cl.categoriaPadre.id = :categoriaId) and p.precio >= :minimo and p.precio <= :maximo ");
        

        q.setParameter("busqueda", "%" + busqueda.toLowerCase() + "%");
        
        q.setParameter("categoriaId", categoriaId);
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        
        productos =  q.getResultList();
        
        return productos;
    }
     
     public List<Producto> findByCategoryAndNameAndDescriptionAndPriceAndHour(String busqueda,int categoriaId, int minimo, int maximo,Date hora){
        List<Producto> productos;
        
        Query q;
              
         q = this.getEntityManager().createQuery("SELECT DISTINCT p FROM Producto p JOIN  p.categoriaId cl JOIN p.palabraClaveList pcl"
                + " WHERE (LOWER(p.titulo) like LOWER(:busqueda) or LOWER(p.descripcion) like LOWER(:busqueda) or LOWER(pcl.palabra) like LOWER(:busqueda)) "
                + "and (cl.id = :categoriaId or cl.categoriaPadre.id = :categoriaId) and p.precio >= :minimo and p.precio <= :maximo "
                + " and p.horaCreacion = :horaBusqueda");
        

        q.setParameter("busqueda", "%" + busqueda.toLowerCase() + "%");
        
        q.setParameter("categoriaId", categoriaId);
        q.setParameter("minimo",minimo);
        q.setParameter("maximo",maximo);
        q.setParameter("horaBusqueda",hora);
        
        productos =  q.getResultList();
        
        return productos;
    }
}
