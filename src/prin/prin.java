package prin;

import api.ABBTDA;
import imp.arbolAVL;

public class prin {
	// Recorridos clasicos para imprimir el árbol en diferentes órdenes.
	public static void preOrder(ABBTDA a) { // Visita primero la raíz, luego el subárbol izquierdo, por último el subárbol derecho.
		if(!a.arbolVacio()) {
			System.out.print(a.raiz() + " ");
			preOrder(a.hijoIzq());
			preOrder(a.hijoDer());
		}
	}
	
	public static void inOrder(ABBTDA a) { // Visita primero el subárbol izquierdo, luego la raíz, por último el subárbol derecho.
		if(!a.arbolVacio()) {
			inOrder(a.hijoIzq());
			System.out.print(a.raiz() + " ");
			inOrder(a.hijoDer());
		}
	}
	
	public static void postOrder(ABBTDA a) { // Visita primero el subárbol izquierdo, luego el subárbol derecho, por último la raíz.
		if(!a.arbolVacio()) {
			postOrder(a.hijoIzq());
			postOrder(a.hijoDer());
			System.out.print(a.raiz() + " ");
		}
	}
	
	public static void main(String[] args) {
		ABBTDA arbolRSI = new arbolAVL();
		arbolRSI.inicializarArbol();
		System.out.println(" ");
		System.out.println("Visualización de distintos ejemplos para comprobar que se haya realizado de forma correcta la implementación del algoritmo AVL.");
		System.out.println("");
		System.out.println("Ejemplo de una rotación simple izquierda: ");
		arbolRSI.agregarElem(10);
		arbolRSI.agregarElem(20);
		arbolRSI.agregarElem(30);

		System.out.println(" ");
		System.out.print("Representación del árbol através del método preorder: ");
		preOrder(arbolRSI);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método inorder: ");
		inOrder(arbolRSI);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método postorder: ");
		postOrder(arbolRSI);
		//---------------------------------------------------------------------------------//
		System.out.println("");
		System.out.println("-".repeat(70));		
		ABBTDA arbolRSD = new arbolAVL();
		arbolRSD.inicializarArbol();
		System.out.println(" ");
		System.out.println("Ejemplo de una rotación simple derecha: ");
		arbolRSD.agregarElem(30);
		arbolRSD.agregarElem(20);
		arbolRSD.agregarElem(10);

		System.out.println(" ");
		System.out.print("Representación del árbol através del método preorder: ");
		preOrder(arbolRSD);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método inorder: ");
		inOrder(arbolRSD);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método postorder: ");
		postOrder(arbolRSD);
		//---------------------------------------------------------------------------------//		
		System.out.println("");
		System.out.println("-".repeat(70));		
		ABBTDA arbolRDI = new arbolAVL();
		arbolRDI.inicializarArbol();
		System.out.println(" ");
		System.out.println("Ejemplo de una rotación doble izquierda: ");
		arbolRDI.agregarElem(20);
		arbolRDI.agregarElem(10);
		arbolRDI.agregarElem(15);

		System.out.println(" ");
		System.out.print("Representación del árbol através del método preorder: ");
		preOrder(arbolRDI);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método inorder: ");
		inOrder(arbolRDI);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método postorder: ");
		postOrder(arbolRDI);
		//---------------------------------------------------------------------------------//
		System.out.println("");
		System.out.println("-".repeat(70));		
		ABBTDA arbolRDD = new arbolAVL();
		arbolRDD.inicializarArbol();
		System.out.println(" ");
		System.out.println("Ejemplo de una rotación doble derecha: ");
		arbolRDD.agregarElem(10);
		arbolRDD.agregarElem(20);
		arbolRDD.agregarElem(15);

		System.out.println(" ");
		System.out.print("Representación del árbol através del método preorder: ");
		preOrder(arbolRDD);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método inorder: ");
		inOrder(arbolRDD);
		System.out.println(" ");
		System.out.print("Representación del árbol através del método postorder: ");
		postOrder(arbolRDD);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("De esta manera, pudimos comprobar que fue realiza de forma correcta la implementación.");
		System.out.println(" ");
		System.out.println("Trabajo finalizado.");
	}

}
