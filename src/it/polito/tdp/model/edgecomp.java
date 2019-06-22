package it.polito.tdp.model;

import java.util.Comparator;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class edgecomp implements Comparator<DefaultWeightedEdge>{
	
	public edgecomp(Graph<District, DefaultWeightedEdge> grafo) {
		super();
		this.g = grafo;
	}
	
	Graph<District, DefaultWeightedEdge> g;
	public int compare (DefaultWeightedEdge e1, DefaultWeightedEdge e2) {
		if (g.getEdgeWeight(e1) >=g.getEdgeWeight(e2)) return -1;
		return 1;
	}
}
