#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *DELIM = ",";  // commas ',' are a common delimiter character for data strings

/* COMPLETED (DO NOT EDIT):       
 * Read the first line of input file to get the size of that board.
 * 
 * PRE-CONDITION #1: file exists
 * PRE-CONDITION #2: first line of file contains valid non-zero integer value
 *
 * fptr: file pointer for the board's input file
 * size: a pointer to an int to store the size
 *
 * POST-CONDITION: the integer whos address is passed in as size (int *) 
 * will now have the size (number of rows and cols) of the board being checked.
 */
void get_board_size(FILE *fptr, int *size) {
    char *line1 = NULL;
    size_t len = 0;

    if (getline(&line1, &len, fptr) == -1) {
        printf("Error reading the input file.\n");
        free(line1);
        exit(1);
    }

    char *size_chars = NULL;
    // 使用 strtok 函数将line1字符串分割为标记（tokens），其中DELIM定义了分隔标记的字符。
    // size_chars接收第一个标记的指针。
    size_chars = strtok(line1, DELIM);
    *size = atoi(size_chars);

    // free memory allocated for reading first link of file
    free(line1);
    line1 = NULL;
}

/*
 * Returns 1 if and only if the board is in a valid Sudoku board state.
 * Otherwise, returns 0.
 * 
 * A valid row or column contains only blanks or the digits 1-size, 
 * with no duplicate digits, where size is the value 1 to 9.
 * 
 * Note: p2A requires only that each row and each column are valid.
 * 
 * board: heap allocated 2D array of integers 
 * size:  number of rows and columns in the board
 */
int valid_board(int **board, int size) {
    // Validate the size of the board first in the range 1 to 9, inclusive
    if (size <= 0 || size > 9) return 0;

    // dynamically allocate 1-d array row_tags and col_tags to check the duplicate values like flags
    int *row_tags = malloc(10 * sizeof(int));
    int *col_tags = malloc(10 * sizeof(int));

    // Handle memory allocation failure
    if (row_tags == NULL || col_tags == NULL) {
        printf("Memory allocation failed.\n");
        free(row_tags);
        free(col_tags);
        exit(1);
    }

    // firstly initialize all to 0
    memset(row_tags, 0, 10 * sizeof(int));
    memset(col_tags, 0, 10 * sizeof(int));

    // traverse each row and column
    for (int i = 0; i < size; i++) {
        // reuse and reset memory to lower the space complexity, and make sure the isolation of each line's check
        memset(row_tags, 0, 10 * sizeof(int));
        memset(col_tags, 0, 10 * sizeof(int));

        // for each column and row, separately check the current line of the board
        for (int j = 0; j < size; j++) {
            // get the pointers to the current row and column value
            int *row_val_ptr = *(board + i) + j; // start value point by row
            int *col_val_ptr = *(board + j) + i; // start value point by column

            // check this row line
            if (*row_val_ptr != 0) {
                // tags contain only 1 or 0, check the tag to see it's valid or not
                // get 1 / true if row_tags checked that number before, meaning it's duplicate and not valid
                if (*(row_tags + *row_val_ptr)) {
                    free(row_tags);
                    free(col_tags);
                    return 0;
                }
                *(row_tags + *row_val_ptr) = 1; // occurrence for 1
            }

            // check this column line
            if (*col_val_ptr != 0) {
                // tags contain only 1 or 0, check the tag to see it's valid or not
                // get 1 / true if col_tags checked that number before, meaning it's duplicate and not valid
                if (*(col_tags + *col_val_ptr)) {
                    free(row_tags);
                    free(col_tags);
                    return 0;
                }
                *(col_tags + *col_val_ptr) = 1; // occurrence for 1
            }
        }
    }

    // valid if no duplicate nums discovered along the 2-d array, free allocation
    free(row_tags);
    free(col_tags);
    return 1;
}


/*
 * This program prints "valid" (without quotes) if the input file contains
 * a valid state of a Sudoku puzzle board wrt to rows and columns only.
 *
 * A single CLA is required, which is the name of the file 
 * that contains board data is required.
 *
 * argc: the number of command line args (CLAs)
 * argv: the CLA strings, includes the program name
 *
 * Returns 0 if able to correctly output valid or invalid.
 * Only exit with a non-zero result if unable to open and read the file given.
 */
int main(int argc, char **argv) {

    // Check if number of command-line arguments is correct.
    if (argc != 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        return 1;
    }

    // Open the file and check if it opened successfully.
    FILE *fp = fopen(*(argv + 1), "r");
    if (fp == NULL) {
        printf("Can't open file for reading.\n");
        exit(1);
    }

    // Declare local variables.
    int size;

    // Call get_board_size to read first line of file as the board size.
    get_board_size(fp, &size);
    // Dynamically allocate a 2D array for given board size.
    // DO NOT create a 1D array of ints on the stack or heap
    // You must dyamically create a 1D arrays of pointers to other 1D arrays of ints
    int **array;

    // assign memory for the array
    array = malloc(size * sizeof(int *));
    if (array == NULL) {
        printf("Memory allocation failed.\n");
        exit(1);
    }

    // assign memory for each 1d array
    for (int i = 0; i < size; i++) {
        *(array + i) = malloc(size * sizeof(int));
        if (*(array + i) == NULL) {
            printf("Memory allocation failed.\n");
            // free previously allocated memory before exit
            // if memory leak happened at this subarray
            for (int j = 0; j < i; j++) {
                free(*(array + j));
            }
            free(array);
            exit(1);
        }
    }

    // Read the rest of the file line by line.
    // Tokenize each line wrt the delimiter character
    // and store the values in your 2D array.
    char *line = NULL;
    size_t len = 0;
    char *token = NULL;
    for (int i = 0; i < size; i++) {

        if (getline(&line, &len, fp) == -1) {
            printf("Error while reading line %i of the file.\n", i + 2);
            free(line);
            // free all dynamic memory for array.
            for (int j = 0; j < i; j++) {
                free(array[j]);
            }
            free(array);
            exit(1);
        }

        token = strtok(line, DELIM);
        for (int j = 0; j < size; j++) {
            // to initialize your 2D array.
            /* ADD ARRAY ACCESS CODE HERE */
            *(*(array + i) + j) = atoi(token);
            token = strtok(NULL, DELIM);
        }
    }

    // Call the function valid_board and print the appropriate
    // output depending on the function's return value.

    int res = valid_board(array, size);

    // Free all dynamically allocated memory.
    for (int i = 0; i < size; i++) {
        free(*(array + i));
    }

    free(array);
    free(line);

    //Close the file.
    if (fclose(fp) != 0) {
        printf("Error while closing the file.\n");
        exit(1);
    }

    if (res == 1) printf("valid\n");
    else printf("invalid\n");
    return 0;
}
