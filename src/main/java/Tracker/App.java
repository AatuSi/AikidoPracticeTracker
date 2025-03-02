package Tracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    private int totalTrainingTime = 0;
    private List<TrainingSession> trainingSessions;
    private TrainingSession youngest;
    private TrainingSession oldest;
    boolean running = true;

    public App() {
        this.trainingSessions = new ArrayList<>();
    }

    public List<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    public int getTotalTrainingTime() {
        return totalTrainingTime;
    }

    public TrainingSession getYoungest() {
        return youngest;
    }

    public TrainingSession getOldest() {
        return oldest;
    }



    public void addTrainingSession(String dateString, int duration) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateString);
            TrainingSession session = new TrainingSession(date, duration);
            if (trainingSessions.isEmpty()) {
                youngest = session;
                oldest = session;
            } else {
                if (session.getDate().before(youngest.getDate())) {
                    youngest = session;
                }
                if (session.getDate().after(oldest.getDate())) {
                    oldest = session;
                }
            }
            totalTrainingTime += duration;
            trainingSessions.add(session);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            e.printStackTrace();
        }
    }

    public void viewTotalTrainingTime() {
        System.out.println("Total training time: " + totalTrainingTime + " minutes.");
    }

    public boolean checkEligibility() {
        if (trainingSessions.size() >= 100) {
            System.out.println("Congratulations! You have trained for more than 100 sessions and are eligible for Kyu graduation.");
            return true;
        }

        if (youngest != null && oldest != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(youngest.getDate());
            cal.add(Calendar.MONTH, 6);
            Date sixMonthsAfterYoungest = cal.getTime();

            if (oldest.getDate().after(sixMonthsAfterYoungest)) {
                System.out.println("Congratulations! You have trained for more than 6 months and are eligible for Kyu graduation.");
                return true;
            } else {
                System.out.println("You have not trained enough yet, and are not eligible for Kuy graduation.");
                return false;
            }
        } else {
            System.out.println("You have to train first before you are eligible for Kyu graduation.");
            return false;
        }
    }

    public void viewMenu() {
        while (running) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("--- Aikido Training Tracker ---");
            System.out.println("1. Add training session");
            System.out.println("2. View total training time");
            System.out.println("3. Check eligibility for Kyu graduation");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    String dateString = scanner.nextLine();
                    System.out.print("Enter duration (minutes): ");
                    int duration = scanner.nextInt();
                    addTrainingSession(dateString, duration);
                    break;
                case 2:
                    viewTotalTrainingTime();
                    break;
                case 3:
                    checkEligibility();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


}
