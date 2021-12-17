package subway.controller;

import subway.domain.Line;
import subway.domain.LineRepository;

public class LineController {
	public void initLines() {
		LineRepository.addLine(new Line("2호선"));
		LineRepository.addLine(new Line("3호선"));
		LineRepository.addLine(new Line("신분당선"));
	}
}
