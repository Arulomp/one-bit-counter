package pw.arulomp.one.bit.counter.counter.internal;

import org.springframework.stereotype.Component;

@Component
public class IntegerTypedOneBitCounter extends AbstractTypedOneBitCounter {
    @Override
    public Class<? extends Number> getSupportedType() {
        return Integer.class;
    }

    @Override
    protected int countOneBitTypesafe(Number number) {
        int value = number.intValue();
        return Integer.bitCount(value);
    }
}
