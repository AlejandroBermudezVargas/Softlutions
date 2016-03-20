package com.cenfotec.dondeEs.services;

import java.util.List;

import com.cenfotec.dondeEs.contracts.LoginRequest;
import com.cenfotec.dondeEs.contracts.UserRequest;
import com.cenfotec.dondeEs.ejb.User;
import com.cenfotec.dondeEs.pojo.UserPOJO;

import com.cenfotec.dondeEs.pojo.ServicePOJO;

public interface UserServiceInterface {
	
	/**
	 * @param idUser Id del usuario
	 * @return Lista de los servicios asociados al usuario
	 * @version 1.0
	 */
	List<ServicePOJO> getAllService(int idUser);
	
	public int saveUser(UserRequest	 ur);
	
	User findByEmail(String email);

	/**
	 * @author Ernesto Méndez A.
	 * @return Lista de todos los usuarios registrados
	 * @version 1.0
	 */
	List<UserPOJO> getAll();
	Boolean updatePassword(LoginRequest ur);
	List<UserPOJO> getAllServicesProviderAuction(int idEvent);
	
	User findById(int id);
	
	/**
	 * @author Ernesto Méndez A.
	 * @param userId el usuario a modificar
	 * @param state el nuevo estado del usuario
	 * @return si la operacion fue exitosa
	 * @version 1.0
	 */
	Boolean changeUserState(int userId, boolean state);
}
