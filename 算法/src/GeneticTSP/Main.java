package GeneticTSP;

public class Main 
{
	public static void main(String[] args) 
	{
		//�����Ŵ��㷨��������
		GeneticAlgorithm GA=new GeneticAlgorithm();
		
		//������ʼ��Ⱥ
		SpeciesList speciesList=new SpeciesList();

		//��ʼ�Ŵ��㷨��ѡ�����ӡ��������ӡ��������ӣ�
		SpeciesNode bestRate=GA.run(speciesList);

		//��ӡ·������̾���
		bestRate.printRate();
	}
}
