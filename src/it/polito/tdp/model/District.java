package it.polito.tdp.model;

import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class District {
	
	public District(Integer id) {
		super();
		this.id = id;
	}

	double centerLat;
	double centerLng;
	Integer id;
	List<Event> eventi;
	
	public double getCenterLat() {
		return centerLat;
	}



	public double getCenterLng() {
		return centerLng;
	}
	
	public LatLng getCenter() {
		LatLng center = new LatLng(centerLat, centerLng);
		return center;
	}

	List<LatLng> coordinate;
	
	
	public void setCenterLat() {
		double cntrlat = 0;
		for (LatLng c : coordinate) {
			cntrlat= cntrlat + c.getLatitude() ;
		}
		centerLat = cntrlat/coordinate.size();
		}



	public void setCenterLng() {
		double cntrlng = 0;
		for (LatLng c : coordinate) {
			cntrlng= cntrlng + c.getLongitude() ;
		}
		centerLng = cntrlng/coordinate.size();
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Event> getEventi() {
		return eventi;
	}

	public void addEventi(Event e) {
		eventi.add(e);
		LatLng coords = new LatLng (e.getGeo_lat(), e.getGeo_lon());
		coordinate.add(coords);
	}

	

}
