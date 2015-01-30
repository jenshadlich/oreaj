package de.jeha.oreaj.genetic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population<GT> implements Iterable<Individual<GT>> {

    private static final Logger LOG = LoggerFactory.getLogger(Population.class);

    private List<Individual<GT>> individuals = null;

    public Population(List<Individual<GT>> is) {
        super();
        this.individuals = is;
    }

    public Individual<GT> best() {
        Collections.sort(individuals);
        try {
            return individuals.get(0);
        } catch (IndexOutOfBoundsException e) {
            LOG.error("Error", e);
            return null;
        }
    }

    public void join(List<Individual<GT>> individuals) {
        this.individuals.addAll(individuals);
    }

    @Override
    public Iterator<Individual<GT>> iterator() {

        final Iterator<Individual<GT>> it = this.individuals.iterator();

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
