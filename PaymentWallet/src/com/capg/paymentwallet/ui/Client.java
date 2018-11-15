package com.capg.paymentwallet.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capg.paymentwallet.bean.AccountBean;
import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.WalletTransaction;
import com.capg.paymentwallet.service.AccountServiceImpl;
import com.capg.paymentwallet.service.IAccountService;

public class Client {
	   
		IAccountService service=new AccountServiceImpl();
		CustomerBean customer=new CustomerBean();
		Scanner scanner=new Scanner(System.in);
		
		
		public static void main(String[] args) throws Exception {
			char ch;
			Client client=new  Client();
			do
			{
			System.out.println("========Payment wallet application========");
			System.out.println("1. Create Account ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Fund Transfer");
			System.out.println("6. Print Transactions");
			System.out.println("7. Exit");
			System.out.println("Choose an option");
			int option =client. scanner.nextInt();
			
			switch (option) {
			case 1:client.create();
	               break;
			case 2:client.showbalance();

				break;

			case 3:client.deposit();

				break;
				
				
			case 4:client.withdraw();

				break;
				
		
			case 5:client.fundtransfer();

				break;
				
			
			case 6:client.printTransaction();

				break;
			case 7:System.exit(0);

				break;
				
				
			default:System.out.println("invalid option");
				break;
			}
			
			System.out.println("Do you want to continue press Y/N");
			ch=client.scanner.next().charAt(0);
			
			}while(ch=='y' || ch=='Y');

			
		}
		
		
		void create() throws Exception
		{
			
			System.out.print("\tEnter Customer firstname\t :");
			String fname=scanner.next();
			
			System.out.print("\tEnter Customer lastname\t :");
			String lname=scanner.next();
			
			System.out.print(" \tEnter  Customer  email id\t :");
			String email=scanner.next();
			
			System.out.print("\tEnter  Customer  phone number\t:");
			String phone=scanner.next();
			
			System.out.print("\tEnter  Customer PAN number\t:");
			String pan=scanner.next();
			
			System.out.print("\tEnter  Customer  address\t:");
			String address=scanner.next();
			
			
			CustomerBean customerBean=new CustomerBean();
			customerBean.setAddress(address);
			customerBean.setEmailId(email);
			customerBean.setPanNum(pan);
			customerBean.setPhoneNo(phone);
			customerBean.setFirstName(fname);
			customerBean.setLastName(lname);
			
			System.out.print("\tEnter  Account ID\t:");
			int accId=scanner.nextInt();
			
			
			System.out.print("\tEnter Date of Opening (DD/MM/YYYY)\t:");
			String accDateInput=scanner.next();
			
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			Date dateOfOpeining=sdf.parse(accDateInput);
			
			
			System.out.print("\tEnter balance to create account\t:");
			double balance=scanner.nextDouble();
			
			AccountBean accountBean=new AccountBean();
		
			accountBean.setBalance(balance);
			accountBean.setInitialDeposit(balance);
			accountBean.setCustomerBean(customerBean);
			accountBean.setAccountId(accId);
			accountBean.setDateOfOpening(dateOfOpeining);
			
			
            boolean isValid = service.validateCustomer(accountBean);
            if(isValid) {
            	boolean isAdded = service.createAccount(accountBean);
            	
            	if(isAdded) {
            		System.out.println("\n\tCongratulations Customer account has been created successfully...\n\t");
            		
            	}
           
			}
			else
			{
				System.out.println("\n\tEnter valid details..\n\t");
			}
		}
		
		
		void showbalance() throws Exception 
		{
			System.out.println("\tEnter phone number\t:");
			String phoneNo=scanner.next();
			
			AccountBean accountBean=service.findAccount(phoneNo);
			
			if(accountBean==null){
				System.out.println("Account Does not exist");
				return ;
			}
			System.out.print(accountBean.getCustomerBean().getFirstName() + " " );
			double balance=accountBean.getBalance();
					
			
			System.out.println("Your balance is: " +balance);
			
				
			
		}
		
