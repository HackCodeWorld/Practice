#include <signal.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

// Constant for the number of seconds before the alarm signal is triggered.
#define ALARM_INTERVAL 5

// Counter for the number of times SIGUSR1 signal has been handled.
static unsigned times_usr = 0;

/* Handles alarm signals (SIGALRM).
 *
 * Pre-conditions: This function is called automatically when a SIGALRM signal is received.
 * No parameters are required for this function as it handles the signal.
 * retval: This function does not return a value as it is a signal handler.
 */
void handler_sigalrm(int sig) {
    time_t result = time(NULL);
    if (result == (time_t) -1) {
        printf("Error: Failed to get the current time.\n");
        exit(0);
    }

    char *time_str = ctime(&result);
    if (time_str == NULL) {
        printf("Error: Failed to convert time to string.\n");
        exit(0);
    }

    printf("PID: %d CURRENT TIME: %s", getpid(), time_str);
    alarm(ALARM_INTERVAL);
}

/* Handles user-defined signal 1 (SIGUSR1).
 *
 * Pre-conditions: This function is called automatically when a SIGUSR1 signal is received.
 * No parameters are required for this function as it handles the signal.
 * retval: This function does not return a value as it is a signal handler.
 */
void handler_sigusr1(int sig) {
    times_usr++;
    printf("SIGUSR1 handled and counted!\n");
}

/* Handles interrupt signals (SIGINT).
 *
 * Pre-conditions: This function is called automatically when a SIGINT signal is received.
 * No parameters are required for this function as it handles the signal.
 * retval: This function does not return a value as it terminates the program with exit(0).
 */
void handler_interrupt(int sig) {
    printf("\nSIGINT handled.\nSIGUSR1 was handled %d times. Exiting now.\n", times_usr);
    exit(0);
}

/* Main entry function of the signal handling program.
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

    // create instance on stack, stack allocated sigaction structures for SIGALRM, SIGUSR1, and SIGINT.
    struct sigaction sa_alarm, sa_usr1, sa_int;

    // clear out or set all bytes to 0 for the size of sa (initialize sigaction struct)
    memset(&sa_alarm, 0, sizeof(sa_alarm));

    // sa.sa_handler is the beginning of the function address, which defines the behavior when signal occurs
    // it then assigns to our customized handler for SIGALRM
    sa_alarm.sa_handler = handler_sigalrm;

    // mapping the signal alarm to customized signal handler
    // sigaction(signal number, object address, makes connection)
    // if result of function call not equal to 0 then error binding occurs
    if (sigaction(SIGALRM, &sa_alarm, NULL) == -1) {
        printf("Error binding SIGALRM handler \n");
        exit(1);
    }

    // clear out or set all bytes to 0 for the size of sa (initialize sigaction struct)
    memset(&sa_usr1, 0, sizeof(sa_usr1));

    // sa.sa_handler is the beginning of the function address, which defines the behavior when signal occurs
    // it then assigns to our customized handler for SIGUSR1
    sa_usr1.sa_handler = handler_sigusr1;

    // mapping the signal alarm to customized signal handler
    // sigaction(signal number, object address, makes connection)
    // if result of function call not equal to 0 then error binding occurs
    if (sigaction(SIGUSR1, &sa_usr1, NULL) == -1) {
        printf("Error binding SIGUSR1 handler \n");
        exit(1);
    }

    // clear out or set all bytes to 0 for the size of sa (initialize sigaction struct)
    memset(&sa_int, 0, sizeof(sa_int));

    // sa.sa_handler is the beginning of the function address, which defines the behavior when signal occurs
    // it then assigns to our customized handler for SIGINT.
    sa_int.sa_handler = handler_interrupt;

    // mapping the signal alarm to customized signal handler
    // sigaction(signal number, object address, makes connection)
    // if result of function call not equal to 0 then error binding occurs
    if (sigaction(SIGINT, &sa_int, NULL) == -1) {
        printf("Error binding SIGINT handler \n");
        exit(1);
    }

    // set a SIGALRM signal to occur in a specified number of seconds
    alarm(ALARM_INTERVAL);

    printf("PID and time print every 5 seconds.\nType Ctrl-C to end the program.\n");

    // infinite loop
    while (1) {
    }

    return 0;
}
