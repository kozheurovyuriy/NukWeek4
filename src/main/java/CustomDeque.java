import java.util.*;

public class CustomDeque<T> implements Deque<T> {

    private Node<T> head = new Node<>();
    private Node<T> tail = new Node<>();
    private int size = 0;

    CustomDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Node<T> {
        private T element;
        private Node next;
        private Node previous;

        Node() {
            this.next = null;
            this.previous = null;
        }

        Node(T element) {
            this.element = element;
        }

        T getElement() {
            return element;
        }

        void setElement(T element) {
            this.element = element;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node getPrevious() {
            return previous;
        }

        void setPrevious(Node previous) {
            this.previous = previous;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean add(T t) {

        if (t == null)
            throw new NullPointerException();

        Node<T> temp = new Node<>(t);

        if (size() == 0) {

            head = temp;
            tail = temp;
            head.setPrevious(null);
        }
        else if (head.getNext() == null) {
            head.setNext(temp);
            tail = temp;
            tail.setPrevious(head);
            head.setPrevious(null);
            head.setNext(tail);
            tail.setNext(null);
        } else {
            Node<T> temp1 = tail;
            tail = temp;
            tail.setNext(null);
            tail.setPrevious(temp1);
            temp1.setNext(tail);
        }
        size++;
        return true;
    }

    @Override
    public void addFirst(T t) {

        if (t == null)
            throw new NullPointerException();

        Node<T> temp = new Node<>(t);

        if (size() == 0) {
            head = temp;
            tail = temp;
            head.setPrevious(null);
            head.setNext(null);
            tail.setNext(null);
            tail.setNext(null);
        }
        else if (head.getNext() == null) {
            tail = head;
            head = temp;
            tail.setPrevious(head);
            tail.setNext(null);
            head.setNext(tail);
            head.setPrevious(null);
        } else {
            Node<T> temp1 = head;
            temp1.setPrevious(temp);
            temp.setNext(temp1);
            temp.setPrevious(null);
            head = temp;
        }
        size++;
    }

    @Override
    public void addLast(T t) {
        add(t);
    }

    @Override
    public T getFirst() {
        if (size() == 0)
            throw new NoSuchElementException();
        return head.getElement();
    }

    @Override
    public T getLast() {
        if (size() == 0)
            throw new NoSuchElementException();
        return tail.getElement();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T removeFirst() {

        if (size() == 0)
            throw new NoSuchElementException();

        T result = head.getElement();

        if(size() == 1){
            head = null;
            tail = null;
            size = 0;
        }
        else if(size() == 2){
            head = tail;
            head.setNext(null);
            head.setPrevious(null);
            tail.setNext(null);
            tail.setPrevious(null);
            size = 1;
        } else {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T removeLast() {
        if (size() == 0)
            throw new NoSuchElementException();

        T result = tail.getElement();

        if(size() == 1){
            head = null;
            tail = null;
            size = 0;
        }
        else if(size() == 2){
            tail = head;
            tail.setNext(null);
            head.setNext(null);
            tail.setPrevious(null);
            head.setPrevious(null);
            size = 1;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        }

        return result;
    }

    @Override
    public boolean offerFirst(T t) {
        addFirst(t);
        return true;
    }

    @Override
    public boolean offerLast(T t) {
        addLast(t);
        return true;
    }

    @Override
    public T pollFirst() {
        return isEmpty() ? null : removeFirst();
    }

    @Override
    public T pollLast() {
        return isEmpty() ? null : removeLast();
    }

    @Override
    public T peekFirst() {
        return isEmpty() ? null : head.getElement();
    }

    @Override
    public T peekLast() {
        return isEmpty() ? null : tail.getElement();
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public void push(T t) {
        addFirst(t);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean remove(Object o) {
        Node<T> temphead = head;

        Node<T> tmp;

        if(o.equals(head.getElement())){
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return true;
        }

        for (int i = 1; i < size(); i++) {
            tmp = temphead;
            temphead = temphead.getNext();

            if(o.equals(temphead.getElement())){
                temphead = tmp;
                temphead.setNext(temphead.getNext().getNext());
                size--;
                if(i==size()-1)
                    tail = temphead;
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        Object[] obc = c.toArray();
        Node<T> temp;

        for (Object anObc : obc) {
            temp = head;
            for (int i = 0; i < size(); i++) {
                if (anObc.equals(temp.getElement()))
                    count++;
                temp = temp.getNext();
            }
        }
        return count == obc.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] tempCol = c.toArray();
        for (Object aTempCol : tempCol) add((T) aTempCol);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean flag = false;
        Object[] objects = c.toArray();
        Node <T> temp;

        for (Object object : objects) {
            temp = head;
            for (int j = 0; j < size(); j++) {
                if (object.equals(temp.getElement())) {
                    remove(object);
                    flag = true;
                    break;
                }

                temp = temp.getNext();
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object o) {
        Node<T>  temp = head;
        if(!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                if (o.equals(temp.getElement()))
                    return true;
                temp = temp.getNext();
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] toArray() {
        Object[] objectsArray = new Object[size()];
        Node<T> temp = head;
        for (int i = 0; i < size(); i++) {
            objectsArray[i] = temp.getElement();
            temp = temp.getNext();
        }
        return objectsArray;
    }

    //Can not override because we have parametrized deque with type T
    //and input parameters have T1 type
    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Operation Unsupported - because this deque" +
                " is parametrized with type T and input method parameters have T1 type");
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeLastOccurrence(Object o) {
        Node<T> temptail = tail;

        Node<T> tmp;

        if(o.equals(tail.getElement())){
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
            return true;
        }

        for (int i = 1; i < size(); i++) {
            tmp = temptail;
            temptail = temptail.getPrevious();

            if(o.equals(temptail.getElement())){
                temptail = tmp;
                temptail.setNext(temptail.getPrevious().getPrevious());
                size--;
                if(i==size()-1)
                    head = temptail;
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node <T> nextElement = head;

            @Override
            public boolean hasNext() {
                return nextElement != null;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                T result = nextElement.getElement();
                nextElement = nextElement.getNext();
                return result;
            }
        };
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {

            Node <T> previousElement = tail;

            @Override
            public boolean hasNext() {
                return previousElement != null;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                T result = previousElement.getElement();
                previousElement = previousElement.getPrevious();
                return result;
            }
        };
    }
}
