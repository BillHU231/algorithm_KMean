package algorithm.com.sysytex.billhu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab1_v1 {

	public static void main(String[] args) {
		Double[] data = { 4., 2., 3., 1., 50., 51., 52., 53., 500., 501., 502., 503. };
		System.out.println(algorithm(data, 3));

	}

	public static List<List<Double>> algorithm(Double[] data, int num_of_clusters) {

		List<Double> datatoList = new ArrayList(Arrays.asList(data));
		List<List<Double>> grendby = new ArrayList<>(num_of_clusters);

		// 隨機放數
		for (int i = 0; i < num_of_clusters; i++) {
			List<Double> clustor = new ArrayList<Double>();

			int num1 = (int) (Math.random() * datatoList.size() + 1);
			clustor.add(datatoList.get(num1));
			grendby.add(clustor);
			//刪除元素
			datatoList.remove(num1);
		}

		
		for (int i = 0; i < num_of_clusters; i++) {
			double clustorTotal = 0;
			double clustoravg;
			for (int j = 0; j < grendby.get(i).size(); j++) {
				clustorTotal += grendby.get(i).get(j);
			}
			clustoravg = clustorTotal / grendby.get(i).size();
			
			// 和群中心比較
			for (Double item : datatoList) {
				//和群中心+-10加入
				if (item - clustoravg == 0 || item - clustoravg <= 10
					&& item - clustoravg >= -10) {
					grendby.get(i).add(item);
					datatoList.remove(item);
				}
			}
								
		}
		
		//在計算群中心平均數
		datatoList=Arrays.asList(data);
		double clustorTotal = 0;
		double clustoravg;
		for (int i = 0; i < num_of_clusters; i++) {
			for (int j = 0; j < grendby.get(i).size(); j++) {
				clustorTotal += grendby.get(i).get(j);
			}
			clustoravg = clustorTotal / grendby.get(i).size();
			
			//初始化list
			grendby.get(i).remove(grendby.get(i));
			//在和群中心必較
			for (Double item : datatoList) {
				
				if (item - clustoravg == 0 || item - clustoravg >= 10
						|| item - clustoravg <= -10) {
					grendby.get(i).add(item);
					datatoList.remove(item);
				}
			}
		}
		
		
		return grendby;
	}

}
