package subway.view;

import subway.dto.MoveDto;
import subway.dto.ResultDto;

public class OutputView {
	public static void printResult(ResultDto resultDto) {
		System.out.println("moveDto = " + resultDto);
	}
}
