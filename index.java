import java.util.Scanner;
import java.util.Random;

public class SafariAdventure {
    static class Event {
        String type;
        String name;
        int points;
        boolean dangerous;

        Event(String type, String name, int points, boolean dangerous) {
            this.type = type;
            this.name = name;
            this.points = points;
            this.dangerous = dangerous;
        }

        Event(String type, String name, int points) {
            this(type, name, points, false);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalPoints = 0;

        // Define areas and their events
        Event[][] areas = {
            // Jungle, Very Easy
            {
                new Event("resource", "Found a rare fruit", 5),
                new Event("resource", "Found undiscovered medicial herbs", 5),
                new Event("animal", "Befriended a loyal pet monkey", 10),
                new Event("dangrous", "Attacked by a fiesty baby tiger", 0, true),
                new Event("hazard", "Fell on spikey brambles", -10),
                new Event("harmless", "Saw a little parakeet", 0)
            },
            // River, Easy
            {
                new Event("resource", "You found fresh food and water for your journey", 10),
                new Event("resources", "You spoke with an old man about legends and he gave you a map", 15),
                new Event("treasure", "You manage to pan for a bit of gold", 20),
                new Event("dangerous", "encountered a crocodile", -5, true),
                new Event("hazard", "faced a sudden flood", -10),
                new Event("harmless", "had a harmless bird sighting", 0)
            },
            // Desert, Intermediate
            {
                new Event("resource", "You traded with a merchant caravan", 30),
                new Event("treasure", "You found a bit of ancient gold", 40),
                new Event("hazard", "You were bit by a sacarab you didn't see coming", -10),
                new Event("dangerous", "There was a mummy you found while trasure hunting", -10, true),
                new Event("hazard", "got caught in a sandstorm", -20),
                new Event("harmless", "had a harmless bird sighting", 0)
            },
            // Mountains, Hard
            {
                new Event("hazard", "You fell down a bit of the mountain and lost some progress along with being hurt", -30),
                new Event("hazard", "There was a boulder that you nearly dodged but got hurt", -35),
                new Event("hazard", "You got caught by an ancient trap and got hurt badly", -40),
                new Event("dangerous", "There is a pack of scary ape creatures", -20, true),
                new Event("treasure", "Your found the legendary treasure of the mountain", 500),
                new Event("harmless", "had a harmless eagle sighting", 0)
            }
        };
        String[] areaNames = {"Jungle", "River", "Desert", "Mountains"};

        

        // for loop for 5 days
        for (int day = 1; day <= 5; day++) {
            System.out.println("\nDay " + day + ": Choose your exploration area.");
            String area;
            int areaIndex;

            // do...while for area selection
            do {
                System.out.print("Enter Jungle, River, Desert, or Mountains: ");
                area = scanner.nextLine().trim();
                areaIndex = -1;
                for (int i = 0; i < areaNames.length; i++) {
                    if (areaNames[i].equalsIgnoreCase(area)) {
                        areaIndex = i;
                        break;
                    }
                }
                if (areaIndex == -1) {
                    System.out.println("Invalid area. Please try again.");
                }
            } while (areaIndex == -1);

            System.out.println("Exploring the " + areaNames[areaIndex] + "...");

            int eventCount = 0;
            int dayPoints = 0;

            // while loop for up to 3 events
            while (eventCount < 3) {
                Event randomEvent = areas[areaIndex][random.nextInt(areas[areaIndex].length)];
                System.out.println("\nYou " + randomEvent.name + ".");

                if (randomEvent.type.equals("harmless")) {
                    System.out.println("Nothing interesting, continuing to the next event.");
                    continue; // Skip harmless events
                }

                eventCount++;

                if (randomEvent.dangerous) {
                    System.out.print("Dangerous animal! Type 'run' to escape: ");
                    String action = scanner.nextLine().trim();
                    if (action.equalsIgnoreCase("run")) {
                        System.out.println("You escaped safely, ending today's exploration.");
                        break; // Escape dangerous animal
                    } else {
                        System.out.println("You didn't escape in time and got injured.");
                        totalPoints -= 20;
                    }
                } else {
                    totalPoints += randomEvent.points;
                    System.out.println("Points adjustment: " + randomEvent.points);
                    if (randomEvent.points > 0) {
                        dayPoints += randomEvent.points;
                    }
                }

                if (dayPoints >= 25) {
                    System.out.println("You have collected enough resources for today.");
                    break; // End day if enough resources collected
                }
            }

            System.out.println("End of Day " + day + ". Current total points: " + totalPoints);
        }

        scanner.close();

        // Summary
        System.out.println("\nAdventure Summary:");
        System.out.println("Total points collected: " + totalPoints);
        if (totalPoints >= 100) {
            System.out.println("You survived and completed the adventure successfully!");
        } else {
            System.out.println("You survived all 5 days but did not collect enough points to complete the adventure.");
        }
    }
}
