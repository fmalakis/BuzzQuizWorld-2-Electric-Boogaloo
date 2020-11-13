import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<RoundI> rounds;
    private QuestionManager questionManager;
    private Parser parser;
    private HighscoreManager highscoreManager;

    public Game() {
        players = new ArrayList<>();
        rounds = new ArrayList<>();
        questionManager = new QuestionManager();
        questionManager.createQuestions();
        parser = new Parser();
        highscoreManager = new HighscoreManager();
    }

    public Game(ArrayList<Player> players, ArrayList<RoundI> rounds, Parser parser) {
        this.players = players;
        this.rounds = rounds;
        this.parser = parser;
    }

    public void initializeGamePlay() {
        players.add(new Player());
//        players.add(new Player());
//        players.add(new Player());
        rounds.add(new StandardRound(1, players, questionManager, parser));
        rounds.add(new BettingRound(1, players, questionManager, parser));
        rounds.add(new StopTheClockRound(1, players, questionManager, parser));
        rounds.add(new QuickAnswerRound(1, players, questionManager, parser));
        rounds.add(new ThermometerRound(players, questionManager, parser));
    }

    public void play() {
        int roundsCounter = 1;
        for (RoundI round : rounds) {
            System.out.println();
            System.out.println("**********" + " Round " + roundsCounter + " **********");
            System.out.printf(round.getDescription());
            System.out.print("Press enter to start round ");
            parser.getEnter();
            while (! round.isOver()) {
                round.askQuestion();
                round.readAnswers();
                round.giveCredits();
            }
            System.out.println();
            System.out.println("End of Round " + (roundsCounter++) + ".");
            for (Player player : players) {
                player.printScore();
            }
        }
        highscoreManager.updateHighscores(players);
    }
}
