import java.util.*;
import java.io.*;
public class KMedoid
{
 public static void main(String args[])throws Exception
{
 Scanner src=new Scanner(new BufferedReader(new FileReader("medoidData.txt")));
 int recordNo=0,i,j,k;
 String line=null;
 while(src.hasNextLine())
 {
  line=src.nextLine();
  recordNo++;
 }
 String splitData[]=new String[2];
 double data[][]=new double[recordNo-1][2];
 recordNo=0;
 src=new Scanner(new BufferedReader(new FileReader("medoidData.txt")));
 while(src.hasNextLine())
 {
  recordNo++;
  line=src.nextLine();
  splitData=line.split(" ");
  
  if(recordNo>1)
   {
    data[recordNo-2][0]=Double.parseDouble(splitData[0]);
    data[recordNo-2][1]=Double.parseDouble(splitData[1]);
   }
 }
System.out.println(recordNo);
double manDist[][]=new double[recordNo-1][recordNo-1];
for(i=0;i<recordNo-1;i++)
 for(j=0;j<recordNo-1;j++)
 {
  if(i==j)
   manDist[i][j]=0;
  else 
   manDist[i][j]=Math.abs(data[i][0]-data[j][0])+Math.abs(data[i][1]-data[j][1]);
 }

 src=new Scanner(System.in);
 System.out.println("Enter the no of medoids");
 int noOfMedoids=src.nextInt();
 double min=999999;
 int minPos=0;
 double currMedoid[][]=new double[noOfMedoids][recordNo-1];
 double prevMedoid[][]=new double[noOfMedoids][recordNo-1];
 int currCenter[]=new int[noOfMedoids];
 int prevCenter[]=new int[noOfMedoids];
 double sum[]=new double[noOfMedoids];
 for(i=0;i<noOfMedoids;i++)sum[i]=0;
 for(i=0;i<noOfMedoids;i++)
  currCenter[i]=i;
 for(i=0;i<noOfMedoids;i++)
  for(j=0;j<recordNo-1;j++)
   currMedoid[i][j]=-1;
   
  min=99999;
  while(true){
  for(i=0;i<recordNo-1;i++)
   {for(j=0;j<noOfMedoids;j++)
     if(manDist[currCenter[j]][i]<min)
     {
      min=manDist[currCenter[j]][i];
	  minPos=j;
     }  
	min=99999;
	currMedoid[minPos][i]=1;
   }

  min=99999;	
  for(i=0;i<noOfMedoids;i++)
  { for(j=0;j<recordNo-1;j++)
    {
     if(currMedoid[i][j]==1)	
      {for(k=0;k<recordNo-1;k++)
	   if(currMedoid[i][k]==1)
	     sum[i]=sum[i]+manDist[j][k];
		   if(sum[i]<min)
	  {
	   min=sum[i];
	   currCenter[i]=j;
	  }
	  }
	  sum[i]=0;
	}	
	min=99999;
  }

 boolean stop=true;
 for(i=0;i<noOfMedoids;i++)
 {
 if(prevCenter[i]!=currCenter[i])
  {stop=false;break;}
 for(j=0;j<recordNo-1;j++)
  if(currMedoid[i][j]!=prevMedoid[i][j])
     {stop=false;break;}
 } 
if(stop)
 break;
else
{for(i=0;i<noOfMedoids;i++)
 for(j=0;j<recordNo-1;j++)
  prevMedoid[i][j]=currMedoid[i][j];
  for(i=0;i<noOfMedoids;i++)
  prevCenter[i]=currCenter[i];
  }
  
for(i=0;i<noOfMedoids;i++)
 for(j=0;j<recordNo-1;j++)
  currMedoid[i][j]=-1;
 }
 
 for(i=0;i<noOfMedoids;i++)
 {System.out.print("Medoid "+i+": ");
  for(j=0;j<recordNo-1;j++)
   if (currMedoid[i][j]==1)
    System.out.print((char)(j+65)+" ");
  System.out.println();
  } 
}
}
