import java.util.*;
import java.io.*;
 class KMeans
{
 public static void main(String args[])throws Exception
{
 
 Scanner src=new Scanner(new FileReader("data2.txt"));
 int noOfRecords=0;
 String line=null;

//Get no of records
 while(src.hasNextLine())
   {
    line=src.nextLine();
    noOfRecords++;  
   }                     
double data[][]=new double[noOfRecords-1][2];

//Get file data in double array
src=new Scanner(new FileReader("data2.txt"));
int recordNo=0;
String splitData[]=new String[2];
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

//Take as i/p no of clusters
src=new Scanner(System.in);
System.out.println("Enter no of clusters");
int k=src.nextInt();

double cluster[][]=new double[k][noOfRecords-1];
double center[][]=new double[k][2];
int belongsToCurrent[]=new int[noOfRecords-1];
int belongsToPrevious[]=new int[noOfRecords-1];
int i,j;

for(i=0;i<k;i++)
 for(j=0;j<2;j++)
  center[i][j]=data[i][j];

int centerPosTemp[][]=new int[k][noOfRecords-1];
double min=32767;int minPos=0;
while(true)
{
for(i=0;i<noOfRecords-1;i++)
{ 
  for(j=0;j<k;j++)
   {
    cluster[j][i]=Math.sqrt(Math.pow((center[j][0]-data[i][0]),2)+Math.pow((center[j][1]-data[i][1]),2));
    if(cluster[j][i]<min)
    {
     min=cluster[j][i];
     minPos=j;
    }
  }
min=32767;
belongsToCurrent[i]=minPos;
}

int centerCount[]=new int[k];
for(i=0;i<k;i++)centerCount[i]=0;

for(i=0;i<k;i++)
 {center[i][0]=0;center[i][1]=0;}

for(i=0;i<k;i++)
{
     for(j=0;j<noOfRecords-1;j++)
       if(belongsToCurrent[j]==i)
          {
          center[i][0]=center[i][0]+data[j][0];
          center[i][1]=center[i][1]+data[j][1];
          centerCount[i]++;
         }
}

for(i=0;i<k;i++)
 {
  center[i][0]=center[i][0]/centerCount[i];
  center[i][1]=center[i][1]/centerCount[i];
 }

boolean stop=true;
for(i=0;i<noOfRecords-1;i++)
  if(belongsToPrevious[i]!=belongsToCurrent[i])
     {stop=false;break;}

if(stop)
 break;
else
for(i=0;i<noOfRecords-1;i++)
  belongsToPrevious[i]=belongsToCurrent[i];

}//end of while loop

//Print result
for(i=0;i<k;i++)
 {
  System.out.print("Cluster"+(i+1)+" ");
 for(j=0;j<noOfRecords-1;j++)
 if (belongsToCurrent[j]==i)
  System.out.print((char)(j+65)+" ");
System.out.println();
}

}
}
