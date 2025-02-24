#include <stdio.h>

#define MAX_EVENTS 10
#define MAX_PROCESSES 10

void initializeMatrix(int processes, int events[], int matrix[MAX_EVENTS][MAX_PROCESSES]) {
    for (int i = 0; i < MAX_EVENTS; i++) {
        for (int j = 0; j < MAX_PROCESSES; j++) {
            matrix[i][j] = 0; // Initialize all values to 0
        }
    }
}

void displayMatrix(int processes, int maxEvents, int matrix[MAX_EVENTS][MAX_PROCESSES]) {
    printf("\nEvent Matrix:\n");
    printf("      ");
    
    // Print column headers (Process numbers)
    for (int j = 0; j < processes; j++) {
        printf("e2%d   ", j + 1);
    }
    printf("\n");

    for (int i = 0; i < maxEvents; i++) {
        printf("e1%d  ", i + 1);
        for (int j = 0; j < processes; j++) {
            printf("%3d   ", matrix[i][j]); // Print each value in matrix
        }
        printf("\n");
    }
}

void displayTimestamps(int processes, int events[], int matrix[MAX_EVENTS][MAX_PROCESSES]) {
    for (int i = 0; i < processes; i++) {
        printf("\nThe time stamps of events in P%d:\n", i + 1);
        for (int j = 0; j < events[i]; j++) {
            printf("%d ", matrix[j][i]); // Display timestamps per process
        }
        printf("\n");
    }
}

int main() {
    int processes;
    printf("Enter number of processes: ");
    scanf("%d", &processes);

    int events[MAX_PROCESSES];
    int matrix[MAX_EVENTS][MAX_PROCESSES];

    // Input number of events per process
    int maxEvents = 0;
    for (int i = 0; i < processes; i++) {
        printf("Enter number of events for process %d: ", i + 1);
        scanf("%d", &events[i]);
        if (events[i] > maxEvents) {
            maxEvents = events[i];
        }
    }

    initializeMatrix(processes, events, matrix);

    // Generate Lamport Clock values
    for (int i = 0; i < processes; i++) {
        for (int j = 0; j < events[i]; j++) {
            matrix[j][i] = j + 1; // Increment clock for each event
        }
    }


    displayMatrix(processes, maxEvents, matrix);

    displayTimestamps(processes, events, matrix);

    return 0;
}
