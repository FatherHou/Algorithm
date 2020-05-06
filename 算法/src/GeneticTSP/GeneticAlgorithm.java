package GeneticTSP;

import java.util.Random;

public class GeneticAlgorithm 
{
	//��ʼ�Ŵ�
	SpeciesNode run(SpeciesList list)
	{
		//������ʼ��Ⱥ
		createBeginningSpecies(list);
		
		for(int i=1;i<=Constant.DEVELOP_NUM;i++)
		{
			//ѡ��
			select(list);
			
			//����
			crossover(list);
			
			//����
			mutate(list);
		}	
		
		return getBest(list);
	}
	
	//������ʼ��Ⱥ
	void createBeginningSpecies(SpeciesList list)
	{
		//100%���
		int randomNum=(int)(Constant.SPECIES_NUM);
		for(int i=1;i<=randomNum;i++)
		{
			SpeciesNode species=new SpeciesNode();//�������
			species.createByRandomGenes();//��ʼ��Ⱥ����

			list.add(species);//�������
		}
		
//		//40%̰��
//		int greedyNum=Constant.SPECIES_NUM-randomNum;
//		for(int i=1;i<=greedyNum;i++)
//		{
//			SpeciesNode species=new SpeciesNode();//�������
//			species.createByGreedyGenes();//��ʼ��Ⱥ����
//
//			this.add(species);//�������
//		}
	}

	//����ÿһ���ֱ�ѡ�еĸ���
	void calRate(SpeciesList list)
	{
		//��������Ӧ��
		float totalFitness=0.0f;
		list.speciesNum=0;
		SpeciesNode point=list.head.next;//�α�
		while(point != null)//Ѱ�ұ�β���
		{
			point.calFitness();//������Ӧ��
			
			totalFitness += point.fitness;
			list.speciesNum++;

			point=point.next;
		}

		//����ѡ�и���
		point=list.head.next;//�α�
		while(point != null)//Ѱ�ұ�β���
		{
			point.rate=point.fitness/totalFitness;
			point=point.next;
		}
	}
	
	//ѡ���������֣����̶ģ�
	void select(SpeciesList list)
	{			
		//������Ӧ��
		calRate(list);
		
		//�ҳ������Ӧ������
		float talentDis=Float.MAX_VALUE;
		SpeciesNode talentSpecies=null;
		SpeciesNode point=list.head.next;//�α�

		while(point!=null)
		{
			if(talentDis > point.distance)
			{
				talentDis=point.distance;
				talentSpecies=point;
			}
			point=point.next;
		}

		//�������Ӧ�����ָ���talentNum��
		SpeciesList newSpeciesList=new SpeciesList();
		int talentNum=(int)(list.speciesNum/4);
		for(int i=1;i<=talentNum;i++)
		{
			//�����������±�
			SpeciesNode newSpecies=talentSpecies.clone();
			newSpeciesList.add(newSpecies);
		}

		//���̶�list.speciesNum-talentNum��
		int roundNum=list.speciesNum-talentNum;
		for(int i=1;i<=roundNum;i++)
		{
			//����0-1�ĸ���
			float rate=(float)Math.random();
			
			SpeciesNode oldPoint=list.head.next;//�α�
			while(oldPoint != null && oldPoint != talentSpecies)//Ѱ�ұ�β���
			{
				if(rate <= oldPoint.rate)
				{
					SpeciesNode newSpecies=oldPoint.clone();
					newSpeciesList.add(newSpecies);
					
					break;
				}
				else
				{
					rate=rate-oldPoint.rate;
				}
				oldPoint=oldPoint.next;
			}
			if(oldPoint == null || oldPoint == talentSpecies)
			{
				//�������һ��
				point=list.head;//�α�
				while(point.next != null)//Ѱ�ұ�β���
					point=point.next;
				SpeciesNode newSpecies=point.clone();
				newSpeciesList.add(newSpecies);
			}
			
		}
		list.head=newSpeciesList.head;
	}
	
	//�������
	void crossover(SpeciesList list)
	{
		//�Ը���pcl~pch����
		float rate=(float)Math.random();
		if(rate > Constant.pcl && rate < Constant.pch)
		{			
			SpeciesNode point=list.head.next;//�α�
			Random rand=new Random();
			int find=rand.nextInt(list.speciesNum);
			while(point != null && find != 0)//Ѱ�ұ�β���
			{
				point=point.next;
				find--;
			}
		
			if(point.next != null)
			{
				int begin=rand.nextInt(Constant.CITY_NUM);

				//ȡpoint��point.next���н��棬�γ��µ�����Ⱦɫ��
				for(int i=begin;i<Constant.CITY_NUM;i++)
				{
					//�ҳ�point.genes����point.next.genes[i]��ȵ�λ��fir
					//�ҳ�point.next.genes����point.genes[i]��ȵ�λ��sec
					int fir,sec;
					for(fir=0;!point.genes[fir].equals(point.next.genes[i]);fir++);
					for(sec=0;!point.next.genes[sec].equals(point.genes[i]);sec++);
					//�������򻥻�
					String tmp;
					tmp=point.genes[i];
					point.genes[i]=point.next.genes[i];
					point.next.genes[i]=tmp;
					
					//��ȥ�������ظ����Ǹ�����
					point.genes[fir]=point.next.genes[i];
					point.next.genes[sec]=point.genes[i];
					
				}
			}
		}
	}
	
	//�������
	void mutate(SpeciesList list)
	{	
		//ÿһ���־��б���Ļ���,�Ը���pm����
		SpeciesNode point=list.head.next;
		while(point != null)
		{
			float rate=(float)Math.random();
			if(rate < Constant.pm)
			{
				//Ѱ����ת���Ҷ˵�
				Random rand=new Random();
				int left=rand.nextInt(Constant.CITY_NUM);
				int right=rand.nextInt(Constant.CITY_NUM);
				if(left > right)
				{
					int tmp;
					tmp=left;
					left=right;
					right=tmp;
				}
				
				//��תleft-right�±�Ԫ��
				while(left < right)
				{
					String tmp;
					tmp=point.genes[left];
					point.genes[left]=point.genes[right];
					point.genes[right]=tmp;

					left++;
					right--;
				}
			}
			point=point.next;
		}
	}

	//�����Ӧ����������
	SpeciesNode getBest(SpeciesList list)
	{
		float distance=Float.MAX_VALUE;
		SpeciesNode bestSpecies=null;
		SpeciesNode point=list.head.next;//�α�
		while(point != null)//Ѱ�ұ�β���
		{
			if(distance > point.distance)
			{
				bestSpecies=point;
				distance=point.distance;
			}

			point=point.next;
		}
		
		return bestSpecies;
	}
}
