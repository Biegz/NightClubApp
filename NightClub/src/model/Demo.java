package model;

import static controller.SignInUp.hash;

import java.time.LocalDate;

import controller.Current;
import controller.CustomerTicketProcessing;
import controller.SignInUp;
import model.model4User.User;
import model.model4User.model4Customer.Customer;
import model.model4User.model4Establishment.Business;

public class Demo {

	public static void main(String[] args) {

//		IO.loadAll();
//		
//		User a = new Customer("John Doe", hash("password"));
//		UsersBag.add(a);
//		
//		Event ev1 = new Event();
//		EventsBag.add(ev1);
//		
//		IO.saveAll();
		
//		UsersBag.users.toString();
//		
//		User a = UsersBag.users.get(0);
//		System.out.println(a.getUsername());
//
//		Event e;
//		e = EventsBag.events.get(0);
//		System.out.println(e.getEventName());
		
		Customer tom = new Customer("Tom", 45);
		UsersBag.add(tom);
		
		Business bs1 = new Business("Bar",23);
		
		SignInUp sign = new SignInUp();
		//System.out.println(sign.hashCode());
	
		
		Current current = new Current();
		
		current.setBusiness(bs1);
		current.setUser(tom);
		
		Event ev1 = new Event();
		Event ev2 = new Event();
		Event ev3 = new Event();
		Event ev4 = new Event();
		Event ev5 = new Event();
		Event ev6 = new Event();

		current.setEvent(ev1);
		
		
		//ev1.business = bs1;
		ev1.setDate(LocalDate.of(2012, 1, 12));
		ev1.setEventName("HOME DEPOT");
		
		ev2.setDate(LocalDate.of(2012, 2, 12));
		ev2.setEventName("Kohls");
		
		ev3.setDate(LocalDate.of(2012, 3, 12));
		ev3.setEventName("CVS");
		
		ev4.setDate(LocalDate.of(2012, 4, 12));
		ev4.setEventName("Costco");
		
		ev5.setDate(LocalDate.of(2012, 5, 12));
		ev5.setEventName("BJ's");
		
		ev6.setDate(LocalDate.of(2012, 6, 12));
		ev6.setEventName("Starbucks");
		
		EventsBag.add(ev1);
		EventsBag.add(ev2);
		EventsBag.add(ev3);
		EventsBag.add(ev4);
		EventsBag.add(ev5);
		EventsBag.add(ev6);

		
		
		EventsBag.save();

		
//		UsersBag.add(tom);
//		EventsBag.add(ev1);
//		
//		UsersBag.save();
//		EventsBag.save();
//		CustomerTicketProcessing CTP = new CustomerTicketProcessing(ev1);
//		CTP.buyTicket(2);
//		System.out.println(tom.getBalance());
//		System.out.println(ev1.getTicketsAvailable());
//		System.out.println(bs1.getFinanceInfo().display());
//		
//		System.out.println(tom.findTicket(ev1).displayInfo());
//		
//		CTP.returnTicket(tom.findTicket(ev1), tom);
//		System.out.println(tom.getBalance());
//		System.out.println(ev1.getTicketsAvailable());
//		System.out.println(bs1.getFinanceInfo().display());
//		
//		CTP.returnTicket(tom.findTicket(ev1), tom);
//		System.out.println(tom.getBalance());
//		System.out.println(ev1.getTicketsAvailable());
//		System.out.println(bs1.getFinanceInfo().display());
		
	}

}

