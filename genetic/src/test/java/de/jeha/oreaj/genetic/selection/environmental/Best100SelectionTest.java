package de.jeha.oreaj.genetic.selection.environmental;

import de.jeha.oreaj.genetic.core.ConfigurationBuilder;
import de.jeha.oreaj.genetic.core.Individual;
import de.jeha.oreaj.genetic.core.Population;
import de.jeha.oreaj.genetic.selection.EnvironmentalSelection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class Best100SelectionTest {

    @Test
    public void test() {
        EnvironmentalSelection<Integer> selection = new Best100Selection<>(
                new ConfigurationBuilder().setPopulationMaxSize(3).build()
        );
        List<Individual<Integer>> individuals = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            individuals.add(new Individual<>(i, i));
        }

        Population<Integer> population = new Population<>(individuals);
        Population<Integer> populationAfterSelection = selection.select(population);

        assertEquals(3, populationAfterSelection.size());
    }
}
