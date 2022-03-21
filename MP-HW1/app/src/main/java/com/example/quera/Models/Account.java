package com.example.quera.Models;

import java.util.ArrayList;

public class Account {
    public static ArrayList<Account> accounts;
    public static Account loggedInAccount;
    private String username;
    private String password;
    private String name;
    private ArrayList<Classroom> classrooms;
    private ArrayList<Exercise> exercises;

    public void addClassrooms(Classroom classroom) {
        classrooms.add(classroom);
    }

    static {
        accounts = new ArrayList<>();
    }


    public Account(){
        this.username = null;
        this.password = null;
        this.name = null;
        this.classrooms = new ArrayList<>();
    }

    public Account(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
        this.classrooms = new ArrayList<>();
        this.exercises = new ArrayList<>();
        accounts.add(this);
    }


    public String serialize() {
        String activeDeckSerialized;
        if (activeDeck != null) activeDeckSerialized = activeDeck.serialize();
        else activeDeckSerialized = null;
        ArrayList<String> spareCardsDeepSerialized = new ArrayList<>();
        ArrayList<String> decksDeepSerialized = new ArrayList<>();
        for (Card card : this.spareCards)
            spareCardsDeepSerialized.add(card.serialize());
        for (Deck deck : this.decks)
            decksDeepSerialized.add(deck.serialize());
        String spareCardsSerialized = (new Gson()).toJson(spareCardsDeepSerialized);
        String decksSerialized = (new Gson()).toJson(decksDeepSerialized);
        AccountDeepSerialized accountDeepSerialized = new AccountDeepSerialized(this.username, this.password, this.nickname, this.score, this.coin, activeDeckSerialized, spareCardsSerialized, decksSerialized);
        return (new Gson()).toJson(accountDeepSerialized);
    }

    public static Account deserialize(String accountSerialized) {
        AccountDeepSerialized accountDeepSerialized = (new Gson()).fromJson(accountSerialized, AccountDeepSerialized.class);
        Deck activeDeck = Deck.deserialize(accountDeepSerialized.activeDeckSerialized);
        Type collectionType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> spareCardsDeepSerialized = (new Gson()).fromJson(accountDeepSerialized.spareCardsSerialized, collectionType);
        ArrayList<String> decksDeepSerialized = (new Gson()).fromJson(accountDeepSerialized.decksSerialized, collectionType);
        ArrayList<Card> spareCards = new ArrayList<>();
        ArrayList<Deck> decks = new ArrayList<>();
        for (String cardSerialized : spareCardsDeepSerialized) {
            try {
                spareCards.add((new Gson()).fromJson(cardSerialized, MonsterCard.class));
            } catch (Exception e) {
                spareCards.add((new Gson()).fromJson(cardSerialized, MagicCard.class));
            }
        }
        for (String deckSerialized : decksDeepSerialized)
            decks.add(Deck.deserialize(deckSerialized));
        Account output = new Account();
        output.setUsername(accountDeepSerialized.username);
        output.setPassword(accountDeepSerialized.password);
        output.setNickname(accountDeepSerialized.nickname);
        output.setScore(accountDeepSerialized.score);
        output.setCoin(accountDeepSerialized.coin);
        output.setActiveDeck(activeDeck);
        output.setSpareCards(spareCards);
        output.setDecks(decks);
        return output;
    }

    public static String saveAccounts() {
        synchronized (accounts) {
            for (Account account : accounts) {
                String accountFilePath = "src/main/resources/static/accounts/" + account.getUsername() + ".json";
                File accountFile = new File(accountFilePath);
                try {
                    FileWriter writer = new FileWriter(accountFile.getPath(), false);
                    String jsonData = account.serialize();
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse accounts JSON files";
                }
            }
            return "Accounts data saved successfully";
        }
    }

    public static synchronized String initializeAccounts() {
        if (accounts.size() == 0) {
            synchronized (accounts) {
                File accountsDirectory = new File("src/main/resources/static/accounts");
                File[] accountsFiles = accountsDirectory.listFiles();
                if (accountsFiles == null)
                    return "Accounts JSON files missing!";
                for (File file : accountsFiles) {
                    String accountJson;
                    try {
                        accountJson = Files.readString(Paths.get(file.getPath()));
                    } catch (IOException e) {
                        return "JSON files can't be accessed!";
                    }
                    accounts.add(Account.deserialize(accountJson));
                }
                return "Accounts loaded successfully";
            }
        }
        return "";
    }


    public static boolean canRegister(String username, String name, String password){
        if(username == null || name == null || password == null) return false;
        return getAccountByUsername(username) == null;
    }

    public static boolean canLogin(String username, String password) {
        if(Account.getAccountByUsername(username) == null) return false;
        return Account.getAccountByUsername(username).getPassword().equals(password);
    }

    public static boolean isStudent(String username){
        return Student.getStudentByUsername(username) != null;
    }

    public static boolean isMaster(String username){
        return Master.getMasterByUsername(username) != null;
    }

    //Getters
    public static Account getAccountByUsername(String username) {
        for (Account account : accounts)
            if (account.getUsername().equals(username))
                return account;
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Classroom> getClassrooms(){
        return this.classrooms;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Exercise getExercisesByName(String exerciseName) {
        return Exercise.getExercisesByName(exerciseName);
    }

    public void addExercises(Exercise exercise) {
        this.exercises.add(exercise);
    }
}

class AccountDeepSerialized {
    protected String username;
    protected String password;
    protected String name ;
    protected String SorM;
    protected String attribute;
    protected String classroomsSerialized;

    public AccountDeepSerialized(String username, String password, String name, String classroomsSerialized) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.classroomsSerialized = classroomsSerialized;
    }
}
