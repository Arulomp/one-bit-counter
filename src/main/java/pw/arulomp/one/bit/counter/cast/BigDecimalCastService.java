package pw.arulomp.one.bit.counter.cast;

import java.math.BigDecimal;

public interface BigDecimalCastService {
    Number castToNarrowingNumber(BigDecimal value);
}
