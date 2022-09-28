package pw.arulomp.one.bit.counter.counter.internal;


import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;

public abstract class AbstractTypedOneBitCounter implements TypedOneBitCounter {
    public abstract Class<? extends Number> getSupportedType();

    @Override
    public int countOneBit(Number number) {
        Class<? extends Number> supportedType = getSupportedType();
        if (number.getClass() != supportedType) {
            throw new IllegalArgumentException(supportedType.getName() + " expected, but " + number.getClass().getName() + " given");
        }
        return countOneBitTypesafe(number);
    }

    protected abstract int countOneBitTypesafe(Number number);
}
