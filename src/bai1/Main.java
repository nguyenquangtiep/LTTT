package bai1;
import java.util.*;

public class Main {
	
	static float[][] pXY;
	static float[] pX, pY;
	static int m, n;
	static Scanner sc = new Scanner(System.in);
	
	public static void main (String[] args) {
		
		// a) Nhap vao ma tran xac suat ket hop P(x, y) co MxN voi M va N nhap tu ban phim
		input();
		showMatrix(); // hien thi ma tran da nhap
		
		// b) Tinh va hien thi
		float hX = entropyX();
		float hY = entropyY();
		float hXY = jointEntropy();
		
		// H(X)
		System.out.println("H(X) = " + hX + " bits.");
		
		// H(Y)
		System.out.println("H(Y) = " + hY + " bits.");
		
		// H(X|Y) = H(X,Y) - H(Y)
		System.out.println("H(X|Y) = " + (hXY - hY) + " bits.");
		
		// H(Y|X) = H(X,Y) - H(X)
		System.out.println("H(Y|X) = " + (hXY - hX) + " bits.");
		
		// H(X,Y)
		System.out.println("H(X,Y) = " + hXY + " bits.");
		
		// H(Y) - H(Y|X) = H(Y) - (H(X,Y) - H(X))
		System.out.println("H(Y) - H(Y|X) = " + (hY + hX - hXY) + " bits.");
		
		// I(X,Y) = H(Y) - H(Y|X)
		System.out.println("I(X,Y) = " + (hY + hX - hXY) + " bits.");
		
		// c) Tinh D(P(x)||P(y)) va D(P(y)||P(x))
		
		// D(P(x)||P(y))
		System.out.println("D(P(x)||P(y)) = " + relativeEntropy(pX, pY));
		
		// D(P(y)||P(x))
		System.out.println("D(P(y)||P(x)) = " + relativeEntropy(pY, pX));
	}
	
	// 
	static void input() {
		System.out.println("Nhap kich co MxN cua ma tran P");
		System.out.print("M = ");
		m = sc.nextInt();
		System.out.print("N = ");
		n = sc.nextInt();
		pXY = new float[n][m];
		String tmp;
		String[] a;
		Fraction f;
		pX = new float[m];
		pY = new float[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print("Nhap xac suat P[" + (i+1) + "][" + (j+1) + "]: ");
				tmp = sc.next();
				a = tmp.split("/");
				if (a.length == 1) {
					f = new Fraction(Integer.valueOf(a[0]), 1);
				}
				else
				{
					f = new Fraction(Integer.valueOf(a[0]), Integer.valueOf(a[1]));
				}
				pXY[i][j] = f.getFraction();
				while (pXY[i][j] < 0) {
					System.out.print("Khong duoc nhap xac suat nho hon khong.\nNhap lai xac suat P[" + (i+1) + "][" + (j+1) + "]: ");
					tmp = sc.next();
					a = tmp.split("/");
					if (a.length == 1) {
						f = new Fraction(Integer.valueOf(a[0]), 1);
					}
					else
					{
						f = new Fraction(Integer.valueOf(a[0]), Integer.valueOf(a[1]));
					}
					pXY[i][j] = f.getFraction();
				}
				pY[i] += pXY[i][j];
				pX[j] += pXY[i][j];
			}
		}
	}
	
	static void showMatrix() {
		// Hien thi ma tran P(x,y)
		System.out.println("Ma tran P(x,y)");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(pXY[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
		
		// Hien thi P(x)
		System.out.println("Ma tran P(x)");
		for (int i = 0; i < m; i++) {
			System.out.print(pX[i] + "\t");
		}
		System.out.println();
		
		// Hien thi P(y)
		System.out.println("Ma tran P(y)");
		for (int i = 0; i < n; i++) {
			System.out.print(pY[i] + "\t");
		}
		System.out.println();
	}
	
	static float entropyX() {
		float h = 0;
		for (int i = 0; i < m; i++) {
			if (pX[i] == 0) continue;
			h -= pX[i] * (Math.log(pX[i]) / Math.log(2));
		}
		return h;
	}
	
	static float entropyY() {
		float h = 0;
		for (int i = 0; i < n; i++) {
			if (pY[i] == 0) continue;
			h -= pY[i] * (Math.log(pY[i]) / Math.log(2));
		}
		return h;
	}
	
	static float jointEntropy() {
		float h = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (pXY[i][j] == 0) continue;
				h -= pXY[i][j] * (Math.log(pXY[i][j]) / Math.log(2));
			}
		}
		return h;
	}
	
	static float relativeEntropy(float[] p, float[] q) {
		float d = 0;
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < q.length; j++) {
				if (p[i] != 0 && q[j] != 0) {
					d += p[i] * (Math.log(p[i] / q[j]) / Math.log(2));
				}
			}
		}
		return d;
	}
}