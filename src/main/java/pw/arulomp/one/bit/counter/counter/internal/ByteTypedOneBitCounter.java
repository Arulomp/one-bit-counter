package pw.arulomp.one.bit.counter.counter.internal;

import org.springframework.stereotype.Component;

@Component
public class ByteTypedOneBitCounter extends AbstractTypedOneBitCounter {

    @Override
    public Class<? extends Number> getSupportedType() {
        return Byte.class;
    }

    @Override
    protected int countOneBitTypesafe(Number number) {
        byte value = number.byteValue();
        return countOneBitTypesafe(value);
    }

    private int countOneBitTypesafe(byte number) {
        int binaryLength = 8;
        byte mask = 1;

        int bitCount = 0;
        for(int i = 0; i < binaryLength; i++) {
            bitCount += (number & mask) == 0 ? 0 : 1;
            mask = (byte) (mask << 1);
        }
        return bitCount;
    }
}
