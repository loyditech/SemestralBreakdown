package game;

import game.events.*;
import java.util.Random;
import java.util.Scanner; 

public class Game {
    private static final int TOTAL_DAYS = 30;
    private final Player player = new Player(300, 100, 0, 70); // Start at 70% (close but manageable)
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
            clampStats();
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
        System.out.println("░█▀▀░█▀▀░█▄█░█▀▀░█▀▀░▀█▀░█▀▄░█▀█░█░░░░░█▀▄░█▀▄░█▀▀░█▀█░█░█░█▀▄░█▀█░█░█░█▀█");
        System.out.println("░▀▀█░█▀▀░█░█░█▀▀░▀▀█░░█░░█▀▄░█▀█░█░░░░░█▀▄░█▀▄░█▀▀░█▀█░█▀▄░█░█░█░█░█▄█░█░█");
        System.out.println("░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀░▀░▀▀▀░░░▀▀░░▀░▀░▀▀▀░▀░▀░▀░▀░▀▀░░▀▀▀░▀░▀░▀░▀");
        System.out.println();
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
        

        System.out.println("\nWELCOME STUDENT!");
        System.out.println("Survive 30 days of college life!");
        System.out.println("Starting Stats: $300, 100 Energy, 0 Stress, 70% Grade");
        System.out.println("Goal: Reach Grade >= 75% to PASS, Money > 0, Energy > 0, Stress < 100");
        System.out.println("WARNING: Starting grade is 70% - you need to improve to pass!");
        System.out.println("PLEASE READ IT CAREFULLY (THERE ARE RANDOM EVENT on RANDOM DAYS)");
        System.out.println("Weekly Limits: Study 4x, Work 3x, Hangout 3x, Rest 5x");
        System.out.print("Press Enter to start...");
        scanner.nextLine();
    }

    private void printStats() {
        System.out.printf("Money: $%.0f | Energy: %d | Stress: %d | Grade: %d%%\n",
            player.getMoney(), player.getEnergy(), player.getStress(), player.getGrade());
        player.showWeeklyProgress();
        
        // Grade status
        if (player.getGrade() >= 75) {
            System.out.println("GOOD: You are currently PASSING!");
        } else if (player.getGrade() >= 72) {
            System.out.println("WARNING: Grade is close to failing (75% to pass)!");
        } else if (player.getGrade() >= 68) {
            System.out.println("DANGER: Grade is low! Study more!");
        } else {
            System.out.println("CRITICAL: You are FAILING! Study immediately!");
        }
        
        // Stress warnings
        if (player.getStress() >= 80) {
            System.out.println("CRITICAL WARNING: Extreme stress! Risk of mental breakdown!");
        } else if (player.getStress() >= 60) {
            System.out.println("WARNING: High stress level! Consider resting or hanging out.");
        }
        
        if (player.getEnergy() < 30) {
            System.out.println("WARNING: Low energy! You need rest.");
        }
    }

    private void triggerRandomEvent() {
        double chance = 0.2;
        if (day >= 15 && day <= 20) chance = 0.3;
        if (day >= 21 && day <= 30) chance = 0.35;
        
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
        System.out.println("1. Study   (+2% grade, +12 stress, -12 energy, -$50)");
        System.out.println("2. Work    (+$200, +15 stress, -1% grade, -15 energy)");
        System.out.println("3. Rest    (+ 15 energy, -5 stress , -$30)");
        System.out.println("4. Hang Out (-$80, -5 energy, -10 stress)");
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
                // Check if grade would exceed 95 before modifying
                if (player.getGrade() < 95) {
                    player.modifyGrade(2); 
                } else {
                    System.out.println("You've reached the maximum grade possible (95%)!");
                }
                player.modifyStress(12);
                player.modifyEnergy(-12);
                player.modifyMoney(-50);
                player.incrementStudy();
                return true;
            }
            case 2 -> {
                if (!player.canWork()) {
                    System.out.println("Work limit reached this week!");
                    return false;
                }
                System.out.println("Working...");
                player.modifyMoney(200);
                player.modifyStress(15);
                player.modifyGrade(-1);
                player.modifyEnergy(-15);
                player.incrementWork();
                return true;
            }
            case 3 -> {
                if (!player.canRest()) {
                    System.out.println("Rest limit reached this week!");
                    return false;
                }
                System.out.println("Resting...");
                player.modifyEnergy(15);
                player.modifyStress(-5);
                player.modifyMoney(30);
                player.incrementRest();
                return true;
            }
            case 4 -> {
                if (!player.canHangout()) {
                    System.out.println("Hang out limit reached this week!");
                    return false;
                }
                if (player.getMoney() >= 80) {
                    System.out.println("Hanging out...");
                    player.modifyMoney(-80);
                    player.modifyEnergy(-5);
                    player.modifyStress(-10);
                    player.incrementHangout();
                    return true;
                } else {
                    System.out.println("Not enough money! Need $80 to hang out.");
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
        // SOFTER stress effects
        if (player.getStress() >= 90) {
            player.modifyEnergy(-6); 
            player.modifyGrade(-1); 
            System.out.println("CRITICAL: Mental health crisis! Energy -6, Grade -1%");
        } else if (player.getStress() >= 75) {
            player.modifyEnergy(-3); 
            System.out.println("Severe stress affecting performance! Energy -3");
        } else if (player.getStress() >= 60) {
            player.modifyEnergy(-1); 
            System.out.println("Stress is draining your energy! Energy -1");
        }
        
        // Improved energy effects
        if (player.getEnergy() > 85 && player.getStress() > 0) {
            player.modifyStress(-3);
            System.out.println("Well-rested! Stress naturally decreases.");
        }
        if (player.getEnergy() < 15) {
            player.modifyGrade(-1); // Reduced from -2
            System.out.println("Extreme exhaustion! Grade -1%");
        } else if (player.getEnergy() < 25) {
            player.modifyGrade(0); // No penalty now
            System.out.println("Exhausted! Focus is difficult.");
        }
        
        // Mid-game motivation boost if improving
        if (day == 15 && player.getGrade() >= 72) {
            System.out.println("Mid-semester motivation! +10 Energy, -10 Stress");
            player.modifyEnergy(10);
            player.modifyStress(-10);
        }
    }

    private void clampStats() {
        player.setEnergy(Math.min(100, Math.max(0, player.getEnergy())));
        player.setStress(Math.min(100, Math.max(0, player.getStress())));
        player.setGrade(Math.min(95, Math.max(0, player.getGrade())));
    }

    private boolean isGameOver() {
        // ONLY check game over at the END of the semester, not during
        if (day > TOTAL_DAYS) {
            if (player.getGrade() < 75) {
                System.out.println("FAILED THE SEMESTER! Final Grade " + player.getGrade() + "% (Required: 75% to pass)");
                return true;
            }
        }
        
        // Immediate game over conditions
        if (player.getEnergy() <= 0) {
            System.out.println("COLLAPSED FROM EXHAUSTION! Energy <= 0");
            return true;
        }
        if (player.getMoney() <= 0) {
            System.out.println("BANKRUPT! Money <= 0");
            return true;
        }
        if (player.getStress() >= 100) {
            System.out.println("MENTAL BREAKDOWN! Stress >= 100");
            return true;
        }
        return false;
    }

    private void showGameResult() {
        boolean passed = player.getGrade() >= 75;
        
        if (passed && day > TOTAL_DAYS) {
            System.out.println("\n========================================");
            System.out.println("          SEMESTER COMPLETED!");
            System.out.println("========================================");
            System.out.println("SUCCESS! You passed the semester!");
            
        } else {
            System.out.println("\n========================================");
            System.out.println("             GAME OVER!");
            System.out.println("========================================");
            
            if (player.getGrade() < 75) {
                System.out.println("FAILED: Final Grade " + player.getGrade() + "% (Required: 75% to pass)");
            } else {
                System.out.println("You didn't meet all requirements.");
            }
        }
        
        showFinalStats();
    }
    
    private void showFinalStats() {
        System.out.printf("\nFINAL STATS:\n");
        System.out.printf("Money: $%.0f\n", player.getMoney());
        System.out.printf("Energy: %d%%\n", player.getEnergy());
        System.out.printf("Stress: %d%%\n", player.getStress());
        System.out.printf("Grade: %d%%\n", player.getGrade());
        
        // Improved scoring system
        int moneyScore = (int)(player.getMoney()) / 10;
        int energyScore = player.getEnergy() /2;
        int stressScore = Math.max(0, 100 - player.getStress());
        int gradeScore = player.getGrade();
        
        int score = moneyScore + energyScore + stressScore + gradeScore;
        System.out.println("Total Score: " + score);
        
        // Performance feedback
        if (score >= 450) {
            System.out.println("Outstanding performance! Perfect balance!");
        } else if (score >= 350) {
            System.out.println("Great job! Well managed.");
        } else if (score >= 250) {
            System.out.println("Good effort! You passed.");
        } else {
            System.out.println("Try again!");
        }
    }
}