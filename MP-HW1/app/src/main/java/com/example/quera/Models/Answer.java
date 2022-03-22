package com.example.quera.Models;

import java.util.ArrayList;

public class Answer {
    private static int counter = 0;

    private static ArrayList<Answer> answers = new ArrayList<>();
    private int id;
    private Student student;
    private int score;
    private String answer;
    private Classroom classroom;
    private Exercise exercise;

    public Answer() {
        counter++;
        answers.add(this);
    }

    public Answer(Student student, int score, String answer, Classroom classroom, Exercise exercise) {
        this.exercise = exercise;
        this.classroom = classroom;
        this.student = student;
        this.score = score;
        this.answer = answer;
        this.id = counter;
        counter++;
        answers.add(this);
    }

    public String serialize() {
        AnswerDeepSerialized answerDeepSerialized = new AnswerDeepSerialized(this.score, this.student.username, this.exercise.id);
        return (new Gson()).toJson(answerDeepSerialized);
    }

    public static Answer deserialize(String answerSerialized) {
        AnswerDeepSerialized answerDeepSerialized = (new Gson()).fromJson(answerSerialized, AnswerDeepSerialized.class);
        Answer output = new Answer();
        output.setScore(answerDeepSerialized.score);
        output.setStudent(Student.getStudentByUsername(answerDeepSerialized.studentUsername));
        output.setExercise(Exercise.getExercisesById(answerDeepSerialized.exerciseID));
        Exercise.getExercisesById(answerDeepSerialized.exerciseID).answers.add(output);
        return output;
    }

    public static String saveAnswers() {
        synchronized (answers) {
            for (Answer answer : answers) {
                String answerFilePath = "src/main/resources/answers/" + answer.id + ".json";
                File answerFile = new File(answerFilePath);
                try {
                    FileWriter writer = new FileWriter(answerFile.getPath(), false);
                    String jsonData = answer.serialize();
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse answers JSON files";
                }
            }
            return "Answers data saved successfully";
        }
    } //complete

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static synchronized String initializeAnswers() {
        if (answers.size() == 0) {
            synchronized (answers) {
                File answersDirectory = new File("src/main/resources/answers");
                File[] answersFiles = answersDirectory.listFiles();
                if (answersFiles == null)
                    return "Answers JSON files missing!";
                for (File file : answersFiles) {
                    String answerJson;
                    try {
                        answerJson = Files.readString(Paths.get(file.getPath()));
                    } catch (IOException e) {
                        return "JSON files can't be accessed!";
                    }
                    answers.add(Answer.deserialize(answerJson));
                }
                return "Answers loaded successfully";
            }
        }
        return "";
    } //complete


    public void setScore(int score) {
        this.score = score;
    }

    public static Answer getAnswerByStudentName(String studentName) {
        for (Answer answer : answers) {
            if (answer.getStudent().getName().equals(studentName))
                return answer;
        }
        return null;
    }

    public static void setAnswers(ArrayList<Answer> answers) {
        Answer.answers = answers;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return this.student;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getScore() {
        if (this.score == -1)
            return "No Score";
        return String.valueOf(this.score);
    }

    public static ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void editAnswer(String answer) {
        this.answer = answer;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}

class AnswerDeepSerialized {
    protected int score;
    protected String studentUsername;
    protected String exerciseID;

    public AnswerDeepSerialized(int score, String studentUsername, String exerciseID) {
        this.score = score;
        this.studentUsername = studentUsername;
        this.exerciseID = exerciseID;
    }
}
















