package test;

public class Test{
	//ͬ���Ƿ��лʺ�1��ʾ��
	private int[] column;
	 
	//�����������Ƿ��лʺ�
	private int[] rup;
	 
	//�����������Ƿ��лʺ�
	private int[] lup;
	 
	//���
	private int[] queen;
	 
	//�����
	private int num;
	private int num1;
	public Test(){
	column=new int[16+1];
	rup=new int[(2*16)+1];
	lup=new int[(2*16)+1];
	num1 = 0; 
	for(int i=1;i<=16;i++)
	column[i]=0;
	 
	for(int i=1;i<=(2*16);i++)
	rup[i]=lup[i]=0;  //��ʼ����ȫ���޻ʺ�
	 
	queen=new int[16+1];
	}
	 
	public void backtrack(int i){
	if(i>16){
	num1++;
	}else{
	for(int j=1;j<=16;j++){
	if((column[j]==0)&&(rup[i+j]==0)&&(lup[i-j+16]==0)){
	//���޻ʺ�
	queen[i]=j;
	//�趨Ϊռ��
	column[j]=rup[i+j]=lup[i-j+16]=1;
	backtrack(i+1);  //ѭ������
	column[j]=rup[i+j]=lup[i-j+16]=0;
	if(i == 1){
		System.out.println(num1);
	}
	}
	}
	}
	}
	 
	protected void showAnswer(){
	num++;
	System.out.println("\n���"+num);
	 
	for(int y=1;y<=16;y++){
	for(int x=1;x<=16;x++){
	if(queen[y]==x){
	System.out.print("Q");
	}else{
	System.out.print(".");
	}
	}
	 
	System.out.println();
	}
	}
	 
	public static void main(String[]args){
	Test queen=new Test();
	queen.backtrack(1);
	}
	}