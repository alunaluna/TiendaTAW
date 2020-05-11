/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import tienda.dao.UsuarioFacade;
import tienda.entity.Usuario;

/**
 *
 * @author eugenio
 */
@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;    
        
    protected String nombre;
    protected String clave; 
    protected String status = "";
    
    protected Usuario user = null;

    /**
     * Creates a new instance of UsuarioBean
     */
    public UsuarioBean() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
                
    
    public String doLogin () {        
        Usuario usuario = this.usuarioFacade.findByNombre(this.nombre);
        if (usuario == null) {
            this.status = "El usuario no se encuentra en la base de datos";
            this.nombre = "";
            this.clave = "";
            return null;
        } else if (!this.clave.equals(usuario.getContrasena())) {
           this.status = "La clave es incorrecta";            
            this.clave = "";           
            return null;           
        } else {
            this.user = usuario;
            return "menu";            
        }        
    }
    
    public String doLogout () {
        this.user = null;
        this.status = "";
        this.nombre = "";
        this.clave = "";
        return "login";        
    }
        
    
}
