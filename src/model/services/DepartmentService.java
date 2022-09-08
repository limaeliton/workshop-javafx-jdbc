package model.services;

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
	
	public void saveOrUpdate(Department obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}

}
