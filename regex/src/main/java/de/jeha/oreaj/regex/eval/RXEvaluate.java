package de.jeha.oreaj.regex.eval;

import de.jeha.oreaj.genetic.Evaluator;
import de.jeha.oreaj.regex.rx.RX;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RXEvaluate implements Evaluator<RX> {

    private Automaton target = null;

    // private final Logger LOG = LoggerFactory.getLogger(RXEvaluate.class);

    public RXEvaluate(Automaton target) {
        this.target = target;
    }

    @Override
    public double evaluate(RX individual) {
        return RXEvaluate.evaluate7(this.target, individual);
    }

    public static double evaluate6(Automaton target, RX individual) {

        RegExp rx = new RegExp(individual.show());
        Automaton semantics = rx.toAutomaton();

        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100000 * weight(tooMuch);
        double weightMissing = 1000 * weight(missing);

        return weightTooMuch + weightMissing;
    }

    public static double evaluate7(Automaton target, RX individual) {

        RegExp rx = new RegExp(individual.show());
        Automaton semantics = rx.toAutomaton();

        Automaton tooMuch = semantics.minus(target);
        Automaton missing = target.minus(semantics);

        double weightTooMuch = 100000 * weight(tooMuch);
        double weightMissing = 1000 * weight(missing);

        return weightTooMuch + weightMissing + individual.show().length() * 0.1;
    }

    public static double weight(Automaton a) {
        double sum = 0.0;

        for (String s : accepted(a, 10)) {
            sum += Math.pow(2, -1 * s.length());
        }
        return sum;
    }

    /**
     * TODO: refactor to use iterator pattern
     *
     * @param automaton
     * @param limit
     * @return
     */
    public static List<String> accepted(Automaton automaton, int limit) {
        int len = 0;
        List<String> acc = new ArrayList<>();
        int cSize = 0;
        int cLoops = 0;

        add:
        while (true) {
            cLoops++;

            // get all accepted strings for the given size
            Set<String> curr = automaton.getStrings(cSize); // TODO: investigate why the set has a different order with Java 8

            // no Strings for the given size
            // TODO: refactor magic number
            if (curr.isEmpty() && cLoops > 1000)
                break;

            for (String s : curr) {
                if (len < limit) {
                    acc.add(s); // add to list of accepted strings
                    len++;
                } else {
                    break add;
                }
            }
            cSize++;
        }

        return acc;
    }

}
