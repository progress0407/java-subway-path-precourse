package subway.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import subway.domain.Line;
import subway.domain.Relation;
import subway.domain.RelationRepository;
import subway.domain.Station;
import subway.dto.MoveDto;
import subway.dto.ResultDto;
import subway.view.InputView;
import subway.view.OutputView;

public class RelationController {

	private static final Map<String, Function<MoveDto, ResultDto>> choiceMap = new LinkedHashMap<>();

	static {
		choiceMap.put("DISTANCE", RelationController::getShortestDistance);
		choiceMap.put("Time", RelationController::getShortestTime);
	}

	public void initRelations() {

		RelationRepository.addRelation(new Relation(new Line("2호선"), new Station("교대역"), new Station("강남역"), 2, 3));
		RelationRepository.addRelation(new Relation(new Line("2호선"), new Station("강남역"), new Station("역삼역"), 2, 3));

		RelationRepository.addRelation(new Relation(new Line("3호선"), new Station("교대역"), new Station("남부터미널역"), 3, 2));
		RelationRepository.addRelation(new Relation(new Line("3호선"), new Station("남부터미널역"), new Station("양재역"), 6, 5));
		RelationRepository.addRelation(new Relation(new Line("3호선"), new Station("양재역"), new Station("매봉역"), 1, 1));

		RelationRepository.addRelation(new Relation(new Line("신분당선"), new Station("강남역"), new Station("양재역"), 2, 8));
		RelationRepository.addRelation(new Relation(new Line("신분당선"), new Station("양재역"), new Station("양재시민의숲역"), 10, 3));
	}

	public void choiceMenu() {
		MoveDto moveDto = InputView.getMainChoice();
		ResultDto resultDto = process(moveDto);
		OutputView.printResult(resultDto);
	}

	public ResultDto process(MoveDto moveDto) {
		String mode = moveDto.getMode();
		ResultDto resultDto = choiceMap.get(mode).apply(moveDto);
		return resultDto;
	}

	public static ResultDto getShortestDistance(MoveDto moveDto) {
		String sourceStationName = moveDto.getSourceStationName();
		String targetStationName = moveDto.getTargetStationName();
		ResultDto resultDto = RelationRepository.getResultByShortestDistance(sourceStationName,
			targetStationName);
		return resultDto;
	}

	public static ResultDto getShortestTime(MoveDto moveDto) {
		String sourceStationName = moveDto.getSourceStationName();
		String targetStationName = moveDto.getTargetStationName();
		ResultDto resultDto = RelationRepository.getResultByShortestTime(sourceStationName,
			targetStationName);
		return resultDto;
	}
}
