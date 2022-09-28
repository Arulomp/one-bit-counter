package pw.arulomp.one.bit.counter.counter.internal;

import org.springframework.stereotype.Component;

@Component
public class FloatTypedOneBitCounter extends AbstractTypedOneBitCounter {
    @Override
    public Class<? extends Number> getSupportedType() {
        return Float.class;
    }

    @Override
    protected int countOneBitTypesafe(Number number) {
        float value = number.floatValue();
        int bits = Float.floatToIntBits(value);
        return Integer.bitCount(bits);
    }
}
