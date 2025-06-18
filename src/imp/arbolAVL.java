package imp;

import api.ABBTDA;

public class arbolAVL implements ABBTDA {

	class NodoABB {
		int info;
		ABBTDA hijoIzq;
		ABBTDA hijoDer;
		int factorBalance;
	}

	NodoABB raiz;

	public int raiz() {
		return raiz.info;
	}

	public ABBTDA hijoIzq() {
		return raiz.hijoIzq;
	}

	public ABBTDA hijoDer() {
		return raiz.hijoDer;
	}

	public boolean arbolVacio() {
		return (raiz == null);
	}

	private int mayor(ABBTDA a) {
		if (a.hijoDer().arbolVacio())
			return a.raiz();
		else
			return mayor(a.hijoDer());
	}

	private int menor(ABBTDA a) {
		if (a.hijoIzq().arbolVacio())
			return a.raiz();
		else
			return mayor(a.hijoIzq());
	}

	public int altura() {
		if (arbolVacio()) {
			return 0;
		} else {
			int altIzq = raiz.hijoIzq.altura();
			int altDer = raiz.hijoDer.altura();
			return 1 + Math.max(altIzq, altDer);
		}
	}

	public void inicializarArbol() {
		raiz = null;
	}

	public void agregarElem(int x) {
		raiz = agregarYBalancear(raiz, x);
	}

	public void eliminarElem(int x) {
	    raiz = eliminarYBalancear(raiz, x);
}

	private NodoABB eliminarYBalancear(NodoABB nodo, int x) {
		if (nodo == null) return null;

		if (x < nodo.info) {
			((arbolAVL) nodo.hijoIzq).raiz = eliminarYBalancear(((arbolAVL) nodo.hijoIzq).raiz, x);
		} else if (x > nodo.info) {
			((arbolAVL) nodo.hijoDer).raiz = eliminarYBalancear(((arbolAVL) nodo.hijoDer).raiz, x);
		} else {
			// Encontramos el nodo a eliminar

			// Caso 1: nodo sin hijos
			if (nodo.hijoIzq.arbolVacio() && nodo.hijoDer.arbolVacio()) {
				return null;
			}
			// Caso 2: nodo con un solo hijo
			else if (nodo.hijoIzq.arbolVacio()) {
				return ((arbolAVL) nodo.hijoDer).raiz;
			}
			else if (nodo.hijoDer.arbolVacio()) {
				return ((arbolAVL) nodo.hijoIzq).raiz;
			}
			// Caso 3: nodo con dos hijos
			else {
				// Buscar sucesor: el menor del subárbol derecho
				int sucesor = menor(nodo.hijoDer);
				nodo.info = sucesor;
				((arbolAVL) nodo.hijoDer).raiz = eliminarYBalancear(((arbolAVL) nodo.hijoDer).raiz, sucesor);
			}
		}
		return verificarybalancear(nodo);
	}

	private NodoABB agregarYBalancear(NodoABB nodo, int x) {
		if (nodo == null) {
			NodoABB nuevo = new NodoABB();
			nuevo.info = x;
			nuevo.hijoIzq = new arbolAVL();
			nuevo.hijoIzq.inicializarArbol();
			nuevo.hijoDer = new arbolAVL();
			nuevo.hijoDer.inicializarArbol();
			nuevo.factorBalance = 0;
			return nuevo;
		}

		if (x < nodo.info) {
			((arbolAVL) nodo.hijoIzq).raiz = agregarYBalancear(((arbolAVL) nodo.hijoIzq).raiz, x);
		} else if (x > nodo.info) {
			((arbolAVL) nodo.hijoDer).raiz = agregarYBalancear(((arbolAVL) nodo.hijoDer).raiz, x);
		} else {
			return nodo; // ya existe
		}

		return verificarybalancear(nodo);
	}

	private NodoABB verificarybalancear(NodoABB nodo) {
		int altIzq = nodo.hijoIzq.altura();
		int altDer = nodo.hijoDer.altura();
		int balance = altDer - altIzq;

		if (balance > 1) {
			NodoABB hijoDer = ((arbolAVL) nodo.hijoDer).raiz;
			int balanceHijo = hijoDer.hijoDer.altura() - hijoDer.hijoIzq.altura();
			if (balanceHijo >= 0) {
				return rotacionSimpleIzquierda(nodo);
			} else {
				return rotacionDobleDerecha(nodo);
			}
		} else if (balance < -1) {
			NodoABB hijoIzq = ((arbolAVL) nodo.hijoIzq).raiz;
			int balanceHijo = hijoIzq.hijoDer.altura() - hijoIzq.hijoIzq.altura();
			if (balanceHijo <= 0) {
				return rotacionSimpleDerecha(nodo);
			} else {
				return rotacionDobleIzquierda(nodo);
			}
		}

		return nodo;
}

	private NodoABB rotacionSimpleIzquierda (NodoABB nodo){
		NodoABB nododer = ((arbolAVL)nodo.hijoDer).raiz;
		NodoABB subarbolintermedio = ((arbolAVL)nododer.hijoIzq).raiz;		

		((arbolAVL) nodo.hijoDer).raiz = subarbolintermedio;
		((arbolAVL) nododer.hijoIzq).raiz = nodo;

		return nododer;
	}

	private NodoABB rotacionSimpleDerecha (NodoABB nodo){
		NodoABB nodoizq = ((arbolAVL) nodo.hijoIzq).raiz;
		NodoABB subIntermedio = ((arbolAVL) nodoizq.hijoDer).raiz;

		((arbolAVL) nodo.hijoIzq).raiz = subIntermedio;
		nodoizq.hijoDer = new arbolAVL();
		((arbolAVL) nodoizq.hijoDer).raiz = nodo;

		return nodoizq;	}

	private NodoABB rotacionDobleIzquierda (NodoABB nodo){
		NodoABB hijoIzq = ((arbolAVL) nodo.hijoIzq).raiz;
		NodoABB nuevaRaizIzq = rotacionSimpleIzquierda(hijoIzq);
		((arbolAVL) nodo.hijoIzq).raiz = nuevaRaizIzq;

		return rotacionSimpleDerecha(nodo);
	}

	private NodoABB rotacionDobleDerecha (NodoABB nodo){
		NodoABB hijoDer = ((arbolAVL) nodo.hijoDer).raiz;
		NodoABB nuevaRaizDer = rotacionSimpleDerecha(hijoDer);
		((arbolAVL) nodo.hijoDer).raiz = nuevaRaizDer;

		return rotacionSimpleIzquierda(nodo);
	}
}
