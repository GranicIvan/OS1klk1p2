class Kamp {

	private int pecurke = 0;
	private int drva = 0;
	private int gotovi = 0; // broj izvidjaca koji su zavrsili

	synchronized public void donesiPecurke(int koliko) {
		pecurke += koliko;
		stampajPoruku("je dodao drva");
	}

	synchronized public void donesiDrva(int koliko) {
		drva += koliko;
		stampajPoruku("je dodao drva");
	}
	
	synchronized public void ispis() {
		System.out.println("Trenutno stanje u kampu je: Pecurke: " + pecurke + ",  drva: " + drva);
	}
	
	synchronized private void stampajPoruku(String poruka) {
		System.out.printf("%-12s %-20s Stanje u kampu: pecurke:%3d  drva:%3d %n",
				Thread.currentThread().getName(),
				poruka,
				pecurke,				
				drva);
	}//stampajPoruku
		
	
	synchronized public void cekajOstale() throws InterruptedException {
		gotovi++;
		notifyAll();
		
		while(gotovi != 24) {
			wait();
		}
			
	}//cekajOstale
}