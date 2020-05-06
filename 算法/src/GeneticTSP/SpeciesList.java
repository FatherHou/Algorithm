package GeneticTSP;
public class SpeciesList 
{
	SpeciesNode head;//ͷ���
	int speciesNum;//��������
	
	SpeciesList()
	{
		head=new SpeciesNode();
		speciesNum=Constant.SPECIES_NUM;
	}
	
	//�������
	void add(SpeciesNode species)
	{
		SpeciesNode point=head;//�α�
		while(point.next != null)//Ѱ�ұ�β���
			point=point.next;
		point.next=species;
	}
	
	//����
	void traverse()
	{
		SpeciesNode point=head.next;//�α�
		while(point != null)//Ѱ�ұ�β���
		{
			for(int i=0;i<Constant.CITY_NUM;i++)
				System.out.print(point.genes[i]+" ");
			System.out.println(point.distance);
			point=point.next;
		}
		System.out.println("_______________________");
	}
}
