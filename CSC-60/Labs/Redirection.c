/* ----------------------------------------------------------------- */
/*                  Redirection                                      */
/* ----------------------------------------------------------------- */
// Javier Briseno

#include "lab9_10.h"

void Redirection(int argc, char *argv[])
{
    int i;		// loop counter
    int out = 0;  // track position of out location redirection >
    int in = 0;   // track position of in location redirection <
    int fd;
    
    for(i = 0; i < argc; i++) {
      if (strcmp(argv[i], ">") == 0) {
        if (out != 0) {
              fprintf(stderr, "Error: Cannot output to more than one file. \n");
              _exit(EXIT_FAILURE);
        }
        else if (i == 0) {
            fprintf(stderr,"Error: No command entered. \n");
            _exit(EXIT_FAILURE);
        }
        out = i;
     }
     else if (strcmp(argv[i], "<") == 0) {
         if (in != 0) {
             fprintf(stderr,"Error: Cannot input from more than one file \n");
             _exit(EXIT_FAILURE);
         }
         else if (i == 0) {
             fprintf(stderr, "Error: No command entered \n");
             _exit(EXIT_FAILURE);
         }
         in = i;
       }
      }//end of for loop

      if (out != 0) {
          if (argv[out + 1] == NULL) {
              fprintf(stderr, "Error: There is no file");
              _exit(EXIT_FAILURE);
          }
          fd = open(argv[out + 1], O_WRONLY | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);

          if (fd == -1) {
              fprintf(stderr, "Error opening out file\n");
              _exit(EXIT_FAILURE);   
          }
          dup2(fd,1);
          close(fd);
          argv[out] = NULL;
      }

      if(in != 0) {
          if (argv[in + 1] == NULL) {
              fprintf(stderr, "No file\n");
              _exit(EXIT_FAILURE);
          }
          fd = open(argv[in + 1], O_RDONLY, S_IRUSR | S_IWUSR);
          if (fd == -1) {
              fprintf(stderr, "Error opening in file\n");
              _exit(EXIT_FAILURE);
          }
          dup2(fd,0);
          close(fd);
          argv[in] = NULL;
      }





    
}  /*end of function*/

