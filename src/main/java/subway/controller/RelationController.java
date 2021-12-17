package subway.controller;

import subway.domain.Line;
import subway.domain.Relation;
import subway.domain.RelationRepository;
import subway.domain.Station;

public class RelationController {
	public void initRelations() {

		RelationRepository.addRelation(new Relation(new Line("2호선"), new Station("교대역"), new Station("강남역"), 2, 3));
		RelationRepository.addRelation(new Relation(new Line("2호선"), new Station("강남역"), new Station("역삼역"), 2, 3));

		RelationRepository.addRelation(new Relation(new Line("3호선"), new Station("교대역"), new Station("남부터미널역"), 3, 2));
		RelationRepository.addRelation(new Relation(new Line("3호선"), new Station("남부터미널역"), new Station("양재역"), 6, 5));
		RelationRepository.addRelation(new Relation(new Line("3호선"), new Station("양재역"), new Station("매봉역"), 1, 1));

		RelationRepository.addRelation(new Relation(new Line("신분당선"), new Station("강남역"), new Station("양재역"), 2, 8));
		RelationRepository.addRelation(new Relation(new Line("신분당선"), new Station("양재역"), new Station("양재시민의숲역"), 10, 3));
	}

}
