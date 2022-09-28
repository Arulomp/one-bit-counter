package pw.arulomp.one.bit.counter.counter.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pw.arulomp.one.bit.counter.counter.OneBitCountService;
import pw.arulomp.one.bit.counter.counter.TypedOneBitCounter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OneBitCountServiceImplTest {

    OneBitCountService service;
    @Mock
    TypedOneBitCounter integerCounter;

    @BeforeEach
    void setUp() {
        lenient().when(integerCounter.getSupportedType()).thenAnswer(invocation -> Integer.class);
        lenient().when(integerCounter.countOneBit(any())).thenReturn(1);

        service = new OneBitCountServiceImpl(List.of(integerCounter));
    }

    @Test
    void test_constructor_duplicateCounters() {
        TypedOneBitCounter counter = mock(TypedOneBitCounter.class);
        when(counter.getSupportedType()).thenAnswer(invocation -> Integer.class);

        assertThrows(RuntimeException.class, () -> new OneBitCountServiceImpl(List.of(integerCounter, counter)));
    }

    @Test
    void test_countOneBit_counterExist() {
        assertDoesNotThrow(() -> service.countOneBit(1));
        assertEquals(1, service.countOneBit(1));
    }

    @Test
    void test_countOneBit_counterNotExist() {
        assertThrows(UnsupportedOperationException.class, () -> service.countOneBit(1.1));
    }
}