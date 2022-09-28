package pw.arulomp.one.bit.counter.counter.internal;

import org.springframework.stereotype.Component;

@Component
public class LongTypedOneBitCounter extends AbstractTypedOneBitCounter {
    @Override
    public Class<? extends Number> getSupportedType() {
        return Long.class;
    }

    @Override
    protected int countOneBitTypesafe(Number number) {
        long value = number.longValue();
        return Long.bitCount(value);
    }
}
