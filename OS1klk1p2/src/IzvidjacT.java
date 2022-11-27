import java.util.Iterator;

public class IzvidjacT extends Thread {

	Suma suma;
	Kamp kamp;

	public IzvidjacT(Suma suma, Kamp kamp) {
		this.kamp = kamp;
		this.suma = suma;
	}
	
	
	@Override
	public void run() {

		int nadjenjePecurke = 0;
		for(int i = 0; i <20 && !interrupted(); i++) {
			nadjenjePecurke = nadjenjePecurke + suma.traziPecurke();
		}
		if(!interrupted()){
			kamp.donesiPecurke(nadjenjePecurke);			
		}
	}//run
	
}
