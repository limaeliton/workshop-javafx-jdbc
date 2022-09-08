package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	
	// dependência
	private SellerDao dao = DaoFactory.createSellerDao();
	
	
	// retorna uma lista de vendedores
	public List<Seller> findAll(){
		// vai no banco e retorna todos os vendedores.
		return dao.findAll();
	}
	
	public void saveOrUpdate(Seller obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}

}
