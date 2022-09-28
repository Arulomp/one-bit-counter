package pw.arulomp.one.bit.counter.counter;

public interface TypedOneBitCounter {
    Class<? extends Number> getSupportedType();
    int countOneBit(Number number);
}
