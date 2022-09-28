package pw.arulomp.one.bit.counter.counter.internal;

import org.springframework.stereotype.Component;

@Component
public class DoubleTypedOneBitCounter extends AbstractTypedOneBitCounter {
    @Override
    public Class<? extends Number> getSupportedType() {
        return Double.class;
    }

    @Override
    protected int countOneBitTypesafe(Number number) {
        double value = number.doubleValue();
        long bits = Double.doubleToLongBits(value);
        return Long.bitCount(bits);
    }
}
