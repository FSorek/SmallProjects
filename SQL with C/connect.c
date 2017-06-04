#include <stdlib.h>
#include <libpq-fe.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#define SIMPLE_SIZE 40
#define SAFE_SIZE 255
void doSQL(PGconn *conn, char *command)
{
  PGresult *result;

  printf("%s\n", command);

  result = PQexec(conn, command);
  printf("status is     : %s\n", PQresStatus(PQresultStatus(result)));

  switch(PQresultStatus(result)) {
  case PGRES_TUPLES_OK:
    {
      int n = 0, r = 0;
      int nrows   = PQntuples(result);
      int nfields = PQnfields(result);
      printf("number of rows returned   = %d\n", nrows);
      printf("number of fields returned = %d\n", nfields);
      for(r = 0; r < nrows; r++) {
	for(n = 0; n < nfields; n++)
	  printf(" %s = %s", PQfname(result, n),PQgetvalue(result,r,n));
	printf("\n");
      }
    }
  }
  PQclear(result);
}

void HTMLprint(PGconn *myConnection, char *tableName, char *outName)
{
	PGresult *result;
	char sqlStmt[150];
	sprintf(sqlStmt,"SELECT * FROM %s",tableName);
  	result = PQexec(myConnection, sqlStmt);
  	switch(PQresultStatus(result)) 
	{
  		case PGRES_TUPLES_OK:
   		{
      		int n = 0, m = 0;
     		int rows   = PQntuples(result); 
     		int columns = PQnfields(result); 
     		FILE *fileOut = fopen(outName, "w+");
			fprintf(fileOut,"<!DOCTYPE html> \n <html lang=\"pl\"> \n <head> \n <meta charset=\"utf-8\"> \n <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> \n <title>TABLE CONTENT</title> \n <style> \n"); //header
			
			//CSS
			fprintf(fileOut,"table,td{ border: 2px solid black; background:lightblue; border-collapse: collapse;} \n td, th{ padding: 5px; } \n th{ border: 2px solid black; border-style: solid dashed solid dashed; text-align: left; font-family: Arial Black} \n body{background-color: Gray} </style></head>\n");
			fprintf(fileOut,"<body> \n <table> \n");
			fprintf(fileOut,"<tr>\n");
			
			for(n=0;n<columns;n++)
				fprintf(fileOut,"<th>%s</th>\n",PQfname(result, n));
			fprintf(fileOut,"</tr>\n");
			
      		for(m=0;m<rows;m++) 
			{
				fprintf(fileOut,"<tr>\n");
				for(n=0;n<columns;n++)
	  				fprintf(fileOut,"<th>%s</th>\n",PQgetvalue(result,m,n));
				fprintf(fileOut,"</tr>\n");
      		}
      		fprintf(fileOut,"</table> \n </body> \n </html>");
      		fclose(fileOut);
    	}
  	}
  	printf("\n");
  	PQclear(result);
}

