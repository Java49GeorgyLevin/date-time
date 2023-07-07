package telran.time;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.UnsupportedTemporalTypeException;

public class NextFriday13 implements TemporalAdjuster {
	private static final int FDAY = 5;

	@Override
	public Temporal adjustInto(Temporal temporal) {
		if(!temporal.isSupported(ChronoUnit.MONTHS)) {
			throw new UnsupportedTemporalTypeException("This temporal unsupports MONTHS");
		}
		int dOfM = temporal.get(ChronoField.DAY_OF_MONTH);
		temporal = temporal.with(ChronoField.DAY_OF_MONTH, 13);
		boolean isFriday13 = false;
		if(dOfM < 13) {
			isFriday13 = isFriday13(temporal);
		}
		while(!isFriday13) {
			temporal = temporal.plus(1, ChronoUnit.MONTHS);	
			isFriday13 = isFriday13(temporal);					
		}
		return temporal;
	}



private boolean isFriday13(Temporal temporal) {
	return temporal.get(ChronoField.DAY_OF_WEEK) == FDAY;
}

}