package com.cenfotec.dondeEs.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.dondeEs.contracts.ServiceResponse;
import com.cenfotec.dondeEs.ejb.Service;
import com.cenfotec.dondeEs.services.ServiceInterface;

@RestController
@RequestMapping(value = "rest/protected/service")
public class ServiceController {

	@Autowired private ServiceInterface serviceInterface;
	
	@RequestMapping(value ="/getAllService", method = RequestMethod.GET)
	public ServiceResponse getAllService(){
		ServiceResponse response = new ServiceResponse();
		response.setServiceList(serviceInterface.getAll());
		return response;
	}
	
	@RequestMapping(value ="/createService", method = RequestMethod.POST)
	public ServiceResponse createService(@RequestBody Service service){
		ServiceResponse response = new ServiceResponse();
		
		Boolean state = serviceInterface.saveService(service);
	
		if(state){
			response.setCode(200);
			response.setCodeMessage("Succesfull");
		}else{
			response.setCode(500);
			response.setCodeMessage("Internal error");
		}
		return response;
	}
	
	@RequestMapping(value ="/getService/{serviceId}", method = RequestMethod.GET)
	public ServiceResponse getService(@PathVariable("serviceId") int serviceId){
		ServiceResponse response = new ServiceResponse();
		response.setService(serviceInterface.getService(serviceId));
		return response;
	}
	
	/**
	 * @author Ernesto Mendez A.
	 * @param catalogId id del catalogo al cual el servicio pertenece
	 * @return lista de servicios con el catalogo especificado
	 * @version 1.0
	 */
	@RequestMapping(value ="/getServicesByCatalog/{catalogId}", method = RequestMethod.GET)
	public ServiceResponse getServicesByCatalog(@PathVariable("catalogId") int catalogId){
		ServiceResponse response = new ServiceResponse();
		response.setServiceLists(serviceInterface.getByCatalog(catalogId));
		return response;
	}
}
