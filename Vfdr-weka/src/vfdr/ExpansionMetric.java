package vfdr;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import weka.core.ContingencyTables;

/**
 * Metrics evaluate the merit of an expansion (given the pre and post expansion
 * class distribution) to chose the best.
 * 
 * @author Cl�ment Fournier (clement.fournier@insa-rennes.fr)
 * @version VFDR-Base
 */
public abstract class ExpansionMetric {

	/**
	 * Evaluates expansion candidates based on the post-expansion class
	 * distribution predicted for the rule. If several post-expansion
	 * distributions are found, their score is put in order in the array
	 * returned.
	 * 
	 * When evaluating an expansion on a numeric attribute, once the splitpoint
	 * is chosen, the antecedent could take either a &lt;= or &gt; condition.
	 * Both distributions are evaluated so as to chose the best condition
	 * 
	 * @param preDist
	 *            Previous distribution
	 * @param postDists
	 *            Post-expansion distributions
	 * @return An array of one score for each post-expansion distribution
	 */
	public abstract double[] evaluateExpansions(Map<String, Integer> preDist, List<Map<String, Integer>> postDists);

	/**
	 * Gets the range of the metric. Used to compute Hoeffding's bound
	 * 
	 * @return The range of the metric
	 */
	public abstract double getMetricRange();

	/**
	 * Actually based on information gain for the moment
	 * 
	 * @author Cl�ment Fournier (clement.fournier@insa-rennes.fr)
	 * @version VFDR-Base
	 */
	public static class Entropy extends ExpansionMetric {

		@Override
		public double[] evaluateExpansions(Map<String, Integer> preDist, List<Map<String, Integer>> postDists) {

			double[] pre = new double[preDist.size()];
			int count = 0;
			for (Map.Entry<String, Integer> e : preDist.entrySet()) {
				pre[count++] = e.getValue();
			}

			double preEntropy = ContingencyTables.entropy(pre);

			double[] scores = new double[postDists.size()];
			count = 0;
			for (Map<String, Integer> dist : postDists) {

				double[] post = new double[dist.size()];
				int i = 0;
				for (Map.Entry<String, Integer> e : dist.entrySet()) {
					post[i++] = e.getValue();
				}

				scores[count++] = preEntropy - ContingencyTables.entropy(post);
			}
			System.err.println("@ExpansionMetric.Entropy.evaluateExpansions: pre = " + preEntropy + ", scores = " + Arrays.toString(scores));
			return scores;
		}

		@Override
		public double getMetricRange() {
			return 1;
		}

	}

}