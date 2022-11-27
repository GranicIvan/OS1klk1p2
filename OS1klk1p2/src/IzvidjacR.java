
public class IzvidjacR implements Runnable {

	Suma suma;
	Kamp kamp;

	public IzvidjacR(Suma suma, Kamp kamp) {
		this.kamp = kamp;
		this.suma = suma;
	}
	
	@Override
	public void run() {
		int sakupljenaDrva = 0;
		for(int i = 0 ; i< 25 ; i++) {
			sakupljenaDrva = sakupljenaDrva + suma.traziDrva();
		}

		kamp.donesiDrva(sakupljenaDrva);
	}//run

}
