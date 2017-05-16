package view;

import java.text.DecimalFormat;

import controller.Current;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Business;
import model.FinancialInfo;

public class PaneForFinancialInfo {
	
	private Business business;
	private FinancialInfo info;
	//rounds doubles to 2 places
	DecimalFormat df = new DecimalFormat("#.##");

	public PaneForFinancialInfo() {
		business = Current.getBusiness();
		info = business.getFinanceInfo();
	}
	
	public Pane getPane(){
		Pane pane = new Pane();
		pane.getChildren().add(view());
		return pane;
	}
	
	private VBox view(){
		VBox view = new VBox(5);
		view.getChildren().addAll(profit(), revenue(), saleTax(), saleTaxTotal());
		return view;
	}
	
	//returns profit
	private HBox profit(){
		HBox profit = new HBox(5);
		Label profitLbl = new Label("Profit: $" + info.getProfit());
		profit.getChildren().addAll(profitLbl);
		return profit;
	}
	
	//returns revenue
	private HBox revenue(){
		HBox revenue = new HBox(5);
		Label revenueLbl = new Label("Revenue: $" + info.getRevenue());
		revenue.getChildren().addAll(revenueLbl);
		return revenue;
	}
	
	//returns sum of all sales' tax values
	private HBox saleTaxTotal(){
		HBox saleTaxTotal = new HBox(5);
		Label saleTaxTotalLbl = new Label("Sale Tax Total: $" + df.format(info.getSaleTaxTotal()));
		saleTaxTotal.getChildren().addAll(saleTaxTotalLbl);
		return saleTaxTotal;
	}
	
	//returns sales tax which is 4.5%
	private HBox saleTax(){
		HBox saleTax = new HBox(5);
		Label saleTaxLbl = new Label("Sale Tax: " + (df.format(info.getSaleTax() * 100)) + "%");
		saleTax.getChildren().addAll(saleTaxLbl);
		return saleTax;
	}
	
}
