package subway;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

import subway.controller.RelationController;
import subway.domain.Relation;
import subway.domain.RelationRepository;
import subway.domain.Station;

public class JGraphtTest {
    @Test
    public void getDijkstraShortestPath() {
        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        graph.addVertex("v1");
        graph.addVertex("v2");
        graph.addVertex("v3");
        graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
        graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
        graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath("v3", "v1").getVertexList();

        assertThat(shortestPath.size()).isEqualTo(3);
    }

    @Test
    void test2() {
        WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        Station stationA = new Station("A");
        Station stationB = new Station("B");
        Station stationC = new Station("C");

        graph.addVertex(stationA);
        graph.addVertex(stationB);
        graph.addVertex(stationC);

        // 에러
        graph.setEdgeWeight(graph.addEdge(stationA, stationC), 100);
        graph.setEdgeWeight(graph.addEdge(stationA, stationB), 2);
        graph.setEdgeWeight(graph.addEdge(stationB, stationC), 3);
        // graph.setEdgeWeight(stationA, stationC, 100);
        // graph.setEdgeWeight(stationA, stationB, 2);
        // graph.setEdgeWeight(stationB, stationC, 3);

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);

        double weight = dijkstraShortestPath.getPathWeight(stationA, stationC);

        assertThat(weight).isEqualTo(5);

        GraphPath path = dijkstraShortestPath.getPath(stationA, stationC);
        List edgeList = path.getEdgeList();
        System.out.println("edgeList = " + edgeList);

        List vertexList = path.getVertexList();
        System.out.println("vertexList = " + vertexList);
    }

    @Test
    void test3() {
        WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        WeightedMultigraph<Station, DefaultWeightedEdge> timeGraph = new WeightedMultigraph(DefaultWeightedEdge.class);

        new RelationController().initRelations();

        List<Station> stationList = RelationRepository.relations()
            .stream()
            .map(relation -> relation.getStationOne())
            .collect(Collectors.toList());

        List<Station> stationAnotherList = RelationRepository.relations().stream()
            .map(relation -> relation.getStationAnother())
            .collect(Collectors.toList());

        stationList.addAll(stationAnotherList);

        stationList = stationList.stream().distinct().collect(Collectors.toList());

        System.out.println("stationList.size() = " + stationList.size());
        for (Station station : stationList) {
            System.out.println("station = " + station);
        }
    }

    @Test
    void test4() {
        WeightedMultigraph<Station, DefaultWeightedEdge> distanceGraph = new WeightedMultigraph(DefaultWeightedEdge.class);
        new RelationController().initRelations();

        List<Station> stationList = RelationRepository.relations()
            .stream()
            .map(relation -> relation.getStationOne())
            .collect(Collectors.toList());

        List<Station> stationAnotherList = RelationRepository.relations().stream()
            .map(relation -> relation.getStationAnother())
            .collect(Collectors.toList());

        stationList.addAll(stationAnotherList);

        stationList = stationList.stream().distinct().collect(Collectors.toList());

        for (Station station : stationList) {
            distanceGraph.addVertex(station);
        }

        List<Relation> relations = RelationRepository.relations();
        for (Relation relation : relations) {
            Station stationAnother = relation.getStationAnother();
            Station stationOne = relation.getStationOne();
            int distance = relation.getDistance();
            distanceGraph.setEdgeWeight(distanceGraph.addEdge(stationOne, stationAnother), distance);
        }

        DijkstraShortestPath shortestPath = new DijkstraShortestPath(distanceGraph);
        double weight = shortestPath.getPathWeight(new Station(("교대역")), new Station("양재역"));

        assertThat(weight).isEqualTo(4);


        // 최단 경로에 따른 총 시간
        List<DefaultWeightedEdge> edgeList = shortestPath.getPath(new Station(("교대역")), new Station("양재역")).getEdgeList();

        int resultShortestTime = 0;
        for (DefaultWeightedEdge edge : edgeList) {
            String result = edge.toString().trim().replaceAll("[()]", "");
            String[] strings = result.split(" : ");
            String one = strings[0];
            String another = strings[1];
            one = getPureName(one);
            another = getPureName(another);
            Relation relation = RelationRepository.findByNames(one, another);
            System.out.println("relation = " + relation);
            resultShortestTime += relation.getTime();
        }

        assertThat(resultShortestTime).isEqualTo(11);
    }

    private String getPureName(String one) {
        return one.replaceAll("Station\\{name\\=\\'", "").replaceAll("\\'\\}", "");
    }
}
