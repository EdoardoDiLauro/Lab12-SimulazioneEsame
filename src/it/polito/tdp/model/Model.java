package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	private Graph<District,DefaultWeightedEdge> grafo;
	Map<Integer, District> districts = new HashMap<>();
	
	EventsDao dao = new EventsDao();
	
	public List<Integer> getAnno() {
		
		List<Integer> result = new ArrayList<Integer>();
		
		for (Event e : dao.listAllEvents()){
			Integer anno = e.getReported_date().getYear();
			if (!result.contains(anno)) result.add(anno);
		}
		
		return result;
	}
	
	public Map<Integer, District> getDistretti(Integer anno){
		
		
		
		for (Event e : dao.listAllEvents()){
			if (!districts.containsKey(e.getDistrict_id())) {
				District dis = new District(e.getDistrict_id());
				districts.put(dis.getId(), dis);
				dis.addEventi(e);
			}
			else {
				District dis = districts.get(e.getDistrict_id());
				dis.addEventi(e);
			}
		}
		
		return districts;
	
	}
	
	public Graph<District,DefaultWeightedEdge> getGrafo(Integer anno){
		
		Map<Integer, District> distretti = this.getDistretti(anno);
		
		Graphs.addAllVertices(this.grafo, distretti.values());
		
		for (Map.Entry<Integer, District> d1 : distretti.entrySet()) {
			for (Map.Entry<Integer, District> d2 : distretti.entrySet()) {
				double w = LatLngTool.distance(d1.getValue().getCenter(), d2.getValue().getCenter(), LengthUnit.KILOMETER);
				Graphs.addEdge(this.grafo, d1.getValue(), d2.getValue(), w);
			}
		}
		
		return this.grafo;
		
		
		
	}
}
