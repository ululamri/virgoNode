package io.virgo.virgoNode.DAG;

public class Branch {

	private int txCount = 0;

	public Branch() {}
	
	public void addTx(LoadedTransaction tx) {
		
		txCount++;
		
		tx.partOf.put(this, txCount);
		
	}
	
}
