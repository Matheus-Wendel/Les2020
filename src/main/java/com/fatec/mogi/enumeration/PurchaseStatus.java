package com.fatec.mogi.enumeration;

public enum PurchaseStatus {
	PROCESSING(1),ACCEPTED(2),IN_TRANSIT(3),DELIVERED(4);
  private int step;
	PurchaseStatus(int i) {
		this.step = i;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
	public PurchaseStatus getNextStep() {
		switch (this.step) {
		case 1: {
			
			return PurchaseStatus.ACCEPTED;
		}
		case 2: {
			
			return PurchaseStatus.IN_TRANSIT;
		}
		case 3: {
			
			return PurchaseStatus.DELIVERED;
		}
		case 4: {
			
			return PurchaseStatus.DELIVERED;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + this.step);
		}
	}

}
