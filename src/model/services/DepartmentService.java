package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	// dependência
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	
	// retorna uma lista de department
	public List<Department> findAll(){
		// vai no banco e retorna o departamento.
		return dao.findAll();
	}

}
