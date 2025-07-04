package interfaces;

import java.time.LocalDate;

public interface IExpirable {
    public LocalDate getExpiryDate();
    public boolean isExpired();
}
