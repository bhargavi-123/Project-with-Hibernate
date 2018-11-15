package com.capg.paymentwallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.capg.paymentwallet.bean.AccountBean;

public class AccountDAOImpl implements IAccountDao {

	EntityManager em;
	
	@Override
	public boolean createAccount(AccountBean accountBean) throws Exception {
		try{
			
			this.em=JPAManager.createEntityManager();
			
			em.getTransaction().begin();
			
			em.persist(accountBean);
			
			em.getTransaction().commit();
		

			JPAManager.closeResources(em);
			return true;
			
		} catch(Exception e) {
			//return false;
			e.printStackTrace();
		}
		return false;
	
	}

	@Override
	public boolean updateAccount(AccountBean accountBean) throws Exception {
		try{
			this.em=JPAManager.createEntityManager();
			em.getTransaction().begin();
			
			em.merge(accountBean);
			
			em.getTransaction().commit();
			JPAManager.closeResources(em);
			return true;
		}catch(Exception e){
			return false;
		}
	
	}

	@Override
	public AccountBean findAccount(String phoneNo) throws Exception {
		try{
			em=JPAManager.createEntityManager();
			AccountBean accountBean2=em.find(AccountBean.class,phoneNo);
			JPAManager.closeResources(em);
			return accountBean2;
			
		}catch(Exception e){
			return null;
		}
	}


}
