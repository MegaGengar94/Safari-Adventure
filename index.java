#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

// Function to check if the input area is valid
bool isValidArea(const string& area) {
    return area.compare("Jungle") == 0 || area.compare("River") == 0 ||
           area.compare("Desert") == 0 || area.compare("Mountains") == 0;
}

int main() {
    srand(time(0)); // Seed random number generator
    int totalPoints = 0;
    bool survived = true; // Tracks if player survives all days
    string area, input;

    cout << "🌄 Welcome to Safari Adventure!" << endl << endl;

    // for Loop: 5 days of exploration
    for (int day = 1; day <= 5; day++) {
        cout << "Day " << day << ":" << endl;

        // do...while Loop: Choose a valid area
        do {
            cout << "Where would you like to explore? (Jungle, River, Desert, Mountains): ";
            getline(cin, area, '\n');
            if (!isValidArea(area)) {
                cout << "Invalid area. Please choose again." << endl;
            }
        } while (!isValidArea(area));

        cout << "\nYou chose: " << area << endl;
        cout << "Exploring " << area << "..." << endl;

        int events = 0; // Track number of events (up to 3)
        int dailyPoints = 0; // Points for the current day
        bool dayEndedEarly = false; // Flag for early day termination

        // while Loop: Simulate up to 3 events in the area
        while (events < 3 && !dayEndedEarly) {
            events++;
            cout << "\nEvent " << events << ": ";

            // Random event (0: bird, 1: resources, 2: animal, 3: weather)
            int eventType = rand() % 4;
            int points = 0;
            string eventDesc;

            // Determine event based on type
            if (eventType == 0) {
                eventDesc = "You spotted a bird. 🐦";
                cout << eventDesc << endl;
                cout << "(Too small to track. Moving on.)" << endl;
                continue; // Skip to next event
            } else if (eventType == 1) {
                // Resource event based on area
                if (area.compare("Jungle") == 0) {
                    eventDesc = "You found edible berries! (+15 points)";
                    points = 15;
                } else if (area.compare("River") == 0) {
                    eventDesc = "You caught some fish! (+20 points)";
                    points = 20;
                } else if (area.compare("Desert") == 0) {
                    eventDesc = "You found an oasis with water! (+10 points)";
                    points = 10;
                } else if (area.compare("Mountains") == 0) {
                    eventDesc = "You mined some rare minerals! (+25 points)";
                    points = 25;
                }
            } else if (eventType == 2) {
                // Dangerous animal event
                string animal = (area.compare("Jungle") == 0 || area.compare("Mountains") == 0) ? "lion" : "crocodile";
                eventDesc = "A " + animal + " appears! 😱";
                cout << eventDesc << endl;
                cout << "Type 'run' to escape: ";
                getline(cin, input, '\n');
                if (input.compare("run") == 0) {
                    cout << "You escaped safely, ending the day early." << endl;
                    dayEndedEarly = true; // Break the event loop
                    break;
                } else {
                    cout << "You didn't escape in time! Adventure ends." << endl;
                    survived = false;
                    dayEndedEarly = true;
                    break;
                }
            } else {
                // Weather hazard event
                if (area.compare("Desert") == 0) {
                    eventDesc = "A sandstorm hits! (-10 points)";
                    points = -10;
                } else if (area.compare("River") == 0) {
                    eventDesc = "Heavy rain floods your path! (-5 points)";
                    points = -5;
                } else {
                    eventDesc = "A sudden storm slows you down! (-8 points)";
                    points = -8;
                }
            }

            // If not a bird event, print description and update points
            if (eventType != 0) {
                cout << eventDesc << endl;
                dailyPoints += points;
                totalPoints += points;
            }

            // Stop if enough resources (20+ points) for the day
            if (dailyPoints >= 20) {
                cout << "Enough resources collected for today!" << endl;
                break;
            }
        }

        // Day summary
        cout << "\nDay Summary: " << dailyPoints << " points earned." << endl;
        cout << "-----------------------------------" << endl << endl;

        // If player didn't survive, end the adventure
        if (!survived) {
            cout << "☠️ Adventure Over! You didn't survive." << endl;
            cout << "Total points collected: " << totalPoints << endl;
            return 0;
        }
    }

    // Final summary
    cout << "🎉 Safari Complete! You collected " << totalPoints << " points!" << endl;
    cout << "You survived and completed the adventure!" << endl;

    return 0;
}
