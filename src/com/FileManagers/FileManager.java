package com.FileManagers;

import com.HighscoreComparator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Class which handles all the File interactions of our game
 *
 * @authot Fotios-Dimitrios Malakis
 */
public class FileManager {

    private File highScoreFile, questionsFile;
    private String highscoreFileName, questionsFileName;

    /**
     * Creates a com.Managers.FileManager object and initializes its two File fields to the correct files. If the user has deleted or has is playing
     * for the first time, creates the 'highscores.txt' file.
     * @param highscoreFileName the name of the .txt file containing the highscores
     * @param questionsFileName the name of the .txt file containing the questions
     * @throws IOException throws IOException if an error arises with the file stream
     */
    public FileManager(String highscoreFileName, String questionsFileName) throws IOException {
        this.highscoreFileName = highscoreFileName;
        this.questionsFileName = questionsFileName;

        highScoreFile = new File(this.highscoreFileName);
        questionsFile = new File(this.questionsFileName);
        try {
            if (!highScoreFile.exists())
                highScoreFile.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the questions.txt file and fetches each line of it
     *
     * @return an <code>ArrayList&#60;String&#62;</code> containing the line strings of questions.txt
     */
    public ArrayList<String> getQuestionsFromFile() {
        ArrayList<String> questions = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(questionsFile);
            while(fileReader.hasNextLine()) {
                String questionString = fileReader.nextLine();
                questions.add(questionString);
            }
            fileReader.close();
        }   catch(IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    /**
     * Parses the highscores.txt file and fetches the Name:Score pairs from it
     *
     * @return a <code>HashMap&#60;String, Integer&#62;</code>  containing 'User':'score' key/pair values
     */
    public LinkedHashMap<String, Integer> getHighscoresFromFile() {
        LinkedHashMap<String, Integer> highscores = new LinkedHashMap<>();
        if (highScoreFile.length() == 0)
            return highscores;
        try {
            Scanner fileReader = new Scanner(highScoreFile);
            while(fileReader.hasNextLine()) {
                String temp = fileReader.nextLine();
                StringTokenizer highscoreLine = new StringTokenizer(temp,":");
                highscores.put(highscoreLine.nextToken(), Integer.parseInt(highscoreLine.nextToken()));
            }
            fileReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return highscores;
    }

    /**
     * Sorts the values (scores) of the given HashMap using a temporary ArrayList&#60;Map.Entry&#60;String, Integer&#62;&#62; initialized
     * with the <code>newHighscores</code> entry set after executing <code>Collections.sort()</code> with the custom comparator method for
     * highscores {@link HighscoreComparator}, and then prints the sorted highscores in 'highscores.txt' (descending order)
     *
     * @param newHighscores a <code>HashMap&#60;String, Integer&#62; containing 'Name':'Score' keypair values</code>
     */
    protected void updateHighscoresOnFile(LinkedHashMap<String, Integer> newHighscores) {
        try {
            FileWriter fileWriter = new FileWriter(highScoreFile);
            ArrayList<Map.Entry<String,Integer>> highscoreList = new ArrayList<>(newHighscores.entrySet());
            Collections.sort(highscoreList, new HighscoreComparator());
            LinkedHashMap<String, Integer> finalHighscores = new LinkedHashMap<>();
            for(Map.Entry<String, Integer> i: highscoreList)
                finalHighscores.put(i.getKey(), i.getValue());
            for(String i: finalHighscores.keySet())
                fileWriter.write(i + ":" + finalHighscores.get(i) + "\n");
            fileWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns the highscore file name
     * @return a String with the highscore file name
     */
    public String getHighscoreFileName() {
        return highscoreFileName;
    }

    /**
     * Returns the questions file name
     * @return a String containing the questions file name
     */
    public String getQuestionsFileName() {
        return questionsFileName;
    }

    /**
     * Returns the highscore file
     * @return highscore file
     */
    public File getHighScoreFile() {
        return highScoreFile;
    }

    /**
     * Returns the questions file
     * @return question file
     */
    public File getQuestionsFile() {
        return questionsFile;
    }
}
