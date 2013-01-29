package de.jeha.oreaj.genetic;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population<GT> implements Iterable<Individual<GT>> {
    private List<Individual<GT>> is = null;

    public Population(List<Individual<GT>> is) {
        super();
        this.is = is;
    }

    public Individual<GT> best() {
        Collections.sort(is);
        try {
            return is.get(0);
        } catch (IndexOutOfBoundsException ioobe) {
            // TODO: error handling
            return null;
        }
    }

    public void join(List<Individual<GT>> is) {
        this.is.addAll(is);
    }

    @Override
    public Iterator<Individual<GT>> iterator() {

        final Iterator<Individual<GT>> it = this.is.iterator();

        return new Iterator<Individual<GT>>() {

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Individual<GT> next() {
                return it.next();
            }

            @Override
            public void remove() {
                it.remove();
            }
        };
    }

}
