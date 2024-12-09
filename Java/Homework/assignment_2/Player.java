package Java.Homework.assignment_2;

public class Player {


    private String name;
    private String position;
    private double passingReceivingRushingYards;
    private boolean isDefensive;

    @SuppressWarnings("unused")
    private Player() {}

    public Player(String name, String position, double yards, boolean defensive) {

        this.name = name;
        this.position = position;
        this.passingReceivingRushingYards = yards;
        this.isDefensive = defensive;
    }

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    public double getPassingReceivingRushingYards() {
        return this.passingReceivingRushingYards;
    }

    public boolean hasDefensivePosition() {

        return this.isDefensive;

    }

    public boolean hasOffensivePosition() {

        return !this.isDefensive;

    }


}
