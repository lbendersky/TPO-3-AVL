package imp;

import api.ABBTDA;

public class arbolAVL implements ABBTDA {

	class NodoABB {
		int info; // Valor del nodo.
		ABBTDA hijoIzq; // Subárbol izquierdo.
		ABBTDA hijoDer; // Subárbol derecho.
		int factorBalance; // Factor de balanceo del nodo (altura derecha - altura izquierda).
		int altura;
	}

	NodoABB raiz; // Nodo raíz del árbol.

	public int raiz() { // Devuelve valor almacenado en la raíz del árbol.
		return raiz.info;
	}

	public ABBTDA hijoIzq() { // Devuelve subárbol izquierdo.
		return raiz.hijoIzq;
	}

	public ABBTDA hijoDer() { // Devuelve subárbol derecho.
		return raiz.hijoDer;
	}

	public boolean arbolVacio() { // Verifica si el árbol está vacío.
		return (raiz == null);
	}

	private int mayor(ABBTDA a) { // Devuelve el mayor valor de un subárbol (recorre hacia la derecha).
		if (a.hijoDer().arbolVacio())
			return a.raiz();
		else
			return mayor(a.hijoDer());
	}

	private int menor(ABBTDA a) { // Devuelve el menor valor de un subárbol (recorre hacia la izquierda).
		if (a.hijoIzq().arbolVacio())
			return a.raiz();
		else
			return menor(a.hijoIzq());
	}

	public int alturaNodo(NodoABB nodo) { // Calcula altura del nodo.
		if (nodo == null)
			return 0;
		return nodo.altura;
	}

	public int altura () {
		if (raiz != null){
			return raiz.altura;
		}
		else 
			return 0;
	}

	public void inicializarArbol() { // Inicializa el árbol vacío.
		raiz = null;
	}

	public void agregarElem(int x) { // Inserta un valor al árbol, lo balancea en caso de ser necesario.
		raiz = agregarYBalancear(raiz, x);
	}

	public void eliminarElem(int x) { // Elimina un valor al árbol, lo balancea en caso de ser necesario.
		raiz = eliminarYBalancear(raiz, x);
	}

    private NodoABB agregarYBalancear(NodoABB nodo, int x) {
        if (nodo == null) {
            NodoABB nuevo = new NodoABB();
            nuevo.info = x;
            nuevo.hijoIzq = new arbolAVL();
            nuevo.hijoIzq.inicializarArbol();
            nuevo.hijoDer = new arbolAVL();
            nuevo.hijoDer.inicializarArbol();
            nuevo.altura = 1;
            return nuevo;
        }

        if (x < nodo.info)
            ((arbolAVL) nodo.hijoIzq).raiz = agregarYBalancear(((arbolAVL) nodo.hijoIzq).raiz, x);
        else if (x > nodo.info)
            ((arbolAVL) nodo.hijoDer).raiz = agregarYBalancear(((arbolAVL) nodo.hijoDer).raiz, x);
        else
            return nodo;

        return verificarybalancear(nodo);
    }

    private NodoABB eliminarYBalancear(NodoABB nodo, int x) {
        if (nodo == null)
            return null;

        if (x < nodo.info)
            ((arbolAVL) nodo.hijoIzq).raiz = eliminarYBalancear(((arbolAVL) nodo.hijoIzq).raiz, x);
        else if (x > nodo.info)
            ((arbolAVL) nodo.hijoDer).raiz = eliminarYBalancear(((arbolAVL) nodo.hijoDer).raiz, x);
        else {
            if (nodo.hijoIzq.arbolVacio() && nodo.hijoDer.arbolVacio())
                return null;
            else if (nodo.hijoIzq.arbolVacio())
                return ((arbolAVL) nodo.hijoDer).raiz;
            else if (nodo.hijoDer.arbolVacio())
                return ((arbolAVL) nodo.hijoIzq).raiz;
            else {
                int sucesor = menor(nodo.hijoDer);
                nodo.info = sucesor;
                ((arbolAVL) nodo.hijoDer).raiz = eliminarYBalancear(((arbolAVL) nodo.hijoDer).raiz, sucesor);
            }
        }

        return verificarybalancear(nodo);
    }