		void deposit() throws Exception
		{
			System.out.println("\tEnter phone number\t:");
		String phoneNo=scanner.next();
		
		AccountBean accountBean=service.findAccount(phoneNo);
		

			if(accountBean==null){
				System.out.println("Account Does not exist");
				}
			System.out.println(accountBean.getCustomerBean().getFirstName());
			System.out.println("\tEnter amount that you want to deposit\t:");
			double depositAmt=scanner.nextDouble();
			
			WalletTransaction wt=new WalletTransaction();
			wt.setTransactionType(1);
			wt.setTransactionDate(new Date());
			wt.setTransactionAmt(depositAmt);
			wt.setBeneficiaryAccountBean(null);
			
			accountBean.addTransation(wt);
			
		   boolean result=service.deposit(accountBean, depositAmt);
			
			if(result){
				System.out.println("Money deposited into your Account is:"+depositAmt);
			}else{
				System.out.println("Deposition money failed");
			}
				
		}
		
		void withdraw() throws Exception
		{
			System.out.println("\tEnter Account ID\t:");
			int accId=scanner.nextInt();
			
			AccountBean accountBean=service.findAccount(accId);

			if(accountBean==null){
				System.out.println("Account Does not exist");
				}
			
			System.out.println(accountBean.getCustomerBean());
			
			System.out.println("Enter amount that you want to withdraw");
			double withdrawAmt=scanner.nextDouble();
			
			
			
			WalletTransaction wt=new WalletTransaction();
			wt.setTransactionType(2);
			wt.setTransactionDate(new Date());
			wt.setTransactionAmt(withdrawAmt);
			wt.setBeneficiaryAccountBean(null);
			
			accountBean.addTransation(wt);
			
			
			
			boolean result=service.withdraw(accountBean, withdrawAmt);
			if(result){
				System.out.println("Withdaw Money from Account done");
			}else{
				System.out.println("Withdaw Money from Account -Failed ");
			}
			
		}
		
		void fundtransfer() throws Exception
		{
			System.out.println("\nEnter Account ID to Transfer Money From\t:");
			int srcAccId=scanner.nextInt();
			
			AccountBean accountBean1=service.findAccount(srcAccId);
			
			System.out.println(accountBean1.getCustomerBean().getFirstName());
			System.out.println("\tEnter Account ID to Transfer Money to\t:");
			int targetAccId=scanner.nextInt();
			
			AccountBean accountBean2=service.findAccount(targetAccId);
			System.out.println(accountBean2.getCustomerBean().getFirstName());
			System.out.println("\tEnter amount that you want to transfer\t:");
			double transferAmt=scanner.nextDouble();
			
			WalletTransaction wt=new WalletTransaction();
			wt.setTransactionType(3);
			wt.setTransactionDate(new Date());
			wt.setTransactionAmt(transferAmt);
			wt.setBeneficiaryAccountBean(accountBean2);
			
			
			accountBean1.addTransation(wt);
			
			
			
			boolean result=service.fundTransfer(accountBean1, accountBean2, transferAmt);
			
			if(result){
				System.out.println("Money transfered succsefully");
			}else{
				System.out.println("Money transfered failed ");
			}
			
		}
		
		
		void printTransaction() throws Exception
		{
			System.out.println("\tEnter Account ID for printing Transaction Details\t:");
			int accId=scanner.nextInt();
			
			AccountBean accountBean=service.findAccount(accId);
			
			List<WalletTransaction>  transactions=accountBean.getAllTransactions();
			
			System.out.println(accountBean);
			System.out.println(accountBean.getCustomerBean());
			
			System.out.println("------------------------------------------------------------------");
			
			for(WalletTransaction wt:transactions){
				
				String str="";
				if(wt.getTransactionType()==1){
					str=str+"DEPOSIT";
				}
				if(wt.getTransactionType()==2){
					str=str+"WITHDRAW";
				}
				if(wt.getTransactionType()==3){
					str=str+"FUND TRANSFER";
				}
				
				
				str=str+"\t\t"+wt.getTransactionDate();
				
				str=str+"\t\t"+wt.getTransactionAmt();
				
				System.out.println(str);
			}
			
			System.out.println("------------------------------------------------------------------");
		
		}
		

}
