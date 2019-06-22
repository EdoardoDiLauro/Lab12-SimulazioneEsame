package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.db.EventsDao;

public class Model {
	
	Graph<District,DefaultWeightedEdge> grafo = new WeightedMultigraph<District,DefaultWeightedEdge>(DefaultWeightedEdge.class);
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
			if (!districts.containsKey(e.getDistrict_id()) && e.getReported_date().getYear()<=anno) {
				District dis = new District(e.getDistrict_id());
				dis.addEventi(e);
				districts.put(dis.getId(), dis);	
			}
			else if (e.getReported_date().getYear()<=anno) {
				District dis = districts.get(e.getDistrict_id());
				dis.addEventi(e);
			}
		}
		
		return districts;
	
	}
	
	public Graph<District,DefaultWeightedEdge> getGrafo(Integer anno){
		
		Map<Integer, District> distretti = this.getDistretti(anno);
		
		
		
		for (Map.Entry<Integer, District> d1 : distretti.entrySet()) {
			for (Map.Entry<Integer, District> d2 : distretti.entrySet()) {
				if (d1.getKey()!=d2.getKey()) {
				double w = LatLngTool.distance(d1.getValue().getCenter(), d2.getValue().getCenter(), LengthUnit.KILOMETER);
				Graphs.addEdgeWithVertices(this.grafo, d1.getValue(), d2.getValue(), w);
				}
			}
		}
		
		return this.grafo;	
	}
	
	public String creaRete(Integer anno) {
		
		StringBuilder sb = new StringBuilder();
		
		this.grafo = this.getGrafo(anno);
		
		for (District d : this.grafo.vertexSet()) {
			List<DefaultWeightedEdge> edges = new ArrayList<DefaultWeightedEdge>(this.grafo.edgesOf(d));
			Collections.sort(edges, new edgecomp(this.grafo));
			sb.append("Distances from district " + d.getId().toString() +" \n");
			for (DefaultWeightedEdge e : edges) {
				if (this.grafo.getEdgeSource(e).equals(d)) {
					sb.append(this.grafo.getEdgeTarget(e).getId() +" "+ this.grafo.getEdgeWeight(e) + "\n");
				}
			}
		}
		
		sb.append("end \n");
		
		return sb.toString();
		
	}
	
	
}
