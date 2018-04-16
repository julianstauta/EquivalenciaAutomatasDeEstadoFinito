package modelo;

import java.util.Vector;

class UnionFind {
	
	/**
	 * Vectores para conocer el padre de un set, su rango y el tamanho del set
	 */

	private Vector<Integer> p, rank, setSize;
	/**
	 * Numero de sets diferentes
	 */
	private int numSets;

	
	/**
	 * Constructor de la clase
	 * @param N numero de elementos iniciales diferentes
	 */
	public UnionFind(int N) {
		p = new Vector<Integer>(N);
		rank = new Vector<Integer>(N);
		setSize = new Vector<Integer>(N);
		numSets = N;
		for (int i = 0; i < N; i++) {
			p.add(i);
			rank.add(0);
			setSize.add(1);
		}
	}

	/**
	 * Encunetra el set al que pertenece un elemento
	 * @param i indice de la pos del elemento
	 * @return set al que pertenece
	 */
	public int findSet(int i) {
		if (p.get(i) == i)
			return i;
		else {
			int ret = findSet(p.get(i));
			p.set(i, ret);
			return ret;
		}
	}

	/**
	 * Dice si dos elementos perteneces al mismo conjunto
	 * @param i indice primer elemento
	 * @param j indice segundo elemento
	 * @return true si pertenecen al mismo conjunto
	 */
	public Boolean isSameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

	/**
	 * Une dos elementos en un mismo conjunto
	 * @param i indice primer elemento
	 * @param j indice segundo elemento
	 */
	public void unionSet(int i, int j) {
		if (!isSameSet(i, j)) {
			numSets--;
			int x = findSet(i), y = findSet(j);
			// rank is used to keep the tree short
			if (rank.get(x) > rank.get(y)) {
				p.set(y, x);
				setSize.set(x, setSize.get(x) + setSize.get(y));
			} else {
				p.set(x, y);
				setSize.set(y, setSize.get(y) + setSize.get(x));
				if (rank.get(x) == rank.get(y))
					rank.set(y, rank.get(y) + 1);
			}
		}
	}

	/**
	 * Numero de conjuntos disjuntos
	 * @return
	 */
	public int numDisjointSets() {
		return numSets;
	}
	/**
	 * Retorna el tamanho del conjunto al que pertenece un elemento
	 * @param i
	 * @return
	 */

	public int sizeOfSet(int i) {
		return setSize.get(findSet(i));
	}
}
