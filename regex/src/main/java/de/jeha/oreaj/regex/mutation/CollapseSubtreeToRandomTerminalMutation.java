package de.jeha.oreaj.regex.mutation;

import de.jeha.oreaj.genetic.core.Mutation;
import de.jeha.oreaj.regex.rx.Letter;
import de.jeha.oreaj.regex.rx.RX;
import de.jeha.oreaj.regex.subtree.Subtree;

import java.util.List;

/**
 * @author jenshadlich@googlemail.com
 */
public class CollapseSubtreeToRandomTerminalMutation implements Mutation<RX> {

    private static final java.util.Random GENERATOR = new java.util.Random();

    private final String[] sigma;

    public CollapseSubtreeToRandomTerminalMutation(String[] sigma) {
        this.sigma = sigma;
    }

    @Override
    public RX mutate(RX genotype) {
        final RX mutant = genotype.deepClone();
        final RX subtree = Subtree.randomSubtree(mutant);
        final Letter newLetter = new Letter(sigma[GENERATOR.nextInt(sigma.length)]);
        final List<RX> siblings = subtree.siblings();

        // if it's not a terminal, replace a random sibling
        if (siblings.size() > 0) {
            RX selectedChild = siblings.get(GENERATOR.nextInt(siblings.size()));
            subtree.substitute(selectedChild, newLetter);
        }

        return mutant;
    }
}
