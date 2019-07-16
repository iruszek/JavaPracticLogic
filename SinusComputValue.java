package ProgramOblicz;
import java.lang.Math;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * It is a program calculating the trigonometric
 *  function sinus in radians,  based on the sequence sin x = x - xpow3/3!+xpow5/5!-xpow7/7! etc...
 *  You can choose the precision by entering the integer value for the sequence
 *   of fractions as the second variable.
 *   The program checks the value with Java.Math sin method
 *   It is my practice of the Java with http://www.ntu.edu.sg/home/ehchua/programming/
 *  java/j2a_basicsexercises.html
 *   
 * To program wykonujący obliczanie funkcji sinus od wartości w radianach, 
 * bazuje na wzorze sin x = x - xpow3/3!+xpow5/5!-xpow7/7! itd...
 *  Drugą liczbą 'int' jest wybór precyzji obliczeń. Program sprawdza poprawność danych
 *  porównując z metodą sin w Java.Math 
 *  Realizacja ćwiczenia z portalu http://www.ntu.edu.sg/home/ehchua/programming/
 *  java/j2a_basicsexercises.html
 */
public class SinusComputValue {
	static double x;
	static float katWst;
	public static void main(String[] args) {
		System.out.println("Witamy w programie obliczjącym wartość sinus dla kąta w radianach.");
		System.out.println("Podaj wielkość kąta w radianach: ");
		Scanner rad = new Scanner(System.in);
		
		try {		
			x=rad.nextDouble();		
			katWst=(float) Math.toDegrees(x);						
			System.out.printf("W stopniach podany kąt ma wielkość %.2f stopni %n", katWst);
			
			//przeliczenie kąta większego od 2*PI rad
			if(x>2*Math.PI) {	
				int k = (int) ( x / (2*Math.PI));
				x = x%(2*Math.PI);		
				if(k>1) {
					System.out.printf("Podany kąt jest większy od 2PI rad i wynosi %g + %d*PI czyli %.2f + %d*360 stopni%n",x,k,(float)Math.toDegrees(x),k);
				}
				else System.out.printf("Podany kąt to %g rad + 2PI czyli %.2f + 360 stopni%n",x,(float)Math.toDegrees(x));
				}
			
			//Pobieranie liczby całkowitej dokładności obliczeń
			System.out.println("Podaj liczbę całkowitą dokłądności obliczenia - n");
			System.out.println("lub wybierz 0 domyślna wartość: 7");
			int numTerms=0;
			numTerms=rad.nextInt();
				
		//Sprawdzenie decyzji użytkownika (czy domyślna wartość?)
		if(numTerms==0) {				
			numTerms = 7;
			System.out.println("Sinus z dokładnością 7 wynosi: \t"+  ObliczSin(x,numTerms));
			System.out.println("Sinus java.lang.Math wynosi: \t"+ Math.sin(x));
		}
		// Obliczanie  dokładnością podaną przez użytkownika
		else {
			System.out.println("Sinus z precyzją: "+numTerms +" wynosi: "+  ObliczSin(x,numTerms));
			System.out.println("Sinus java.lang.Math wynosi: "+ Math.sin(x));
		}
		}catch( InputMismatchException e) {
			System.out.println("Podałeś dane w niewłaściwym formacie");
			
		}
		rad.close();
	}
	
	
	static double ObliczSin(double a, int b) {
		double czynnik;					//Tutaj cząstkowa wartość obliczeń
		double SinusW=0; 				//całka cząstek równania -(a**3/3!)+(a**5/5!)-(a**7/7!)+ itd.
		
		if (b%2==0)	{	b=b+1;}						//Korekta liczby całkowitej do nieparzystej
		
		for (int n=b; n>1; n-=2)
		{
			czynnik=1;					//początkowa wartość czynnika do obliczeń (a/n)*(a/n-1)..
			
			if ((n+1)%4!=0) {			//Jeżeli czynnik a**3 a**7 itp to znak minus '-'
				for (int i= n; i>0; i--){
					czynnik *= (a/i);	//i=n
				}
				SinusW -= czynnik; 		//odejmuje czynnik od wyniku całkowitego
			}
			else {						// Jeżeli czynnik a**5 a**9 itd to znak plus '+'
				for (int i= n; i>0; i--){
				czynnik *= (a/i);
				}
				SinusW += czynnik;		//dodaje czynnik do wyniku całkowitego
			}
		}
		return  a-SinusW;				//Oblicza wynik końcowy wg. wzoru i zwraca	
	}
}
