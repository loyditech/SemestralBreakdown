# 🎮 **SEMESTRAL BREAKDOWN – Java Console Game**  
![Java](https://img.shields.io/badge/Java-Console%20Game-orange)  
![OOP](https://img.shields.io/badge/OOP-Principles%20Applied-blue)  
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)  
![Platform](https://img.shields.io/badge/Platform-Console-lightgrey)

---

## 📑 **Table of Contents**
1. [Project Title](#1-project-title)  
2. [Description / Overview](#2-description--overview)  
3. [OOP Concepts Applied](#3-oop-concepts-applied)  
4. [Program Structure](#4-program-structure)  
5. [How to Run the Program](#5-how-to-run-the-program)  
6. [Sample Output](#6-sample-output)  
7. [Author & Acknowledgment](#7-author--acknowledgment)  
8. [Future Enhancements](#8-future-enhancements)

---

# 1. **Project Title**  
# 🎓 *SEMESTRAL BREAKDOWN*

---

# 2. **Description / Overview**  
**Semestral Breakdown** is a Java-based **console simulation game** where you experience what it’s like to survive a full **30-day college semester**. The player must balance four main stats:

- **💰Money**
- **⚡Energy**
- **😣Stress**
- **📚Grades**

Every day, the player chooses actions (Study, Work, Rest, Hang Out) while also encountering **random life events** that affect progress.  
Your mission?  
✔ **Pass the semester (Grade ≥ 75%)**  
✔ Avoid collapse (Energy > 0)  
✔ Manage stress (Stress < 100)  
✔ Avoid going broke (Money > 0)

The game demonstrates decision-making, stat balancing, and strong object-oriented design.

---

# 3. **OOP Concepts Applied**

## 🛡️ **Encapsulation**
- Player attributes are **private** (money, energy, stress, grade).
- Controlled through **getters, setters, and modifier methods**.
- Prevents unauthorized stat manipulation.

---

## 🧬 **Inheritance**
- All events inherit from an abstract base class **Event**.
- Subclasses include:
  - `ExamWeekEvent`
  - `PowerOutageEvent`
  - `AllowanceDelayEvent`
  - `RainyDayEvent`
  - `GoodDayEvent`

This allows a shared structure while enabling unique event effects.

---

## 🔁 **Polymorphism**
- Game stores events inside an `Event[] eventPool`.
- `apply(player)` behaves differently depending on event type.
- Enables flexible and extendable event handling.

---

## 🎭 **Abstraction**
- The abstract `Event` class defines required behavior.
- Game only calls the method — unaware of internal event logic.
- Simplifies architecture and hides complexity.

---

# 4. **Program Structure**

## 📌 **Main Classes**

### 🕹️ `Game`
- Controls main gameplay loop (30 days)
- Handles:
  - Daily actions  
  - Random events  
  - Weekly limits  
  - Stat updates  
  - Win/lose conditions  

---

### 👤 `Player`
- Stores player stats
- Modifies attributes through methods
- Implements weekly action limits:
  - 🧠Study (4x)
  - 💼Work (3x)
  - 🛌Rest (5x)
  - 🎉Hang Out (3x)

---

### 🎲 `Event` (Abstract)
- Parent class for all in-game random events
- Defines `apply(Player)` method

---

### 🌩️ **Event Subclasses**
- `ExamWeekEvent`  
- `PowerOutageEvent`  
- `AllowanceDelayEvent`  
- `RainyDayEvent`  
- `GoodDayEvent`  

Each modifies player stats differently.

---

## 📂  Diagram
<img width="1581" height="1434" alt="588115308_1438338531044278_7645152756966214044_n" src="https://github.com/user-attachments/assets/68a42802-c397-4d0d-8b7d-f82c03747271" />



---

# 5. **📘 How to Run & Play the Game**  
  This project is a console-based Java simulation game where you must survive a semester by managing your energy, stress, money, and grades.
  The main entry point of the game is:
   game/SemestralBreakdown.java



## 🚀 How to Run the Program <br>
### When using a Command Line:
1. Navigate to the project's src folder:<br>
     `cd/src`<br>
2. Compile all Java Files:<br>
     `javac game/*. java game/events/*.java`<br>
3. Run the main class:<br>
     `java game.SemestralBreakdown`<br>

### When using an IDE:<br>
1. Open the project folder in your IDE<br>
2. Mark src as Source Root (if required)<br>
3. Open the file:<br>
     `game/semestralBreakdown.java`<br>
4. Right-click Run 'SemestralBreakdown.main()'


## 🎮 How to Play the Game

Once the game starts, you will see your starting stats:<br>
**💰Money**<br>
**⚡Energy**<br>
**😣Stress**<br>
**📚Grade**<br>

Each day in the game:<br>
  - A random event may occur<br>
  - Your stats will be updated<br>
  - You must make decisions that affect your performance<br>
  - The goal is to finish the semester without failing<br>
  - Events (e.g., ExamWeekEvent) influence your stats based on conditions like energy, stress, etc.<br>

```
if (player.getEnergy() >= 60) {
    player.modifyGrade(+3);
} else {
    player.modifyGrade(-5);
}
player.modifyEnergy(-10);
player.modifyStress(7);
```

## 🏁 Game Ending

The game ends when:<br>
  - The semester reaches its final day OR<br>
  - You hit a game-over condition (0 energy, 0 money, 100 stress, etc.)<br>
  - Your final stats and final score will be shown on screen.<br>

# 6. **Sample Output**  


## Main Menu

░█▀▀░█▀▀░█▄█░█▀▀░█▀▀░▀█▀░█▀▄░█▀█░█░░░░░█▀▄░█▀▄░█▀▀░█▀█░█░█░█▀▄░█▀█░█░█░█▀█
░▀▀█░█▀▀░█░█░█▀▀░▀▀█░░█░░█▀▄░█▀█░█░░░░░█▀▄░█▀▄░█▀▀░█▀█░█▀▄░█░█░█░█░█▄█░█░█
░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀▀▀░░▀░░▀░▀░▀░▀░▀▀▀░░░▀▀░░▀░▀░▀▀▀░▀░▀░▀░▀░▀▀░░▀▀▀░▀░▀░▀░▀

Press Enter to continue...

WELCOME STUDENT!
Survive 30 days of college life!
Starting Stats: $300, 100 Energy, 0 Stress, 70% Grade
Goal: Reach Grade >= 75% to PASS, Money > 0, Energy > 0, Stress < 100
WARNING: Starting grade is 70% - you need to improve to pass!
PLEASE READ IT CAREFULLY (THERE ARE RANDOM EVENT on RANDOM DAYS)
Weekly Limits: Study 4x, Work 3x, Hangout 3x, Rest 5x
Press Enter to start...



## Selecting Study (1)
```
----- Day 1/30 -----
Money: $300 | Energy: 100 | Stress: 0 | Grade: 70%
Weekly:| Study 0/4 | Work 0/3 | Rest 0/3 | Hangout 0/3 | 
DANGER: Grade is low! Study more!
```
```
DAILY ACTIONS:
1. Study   (+2% grade, +12 stress, -12 energy, -$50)
2. Work    (+$200, +15 stress, -1% grade, -15 energy)
3. Rest    (+ 15 energy, -5 stress , -$30)
4. Hang Out (-$80, -5 energy, -10 stress)
Choose: 1
Studying...
Well-rested! Stress naturally decreases.
Press Enter to continue...
```

## Selecting Work (2)
```
----- Day 2/30 -----
Money: $250 | Energy: 88 | Stress: 9 | Grade: 72%
Weekly:| Study 1/4 | Work 0/3 | Rest 0/3 | Hangout 0/3 |
WARNING: Grade is close to failing (75% to pass)!

DAILY ACTIONS:
1. Study   (+2% grade, +12 stress, -12 energy, -$50)
2. Work    (+$200, +15 stress, -1% grade, -15 energy)
3. Rest    (+ 15 energy, -5 stress , -$30)
4. Hang Out (-$80, -5 energy, -10 stress)
Choose: 2
Working...
Press Enter to continue...
```


## Selecting Rest (3)
```
----- Day 3/30 -----
Money: $450 | Energy: 73 | Stress: 24 | Grade: 71%
Weekly:| Study 1/4 | Work 1/3 | Rest 0/3 | Hangout 0/3 |
DANGER: Grade is low! Study more!

DAILY ACTIONS:
1. Study   (+2% grade, +12 stress, -12 energy, -$50)
2. Work    (+$200, +15 stress, -1% grade, -15 energy)
3. Rest    (+ 15 energy, -5 stress , -$30)
4. Hang Out (-$80, -5 energy, -10 stress)
Choose: 3
Resting...
Well-rested! Stress naturally decreases.
Press Enter to continue...
```


## Selecting Hang Out (4)
```
----- Day 4/30 -----
Money: $480 | Energy: 88 | Stress: 16 | Grade: 71%
Weekly:| Study 1/4 | Work 1/3 | Rest 1/3 | Hangout 0/3 |
DANGER: Grade is low! Study more!

DAILY ACTIONS:
1. Study   (+2% grade, +12 stress, -12 energy, -$50)
2. Work    (+$200, +15 stress, -1% grade, -15 energy)
3. Rest    (+ 15 energy, -5 stress , -$30)
4. Hang Out (-$80, -5 energy, -10 stress)
Choose: 4
Hanging out...
Press Enter to continue...
```
















# 7. **Author & Acknowledgment**  

| Profile | Name | Role |
|:---:|:---:|:---:|
| <img src="Profile/danr.jpg" width="80" style="border-radius: 50%;"/><br>[@Dadanchii](https://github.com/Dadanchii) | **Dhanreigh I. Atienza** | Developer<br>Tester<br>ReadMe Creator |
| <img src="Profile/mark.jpg" width="80" style="border-radius: 50%;"/><br>[@loyditech](https://github.com/loyditech) | **Mark John Lloyd L. Ncinas** | Main Developer<br>Debugger<br>ReadMe Creator |
| <img src="Profile/dave.jpg" width="80" style="border-radius: 50%;"/><br>[@dp30-sub](https://github.com/dp30-sub) | **Dave Rowan V. Paunil** | Developer<br>Tester<br>ReadMe Creator |

## 🙏 Acknowledgements

We would like to express our gratitude to the following:

- **Mr. EMMANUEL CHARLIE B. ENRIQUEZ** – for guidance,consultation and project requirements.  
- **Classmates / Friends** – for feedback and testing the early versions.  
- **Online Resources** – documentation, guides, and tutorials that helped during development.  

  


# 8. **Future Enhancements**  

## 🚀 Future Enhancements

Planned updates and improvements:

- 🔹 Add save/load game functionality  
- 🔹 Improve event system with more random scenarios  
- 🔹 Add difficulty levels (Easy, Normal, Hard)  
- 🔹 Add more detailed character stats  
- 🔹 Add color-coded console output  
- 🔹 Integrate sound or GUI version  
- 🔹 Add weekly summary reports  
- 🔹 Improve scoring system  
- 🔹 Add achievements system  




