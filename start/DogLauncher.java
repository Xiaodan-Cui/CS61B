
public class DogLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dog smallDog;
		new Dog(20);
		smallDog=new Dog(5);
		Dog hugeDog=new Dog(150);
		smallDog.makeNoise();
		hugeDog.makeNoise();
		Dog[] dogs=new Dog[2];
		dogs[0]=new Dog(8);
		dogs[1]=new Dog(20);
		dogs[0].makeNoise();
	}

}
