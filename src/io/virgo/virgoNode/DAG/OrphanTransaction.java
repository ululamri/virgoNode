package io.virgo.virgoNode.DAG;

import java.util.ArrayList;
import java.util.Arrays;

import io.virgo.virgoCryptoLib.ECDSASignature;
import io.virgo.virgoNode.Main;

public class OrphanTransaction extends Transaction {
	
	private ArrayList<String> waitedTxs;
	
	public OrphanTransaction(byte[] sigBytes, byte[] pubKey, String[] parents, String[] inputs, TxOutput[] outputs, long date, String[] waitedTxs, boolean saved) {
		super(pubKey, ECDSASignature.fromByteArray(sigBytes), parents, inputs, outputs, date, saved);
		
		this.waitedTxs = new ArrayList<String>(Arrays.asList(waitedTxs));
	}
	
	public OrphanTransaction(Transaction tx, String[] waitedTxs) {
		super(tx);
		
		this.waitedTxs = new ArrayList<String>(Arrays.asList(waitedTxs));
	}

	public void removeWaitedTx(String tx) {
		waitedTxs.remove(tx);
		if(waitedTxs.size() == 0) {
			Main.getDAG().waitingTxsUids.remove(getUid());
			Main.getDAG().initTx(this);
		}
	}
}
