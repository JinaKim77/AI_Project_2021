package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
* 
*
* @author Jina Kim
*
* 
* 
*
*/

public class FuzzyLogicImp {

	private static final String FCL_FILE1 = "./resources/fcl/PlayerCal.fcl";
	private static final String FCL_FILE2 = "./resources/fcl/EnemyCal.fcl";
	
	// == Get risk for the player ==
	public double getRiskPlayer(double strength, int damage) {
		FIS fis = FIS.load(FCL_FILE1, true); //Load and parse the FCL
		
		FunctionBlock fb = fis.getFunctionBlock("getRiskPlayer");
		//JFuzzyChart.get().chart(fb);
		fis.setVariable("strength", strength);
		fis.setVariable("damage",damage);
		fis.evaluate();
		
		Variable risk = fb.getVariable("risk");
		//JFuzzyChart.get().chart(risk, risk.getDefuzzifier(), true);
			
		return risk.getValue();
		
	}
	
	// == Get risk for the enemy ==
	public double getRiskEnemy(double strength, int damage) {
		FIS fis = FIS.load(FCL_FILE2, true); //Load and parse the FCL
		
		FunctionBlock fb = fis.getFunctionBlock("getRiskEnemy");
		//JFuzzyChart.get().chart(fb);
		fis.setVariable("strength", strength);
		fis.setVariable("damage",damage);
		fis.evaluate();
		
		Variable risk = fb.getVariable("risk");
		//JFuzzyChart.get().chart(risk, risk.getDefuzzifier(), true);
			
		return risk.getValue();
		
	}
	
}
