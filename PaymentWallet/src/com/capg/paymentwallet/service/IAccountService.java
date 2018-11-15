package com.capg.paymentwallet.service;



import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.exception.CustomerException;

public interface IAccountService {
	   public boolean createAccount(AccountBean accountBean) throws Exception ;
       public AccountBean findAccount(String phoneNo) throws Exception;
       public boolean deposit(AccountBean accountBean,double depositAmount) throws Exception ;
       public boolean withdraw(AccountBean accountBean,double withdrawAmount) throws Exception;
       public boolean fundTransfer(AccountBean transferingAccountBean, AccountBean beneficiaryAccountBean, double transferAmount) throws Exception ;
       public boolean validateCustomer(AccountBean accountBean) throws CustomerException;
    		
		 
	
}
