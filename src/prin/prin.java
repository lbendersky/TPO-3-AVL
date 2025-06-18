package prin;

import api.ABBTDA;
import imp.arbolAVL;

public class prin {

	public static void preOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			System.out.print(a.raiz() + " ");
			preOrder(a.hijoIzq());
			preOrder(a.hijoDer());
		}
	}
	
	public static void inOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			inOrder(a.hijoIzq());
			System.out.print(a.raiz() + " ");
			inOrder(a.hijoDer());
		}
	}
	
	public static void postOrder(ABBTDA a) {
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
		System.out.println("Ejemplo de una rotación simple izquierda: ");
		arbolRSI.agregarElem(10);
		arbolRSI.agregarElem(20);
		arbolRSI.agregarElem(30);

		System.out.println(" ");
		System.out.println("Representación del árbol através del método preorder: ");
		preOrder(arbolRSI);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método inorder: ");
		inOrder(arbolRSI);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método postorder: ");
		postOrder(arbolRSI);
		//---------------------------------------------------------------------------------//
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		ABBTDA arbolRSD = new arbolAVL();
		arbolRSD.inicializarArbol();
		System.out.println(" ");
		System.out.println("Ejemplo de una rotación simple derecha: ");
		arbolRSD.agregarElem(30);
		arbolRSD.agregarElem(20);
		arbolRSD.agregarElem(10);

		System.out.println(" ");
		System.out.println("Representación del árbol através del método preorder: ");
		preOrder(arbolRSD);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método inorder: ");
		inOrder(arbolRSD);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método postorder: ");
		postOrder(arbolRSD);
		//---------------------------------------------------------------------------------//		
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		ABBTDA arbolRDI = new arbolAVL();
		arbolRDI.inicializarArbol();
		System.out.println(" ");
		System.out.println("Ejemplo de una rotación doble izquierda: ");
		arbolRDI.agregarElem(20);
		arbolRDI.agregarElem(10);
		arbolRDI.agregarElem(15);

		System.out.println(" ");
		System.out.println("Representación del árbol através del método preorder: ");
		preOrder(arbolRDI);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método inorder: ");
		inOrder(arbolRDI);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método postorder: ");
		postOrder(arbolRDI);
		//---------------------------------------------------------------------------------//
		System.out.println("");
		System.out.println("----------------------------------------------------------------");
		ABBTDA arbolRDD = new arbolAVL();
		arbolRDD.inicializarArbol();
		System.out.println(" ");
		System.out.println("Ejemplo de una rotación doble derecha: ");
		arbolRDD.agregarElem(10);
		arbolRDD.agregarElem(20);
		arbolRDD.agregarElem(15);

		System.out.println(" ");
		System.out.println("Representación del árbol através del método preorder: ");
		preOrder(arbolRDD);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método inorder: ");
		inOrder(arbolRDD);
		System.out.println(" ");
		System.out.println("Representación del árbol através del método postorder: ");
		postOrder(arbolRDD);

	}

}
