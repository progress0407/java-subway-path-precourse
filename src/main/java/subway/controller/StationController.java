package subway.controller;

import subway.domain.Station;
import subway.domain.StationRepository;

public class StationController {
	public void initStations() {
		StationRepository.addStation(new Station("교대역"));
		StationRepository.addStation(new Station("강남역"));
		StationRepository.addStation(new Station("역삼역"));
		StationRepository.addStation(new Station("남부터미널역"));
		StationRepository.addStation(new Station("양재역"));
		StationRepository.addStation(new Station("양재시민의숲역"));
		StationRepository.addStation(new Station("매봉역"));
	}
}