    private NodoABB verificarybalancear(NodoABB nodo) {
        int altIzq = alturaNodo(((arbolAVL) nodo.hijoIzq).raiz);
        int altDer = alturaNodo(((arbolAVL) nodo.hijoDer).raiz);

        nodo.altura = 1 + Math.max(altIzq, altDer);

        int balance = altDer - altIzq;

        if (balance > 1) {
            NodoABB hijoDer = ((arbolAVL) nodo.hijoDer).raiz;
            int balanceHijo = alturaNodo(((arbolAVL) hijoDer.hijoDer).raiz) - alturaNodo(((arbolAVL) hijoDer.hijoIzq).raiz);
            if (balanceHijo >= 0)
                return rotacionSimpleIzquierda(nodo);
            else
                return rotacionDobleDerecha(nodo);
        } else if (balance < -1) {
            NodoABB hijoIzq = ((arbolAVL) nodo.hijoIzq).raiz;
            int balanceHijo = alturaNodo(((arbolAVL) hijoIzq.hijoDer).raiz) - alturaNodo(((arbolAVL) hijoIzq.hijoIzq).raiz);
            if (balanceHijo <= 0)
                return rotacionSimpleDerecha(nodo);
            else
                return rotacionDobleIzquierda(nodo);
        }

        return nodo;
    }


    private NodoABB rotacionSimpleIzquierda(NodoABB nodo) {
        NodoABB nododer = ((arbolAVL) nodo.hijoDer).raiz;
        NodoABB subarbolintermedio = ((arbolAVL) nododer.hijoIzq).raiz;

        ((arbolAVL) nodo.hijoDer).raiz = subarbolintermedio;
        ((arbolAVL) nododer.hijoIzq).raiz = nodo;

        nodo.altura = 1 + Math.max(alturaNodo(((arbolAVL) nodo.hijoIzq).raiz), alturaNodo(((arbolAVL) nodo.hijoDer).raiz));
        nododer.altura = 1 + Math.max(alturaNodo(((arbolAVL) nododer.hijoIzq).raiz), alturaNodo(((arbolAVL) nododer.hijoDer).raiz));

        return nododer;
    }

    private NodoABB rotacionSimpleDerecha(NodoABB nodo) {
        NodoABB nodoizq = ((arbolAVL) nodo.hijoIzq).raiz;
        NodoABB subIntermedio = ((arbolAVL) nodoizq.hijoDer).raiz;

        ((arbolAVL) nodo.hijoIzq).raiz = subIntermedio;
        nodoizq.hijoDer = new arbolAVL();
        ((arbolAVL) nodoizq.hijoDer).raiz = nodo;

        nodo.altura = 1 + Math.max(alturaNodo(((arbolAVL) nodo.hijoIzq).raiz), alturaNodo(((arbolAVL) nodo.hijoDer).raiz));
        nodoizq.altura = 1 + Math.max(alturaNodo(((arbolAVL) nodoizq.hijoIzq).raiz), alturaNodo(((arbolAVL) nodoizq.hijoDer).raiz));

        return nodoizq;
    }

    private NodoABB rotacionDobleIzquierda(NodoABB nodo) {
        NodoABB hijoIzq = ((arbolAVL) nodo.hijoIzq).raiz;
        ((arbolAVL) nodo.hijoIzq).raiz = rotacionSimpleIzquierda(hijoIzq);
        return rotacionSimpleDerecha(nodo);
    }

    private NodoABB rotacionDobleDerecha(NodoABB nodo) {
        NodoABB hijoDer = ((arbolAVL) nodo.hijoDer).raiz;
        ((arbolAVL) nodo.hijoDer).raiz = rotacionSimpleDerecha(hijoDer);
        return rotacionSimpleIzquierda(nodo);
    }

}
