package model;

import java.io.Serializable;

public class FinancialInfo implements Serializable {
	
	private double profit;
	private double revenue;
	private double costOGoods;
	private final double saleTax = .045;
	private double saleTaxTotal;
	
	public FinancialInfo(){
		profit = 0;
		revenue = 0;
		costOGoods = 0;
		saleTaxTotal = 0;
	}
	
	public void addSale(double sale, double goodPrice){
		revenue += sale;
		costOGoods += goodPrice;
		addTaxTotal(sale);
	}
	
	public void removeSale(Ticket ticket) {
		revenue -= ticket.getCost();
		saleTaxTotal -= (ticket.getCost() * saleTax);
	}
	
	public void removeSale(Table table) {
		revenue -= table.getCost();
		saleTaxTotal -= (table.getCost() * saleTax);
	}
	
	private void addTaxTotal(double sale){
		saleTaxTotal += (sale * saleTax);
	}
	
	public double getProfit(){
		return revenue - costOGoods;
	}
	
	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public double getCostOGoods() {
		return costOGoods;
	}

	public void setCostOGoods(double costOGoods) {
		this.costOGoods = costOGoods;
	}

	public double getSaleTaxTotal() {
		return saleTaxTotal;
	}

	public void setSaleTaxTotal(double saleTaxTotal) {
		this.saleTaxTotal = saleTaxTotal;
	}

	public double getSaleTax() {
		return saleTax;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String display(){
		String display = ("Total revenue: $" + revenue + ". Cost of goods sold: $" + costOGoods + ". For a total profit of: $" 
				+ getProfit() + ". Total sales tax: $" + saleTaxTotal);
		return display;
	}

}
