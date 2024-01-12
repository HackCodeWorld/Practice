#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

// Constant for the maximum buffer size for user input.
#define MAX_BUFFER_SIZE 100

// a global counter for success times of division
// It keeps track of how many times the division operation is completed without errors.
int success = 0;

/* Handles floating point exception signals (SIGFPE).
 *
 * Pre-conditions: This function is called automatically when a SIGFPE signal is received.
 * No parameters are required for this function as it handles the signal.
 * retval: This function does not return a value as it terminates the program with exit(0).
 */
void handler_sigfpe() {
    printf("Error: a division by 0 operation was attempted.\n"
           "Total number of operations completed successfully: %d\n"
           "The program will be terminated.\n", success);
    exit(0);
}

/* Handles interrupt signals (SIGINT).
 *
 * Pre-conditions: This function is called automatically when a SIGINT signal is received.
 * No parameters are required for this function as it handles the signal.
 * retval: This function does not return a value as it terminates the program with exit(0).
 */
void handler_interrupt() {
    printf("\nTotal number of operations completed successfully: %d\n"
           "The program will be terminated.\n", success);
    exit(0);
}

/* Main entry function of the division program.
 *
 * Pre-conditions: None. The program does not require any specific conditions to start.
 * No parameters are required for this function.
 * retval: Returns 0 upon normal termination of the program. However, the program is designed
 *         to terminate through exit(0) calls in signal handlers and does not naturally reach
 *         the return statement.
 */
int main() {
    // register a handler function to be called if and
    // when a specific type of signal is sent to the program

    // create instance on stack, stack allocated sigaction structure
    // with floating point or arithmetic error signal (SIGFPE) for sa_floating
    // and interrupt signal (SIGINT) for sa_int
    struct sigaction sa_floating, sa_int;

    // clear out or set all bytes to 0 for the size of sa (initialize sigaction struct)
    memset(&sa_floating, 0, sizeof(sa_floating));

    // sa.sa_handler is the beginning of the function address, which defines the behavior when signal occurs
    // it then assigns to our customized handler
    sa_floating.sa_handler = handler_sigfpe;

    // registering the signal floating point exception to customized signal handler
    // sigaction(signal number, object address, makes connection)
    // if result of function call not equal to 0 then error binding occurs
    if (sigaction(SIGFPE, &sa_floating, NULL) != 0) {
        printf("Error binding SIGFPE handler \n");
        exit(1);
    }

    // clear out or set all bytes to 0 for the size of sa (initialize sigaction struct)
    memset(&sa_int, 0, sizeof(sa_int));

    // sa.sa_handler is the beginning of the function address, which defines the behavior when signal occurs
    // it then assigns to our customized handler
    sa_int.sa_handler = handler_interrupt;

    // registering the signal floating point exception to customized signal handler
    // sigaction(signal number, object address, makes connection)
    // if result of function call not equal to 0 then error binding occurs
    if (sigaction(SIGINT, &sa_int, NULL) != 0) {
        printf("Error binding SIGINT handler \n");
        exit(1);
    }

    // loop for division operations.
    while (1) {
        // Variables for storing user input and calculation results.
        // first_int and second_int store integers input by the user.
        // result stores the result of the division operation.
        // remainder stores the remainder of the division operation.
        int first_int = 0;
        int second_int = 0;
        int result = 0;
        int remainder = 0;

        // Buffer for storing raw input from the user before conversion to an integer.
        // It has a fixed size of 100 characters.
        char buffer[MAX_BUFFER_SIZE];

        // Prompt the user to enter the first integer and read the input.
        // If fgets returns NULL, it indicates an error or end-of-file condition.
        printf("Enter first integer: ");
        if (fgets(buffer, MAX_BUFFER_SIZE, stdin) == NULL) {
            printf("Error or end of file reached.\n");
            exit(0);
        } else {
            first_int = atoi(buffer);
        }

        // Again, prompt and similar error handling as the first integer.
        printf("Enter second integer: ");
        if (fgets(buffer, MAX_BUFFER_SIZE, stdin) == NULL) {
            printf("Error or end of file reached.\n");
            exit(0);
        } else {
            // Convert the input string to an integer using atoi.
            second_int = atoi(buffer);
        }

        // Check if the second integer is zero to avoid division by zero.
        if (second_int != 0) {
            result = first_int / second_int;
            remainder = first_int % second_int;
            success++;
        } else {
            // If second integer is zero, raise a floating point exception.
            // This triggers the SIGFPE signal and calls the corresponding handler.
            raise(SIGFPE);
        }

        printf("%d / %d is %d with a remainder of %d\n",
               first_int, second_int, result, remainder);
    }

    return 0;
}
