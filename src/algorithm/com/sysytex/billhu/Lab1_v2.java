package algorithm.com.sysytex.billhu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Lab1_v2 {

	public static void main(String[] args) {
		Double[] data = { 4., 2., 3., 1., 50., 51., 52., 53., 500., 501., 502., 503. };
		System.out.println(algorithm(data, 3));

	}

	public static List<List<Double>> algorithm(Double[] data, int num_of_clusters) {
		List<Double> dataList = new LinkedList<Double>(Arrays.asList(data));
		
		// 隨機選k點,放入群
		List<List<Double>> answer = new ArrayList<List<Double>>();
		for (int i = 1; i <= num_of_clusters; i++) {
			List<Double> cluster = new ArrayList<Double>();
			int num = (int) (Math.random() * (num_of_clusters + 1));
			cluster.add(dataList.get(num));
			dataList.remove(num);
			answer.add(cluster);
		}

		// 和群中心比較
		List<List<Double>> answer2 = compare1(dataList, answer);

		// 取每組平均值
		List<Double> clustersAvg1 = clusterAvg(answer2);
		// --------------------------------------------------------
		while (true) {

			dataList =new LinkedList<Double>(Arrays.asList(data));

			List<List<Double>> answer3 = compare2(dataList, answer2, clustersAvg1);

			List<Double> clustersAvg2 = clusterAvg(answer3);

			boolean level1 = false;
			boolean level2 = false;

			// 表示群中心都為一樣
			int num1 = 0;
			for (int i = 0; i < clustersAvg1.size(); i++) {
				if (clustersAvg1.get(i) == clustersAvg2.get(i)) {
					num1++;
				}
			}
			if (num1 == num_of_clusters) {
				level1 = true;
			}

			// 表示組別內容值為一樣
			int num2 = 0;
			for (int i = 0; i < answer2.size(); i++) {
				for (int j = 0; j < answer2.get(i).size(); j++) {
					if (answer2.get(i).get(j) == answer3.get(i).get(j)) {
						num2++;
					}
				}
			}
			if (num2 == data.length) {
				level2 = true;
			}
			// 表示條件達成
			if (level1 || level2) {
				answer=answer3;
				break;
			}else {
				answer2 = answer3;
				clustersAvg1 = clustersAvg2;
			}
		}
		return answer;
		

	}

	public static List<List<Double>> compare2(List<Double> dataList,List<List<Double>> clusters,List<Double> clustersAvg) {
		// 記錄哪一群
		Double[] compares = new Double[dataList.size()];
		Map<Double, Integer> comparesMap = new HashMap<Double, Integer>();
		
		for (int i=0;i<dataList.size();i++) {
			
			

			for (int j = 0; j < clustersAvg.size(); j++) {
				Double cluAvg = clustersAvg.get(j);
				// 距離取絕對值
				Double distance = Math.abs(dataList.get(i) - cluAvg);
				compares[i] = distance;
				comparesMap.put(distance, i);
			}
			
			// 越小的代表越接近那個群
			Arrays.sort(compares);
			int clu = comparesMap.get(compares[0]);
			clusters.get(clu).add(dataList.get(clu));

		}
		
//		for(Double[] item:comparesList) {
//			Arrays.sort(item);
//			int clu = comparesMap.get(item[0]);
//		}
		
		
					
		return clusters;

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

	public static List<List<Double>> compare1(List<Double> dataList, List<List<Double>> clusters) {
		for (Double item : dataList) {
			Double[] compares = new Double[clusters.size()];
			// 記錄哪一群
			Map<Double, Integer> comparesMap = new HashMap<Double, Integer>();

			for (int i = 0; i < clusters.size(); i++) {
				Double cluAvg = clusters.get(i).get(0);
				// 距離取絕對值
				Double distance = Math.abs(item - cluAvg);
				compares[i] = distance;
				comparesMap.put(distance, i);
			}
			// 越小的代表越接近那個群
			Arrays.sort(compares);
			int clu = comparesMap.get(compares[0]);
			clusters.get(clu).add(item);
		}
		return clusters;

	}

}
