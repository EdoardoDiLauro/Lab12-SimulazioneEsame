package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;

public class District {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		District other = (District) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public District(Integer id) {
		super();
		this.id = id;
	}

	double centerLat;
	double centerLng;
	Integer id;
	double km;
	
	public double getKm() {
		return km;
	}



	public void setKm(double km) {
		this.km = km;
	}



	public double getCenterLat() {
		return centerLat;
	}



	public double getCenterLng() {
		return centerLng;
	}
	
	public LatLng getCenter() {
		
		double cntrlat = 0;
		for (LatLng c : coordinate) {
			cntrlat= cntrlat + c.getLatitude() ;
		}
		centerLat = cntrlat/coordinate.size();
		
		double cntrlng = 0;
		for (LatLng c : coordinate) {
			cntrlng= cntrlng + c.getLongitude() ;
		}
		centerLng = cntrlng/coordinate.size();
		
		LatLng center = new LatLng(centerLat, centerLng);
		return center;
	}

	List<LatLng> coordinate = new ArrayList<LatLng>();
	
	
	
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

	

	public void addEventi(Event e) {
		LatLng coords = new LatLng (e.getGeo_lat(), e.getGeo_lon());
		coordinate.add(coords);
	}

	

}
