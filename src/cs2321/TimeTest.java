package cs2321;


import java.util.Arrays;
import java.util.Vector;

import net.datastructures.Position;



public class TimeTest{
    public static void main(String[] args) throws Exception{
        Vector<ArrayList<Integer>> arrayLists=new Vector<>(5000);
        //ArrayList<Integer>[] arrayList=new ArrayList<>()[5000];
        Vector<DoublyLinkedList<Integer>> positionLists=new Vector<>(5000);
        //DoublyLinkedList<Integer>[] positionList;

        long[] arraytimes=new long[100];
        long[] doublyTimes=new long[100];
        for(int i=0;i<5000;i++){
            arrayLists.add(new ArrayList<>());
            positionLists.add(new DoublyLinkedList<>());
            for(int j=0;j<100;j++){
                arrayLists.get(i).add(j,j);
                positionLists.get(i).addLast(j);
            }
        }

        for(int i=0;i<100;i++){
            long startTime=System.nanoTime();
            for(int j=0;j<5000;j++){
                arrayLists.get(j).get(i);
            }
            long endTime=System.nanoTime();
            arraytimes[i]=(endTime-startTime)/5000;
            //start test2
            startTime=System.nanoTime();
            for(int j=0;j<5000;j++){
                Position<Integer> p= positionLists.get(j).first();
                for(int k=0;k<i;k++){
                    p=positionLists.get(j).after(p);
                }
                p.getElement();
            }
            endTime=System.nanoTime();
            doublyTimes[i]=(endTime-startTime)/5000;
        }
        System.out.println(Arrays.toString(arraytimes));
        System.out.println(Arrays.toString(doublyTimes));

        long[] time1=new long[200];
        long[] time2=new long[200];
        for(int i=0;i<200;i++){
            Vector<ArrayList<Integer>> arrayLists2=new Vector<>(5000);
            //ArrayList<Integer>[] arrayList=new ArrayList<>()[5000];
            Vector<DoublyLinkedList<Integer>> positionLists2=new Vector<>(5000);
            //DoublyLinkedList<Integer>[] positionList;
            for(int j=0;j<5000;j++){
                arrayLists2.add(j, new ArrayList<>());
                positionLists2.add(j,new DoublyLinkedList<>());
            }
            long startTime=System.nanoTime();
            for(int j=0;j<5000;j++){
                for(int k=0;k<i;k++){
                    arrayLists2.get(j).add(0, 0);
                }
            }
            long endTime=System.nanoTime();
            time1[i]=(endTime-startTime)/5000;

             startTime=System.nanoTime();
            for(int j=0;j<5000;j++){
                for(int k=0;k<i;k++){
                    positionLists2.get(j).addFirst(0);
                }
            }
             endTime=System.nanoTime();
            time2[i]=(endTime-startTime)/5000;
            
        }
        System.out.println(Arrays.toString(time1));
        System.out.println(Arrays.toString(time2));

        
        for(int i=0;i<200;i++){
            Vector<ArrayList<Integer>> arrayLists2=new Vector<>(5000);
            //ArrayList<Integer>[] arrayList=new ArrayList<>()[5000];
            Vector<DoublyLinkedList<Integer>> positionLists2=new Vector<>(5000);
            //DoublyLinkedList<Integer>[] positionList;
            for(int j=0;j<5000;j++){
                arrayLists2.add(j, new ArrayList<>());
                positionLists2.add(j,new DoublyLinkedList<>());
            }
            long startTime=System.nanoTime();
            for(int j=0;j<5000;j++){
                for(int k=0;k<i;k++){
                    arrayLists2.get(j).add(arrayLists2.get(j).size(), 0);
                }
            }
            long endTime=System.nanoTime();
            time1[i]=(endTime-startTime)/5000;

             startTime=System.nanoTime();
            for(int j=0;j<5000;j++){
                for(int k=0;k<i;k++){
                    positionLists2.get(j).addLast(0);
                }
            }
             endTime=System.nanoTime();
            time2[i]=(endTime-startTime)/5000;
            
        }
        System.out.println(Arrays.toString(time1));
        System.out.println(Arrays.toString(time2));


    }
}