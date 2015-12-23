/**
 * 
 */
package com.mongo.metier;

import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author rudi
 *
 */
public class Data {
	
	private String id = UUID.randomUUID().toString();
	 @NotBlank
	    private String mark;
   /* @NotBlank
    private String model;
 
    @NotBlank
    private int year;
    
    @NotBlank
    private Fuel fuel_type;
    
    @NotBlank
    private Double txCO2;
    
    private Double txNox;
    */
 
	/*public Data(String _mark, String _model, int _year,Fuel _fuel_type, Double _txCO2,
			Double _txNox) {
		// TODO Auto-generated constructor stub
		this.model = _model;
		this.mark = _mark;
		this.year = _year;
		this.fuel_type = _fuel_type;
		this.txCO2 = _txCO2;
		this.setTxNox(_txNox);
	} */   
	public Data(String _mark) {
		// TODO Auto-generated constructor stub
	
		this.mark = _mark;
	
	}
 
    public String getId() {
        return id;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark){
		this.mark = mark;
	}
    /*public String getModel() {
        return model;
    }
    public void setModel(String model){
		this.model = model;
	}
    public int getYear() {
        return year;
    }
    public void setYear(int year){
		this.year = year;
	}
    public Fuel getFuel_Type() {
        return fuel_type;
    }
    public void setFuel_Type(Fuel fuel_type){
		this.fuel_type = fuel_type;
	}
    public Double getTxCO2() {
		return txCO2;
	}
	public void setTxCO2(Double txCO2){
		this.txCO2 = txCO2;
	}

	public Double getTxNox() {
		return txNox;
	}

	public void setTxNox(Double _txNox) {
		this.txNox = _txNox;
	}
 */
    
}
