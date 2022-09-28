package pw.arulomp.one.bit.counter.counter.internal;

import org.springframework.stereotype.Component;

@Component
public class ShortTypedOneBitCounter extends AbstractTypedOneBitCounter {

    @Override
    public Class<? extends Number> getSupportedType() {
        return Short.class;
    }

    @Override
    protected int countOneBitTypesafe(Number number) {
        short value = number.shortValue();
        return countOneBitTypesafe(value);
    }

    private int countOneBitTypesafe(short number) {
        int binaryLength = 16;
        short mask = 1;

        int bitCount = 0;
        for(int i = 0; i < binaryLength; i++) {
            bitCount += (number & mask) == 0 ? 0 : 1;
            mask = (short) (mask << 1);
        }
        return bitCount;
    }
}
