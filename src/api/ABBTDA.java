package api;

public interface ABBTDA {
	void inicializarArbol();
	void agregarElem(int x);	// arbol inicializado
	void eliminarElem(int x);	// arbol inicializado
	int raiz();					// arbol inicializado y no vacio
	ABBTDA hijoIzq();			// arbol inicializado y no vacio
	ABBTDA hijoDer();			// arbol inicializado y no vacio
	boolean arbolVacio();		// arbol inicializado 
}
