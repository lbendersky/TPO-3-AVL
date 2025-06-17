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
	
	@Override
	public void inicializarArbol() {
		raiz = null;
	}

	@Override
	public void agregarElem(int x) {
		if (raiz == null){
			raiz = new NodoABB();
			raiz.info = x;
			raiz.hijoIzq = new arbolAVL();
			raiz.hijoIzq.inicializarArbol();
			raiz.hijoDer = new arbolAVL();
			raiz.hijoDer.inicializarArbol();
		} else {
			insertarYRebalancear(raiz, x);
		}
	}

	private boolean insertarYRebalancear(NodoABB nodo, int x) {
    boolean alturaCambio = false;

    if (x < nodo.info) {
        if (((arbolAVL) nodo.hijoIzq).raiz == null) {
            NodoABB nuevo = new NodoABB();
            nuevo.info = x;
            nuevo.hijoIzq = new arbolAVL();
            nuevo.hijoIzq.inicializarArbol();
            nuevo.hijoDer = new arbolAVL();
            nuevo.hijoDer.inicializarArbol();
            nuevo.factorBalance = 0;
            ((arbolAVL) nodo.hijoIzq).raiz = nuevo;
            nodo.factorBalance--;  // desbalance en el lado izquierdo
            alturaCambio = nodo.factorBalance != 0;
        } 
		else {
            alturaCambio = insertarYRebalancear(((arbolAVL) nodo.hijoIzq).raiz, x);
            if (alturaCambio) {
                nodo.factorBalance--;
                alturaCambio = Math.abs(nodo.factorBalance) == 1;
            }
        }

    } 
	else if (x > nodo.info) {
        if (((arbolAVL) nodo.hijoDer).raiz == null) {
            NodoABB nuevo = new NodoABB();
            nuevo.info = x;
            nuevo.hijoIzq = new arbolAVL();
            nuevo.hijoIzq.inicializarArbol();
            nuevo.hijoDer = new arbolAVL();
            nuevo.hijoDer.inicializarArbol();
            nuevo.factorBalance = 0;
            ((arbolAVL) nodo.hijoDer).raiz = nuevo;
            nodo.factorBalance++;  // desbalance en el lado derecho
            alturaCambio = nodo.factorBalance != 0;
        } 
		else {
            alturaCambio = insertarYRebalancear(((arbolAVL) nodo.hijoDer).raiz, x);
            if (alturaCambio) {
                nodo.factorBalance++;
                alturaCambio = Math.abs(nodo.factorBalance) == 1;
            }
        }

    } 
	else {
        alturaCambio = false;  // ya existe el elemento
    }

    // Verificar si es necesario el rebalanceo
    if (nodo.factorBalance == 2 || nodo.factorBalance == -2) {
        rebalancear(nodo);
        alturaCambio = false; // se arreglo el balance
    }

    return alturaCambio;
	}

	private NodoABB rebalancear(NodoABB nodo) {
    if (nodo.factorBalance == -2) { // pesado en el lado izquierdo
        NodoABB hijoIzq = ((arbolAVL) nodo.hijoIzq).raiz;
        if (hijoIzq.factorBalance <= 0) {
            return rotacionSimpleDerecha(nodo);
        } 
		else {
            return rotacionDobleIzquierda(nodo);
        }
    } 
	else if (nodo.factorBalance == 2) { // pesado en el lado derecho
        NodoABB hijoDer = ((arbolAVL) nodo.hijoDer).raiz;
        if (hijoDer.factorBalance >= 0) {
            return rotacionSimpleIzquierda(nodo);
        } 
		else {
            return rotacionDobleDerecha(nodo);
        }
    }
    return nodo; // no imbalance
	}


	@Override
	public void eliminarElem(int x) {
		if (raiz != null) {
			if (raiz.info == x && raiz.hijoIzq.arbolVacio() && raiz.hijoDer.arbolVacio()) {
				raiz = null;
			} else if (raiz.info == x && !raiz.hijoIzq.arbolVacio()) {
				raiz.info = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminarElem(raiz.info);
			} else if (raiz.info == x && raiz.hijoIzq.arbolVacio()) {
				raiz.info = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminarElem(raiz.info);
			} else if (raiz.info < x) {
				raiz.hijoDer.eliminarElem(x);
			} else {
				raiz.hijoIzq.eliminarElem(x);
			}
		}
	}

	@Override
	public int raiz() {
		return raiz.info;
	}

	@Override
	public ABBTDA hijoIzq() {
		return raiz.hijoIzq;
	}

	@Override
	public ABBTDA hijoDer() {
		return raiz.hijoDer;
	}

	@Override
	public boolean arbolVacio() {
		return (raiz == null);
	}
	
	private int mayor(ABBTDA a){
		if (a.hijoDer().arbolVacio())
			return a.raiz();
		else
			return mayor(a.hijoDer());
		}
	
	private int menor(ABBTDA a){
		if (a.hijoIzq().arbolVacio())
			return a.raiz();
		else
			return mayor(a.hijoIzq());
		}


}