

public class Fraction {
	
	private int numerator;
	private int denominator;
	
	public Fraction() {
		this.numerator = 0;
		this.denominator = 1;
	}
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	boolean isValid() {
		if (this.getDenominator() == 0) {
			return false;
		}
		else
			return true;
	}
	
	public float getFraction() {
		if (this.isValid()) {
			return (float)this.getNumerator() / this.getDenominator();
		}
		else
		{
			return 0;
		}
	}
}
