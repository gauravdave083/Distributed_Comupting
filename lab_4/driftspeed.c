#include <stdio.h>
#include <stdlib.h>

#define MAX_PROCESS 10
#define MAX_EVENTS 10

int main() {
    int processes, events[MAX_PROCESS], clock[MAX_PROCESS][MAX_EVENTS];
    float drift[MAX_PROCESS]; // Drift factor for each process
    int timestamp[MAX_PROCESS][MAX_EVENTS] = {0};

    // Input number of processes
    printf("Enter number of processes: ");
    scanf("%d", &processes);

    // Input number of events and drift speed for each process
    for (int i = 0; i < processes; i++) {
        printf("Enter number of events for process P%d: ", i + 1);
        scanf("%d", &events[i]);
        printf("Enter drift speed for process P%d (e.g., 1.2 for 20%% faster clock): ", i + 1);
        scanf("%f", &drift[i]);
    }

    // Initialize event dependency matrix
    printf("\nEnter event dependency matrix (1 for send, -1 for receive, 0 otherwise):\n");
    for (int i = 0; i < events[0]; i++) {
        for (int j = 0; j < events[1]; j++) {
            scanf("%d", &clock[i][j]);
        }
    }

    // Generate logical timestamps considering drift speed
    for (int i = 0; i < processes; i++) {
        for (int j = 0; j < events[i]; j++) {
            if (j == 0) {
                timestamp[i][j] = 1;
            } else {
                timestamp[i][j] = timestamp[i][j - 1] + (int)(1 * drift[i]);
            }
        }
    }

    // Handling message passing between processes
    for (int i = 0; i < events[0]; i++) {
        for (int j = 0; j < events[1]; j++) {
            if (clock[i][j] == 1) { // Sending event
                int sender_time = timestamp[0][i]; // Sender timestamp
                int receiver_event = -1;

                // Find the receiving event
                for (int k = 0; k < events[1]; k++) {
                    if (clock[k][j] == -1) {
                        receiver_event = k;
                        break;
                    }
                }

                if (receiver_event != -1) {
                    timestamp[1][receiver_event] = (sender_time > timestamp[1][receiver_event])
                                                   ? sender_time + (int)(1 * drift[1])
                                                   : timestamp[1][receiver_event] + (int)(1 * drift[1]);
                }
            }
        }
    }

    // Print event matrix
    printf("\nEvent Matrix:\n");
    for (int i = 0; i < events[0]; i++) {
        for (int j = 0; j < events[1]; j++) {
            printf("%3d ", clock[i][j]);
        }
        printf("\n");
    }

    // Print timestamps
    for (int i = 0; i < processes; i++) {
        printf("\nThe timestamps of events in P%d: ", i + 1);
        for (int j = 0; j < events[i]; j++) {
            printf("%d ", timestamp[i][j]);
        }
        printf("\n");
    }

    return 0;
}
