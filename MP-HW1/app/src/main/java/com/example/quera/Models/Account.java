package com.example.quera.Models;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Account {
    public static ArrayList<Account> accounts;
    public static Account loggedInAccount;
    private String username;
    private String password;
    private String name;
    public ArrayList<Classroom> classrooms;
    public ArrayList<Exercise> exercises;

    public void addClassrooms(Classroom classroom) {
        classrooms.add(classroom);
    }

    static {
        accounts = new ArrayList<>();
    }

    public Account() {
        this.username = null;
        this.password = null;
        this.name = null;
        this.classrooms = new ArrayList<>();
    }

    public Account(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.classrooms = new ArrayList<>();
        this.exercises = new ArrayList<>();
        accounts.add(this);
    }


    public String serialize() {
        String sorm;
        String attribute;

        if (this instanceof Master) {
            sorm = "m";
            attribute = ((Master) this).getUniversity();
        } else {
            sorm = "s";
            attribute = ((Student) this).getStudentID();
        }

//        ArrayList<String> classroomsDeepSerialized = new ArrayList<>();
//        for (Classroom classroom : this.classroom)
//            classroomsDeepSerialized.add(classroom.serialize());
//        String classroomsSerialized = (new Gson()).toJson(classroomsDeepSerialized);

        AccountDeepSerialized accountDeepSerialized = new AccountDeepSerialized(this.username, this.password, this.name, sorm, attribute);
        return (new Gson()).toJson(accountDeepSerialized);
    } //complete

    public static Account deserialize(String accountSerialized) {
        AccountDeepSerialized accountDeepSerialized = (new Gson()).fromJson(accountSerialized, AccountDeepSerialized.class);

//        ArrayList<String> classroomsDeepSerialized = (new Gson()).fromJson(accountDeepSerialized.classroomsSerialized, collectionType);
//        ArrayList<Classroom> classrooms = new ArrayList<>();
//        for (String classroomSerialized : classroomsDeepSerialized)
//            classrooms.add(Classroom.deserialize(classroomSerialized));

        Account output;
        if (accountDeepSerialized.SorM.equals("m")) {
            output = new Master(accountDeepSerialized.username, accountDeepSerialized.password, accountDeepSerialized.name, accountDeepSerialized.attribute);
        } else {
            output = new Student(accountDeepSerialized.username, accountDeepSerialized.password, accountDeepSerialized.name, accountDeepSerialized.attribute);
        }
        return output;
    } //complete

    public static String saveAccounts() {
        synchronized (accounts) {
            for (Account account : accounts) {
                String accountFilePath = "src/main/resources/accounts/" + account.getUsername() + ".json";
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
    } //complete

    public static synchronized String initializeAccounts() {
        if (accounts.size() == 0) {
            synchronized (accounts) {
                File accountsDirectory = new File("src/main/resources/accounts");
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
    } //complete


    public static boolean canRegister(String username, String name, String password) {
        if (username == null || name == null || password == null) return false;
        return getAccountByUsername(username) == null;
    }

    public static boolean canLogin(String username, String password) {
        if (Account.getAccountByUsername(username) == null) return false;
        return Account.getAccountByUsername(username).getPassword().equals(password);
    }

    public static boolean isStudent(String username) {
        return Student.getStudentByUsername(username) != null;
    }

    public static boolean isMaster(String username) {
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

    public ArrayList<Classroom> getClassrooms() {
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

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
}

class AccountDeepSerialized {
    protected String username;
    protected String password;
    protected String name;
    protected String SorM;
    protected String attribute;
//    protected String classroomsSerialized;

//    public AccountDeepSerialized(String username, String password, String name, String sorM, String attribute, String classroomsSerialized) {
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        SorM = sorM;
//        this.attribute = attribute;
//        this.classroomsSerialized = classroomsSerialized;
//    }

    public AccountDeepSerialized(String username, String password, String name, String sorM, String attribute) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.SorM = sorM;
        this.attribute = attribute;
    }
}
