package subway.view;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import subway.dto.MoveDto;

public class InputView {
	public static final String INPUT_MAIN_MENU_CHOICE = "## 메인 화면\n1. 경로 조회\nQ. 종료";
	public static final String INPUT_SUB_MENU_CHOICE = "## 경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기";
	private static final Scanner scanner = new Scanner(System.in);
	public static final String CHOICE_EXIT = "Q";
	public static final String CHOICE_GET_BACK = "B";

	public static Map<String, Supplier<MoveDto>> mainChoiceMap = new LinkedHashMap<>();
	public static Map<String, BiFunction<String, String, MoveDto>> subChoiceMap = new LinkedHashMap<>();

	static {
		mainChoiceMap.put("1", InputView::toSubMenu);

		subChoiceMap.put("1", InputView::toShortestDistance);
		subChoiceMap.put("2", InputView::toShortestTime);
	}

	public static MoveDto getMainChoice() {
		System.out.println(INPUT_MAIN_MENU_CHOICE);
		System.out.println();
		String input = scanner.nextLine();
		if (input.equals(CHOICE_EXIT)) {
			return null;
		}
		MoveDto moveDto = mainChoiceMap.get(input).get();

		return moveDto;
	}

	public static MoveDto toSubMenu()  {
		System.out.println(INPUT_SUB_MENU_CHOICE);
		String input = scanner.nextLine();
		if (input.equals(CHOICE_GET_BACK)) {
			return null;
		}

		System.out.println("## 출발역을 입력하세요.");
		String inputSourceStationName = scanner.nextLine();

		System.out.println("## 도착역을 입력하세요.");
		String inputTargetStationName = scanner.nextLine();

		MoveDto moveDto = subChoiceMap.get(input).apply(inputSourceStationName, inputTargetStationName);

		return moveDto;
	}

	private static MoveDto toShortestDistance(String inputSourceStationName, String inputTargetStationName) {
		return new MoveDto("DISTANCE", inputSourceStationName, inputTargetStationName);
	}

	private static MoveDto toShortestTime(String inputSourceStationName, String inputTargetStationName) {
		return new MoveDto("DISTANCE",inputSourceStationName, inputTargetStationName);
	}
}
