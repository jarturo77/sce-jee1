package com.company.web;

//import com.company.service.EmployeeService;
//import com.company.service.EmployeeServiceImpl;
//import com.company.service.StoreService;
//import com.company.service.StoreServiceImpl;
//import com.company.domain.Employee;
//import com.company.domain.Store;
public class ClientEntityEmployee {
	public static void main(String[] args) {
	/*	
		////////////////////////////////buscar empleado por id Empleado
		
		EmployeeService employeeService = new EmployeeServiceImpl();
		int idEmployee = 1;
		Employee employee = employeeService.findEmployeeById(idEmployee);
		if(employee != null) {
			System.out.println("Empleado encontrado: " + employee.getName() + " " + employee.getLastname());
		}else {
			System.out.println("Empleado no encontrado");
		}
		*/
		//////////////////////////////crear un nuevo empleado
		/*	
		EmployeeService createEmployee = new EmployeeServiceImpl();
		//parametros para crear un nuevo empleado
		String name = "Jose";
		String lastname = "Herrera";
		String birthdate = "05/02/1989";
		int active = 1;
		int idStore = 1;
		
		Employee newEmployee = createEmployee.registerEmployee(name, lastname, birthdate, active, idStore);
		if(newEmployee != null){
			System.out.println("Empleado creado con exito. ID: " + newEmployee.getIdEmployee());	
			
		}else{
			System.out.println("No se puede crear el empleado verifica la exixtencia de la sucursal");
		}
		
		*/
		//////////////////////modificar sucursal a la que pertenece el empleado
		/*
		EmployeeService changeStore = new EmployeeServiceImpl();
		int idEmpleado1 = 1;
		int idStoreChange = 1;
		Employee newStore = changeStore.ChangeStoreEmployee(idEmpleado1, idStoreChange);
		if(newStore != null){
			System.out.println("El cambio de sucursal del empleado se realizó correctamente. IDSucursal: " + newStore.getStore());	
			
		}else{
			System.out.println("No se puede cambiar la sucursal a la que pertenece el empleado");
		}
		
		*/
		///////////////////////////////cambiar estado del empleado 
		/*
		EmployeeService changeState = new EmployeeServiceImpl();
		int IdEmpleado2 = 1;
		boolean newState = true;
		Employee state = changeState.ChangeStateEmployee(IdEmpleado2, newState);
		if(state != null){
			System.out.println("El cambio de estado del empleado se realizó correctamente. Estado es: " + state.isActive());	
			
		}else{
			System.out.println("No se puede cambiar el estado al que pertenece el empleado");
		}
		*/
		
		///////////////////////////////cambiar direccion de la Sucursal
		/*
		StoreService changeAddres = new StoreServiceImpl();
		int idStore = 1;
		String address = "Santa Salvador";
		Store changeaddress = changeAddres.ChangeAddressStore(idStore, address);
		
		if(changeaddress != null){
			System.out.println("El cambio de direccion de sucursal se realizó correctamente. La nueva direccion es: " + changeaddress.getAddress());	
			
		}else{
			System.out.println("No se puede cambiar la direccion de la sucursal");
		}
		*/
		///////////////////////////////cambiar nombre de la Sucursal
		/*
		
		StoreService changeName = new StoreServiceImpl();
		int idStore1 = 1;
		String name = "Sucursal 1";
		Store changename = changeName.ChangeNameStore(idStore1, name);
		if(changename != null){
			System.out.println("El cambio de nombre de sucursal se realizó correctamente. El nuevo nombre es: " + changename.getName());	

		}else{
			System.out.println("No se puede cambiar el nombre de la sucursal");
		}
		
		*/
		
		
		
	}
}
