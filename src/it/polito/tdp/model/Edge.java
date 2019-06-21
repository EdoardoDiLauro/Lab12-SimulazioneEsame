package it.polito.tdp.model;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Edge {
	
	public Edge(District disa, District disb) {
		super();
		this.disa = disa;
		this.disb = disb;
	}
	
	District disa;
	District disb;
	double weight;
	

	public void setWeight(double weight) {
		double w = 0;
		w = LatLngTool.distance(disa.getCenter(), disb.getCenter(), LengthUnit.KILOMETER);
		weight = w;
	}

}
