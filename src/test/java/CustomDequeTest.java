import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CustomDequeTest {

    private CustomDeque<Integer> customDeque;

    @Before
    public void setup(){
        customDeque = new CustomDeque<>();
        customDeque.add(1);
        customDeque.add(2);
        customDeque.add(3);
        customDeque.add(4);
    }

    @After
    public void tearDown(){
        customDeque = null;
    }

    @Test
    public void shouldIncreasedSize() throws Exception {
        assertEquals((long) customDeque.size(), 4);
    }

    @Test
    public void whenAddOneElementShouldReturnTrue() throws Exception {
        assertTrue(customDeque.add(11));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenAddNullElement(){
        customDeque.add(null);
    }

    @Test
    public void shouldReturnTrueIfEmpty() throws Exception {
        customDeque = new CustomDeque<>();
        assertTrue(customDeque.isEmpty());
    }

    @Test
    public void whenAddElementsShouldReturnLastElement() throws Exception {
        assertEquals((long) customDeque.getLast() , 4);
    }

    @Test
    public void whenAddElementsShouldReturnFirstElement() throws Exception {
        assertEquals((long) customDeque.getFirst() , 1);
    }

    @Test
    public void whenAddFirstElementsShouldReturnThisElement() throws Exception {
        customDeque.addFirst(111);
        customDeque.addFirst(222);
        assertEquals((long) customDeque.getFirst() , 222);
    }

    @Test
    public void whenOfferFirstElementsShouldReturnTrue() throws Exception {
        assertTrue(customDeque.offerFirst(333));
    }

    @Test
    public void whenAddLastElementsShouldReturnThisElement() throws Exception {
        customDeque.addLast(111);
        assertEquals((long) customDeque.getLast() , 111);
    }

    @Test
    public void whenOfferLastElementsShouldReturnTrue() throws Exception {
        assertTrue(customDeque.offerLast(333));
    }


    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenGetFirstElementsAndSizeIsZero(){
        customDeque = new CustomDeque<>();
        customDeque.getFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenGetLastElementsAndSizeIsZero(){
        customDeque = new CustomDeque<>();
        customDeque.getLast();
    }

    @Test
    public void whenRemoveFirstElementsShouldReturnAndDeleteThisElement() throws Exception {
        assertEquals((long) customDeque.removeFirst() , 1);
        assertEquals((long) customDeque.getFirst(), 2);
        assertTrue(customDeque.size() == 3);
    }

    @Test
    public void whenPoolFirstElementsShouldReturnAndDeleteThisElement() throws Exception {
        assertEquals((long) customDeque.pollFirst(), 1);
        assertEquals((long) customDeque.getFirst(), 2);
        assertTrue(customDeque.size() == 3);
    }

    @Test
    public void whenPeekFirstElementsShouldReturnAndDeleteThisElement() throws Exception {
        assertEquals((long) customDeque.peekFirst(), 1);
        assertEquals((long) customDeque.getFirst(), 1);
        assertTrue(customDeque.size() == 4);
    }

    @Test
    public void whenPoolFirstElementsFromEmptyDequeShouldReturnTrue() throws Exception {
        customDeque = new CustomDeque<>();
        assertTrue(customDeque.pollFirst() == null);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenRemoveFirstElementsAndSizeZero(){
        customDeque = new CustomDeque<>();
        customDeque.removeFirst();
    }

    @Test
    public void whenRemoveLastElementsShouldReturnAndDeleteThisElement() throws Exception {
        assertEquals((long) customDeque.removeLast() , 4);
        assertEquals((long) customDeque.getLast(), 3);
        assertTrue(customDeque.size() == 3);
    }

    @Test
    public void whenPoolLastElementsShouldReturnAndDeleteThisElement() throws Exception {
        assertEquals((long) customDeque.pollLast(), 4);
        assertEquals((long) customDeque.getLast(), 3);
        assertTrue(customDeque.size() == 3);
    }

    @Test
    public void whenPeekLastElementsShouldReturnAndDeleteThisElement() throws Exception {
        assertEquals((long) customDeque.peekLast(), 4);
        assertEquals((long) customDeque.getLast(), 4);
        assertTrue(customDeque.size() == 4);
    }

    @Test
    public void whenPoolLastElementsFromEmptyDequeShouldReturnTrue() throws Exception {
        customDeque = new CustomDeque<>();
        assertTrue(customDeque.pollLast() == null);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionWhenRemoveLastElementsAndSizeZero(){
        customDeque = new CustomDeque<>();
        customDeque.removeLast();
    }

    @Test
    public void whenRemoveObjectShouldReturnTrue() throws Exception {
        assertTrue(customDeque.remove(3));
    }

    @Test
    public void whenRemoveNotExistObjectShouldReturnFalse() throws Exception {
        assertFalse(customDeque.remove(8));
    }

    @Test
    public void shouldReturnTrueIfAllCollectionIsPresent() throws Exception {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,2,3);
        assertTrue(customDeque.containsAll(list));
    }

    @Test
    public void shouldReturnTrueWhenAddAllCollectionIsSuccess() throws Exception {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,2,3);
        assertTrue(customDeque.addAll(list));
        assertEquals((long) customDeque.getLast(), 3);
    }

    @Test
    public void shouldRemoveAllCollectionFromDeque() throws Exception {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,2,3);
        assertTrue(customDeque.removeAll(list));
        assertEquals(customDeque.size(), 2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenRetainAll(){
        customDeque.retainAll(null);
    }

    @Test
    public void shouldCorrectClearDeque() throws Exception {
        customDeque.clear();
        assertTrue(customDeque.isEmpty());
        assertEquals(customDeque.size(), 0);
    }

    @Test
    public void shouldReturnTrueWhenObjectExistInDeque() throws Exception {
        assertTrue(customDeque.contains(3));
        assertFalse(customDeque.contains(33));
    }

    @Test
    public void shouldCreateArrayFromDeque() throws Exception {
        Object[] objects = customDeque.toArray();
        Object object = objects[3];
        int i = (int) object;
        assertEquals((long) i, 4);
        assertTrue(objects.length == 4);
    }

}