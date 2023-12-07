package com.company.web;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

	
import com.company.domain.Store;
import com.company.service.StoreService;

@Named("storeBean")
@RequestScoped
public class StoreBean {
	static Logger log = LogManager.getLogger();
	
	@Inject
	private StoreService storeService;
	
	private Store storeSelected;
	
	List<Store> stores;
	
	
	
	
	
	public StoreBean() {
		log.debug("Iniciando el objeto StoreBean");
			
	}
	
	@PostConstruct
    public void init(){
		 
		System.out.println("Iniciando el init PostConstruct");
        //Inciamos las variables
        this.stores = storeService.findAllStores();
        
        log.debug("Sucursales recuperadas en ManagedBean:" + this.stores);
       
        this.storeSelected = new Store();
        
      
    }
	
	 
	
	
	@Transactional
	public void editListener(RowEditEvent event){
		try {
	        Store store = (Store) event.getObject();
	        log.debug("datos a modificar:" + store);
	        storeSelected.setUpdated_by("admin");
	    	storeSelected.setUpdated_at(new Date());
	        storeService.update(store);
	        PrimeFaces.current().executeScript("PF('successUpdateDialog').show();");
		} catch (Exception e) {
            e.printStackTrace(); 

            // Mostrar un mensaje de error al usuario
            FacesContext.getCurrentInstance().addMessage("dataTableMessages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - Modificar", "Error al modificar la sucursal."));
			}
	        
	        
	    }
	    
	      public Store getStoreSelected() {
	        return storeSelected;
	    }

	    public void setStoreSelected(Store storeSelected) {
	        this.storeSelected = storeSelected;
	    }

	    public List<Store> getStores() {
	        return stores;
	    }

	    public void setStores(List<Store> stores) {
	        this.stores = stores;
	    }
	    
	    @Transactional
	    public void insertStore(){
	    try {
	    	storeSelected.setCreated_by("admin");
	    	storeSelected.setCreated_at(new Date());
	        this.storeService.insert(storeSelected);
	        this.stores.add(storeSelected);
	        // Mostrar el diálogo de éxito
	        PrimeFaces.current().executeScript("PF('successSaveDialog').show();");
	        this.storeSelected = null;
	    } catch (Exception e) {
            e.printStackTrace(); 

            // Mostrar un mensaje de error al usuario
            FacesContext.getCurrentInstance().addMessage("dataTableMessages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - Guardar", "Error al registrar la sucursal."));
			}
	       
	        
	    }
	    
	    @Transactional
	    public void deleteStore(){
	    try {
	        this.storeService.delete(storeSelected);
	        this.stores.remove(this.storeSelected);
	        PrimeFaces.current().executeScript("PF('successDeleteDialog').show();");
	        this.storeSelected = null;
	    } catch (Exception e) {
            e.printStackTrace(); 

            // Mostrar un mensaje de error al usuario
            FacesContext.getCurrentInstance().addMessage("dataTableMessages",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error - Eliminar", "Error al eliminar la sucursal."));
			}
	    }
	    
	    public void restartStoreSelected(){
	        this.storeSelected = new Store();
	    }
	    
	 

	

}
