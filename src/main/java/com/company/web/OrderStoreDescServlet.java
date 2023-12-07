package com.company.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.domain.Store;
import com.company.service.StoreService;

@WebServlet("/orderStoreDesc")
public class OrderStoreDescServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
    StoreService storeService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
    	List<Object[]> stores = storeService.orderStore();
    	
		for (Object[] result : stores) {

			Store store = (Store) result[0];
			Long femaleCount = (Long) result[1];
			System.out.println("Store: " + store.getName() + ", Female Count: " + femaleCount);
		}
 
       
        //System.out.println("stores :" + stores);
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("/listOrderStore.jsp").forward(request, response);
    }

}
