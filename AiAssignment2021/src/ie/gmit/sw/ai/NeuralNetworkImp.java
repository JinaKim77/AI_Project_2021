package ie.gmit.sw.ai;

import ie.gmit.sw.ai.nn.BackpropagationTrainer;
import ie.gmit.sw.ai.nn.NeuralNetwork;
import ie.gmit.sw.ai.nn.Utils;
import ie.gmit.sw.ai.nn.activator.Activator;

/**
* 
*
* @author Jina Kim
*
* 
* 
*
*/

public class NeuralNetworkImp {
	public int testIndex;

	public NeuralNetworkImp() throws Exception {
		NeuralNetwork nn = new NeuralNetwork(Activator.ActivationFunction.Sigmoid, 4, 3, 4);
		
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
		trainer.train(data, expected, 0.6, 10000);
		//trainer.train(Utils.normalize(data, 0, 10), expected, 0.6, 10000);
		
		testIndex = 6;  //*
		
		double[] result = nn.process(data[testIndex]);
		for (int i = 0; i < expected[testIndex].length; i++){
		 System.out.print(expected[testIndex][i] + ",");
		}
		System.out.println("==> " + (Utils.getMaxIndex(result) + 1)+"\n");
		
		int pnn = (Utils.getMaxIndex(result) + 1);
		
		PlayerNN(pnn);
		
	}
	
	/*
	 *  
	 * 
	 *  Inputs
	 *  -------------
	 *  1) Health (2 = Healthy, 1 = Minor Injuries, 0 = Serious Injuries)
	 *  2) Has a Sword (1 = yes, 0 = no)
	 *  3) Has a Gun (1 = yes, 0 = no)
	 *  4) Number of Enemies (This value may need to be normalized)
	 *  
	 *  Outputs
	 *  -------------
	 *  1) Panic
	 *  2) Attack
	 *  3) Hide
	 *  4) Run
	 *  
	 */
	

	private double[][] data = { //Health, Sword, Gun, Enemies
		{ 2, 0, 0, 0 }, { 2, 0, 0, 1 }, { 2, 0, 1, 1 }, { 2, 0, 1, 2 }, { 2, 1, 0, 2 },
		{ 2, 1, 0, 1 }, { 1, 0, 0, 0 }, { 1, 0, 0, 1 }, { 1, 0, 1, 1 }, { 1, 0, 1, 2 }, 
		{ 1, 1, 0, 2 }, { 1, 1, 0, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 1 }, 
		{ 0, 0, 1, 2 }, { 0, 1, 0, 2 }, { 0, 1, 0, 1 } };

	private double[][] expected = { //Panic, Attack, Hide, Run
		{ 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 1.0, 0.0, 0.0, 0.0 }, 
		{ 0.0, 0.0, 0.0, 1.0 }, { 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
		{ 1.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, 
		{ 0.0, 0.0, 1.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 0.0, 0.0, 1.0 }, { 0.0, 1.0, 0.0, 0.0 }, 
		{ 0.0, 1.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 1.0 } };

	public void PlayerNN (int nn) throws Exception {
		
		if(nn == 1) {
			panic();
		}else if(nn == 2) {
			attack();
		}else if(nn == 3) {
			hide();
		}else if(nn == 4) {
			run();
		}
	}
	
	public void panic() throws Exception {
		System.out.println("Panic..\n");
	}
	
	public void attack() throws Exception {
		System.out.println("Attack..\n");
	}
	
	public void hide() throws Exception {
		System.out.println("Hide..\n");
	}
	
	public void run() throws Exception {
		System.out.println("Run..\n");
	}
	
	public static void main(String[] args) throws Exception {

		new NeuralNetworkImp();

	}

}
