package UnitTests.StorageTests;

import Model.Features.GenericFeature;
import Model.Features.IntegerFeature;
import Model.Storage;
import org.junit.Before;
import org.junit.Test;
import Model.Metrics.IntegerAbsoluteMetric;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests the Storage when size = 2
 * @author Josh Campitelli
 */
public class StorageTestTwoInstances {
    private Storage storage;
    private ArrayList<GenericFeature> instance1;
    private ArrayList<GenericFeature> instance2;
    private IntegerAbsoluteMetric intMetric;

    @Before
    public void setUp() {
        storage = new Storage();
        instance1 = new ArrayList<>();
        instance2 = new ArrayList<>();
        intMetric = new IntegerAbsoluteMetric("test", storage);


        instance1.add(new IntegerFeature("key1",1,intMetric));
        instance2.add(new IntegerFeature("key2",2, intMetric));
    }

    @Test
    public void testSize() {
        storage.insert("key1", instance1);
        storage.insert("key2", instance2);
        assertEquals("Size should be 2.", 2, storage.getSize());
    }

    @Test
    public void testRemove() {
        storage.insert("key1", instance1);
        storage.insert("key2", instance2);
        assertEquals("Size should be 2.", 2, storage.getSize());

        storage.remove("key1");
        storage.remove("key2");
        assertEquals("Size should be 0.", 0, storage.getSize());
    }
}