package game;

import game.events.*;
import java.util.Random;
import java.util.Scanner; 

public class Game {
    private static final int TOTAL_DAYS = 30;
    private final Player player = new Player(1200, 100, 0, 70);
    private final Random rng = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private final Event[] eventPool = {
        new ExamWeekEvent(), new AllowanceDelayEvent(), new PowerOutageEvent(),
        new RainyDayEvent(), new GoodDayEvent()
    };
    private int day = 1;

    @SuppressWarnings("ConvertToTryWithResources")
    public void start() {
        showWelcome();
        while (day <= TOTAL_DAYS && !isGameOver()) {
            System.out.printf("\n----- Day %d/%d -----\n", day, TOTAL_DAYS);
            printStats();
            if (day % 7 == 1 && day > 1) player.resetWeeklyCounters();
            triggerRandomEvent();
            handlePlayerAction();
            applyDailyEffects();
            day++;
            if (day <= TOTAL_DAYS && !isGameOver()) {
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
            }
        }
        showGameResult();
        scanner.close();
    }

    private void showWelcome() {
        System.out.println("========================================");
        System.out.println("           SEMESTRAL BREAKDOWN");
        System.out.println("========================================");
        System.out.println("Survive 30 days of college life!");
        System.out.println("Keep: Grade >= 70, Money > 0, Energy > 0");
        System.out.print("Press Enter to start...");
        scanner.nextLine();
    }

    private void printStats() {
        System.out.printf("Money: $%.0f | Energy: %d | Stress: %d | Grade: %d\n",
            player.getMoney(), player.getEnergy(), player.getStress(), player.getGrade());
        player.showWeeklyProgress();
    }

    private void triggerRandomEvent() {
        double chance = 0.25;
        if (day >= 20 && day <= 23) chance = 0.4;
        if (rng.nextDouble() < chance) {
            Event event = eventPool[rng.nextInt(eventPool.length)];
            System.out.println("\nEVENT: " + event.getName());
            event.apply(player);
            clampStats();
        }
    }

    private void handlePlayerAction() {
        boolean valid = false;
        while (!valid) {
            showActions();
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                valid = processAction(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number 1-4");
            }
        }
    }

    private void showActions() {
        System.out.println("\nDAILY ACTIONS:");
        System.out.println("1. Study   (+1 grade, +6 stress, -8 energy)");
        System.out.println("2. Work    (+$300, +5 stress, -2 grade, -7 energy)");
        System.out.println("3. Rest    (+18 energy, -8 stress)");
        System.out.println("4. Hang Out (-$120, +6 energy, -9 stress)");
        System.out.print("Choose: ");
    }

    private boolean processAction(int choice) {
        switch (choice) {
            case 1 -> {
                if (!player.canStudy()) {
                    System.out.println("Study limit reached this week!");
                    return false;
                }
                System.out.println("Studying...");
                player.modifyGrade(1);
                player.modifyStress(6);
                player.modifyEnergy(-8);
                player.incrementStudy();
                return true;
            }
            case 2 -> {
                if (!player.canWork()) {
                    System.out.println("Work limit reached this week!");
                    return false;
                }
                System.out.println("Working...");
                player.modifyMoney(300);
                player.modifyStress(5);
                player.modifyGrade(-2);
                player.modifyEnergy(-7);
                player.incrementWork();
                return true;
            }
            case 3 -> {
                if (!player.canRest()) {
                    System.out.println("Rest limit reached this week!");
                    return false;
                }
                System.out.println("Resting...");
                player.modifyEnergy(18);
                player.modifyStress(-8);
                player.incrementRest();
                return true;
            }
            case 4 -> {
                if (!player.canHangout()) {
                    System.out.println("Hang out limit reached this week!");
                    return false;
                }
                if (player.getMoney() >= 120) {
                    System.out.println("Hanging out...");
                    player.modifyMoney(-120);
                    player.modifyEnergy(6);
                    player.modifyStress(-9);
                    player.incrementHangout();
                    return true;
                } else {
                    System.out.println("Not enough money!");
                    return false;
                }
            }
            default -> {
                System.out.println("Please enter 1-4");
                return false;
            }
        }
    }

    private void applyDailyEffects() {
        if (player.getEnergy() > 80 && player.getStress() > 0) {
            player.modifyStress(-2);
        }
        if (player.getEnergy() < 20) {
            player.modifyGrade(-1);
            System.out.println("Exhausted! -1 Grade");
        }
    }

    private void clampStats() {
        player.setEnergy(Math.min(100, Math.max(0, player.getEnergy())));
        player.setStress(Math.min(100, Math.max(0, player.getStress())));
        player.setGrade(Math.min(100, Math.max(0, player.getGrade())));
    }

    private boolean isGameOver() {
        if (player.getEnergy() <= 0) {
            System.out.println("COLLAPSED FROM EXHAUSTION! Energy <= 0");
            return true;
        }
        if (player.getMoney() <= 0) {
            System.out.println("BANKRUPT! Money <= 0");
            return true;
        }
        if (player.getGrade() < 70) {
            System.out.println("FAILED! Grade < 70");
            return true;
        }
        return false;
    }

    private void showGameResult() {
        System.out.println("\n========================================");
        System.out.println("          SEMESTER COMPLETED!");
        System.out.println("========================================");
        if (player.getGrade() >= 70 && player.getMoney() > 0 && player.getEnergy() > 0) {
            System.out.println("SUCCESS! You survived the semester!");
            System.out.printf("Final - Money: $%.0f, Energy: %d%%, Stress: %d%%, Grade: %d%%\n",
                player.getMoney(), player.getEnergy(), player.getStress(), player.getGrade());
            int score = (int)(player.getMoney()) + player.getEnergy() + (100 - player.getStress()) + player.getGrade();
            System.out.println("Total Score: " + score);
        } else {
            System.out.println("You didn't meet all requirements.");
        }
    }
}