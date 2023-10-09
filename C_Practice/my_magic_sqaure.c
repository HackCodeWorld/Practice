
#include <stdio.h>
#include <stdlib.h>

// Structure that represents a magic square
typedef struct {
    int size;           // dimension of the square
    int **magic_square; // pointer to heap allocated magic square
} MagicSquare;


/**
 * Prompts the user for the magic square's size, reads it, checks if it's an odd number >= 3.
 * If not valid, it displays the required error message.
 * Pre-conditions: None
 * retval: Size inputted by the user (may not be valid).
 */
int getSize() {
    int n; // size of the square

    // initial prompt
    printf("Enter magic square's size (odd integer >=3)\n");
    scanf("%d", &n);

    // check even
    if (n % 2 == 0) {
        printf("Magic square size must be odd.\n");
    }
    // check size >= 3
    else if (n < 3) {
        printf("Magic square size must be >= 3.\n");
    }

    return n;
}

/**
 * Adjusts the indices of a magic square's row and column to ensure they fall within
 * the boundaries of the magic square.
 *
 * Pre-conditions:
 * *i: Initial row index.
 * *j: Initial column index.
 * n: Size of the magic square.
 * Post-conditions: *i and *j are modified to remain within the boundaries [0, n-1].
 */
void boundary_check(int *i, int *j, int n) {
    *i = (*i + n) % n;  // adjust row index within boundary.
    *j = (*j + n) % n;  // adjust column index within boundary.
}

/**
 * Creates and returns a magic square of size 'n'.
 *
 * Pre-conditions:
 * n: Size of the desired magic square. Should be an odd integer >= 3.
 * retval: Pointer to the completed MagicSquare structure.
 */
MagicSquare *generateMagicSquare(int n) {
    // dynamically allocate the memory space for MagicSquare structure
    MagicSquare *ms = (MagicSquare *) malloc(sizeof(MagicSquare));
    if (ms == NULL) {
        printf("Error: Memory allocation for MagicSquare structure failed!\n");
        exit(1);
    }
    ms->size = n;
    // dynamically allocate the memory space for MagicSquare structure's matrix rows
    ms->magic_square = (int **) malloc(n * sizeof(int *));
    if (ms->magic_square == NULL) {
        printf("Error: Memory allocation for pointer to heap allocated magic square failed!\n");
        exit(1);
    }
    for (int i = 0; i < n; i++) {
        // dynamically allocate the memory space for MagicSquare structure's
        // matrix rows' columns
        *(ms->magic_square + i) = (int *) malloc(n * sizeof(int));
        if (*(ms->magic_square + i) == NULL) {
            printf("Error: Memory allocation for MagicSquare structure's matrix row's columns failed!\n");
            exit(1);
        }
        // initialize all elements to default 0
        for (int j = 0; j < n; j++) {
            *(*(ms->magic_square + i) + j) = 0;
        }
    }

    // started from 1
    int num = 1;
    // column in the middle for the base step
    int i = 0, j = n / 2;

    // iterate through number 1 to n^2
    while (num <= n * n) {
        // setup constraints and boundary check for memory
        boundary_check(&i, &j, n);

        // conflict with existent number
        if (*(*(ms->magic_square + i) + j) != 0) {
            // recover column, num shifts down by 1
            i = (i + 2) % n;  // Adjust row
            j = (j - 1 + n) % n;  // Adjust column
            // + n will not affect the val but keep it positive
        } else {
            // assign value to the magic_square
            *(*(ms->magic_square + i) + j) = num++;

            // move towards up and right
            i--;
            j++;
        }
    }

    return ms;
}

/**
 * Writes the values of the given magic square to a file.
 *
 * Pre-conditions:
 * magic_square: Pointer to the magic square structure to write to the file.
 * filename: Name of the output file.
 * Post-conditions: A file named 'filename' will be created or overwritten with the magic square values.
 */
void fileOutputMagicSquare(MagicSquare *magic_square, char *filename) {
    // open output file
    FILE *fp = fopen(filename, "w");
    if (!fp) {
        printf("Error opening file to write!\n");
        exit(1);
    }
    // write the file size at first
    fprintf(fp, "%d\n", magic_square->size);
    for (int i = 0; i < magic_square->size; i++) {
        for (int j = 0; j < magic_square->size; j++) {
            // write each num in square
            fprintf(fp, "%d", *(*(magic_square->magic_square + i) + j));
            if (j != magic_square->size - 1) {
                fprintf(fp, ",");
            }
        }
        fprintf(fp, "\n");
    }

    // close any opened files when it's done using them
    if (fclose(fp) == EOF) {
        perror("Error while closing the file");
        exit(1);
    }
}

/**
 * Main execution point. Generates a magic square of user-specified size and outputs the square
 * to the output filename given as a command line argument.
 *
 * Usage:
 * ./program_name [output_file_name]
 * Pre-conditions:
 * argc: Total number of command-line arguments, should be 2.
 * argv[0]: Program's name.
 * argv[1]: Name of the output file.
 * retval: 0 for successful execution, 1 if there's an error.
 */
int main(int argc, char **argv) {
    // check argc counts correct
    if (argc != 2) {
        printf("Usage: ./my_magic_square <output_filename>\n");
        return 1;
    }

    // get the size of the magic square from the user
    int n = getSize();
    // generate the magic square
    MagicSquare *ms = generateMagicSquare(n);
    // write to the output file
    fileOutputMagicSquare(ms, argv[1]);

    // free all heap memory reversely
    for (int i = 0; i < n; i++) {
        free(*(ms->magic_square + i));
    }
    free(ms->magic_square);
    free(ms);

    return 0;
}
