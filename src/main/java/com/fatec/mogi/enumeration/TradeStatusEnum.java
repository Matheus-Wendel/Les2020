package com.fatec.mogi.enumeration;

public enum TradeStatusEnum {
	REQUESTED(1),IN_EXCHANGE(2),FINISHED(3);
	  private int step;
	TradeStatusEnum(int i) {
			this.step = i;
		}
		public int getStep() {
			return step;
		}
		public void setStep(int step) {
			this.step = step;
		}
		
		public TradeStatusEnum getNextStep() {
			switch (this.step) {
			case 1: {
				
				return TradeStatusEnum.IN_EXCHANGE;
			}
			case 2: {
				
				return TradeStatusEnum.FINISHED;
			}
			case 3: {
				
				return TradeStatusEnum.FINISHED;
			}
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + this.step);
			}
		}

		

}
