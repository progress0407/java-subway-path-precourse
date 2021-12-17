package subway;

import java.util.Scanner;

import subway.controller.LineController;
import subway.controller.RelationController;
import subway.controller.StationController;
import subway.domain.RelationRepository;

public class Application {
    private static LineController lineController;
    private static StationController stationController;
    private static RelationController relationController;


    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        init();
        run();
    }

    private static void run() {
        relationController.choiceMenu();
    }

    private static void init() {
        lineController = new LineController();
        stationController = new StationController();
        relationController = new RelationController();

        lineController.initLines();
        stationController.initStations();
        relationController.initRelations();
    }
}
