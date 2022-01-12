package algorithm.com.sysytex.billhu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lab1_v2 {

	public static void main(String[] args) {
		Double[] data = { 4., 2., 3., 1., 50., 51., 52., 53., 500., 501., 502., 503. };
		System.out.println(algorithm(data, 3));

	}

	public static List<List<Double>> algorithm(Double[] data, int num_of_clusters) {
		List<Double> dataList = new LinkedList<Double>(Arrays.asList(data));
		List<List<Double>> clusters = new ArrayList<List<Double>>();		
		// 隨機選k點
		
		List<Double> clustersAvginit=new ArrayList<Double>();
		List<Double> clustersAvg=new ArrayList<Double>();
		
		
		for (int i = 1; i <= num_of_clusters; i++) {
			int num = (int) (Math.random() * (num_of_clusters + 1));
			double num2=dataList.get(num);
			clustersAvginit.add(num2);
			dataList.remove(num);
		}
		
		dataList = new LinkedList<Double>(Arrays.asList(data));
		while(true) {
			clusters= compare1(dataList, clustersAvginit);
					
			clustersAvg = clusterAvg(clusters);
			
			if(compareAvg(clustersAvginit,clustersAvginit)) {
				break;
			}else {
				clustersAvginit=clustersAvg;
			}
		}
		
		return clusters;


	}
	
	public static boolean compareAvg(List<Double> clustersAvg1,List<Double> clustersAvg2) {
		for(int i=0;i<clustersAvg1.size();i++) {
			if(clustersAvg1.get(i)==clustersAvg2.get(i)) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}



	public static List<Double> clusterAvg(List<List<Double>> clusters) {
		List<Double> clustersAvg = new ArrayList<Double>();
		for (List<Double> item1 : clusters) {
			Double sum = 0.;
			for (Double item2 : item1) {
				sum += item2;
			}
			clustersAvg.add(sum / item1.size());
		}
		return clustersAvg;
	}

	public static List<List<Double>> compare1(List<Double> dataList, List<Double> clustersAvg) {
		//群
		List<List<Double>> clusters =new ArrayList<List<Double>>();
		//紀錄和中心點距離
		List<List<Double>> diffs=new ArrayList<List<Double>>();
		
		for(int i=0;i<dataList.size();i++) {
			List<Double> diff=new ArrayList<Double>();
			for(int j=0;j<clustersAvg.size();j++) {
				Double num=Math.abs(  dataList.get(i)-clustersAvg.get(j));
				diff.add(num);
			}
			diffs.add(diff);
		}
		
		for(int i=0;i<clustersAvg.size();i++) {
			List<Double> cluster =new ArrayList<Double>();
			clusters.add(cluster);
		}
		
		
		//距離越小就放入群
		
		for(int i=0;i<diffs.size();i++) {
			
			
			List<Double> item1=diffs.get(i);
			List<Double> item2=item1.stream().sorted().collect(Collectors.toList());
			
			int index=item1.indexOf(item2.get(0));		
			clusters.get(index).add(dataList.get(i));
		}
		return clusters;

	}

}
