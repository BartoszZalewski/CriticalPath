package criticalpath;

import java.util.Scanner;

class Vertex
{
    public boolean last;
    public int value;
    
    Vertex(int v)
    {
        value=v;
        last = true;
    }   
}

public class CriticalPath {

    public static Scanner input = new Scanner(System.in);
    
    //Algorytm Forda-Bellmana
    public static void crithPathMethod(int [][]tab, Vertex[] ver, int n)
    {
        String wynik ="";
        int maxG=0;
        for(int x=0;x<n;x++)
        {
            int tmp[] = new int[n];
            int max =0;
            for(int i=0;i<n;i++)
            {
                tmp[i]=tab[x][i];
                if(tmp[i]>max)
                    max=tmp[i];
            }
        
            boolean zmiana = true;
        
            while(zmiana)
            {
                zmiana=false;
                for(int i=0;i<n;i++)
                {
                    if(tmp[i]!=0 && ver[i].last==false)
                    {
                        for(int j =0; j<n;j++)
                        {
                            int h;
                            if(tab[i][j]!=0)
                            {
                                if(j==x)
                                {
                                    System.out.println("CYKL");
                                    return;
                                }
                                h=tab[i][j]+tmp[i];
                                if(h>tmp[j])
                                {
                                    tmp[j]=h;
                                    zmiana=true;
                                    if(h>max)
                                        max=h;
                                }
                            }
                        }
                    }
                }
            }
        
            max+=ver[x].value;
            if(n>25)
            {
                if(max>maxG)
                    maxG=max;
            }
            else
            {
                if(x<n-1)
                    wynik+=max+",";
                else
                    wynik+=max;
            }
        }
        if(n>25)
            System.out.println(maxG);
        else
            System.out.println(wynik);
    }
    
    public static void main(String[] args) {
        
        int set = input.nextInt();
        for(int i=0;i<set;i++)
        {
            int n = input.nextInt();
            int valueTab[][] = new int[n][n];
            Vertex vertexTab[] = new Vertex[n];
            for(int j=0;j<n;j++)
                vertexTab[j] = new Vertex(input.nextInt());
            String inputLine = input.nextLine();    
            for(int k=0;k<n;k++)
            {
                inputLine = input.nextLine();
                for(int l=0;l<n;l++)
                {
                    if(inputLine.charAt(l)=='1')
                    {
                        valueTab[k][l]=vertexTab[l].value;
                        vertexTab[l].last=false;
                    }
                    else
                        valueTab[k][l]=0;
                }
            }
            crithPathMethod(valueTab, vertexTab, n);  
        }
    } 
}
