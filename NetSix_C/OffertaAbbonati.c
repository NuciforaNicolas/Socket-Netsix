#include "OffertaAbbonati.h"
#include <string.h>
#include <stdio.h>

char* serietv[6] = {"Friends", "How i met your mother", "Game of thrones", "Breaking bad", "CSI", "The boys"};
int episodi[6] = {52, 48, 50, 120, 250, 20};

int getDisponibilita(char* titolo, int episodio){
    for(int i = 0; i < 6; i++){
        if(strncmp(serietv[i], titolo, strlen(serietv[i])) == 0){
            if(episodio <= episodi[i])
                return 1;
            else
                return 0;
        }
    }

    return -1;
}