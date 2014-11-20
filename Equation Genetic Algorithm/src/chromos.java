
public class chromos {
	
	public static void main(String[] args){
		
		double targetValue = Double.parseDouble(args[0]);
		chromos[] pool = new chromos[40];
		chromos[] newPool = new chromos[40];
		targetValue = 678.896;
		int generation = 0;
		double crossoverRate = .7;
		double mutationRate = .01;
		boolean done = false;
		double biggest = 0;
		int index = 0;
		double totalFitness = 0;
		int answer = 0;
 
		/* Population Creation and Encoding */
		for(int i=0; i<40; i++){
			pool[i] = new chromos();
			pool[i].decodeChromosone();
			pool[i].evaluateChromosoneString();
			pool[i].getFitness(targetValue);
			
			totalFitness += pool[i].fitness;
			
			if (pool[i].fitness > biggest){
				biggest = pool[i].fitness;
				index = i;
			}
			
		}
		
		while(!done) {
			/* Generating new pool */
			newPool[0] = pool[index]; //Elitism
			newPool[1] = pool[index]; //Elitism with mutation (for possible improvation)
			newPool[1].mutate(1-mutationRate);
			for(int j=2; j<39; j+=2){ //Generates the next 38 by reproduction
				/* Use roulette wheel selection to select two mates */
				double slice1 = Math.random()*totalFitness;
				double slice2 = Math.random()*totalFitness;
				double atotal = 0;
				double btotal = 0;
				int i = 0;
				int member1 = 0;
				int member2 = 0;
				while (atotal<slice1 || btotal<slice2){
					if (atotal<slice1) {
						atotal += pool[i].fitness;
						member1 = i;
					}	
					if (btotal<slice2) {
						btotal += pool[i].fitness;
						member2 = i;
					}
					i++;
				}
				chromos baby1 = pool[member1].crossover(pool[member1], pool[member2], crossoverRate);
				chromos baby2 = pool[member2].crossover(pool[member2], pool[member1], crossoverRate);
				baby1.mutate(mutationRate);
				baby2.mutate(mutationRate);
				newPool[j] = baby1;
				newPool[j+1] = baby2;
			}
			biggest = 0;
			/* Evaluate the new pool */
			totalFitness = 0;
			for(int i=0; i<40; i++){
				newPool[i].decodeChromosone();
				newPool[i].evaluateChromosoneString();
				newPool[i].getFitness(targetValue);
				
				if ((newPool[i].fitness==0) && newPool[i].isValid){
					done = true;
					answer = i;
					break;
				}
				totalFitness += newPool[i].fitness;
				
				if (newPool[i].fitness > biggest){
					biggest = newPool[i].fitness;
					index = i;
				}
				
			}
			
			/* Start over */
			System.arraycopy(newPool, 0, pool, 0, 40);
			generation++;
		}
		System.out.println(newPool[answer].decodedChromosone+"="+targetValue);
		System.out.println("Generation: "+generation);
		}
	

		String chromosone; 
		String decodedChromosone;
		double score;
		double fitness;
		boolean isValid;
		
		private chromos(){
			double x = Math.pow(2, 40);
			long population = (long) (Math.random()* x);
			this.chromosone = Long.toBinaryString(population);
			isValid = true;
		}
		private chromos(String chromosone){
			this.chromosone = chromosone;
			isValid = true;
		}
		private String decodeChromosone(){
			this.decodedChromosone = "";
			int x = chromosone.length() % 4;
			if (x == 0)
				x = 4;
			this.chromosone = "00000".substring(0,4 - x) + chromosone;
			int i =-1;
			decode:
			for(int j=0; j<(chromosone.length()/4); j++){
				int current = Integer.parseInt(chromosone.substring(j*4, (j+1)*4),2);

				switch(current){
				case 10:
					decodedChromosone += "+"; break;
				case 11:
					decodedChromosone += "-"; break;
				case 12:
					decodedChromosone += "*"; break;
				case 13:
					decodedChromosone += "/"; break;	
				case 14:
				case 15:
					continue decode;
				default:	
					decodedChromosone += current;
					
				}
				i++;
				if (i%2 == 0) {
					if (Character.isDigit(decodedChromosone.charAt(i)) == false){
						decodedChromosone = decodedChromosone.substring(0, i) + (decodedChromosone).substring(i+1);
						i--;}
				} else {
					if (Character.isDigit(decodedChromosone.charAt(i))){
							decodedChromosone = decodedChromosone.substring(0, i) + (decodedChromosone).substring(i+1);
						i--;
					}
					}
				
				
			}
			if (decodedChromosone.length() == 0)
				decodedChromosone = "0";
			if (!Character.isDigit(decodedChromosone.charAt(decodedChromosone.length()-1))){
				decodedChromosone = decodedChromosone.substring(0, decodedChromosone.length()-1);
			}
			return decodedChromosone;
		}
		private double evaluateChromosoneString(){
			score = 0;
			score += Character.digit(this.decodedChromosone.charAt(0),10);
			eval:
			for (int i=1; i<decodedChromosone.length() ;i+=2){
				switch(decodedChromosone.charAt(i)){
					case '+':
						score += Character.digit(this.decodedChromosone.charAt(i+1),10); break;
					case '-':
						score -= Character.digit(this.decodedChromosone.charAt(i+1),10); break;
					case '*':	
						score *= Character.digit(this.decodedChromosone.charAt(i+1),10); break;
					case '/':	
						try {
							score /= Character.digit(this.decodedChromosone.charAt(i+1),10); break;
						} catch (ArithmeticException e){
							score = 0;
							isValid = false;
							break eval;
						}
				}
			}
			return score;
		}
		private double getFitness(double targetValue){
			if (this.isValid){
				
				double x = targetValue - this.score;
				if (x!=0){
					this.fitness = Math.abs(1/x);
				} else {
					this.fitness = 0;
				}
			} else {
				this.fitness = 0;
			}
			
			
			return fitness;
		}
		private chromos crossover(chromos parent1, chromos parent2, double crossoverRate){
			String crossoverChromosone = "";
			if (Math.random() < crossoverRate) {
				crossoverChromosone = parent1.chromosone;
			} else {
				String crossoverChromosone1 = parent1.chromosone.substring(0, (int)(parent1.chromosone.length()*Math.random())); 
				String crossoverChromosone2 = parent2.chromosone.substring((int)(parent2.chromosone.length()*Math.random()));
				crossoverChromosone = crossoverChromosone1 + crossoverChromosone2;
			}
			
			chromos child = new chromos(crossoverChromosone);
			return child;
		}
		private void mutate(double mutationRate){
			for(int k=0; k<this.chromosone.length(); k++){
				if (Math.random()<=mutationRate)
					this.chromosone = chromosone.substring(0, k) + "10".indexOf(chromosone.substring(k,k+1)) + chromosone.substring(k+1); 
			}
		}
}


