package nl.robinlaugs.statistics;

public class StatisticsController implements Statistics {

    private final StatisticsListener listener;

    public StatisticsController(StatisticsListener listener) {
        this.listener = listener;
    }

    @Override
    public void addStatistic(String statistic) {
        listener.onStatisticReceived(statistic);
    }

}
