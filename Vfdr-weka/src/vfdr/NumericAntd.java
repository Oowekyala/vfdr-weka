package vfdr;

import weka.core.Attribute;
import weka.core.Instance;

/**
 * Antd for numerical attributes.
 * 
 * @author cl-fo
 * 
 */
public class NumericAntd extends Antd {

	/** The split point for this numeric antecedent */
	private double m_splitPoint;

	/**
	 * Is 0 if the condition is <=, 1 otherwise
	 */
	private boolean m_condition;

	/**
	 * Builds a numeric antecedent from the attname
	 * 
	 * @param a
	 */
	public NumericAntd(Attribute attribute) {
		m_attribute = attribute;
		m_splitPoint = Double.NaN;
		m_isNominal = false;
	}


	@Override
	public boolean covers(Instance inst) {

		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get split point of this numeric antecedent
	 * 
	 * @return the split point of this numeric antecedent
	 */
	public double getSplitPoint() {
		return m_splitPoint;
	}

	public void setSplitPoint(double m_splitPoint) {
		this.m_splitPoint = m_splitPoint;
	}

	public boolean isHigherCondition() {
		return m_condition;
	}

	public void setCondition(boolean m_condition) {
		this.m_condition = m_condition;
	}

}
