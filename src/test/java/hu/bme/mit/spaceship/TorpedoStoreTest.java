package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void fireMustFail() {
        TorpedoStore store = new TorpedoStore(1, 1);
        boolean res = store.fire(1);
        assertEquals(false, res);
    }

    @Test
    void fireMultipleTimesOnceAndWillBeEmpty() {
        TorpedoStore store = new TorpedoStore(5);
        for (int i = 0; i< 5; i++) {
            boolean res = store.fire(1);
            assertEquals(5-i-1, store.getTorpedoCount());
            assertEquals(true, res);
        }
        boolean res = store.isEmpty();
        assertEquals(true, res);
    }

    @Test
    void fireFailAfterZeroCount() {
        TorpedoStore store = new TorpedoStore(0);
        assertThrows(
            IllegalArgumentException.class,
            () -> store.fire(1)
        );
    }

    @Test
    void fireInvalidValuesFail() {
        TorpedoStore store = new TorpedoStore(4);
        assertThrows(
            IllegalArgumentException.class,
            () -> store.fire(100)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> store.fire(-1)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> store.fire(0)
        );
    }

    @Test
    void fireFailActuallyDoesNotFire() {
        TorpedoStore store = new TorpedoStore(100, 1);
        store.fire(10);
        int res = store.getTorpedoCount();
        assertEquals(100, res);
    }
}
