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

		// �H�����
		for (int i = 0; i < num_of_clusters; i++) {
			List<Double> clustor = new ArrayList<Double>();

			int num1 = (int) (Math.random() * datatoList.size() + 1);
			clustor.add(datatoList.get(num1));
			grendby.add(clustor);
			//�R������
			datatoList.remove(num1);
		}

		
		for (int i = 0; i < num_of_clusters; i++) {
			double clustorTotal = 0;
			double clustoravg;
			for (int j = 0; j < grendby.get(i).size(); j++) {
				clustorTotal += grendby.get(i).get(j);
			}
			clustoravg = clustorTotal / grendby.get(i).size();
			
			// �M�s���ߤ��
			for (Double item : datatoList) {
				//�M�s����+-10�[�J
				if (item - clustoravg == 0 || item - clustoravg <= 10
					&& item - clustoravg >= -10) {
					grendby.get(i).add(item);
					datatoList.remove(item);
				}
			}
								
		}
		
		//�b�p��s���ߥ�����
		datatoList=Arrays.asList(data);
		double clustorTotal = 0;
		double clustoravg;
		for (int i = 0; i < num_of_clusters; i++) {
			for (int j = 0; j < grendby.get(i).size(); j++) {
				clustorTotal += grendby.get(i).get(j);
			}
			clustoravg = clustorTotal / grendby.get(i).size();
			
			//��l��list
			grendby.get(i).remove(grendby.get(i));
			//�b�M�s���ߥ���
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
