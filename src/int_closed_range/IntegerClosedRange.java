package int_closed_range;

public class IntegerClosedRange {
	private int lowerEndpoint_;
	private int upperEndpoint_;

	public IntegerClosedRange(int lowerEndpoint, int upperEndpoint) {
		if (upperEndpoint < lowerEndpoint) {
			throw new IllegalArgumentException();
		}
		this.lowerEndpoint_ = lowerEndpoint;
		this.upperEndpoint_ = upperEndpoint;
	}

	public int getLowerEndpoint() {
		return this.lowerEndpoint_;
	}

	public int getUpperEndpoint() {
		return this.upperEndpoint_;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.lowerEndpoint_);
		sb.append(",");
		sb.append(this.upperEndpoint_);
		sb.append("]");
		return sb.toString();
	}

	public boolean contains(int integer_number) {
		return lowerEndpoint_ <= integer_number
				&& integer_number <=upperEndpoint_;
	}

	public boolean contains(IntegerClosedRange integerClosedRange) {
		if (integerClosedRange.getLowerEndpoint() < this.lowerEndpoint_) {
			return false;
		}
		if (this.upperEndpoint_ < integerClosedRange.getUpperEndpoint()) {
			return false;
		}
		return true;
	}

	public boolean equals(IntegerClosedRange integerClosedRange) {
		if (this.lowerEndpoint_ != integerClosedRange.getLowerEndpoint()) {
			return false;
		}
		if (this.upperEndpoint_ != integerClosedRange.getUpperEndpoint()) {
			return false;
		}
		return true;
	}

}