int main(int argc, char *argv[])
{
	//########################### VARIABLES
	char *password;
	
	char username[SIMPLE_SIZE];
	char dbName[SIMPLE_SIZE];
	char fileName[SIMPLE_SIZE];
	char nonExtFileName[SIMPLE_SIZE];

	char sqlString[SAFE_SIZE];
	char connectionInfo[SAFE_SIZE];
	
	char *tableArray[SIMPLE_SIZE];
	char columnNamesArray[SAFE_SIZE][SAFE_SIZE];
	
	char line[SAFE_SIZE];
	char fReadChar;
	
	int i=0;
	int columns=0;
	
	FILE *file;
	
	//########################### INPUT READING
	printf("Podaj nazwe uzytkownika: ");
	fgets(username, SIMPLE_SIZE, stdin);
	
	password = getpass("Podaj haslo: ");
	
	printf("Podaj nazwe bazy danych: ");
	fgets(dbName, SIMPLE_SIZE, stdin);
	
	printf("Podaj nazwe pliku(bez rozszerzenia): ");
	scanf(" %s", &fileName);
	strcpy(nonExtFileName,fileName);
	
	sprintf(connectionInfo, "host=localhost port=5432 dbname=%s user=%s password=%s",dbName , username, password);
	PGconn *myconnection = PQconnectdb(connectionInfo);

  // sprawdzamy status
  if(PQstatus(myconnection) == CONNECTION_OK) {
  	printf("connection successful!\n");
  	
  	//########################### FILE OPERATIONS
	strcat(fileName, ".csv"); // add extension to the fileName
	if((file = fopen(fileName, "r")) == NULL)
		{
			printf("Nie znaleziono pliku o nazwie %s! Koncze dzialanie programu.", fileName);
			return EXIT_SUCCESS;
		}
	fscanf(file,"%s",line);

	//########################### CREATE ARRAY WITH TABLE NAMES
  	tableArray[i] = strtok(line,";");
    
	while(tableArray[i]!=NULL)
	{
		tableArray[++i] = strtok(NULL,";");
		columns++;
	}	
	
	for(i=0;i<columns;i++)
		strcpy(columnNamesArray[i],tableArray[i]);
	
	sprintf(sqlString,"DROP TABLE %s", nonExtFileName);
  	doSQL(myconnection, sqlString);
  	
  	sprintf(sqlString,"CREATE TABLE %s(%s INTEGER UNIQUE)", nonExtFileName, tableArray[0]);
	doSQL(myconnection,sqlString);
	for(i=1;tableArray[i]!=NULL;i++){
		sprintf(sqlString,"ALTER TABLE %s ADD %s VARCHAR(20)", nonExtFileName, tableArray[i]);
		doSQL(myconnection,sqlString);
	}
  	
	//########################### ADD ENTRIES TO TABLE
  	while(fscanf(file,"%s",line) == 1)
  	{
  		i=0;
  		tableArray[i] = strtok(line,";");
		for(;i<columns;)
		{
			tableArray[++i] = strtok(NULL,";");
		}	
		for(i=0;i<columns;i++) // extend column size if needed
		{
			if(strlen(tableArray[i])>20)
			{
				sprintf(sqlString,"ALTER TABLE %s ALTER COLUMN %s TYPE VARCHAR(%d)", nonExtFileName, columnNamesArray[i], (int)strlen(tableArray[i]));
				doSQL(myconnection, sqlString);
			}
		}
		sprintf(sqlString,"INSERT INTO %s VALUES(%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", nonExtFileName, tableArray[0], tableArray[1], tableArray[2], tableArray[3], tableArray[4], tableArray[5], tableArray[6], tableArray[7], tableArray[8]);
		doSQL(myconnection, sqlString);
	}
	
	char input;
	char recordString[columns][SIMPLE_SIZE] ;

  	int boolInput = 0;
  	while(boolInput == 0)
  	{
  		printf("Czy chcesz dodac rekord do tabeli? T/N    ");
  		scanf(" %c", &input);
  		input = toupper(input);
  		
	  	if(input != 'T' && input != 'N')
	  	{
	  		printf("Podano nieprawidlowy znak.\n");
		}
		else if(input == 'T')
		{
			for(i=0;i<columns;i++)
			{
				printf("Podaj %s: ", columnNamesArray[i]);
				scanf(" %s", &recordString[i]);
			}
			sprintf(sqlString, "INSERT INTO %s VALUES(%s, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", nonExtFileName, recordString[0], recordString[1], recordString[2], recordString[3], recordString[4], recordString[5], recordString[6], recordString[7], recordString[8]);
			doSQL(myconnection, sqlString);
		}
		else
		{
			boolInput = 1;
		}
  	}
    HTMLprint(myconnection,argv[1],argv[2]);
  }
  else
    printf("connection failed: %s\n", PQerrorMessage(myconnection));

  fclose(file);
  PQfinish(myconnection);
  return EXIT_SUCCESS;
}
