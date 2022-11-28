import java.util.LinkedList;

/*
 * Napisati program koji kreira jedan izvidjacki kamp i 24 izvidjaca. 12 izvi-
 * djaca je definisano pomocu klase Thread a 12 pomocu interfejsa Runnable i svi
 * su korisnicki procesi. Kamp nije zaseban proces. (5 poena)
 * 
 * 12 izvidjaca definisano pomocu klase Thread po pokretanju idu u sumu da traze
 * pecurke, tj. pozivaju metod Suma::traziPecurke 20 puta, posle cega donose sve
 * pecurke koje su pronasli nazad u kamp (pomocu metoda Kamp::donesiPecurke).
 * Ako ih neko prekine u potrazi za pecurkama, hitno se vracaju u kamp bez
 * pecuraka i regularno zavrsavaju svoj rad. (5 poena)
 * 
 * 12 izvidjaca definisano pomocu interfejsa Runnable po pokretanju idu u sumu
 * da traze drva za potpalu, tj. pozivaju metod Suma::traziDrva 25 puta, posle
 * cega donose sva sakupljena drva nazad u kamp (Kamp::donesiDrva). Ako ih neko
 * prekine u potrazi, izvidjaci se ne obaziru na to i regularno zavrsavaju svoj
 * rad kada obave svih 25 potraga. (5 poena)
 * 
 * Sinhronizovati klasu Kamp tako da se ni u kom slucaju ne izgube pecurke ili
 * drva. Takodje, dodati da izvidjaci po povratku u kamp, pre nego sto zavrse
 * rad, sacekaju da se vrate u kamp i svi ostali izvidjaci. (10 poena) 
 * 
 * Obratiti paznju na elegantnost i objektnu orijentisanost realizacije i stil
 * resenja. Za program koji se ne kompajlira, automatski se dobija 0 poena bez
 * daljeg pregledanja.
 */
public class Program {

	public static void main(String[] args) {
		
		
		Suma suma = new Suma();
		Kamp kamp = new Kamp();
		
		LinkedList<IzvidjacT> izvidjaciPecurke = new LinkedList<IzvidjacT>();
		LinkedList<Thread> izvidjaciDrva = new LinkedList<Thread>();
		
		
		//Pravimo izvidjace i dodajemo ih u listu
		for(int i = 0; i < 12 ; i++) {
			IzvidjacT novi = new IzvidjacT(suma, kamp);	
			novi.setName("Pecurkar " + i );
			novi.start();
			izvidjaciPecurke.add( novi);						
		}
		
		for(int i = 0; i < 12 ; i++) {
			Thread novi = new  Thread(new IzvidjacR(suma, kamp));
			novi.setName("drvoseca " + i );
			novi.start();			
			izvidjaciDrva.add( novi );				
		}
		
		//ovako cekamo da se svi zavrse
		for(int i = 0; i < 12 ; i++) {
			try {
				izvidjaciPecurke.get(i).join();	
				izvidjaciDrva.get(i).join();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		kamp.ispis();
		System.err.println("Main se zavrsio!!!");
		
	}
}



