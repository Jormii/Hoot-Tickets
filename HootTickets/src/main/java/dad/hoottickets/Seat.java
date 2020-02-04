package dad.hoottickets;

public class Seat {

	private int totalSeats;
	private int availableSeats;
	private int seatPrice;

	public Seat(int totalSeats, int availableSeats, int seatPrice) {
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.seatPrice = seatPrice;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seatPrice;
		result = prime * result + totalSeats;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (seatPrice != other.seatPrice)
			return false;
		if (totalSeats != other.totalSeats)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Total seats of this type: %d. Seats available: %d. Price: %d", totalSeats, availableSeats,
				seatPrice);
	}

}
