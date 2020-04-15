package second;

import edu.duke.*;
import java.io.*;

public class TagFinder {
	private int a;
	public int findGene(String dna, String stop_codon, int start) {
            int in_start = start;
			
		while(start!=-1) {
			
			int stop = dna.indexOf(stop_codon, start+3);
			if(stop==-1)
				return dna.length();
//			System.out.println(((stop - start_codon_index) % 3)+" "+stop_codon+" "+stop+" "+dna.substring(start_codon_index, stop+3));
			if((stop - in_start) % 3 == 0)
				return stop;
			start = stop;
		}

		return dna.length();
	}
	public int find_CTG(String dna) {
		String stop_codon = "ctg";
		int res = 0;
		int start = 0;
while(start!=-1) {
			
			int stop = dna.indexOf(stop_codon, start);
			if(stop==-1)
				return res;
//			System.out.println(((stop - start_codon_index) % 3)+" "+stop_codon+" "+stop+" "+dna.substring(start_codon_index, stop+3));
			res++;
			start = stop+3;
		}
return res;
	}
	public int findProtein2(String dna) {
		int longest=0;
		int res = 0;
		String end[] = {"taa", "tag", "tga"};
		dna = dna.toLowerCase();
		int start = dna.indexOf("atg");
		
		System.out.println(start);
		while(start!=-1) {
			int stop_min = dna.length();
			for(int i=0;i<3;i++) {
				int stop = findGene(dna,end[i],start);
				if(stop==dna.length())
					continue;
				System.out.println("stop="+stop+" "+(stop-start));
				if ((stop - start) % 3 == 0 && stop_min>stop) {
					stop_min = stop;
				}
				
			}
			System.out.println(stop_min);
			if(stop_min!=dna.length()) {
				String gene = dna.substring(start,stop_min+3);
				System.out.println(gene);
				if(gene.length()>longest)
					longest = gene.length();
//				if(countcg(gene)/gene.length()>0.35)
//				if(gene.length()>60)
				res++;
				start = dna.indexOf("atg", stop_min+3);
			}
			else
			start = dna.indexOf("atg", start+3);
		
		}
		System.out.println("longest="+longest);
		return res;
		
	}
	public double countcg(String gene) {
		gene = gene.toLowerCase();
		double res=0;
		for(int i=0;i<gene.length();i++) {
			if(gene.charAt(i)=='c'||gene.charAt(i)=='g')
				res++;
		}
		return res;
	}
	public void findAbc(String input) {
		int index = input.indexOf("abc");
	       while (true){
	           if (index == -1 || index >= input.length() - 3){
	               break;
	           }
	           String found = input.substring(index+1, index+4);
	           System.out.print(found+",");
	           index = input.indexOf("abc",index+3);
//	           System.out.print(index);
	       }
	}
	public static void main(String args[]) {
		TagFinder tg = new TagFinder();
//		String dna = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
//		System.out.println(tg.findProtein2(dna));
	//		System.out.println(tg.countcg("ATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAA"));
				FileResource fr = new FileResource();
		for(String dna: fr.lines())
		{		
			System.out.println(dna.length()+" "+dna);
			System.out.println(tg.findProtein2(dna));
			System.out.println(tg.find_CTG(dna.toLowerCase()));
			System.out.print(tg.a);
//		tg.findAbc("abcabcabcabca");
		}
	}
}