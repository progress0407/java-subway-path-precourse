package subway.view;

import java.util.List;

import subway.dto.MoveDto;
import subway.dto.ResultDto;

public class OutputView {

	public static final String OUTPUT_RESULT = "## 조회 결과";
	public static final String INFO_PREFIX_MESSAGE = "[INFO] ";
	public static final String INFO_LINE = INFO_PREFIX_MESSAGE + "---";
	public static final String OUTPUT_DISTANCE = INFO_PREFIX_MESSAGE + "총 거리: %dkm\n";
	public static final String OUTPUT_TIME = INFO_PREFIX_MESSAGE + "총 소요 시간: %d분\n";

	/**
	 * ## 조회 결과
	 * [INFO] ---
	 * [INFO] 총 거리: 6km
	 * [INFO] 총 소요 시간: 14분
	 * [INFO] ---
	 * [INFO] 교대역
	 * [INFO] 강남역
	 * [INFO] 양재역
	 */
	public static void printResult(ResultDto resultDto) {
		printSummary(resultDto);
		printStationList(resultDto);
	}

	private static void printSummary(ResultDto resultDto) {
		System.out.println(OUTPUT_RESULT);
		System.out.println(INFO_LINE);
		System.out.printf(OUTPUT_DISTANCE, resultDto.getShortestDistance());
		System.out.printf(OUTPUT_TIME, resultDto.getShortestTime());
		System.out.println(INFO_LINE);
	}

	private static void printStationList(ResultDto resultDto) {
		List<String> stationNames = resultDto.getStationNames();
		for (String stationName : stationNames) {
			System.out.println(INFO_PREFIX_MESSAGE + stationName);
		}
	}
}
