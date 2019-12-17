public class Pair<A, B> {
	A fr;
	B sec;
	
	Pair(A a, B b){
		this.fr = a;
		this.sec = b;
	}
	
	public A getFirst(){
		return this.fr;
	}
	
	public B getSec(){
		return this.sec;
	}
	
	public String toString(){
		return getFirst() + " " + getSec();
	}
}
