package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import subway.dto.ResultDto;

public class RelationRepository {
	private static final List<Relation> relations = new ArrayList<>();
	private static final WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(
		DefaultWeightedEdge.class);
	private static final WeightedMultigraph<Station, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(
		DefaultWeightedEdge.class);

	public static List<Relation> relations() {
		return Collections.unmodifiableList(relations);
	}

	public static void addRelation(Relation relation) {
		relations.add(relation);
		addDistanceGraph(relation);
		addTimeGraph(relation);
	}

	private static void addDistanceGraph(Relation relation) {
		Station stationOne = relation.getStationOne();
		Station stationAnother = relation.getStationAnother();
		distanceGraph.addVertex(stationOne);
		distanceGraph.addVertex(stationAnother);
		int distance = relation.getDistance();
		distanceGraph.setEdgeWeight(distanceGraph.addEdge(stationOne, stationAnother), distance);
	}

	private static void addTimeGraph(Relation relation) {
		Station stationOne = relation.getStationOne();
		Station stationAnother = relation.getStationAnother();
		distanceGraph.addVertex(stationOne);
		distanceGraph.addVertex(stationAnother);
		int time = relation.getTime();
		distanceGraph.setEdgeWeight(distanceGraph.addEdge(stationOne, stationAnother), time);
	}

	public static boolean deleteRelationByName(String nameOne, String nameAnother) {
		return relations.removeIf(relation -> {
				String stationName = relation.getStationOne().getName();
				String stationAnotherName = relation.getStationAnother().getName();
				boolean result = (Objects.equals(stationName, nameOne) && Objects.equals(stationAnotherName, nameAnother))
					|| (Objects.equals(stationName, nameAnother) && Objects.equals(stationAnotherName, nameOne));
				return result;
			}
		);
	}

	public static void deleteAll() {
		relations.clear();
	}

	public static Relation findByNames(String nameOne, String nameAnother) {
		return relations().stream()
			.filter(relation -> relation.equals(nameOne, nameAnother))
			.findAny()
			.get();
	}

	public static ResultDto getResultByShortestDistance(String sourceStationName, String targetStationName) {
		DijkstraShortestPath shortestPath = new DijkstraShortestPath(distanceGraph);
		GraphPath graphPath = shortestPath.getPath(new Station(sourceStationName), new Station(targetStationName));
		int distanceWeight = (int)graphPath.getWeight();
		int timeWeight = (int)getNormalTimeWeight(graphPath);
		return new ResultDto(distanceWeight, timeWeight);
	}

	private static double getNormalTimeWeight(GraphPath graphPath) {
		int resultTime = 0;
		List edgeList = graphPath.getEdgeList();
		for (Object edge : edgeList) {
			Relation relation = getRelation(edge);
			resultTime += relation.getTime();
		}
		return resultTime;
	}

	private static Relation getRelation(Object edge) {
		String[] strings = edge.toString().trim().replaceAll("[()]", "").split(" : ");
		String one = strings[0];
		String another = strings[1];
		Relation relation = findByNames(one, another);
		return relation;
	}

	public static ResultDto getResultByShortestTime(String sourceStationName, String targetStationName) {
		DijkstraShortestPath shortestPath = new DijkstraShortestPath(timeGraph);
		GraphPath graphPath = shortestPath.getPath(new Station(sourceStationName), new Station(targetStationName));
		int distanceWeight = (int)getNormalDistanceWeight(graphPath);
		int timeWeight = (int)graphPath.getWeight();
		return new ResultDto(distanceWeight, timeWeight);
	}

	private static double getNormalDistanceWeight(GraphPath graphPath) {
		int resultDistance = 0;
		List edgeList = graphPath.getEdgeList();
		for (Object edge : edgeList) {
			Relation relation = getRelation(edge);
			resultDistance += relation.getDistance();
		}
		return resultDistance;
	}

}
