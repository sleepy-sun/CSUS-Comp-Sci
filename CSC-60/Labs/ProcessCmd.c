/* ----------------------------------------------------------------- */
/*                  ProcessCmd                                       */
/* ----------------------------------------------------------------- */
// Javier Briseno

#include "lab9_10.h"

void ProcessCmd(int argc, char **argv) {                             

    /* Step 1: Call Redirection to deal with operators:             */
    /* < , or  >, or both                                           */
    Redirection(argc,argv);
    int value = execvp(argv[0], argv);

    /* Step 2: perform system call execvp to execute command        */
    /* Hint: Please be sure to review execvp.c sample program       */
    if (value == -1) {                                        
       fprintf(stderr, "Error on the exec call\n");              
       _exit(EXIT_FAILURE);                                      
     }                                                            
    return;
    }
/* ----------------------------------------------------------------- */
