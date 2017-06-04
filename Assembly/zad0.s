.intel_syntax noprefix
	.text
	.globl _start

//#### ebx->wskaznik 1szego arg.; ecx->ilosc znakow wyrazu; al->kolejne znaki wyrazu; 
_start:
  mov edx, 0
  mov ebp, esp
  mov ebx,[ebp+8];//ebx wskazuje na poczatek pierwszego argumentu
  mov ecx,0;//w ecx bedzie liczba oznaczajaca ilosc znakow wyrazu
  mov al,[ebx];//Wczytujemy pierwszy znak
 
//#### ustaw: odpowiada za konczenie programu oraz ustawianie ebx na poczatek kolejnych wyrazow
ustaw:;//Tutaj cofamy sie po wypisaniu wyrazu y razy
	cmp al,0;//Jezeli doszlismy do konca napisu to konczymy
	jz koniec
 	add ebx,ecx;//Ustawiamy ebx na poczatek nastepnego niewypisanego wyrazu
 	mov ecx ,0;//Zerujemy dlugosc wyrazu
 	
//#### licz: odpowiada za zliczanie ilosci znakow wyrazu az do spacji/konca wyrazu.
licz:;//Tutaj cofamy sie jezeli nie znalezlismy konca wyrazu ani konca napisu 
 	mov al,[ebx+ecx];//Wczytujemy nastepny znak
 	inc ecx;//Zwiekszamy licznik znakow w wyrazie
	cmp al,' ';//Jezeli znalezlismy spacje to konczymy zliczanie znakow
	jz spacja
 	cmp al,0;//Jezeli doszlismy do konca napisu to konczymy zliczanie ale nie konczymy programu(wypiszemy ostatni wyraz)
 	jz spacja
  	jmp licz;//Jezeli nie znalezlismy konca ani spacji to zliczamy dalej znaki
  	
//#### spacja: wrzuca wartosci rejestrow na stos; przypisuje wkaznik na y do ecx;
spacja:
	push eax
	push ebx
	push ecx
	mov edx,0
	mov ecx, [ebp+12] ;//Zapisujemy wskaznik na drugi argument w ecx
	
//#### czytaj odpowiada za przeczytanie drugiego parametru i zamienienie ja na liczbe. Liczba znajdzie sie w rejestrze edx;
czytaj:
	mov bl, byte ptr [ecx] ;//Zapisujemy znak jako liczbe (ASCII)
	sub bl, '0' ;//Odejmujemy kod ASCII zera(kod ASCII cyfry minus kod zera to ta cyfra)
	movzx ebx, bl ;//Wpisujemy cyfre do ebx
	add edx, ebx;//Zapisujemy wynik w edx
	inc ecx;//Przesuwamy wskaznik na nastepny znak
	mov bl, [ecx];//Czytamy znak
	cmp bl,0;//Jezeli doszlismy do konca to mamy wynik
	jz liczba
	imul edx,10;//Jezeli liczba ma wiecej znakow to mnozymy razy 10 zeby dodac nowy znak
	jmp czytaj
	
//pobieramy wartosci ze stosu z powrotem do rejestrow, sprawdzamy czy edx jest 0;
liczba:
	pop ecx
	pop ebx
	pop eax

 	cmp edx,0;//Jezeli mamy wypisac kazdy wyraz 0 razy to koniec
 	jz koniec
 	
//#### druk: odpowiada za wypisywanie slowa y razy;
druk:
 	cmp edx,0;//Sprawdzamy czy wypisalismy wyraz tyle razy ile wskazywal drugi parametr;
 	jz ustaw;//Jezeli wypisalismy go y razy to wracamy do czytania nastepnego wyrazu
 	push eax
 	push ebx
 	push ecx
 	push edx
 	mov eax, 4
 	mov edx, ecx;//W edx mamy ile znakow wypisac
 	mov ecx, ebx;//W ecx mamy adres lancucha ktory wypisujemy
 	mov ebx, 1
 	int 0x80
 	pop edx
 	pop ecx
 	pop ebx
 	pop eax
 	dec edx;//Wypisalismy wyraz wiec zmniejszamy liczbe wyrazow do wypisania o 1
 	cmp al,0;//Jezeli wypisujemy ostatni wyraz to po nim nie ma spacji wiec musimy ja dodac
 	jnz ostatni;//ta czesc kodu odpowiada za wypisanie spacji
	push eax
	push ebx
	push ecx
	push edx
	mov eax,4
	mov ebx,1
	mov ecx,offset pusty
	mov edx,1
	int 0x80
	pop edx
	pop ecx
	pop ebx
	pop eax
	
ostatni:
 	jmp druk;//Wracamy do druk zeby zdecydowac czy wypisujemy wyraz jeszcze raz czy szukamy nastepnego

koniec:
	mov eax, 1
	mov ebx, 0
	int 0x80;//RETURN 0;

	.data
msg:	.ascii "\n"
pusty: .asciz " "
