package pw.arulomp.one.bit.counter.counter.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.arulomp.one.bit.counter.counter.OneBitCountService;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OneBitCountServiceImpl implements OneBitCountService {

    private final Map<? extends Class<? extends Number>, TypedOneBitCounter> counters;

    @Autowired
    public OneBitCountServiceImpl(List<TypedOneBitCounter> counters) {
        this.counters = counters.stream().collect(Collectors.toMap(
                TypedOneBitCounter::getSupportedType,
                Function.identity()
        ));
    }
    @Override
    public int countOneBit(Number number) {
        Class<? extends Number> numberClass = number.getClass();
        if (!counters.containsKey(numberClass)) {
            throw new UnsupportedOperationException("Number with type" + numberClass + " unsupported");
        }
        TypedOneBitCounter counter = counters.get(numberClass);
        log.debug("numberClass {}, counter {}", numberClass, counter.getClass());
        return counter.countOneBit(number);
    }
}
