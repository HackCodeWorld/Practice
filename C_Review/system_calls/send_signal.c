#include <stdio.h>
#include <string.h>
#include <signal.h>
#include <stdlib.h>
#include <stdint.h>

/* Main entry function for the signal sender program.
 *
 * Pre-conditions: Requires two command-line arguments - a signal type and a process ID (pid).
 * param1 (argc): Count of the command-line arguments.
 * param2 (argv): Array of command-line arguments.
 * retval: Returns 0 on successful execution, 1 on error or invalid input.
 */
int main(int argc, char *argv[]) {
    // Check if the correct number of arguments is provided.
    if (argc != 3) {
        printf("Usage: %s <signal type> <pid>\n", argv[0]);
        return 1;
    }

    // Convert the second argument (pid) from string to long.
    // end_ptr will point to the first invalid character.
    char *end_ptr;
    long potential_pid = strtol(argv[2], &end_ptr, 10);

    // Validate the converted pid.
    if (*end_ptr != '\0' || potential_pid > INT32_MAX) {
        printf("Error: Input is not a valid integer or too large.\n");
        return 1;
    }

    // convert long to pid_t
    pid_t pid = (pid_t) potential_pid;

    // Variable to store the signal type
    int signal;

    // Determine the signal type based on the first argument.
    // SIGUSR1 for "-u", SIGINT for "-i".
    if (strcmp(argv[1], "-u") == 0) {
        signal = SIGTSTP;
    }
    else if (strcmp(argv[1], "-i") == 0) {
        signal = SIGINT;
    }
    else {
        // If the signal type is neither "-u" nor "-i", return an error.
        printf("Invalid signal type! Use -i for SIGINT or -u for SIGUSR1.\n");
        return 1;
    }

    // Send the specified signal to the specified process.
    // If the system call is unsuccessful, print an error message.
    if (kill(pid, signal) == -1) {
        printf("Error sending signal to process %d\n", pid);
        return 1;
    }

    return 0;
}
