/**
 * 2020b - 03/05/2020
 */

import java.util.Random;

public class RailwayStationTester_ran {
    public static void main(String[] args) {

        /***********  RailwayStation CLASS TESTER ***********/
        /*****************************/
        Random rand = new Random();
        //Check constructor

        System.out.println("1. check addTrain:");
        RailwayStation rs = new RailwayStation();
        RailwayStation rsSmall = new RailwayStation();
        RailwayStation rsEmptyDes = new RailwayStation();
        RailwayStation rsTestCost = new RailwayStation();


        //AddTrain
        Train f11 = new Train("Haifa", 12, 0, 210, 250, 250, 55);
        Train f12 = new Train("Jerusalem", 10, 50, 210, 250, 250, 40);
        Train f13 = new Train("Jerusalem", 10, 50, 210, 250, 250, 40);
        Train f14 = new Train("Jerusalem", 10, 50, 210, 250, 250, 40);
        Train f15 = new Train("Tel-Aviv", 10, 50, 210, 250, 250, 40);
        Train f16 = new Train("", 22, 22, 210, 250, 250, 40);
        Train f17 = new Train("Amsterdam", 12, 0, 210, 250, 250, 40);
        Train f18 = new Train("Berlin", 13, 0, 210, 250, 250, 40);

        System.out.println("\t t: \t" + rs.addTrain(f11) + "\t;\t" + f11);
        System.out.println("\t t: \t" + rs.addTrain(f12) + "\t;\t" + f12);
        System.out.println("\t f: \t" + rs.addTrain(f13) + "\t;\t" + f13);
        System.out.println("\t f: \t" + rs.addTrain(f14) + "\t;\t" + f14);

        Train tst1 = new Train("same", 12, 30, 60, 25, 50, 10);
        Train tst2 = new Train("same", 12, 30, 61, 41, 50, 11);
        Train tst3 = new Train("not same", 12, 30, 60, 40, 50, 10);
        Train tst4 = new Train("same", 12, 31, 60, 40, 50, 10);
        Train tst5 = new Train("same", 13, 30, 60, 40, 50, 10);
        Train tst6 = new Train("same", 12, 30, 61, 40, 51, 10);

        System.out.println("\t t: \t" + rs.addTrain(tst1) + "\t;\t" + tst1);
        System.out.println("\t f: \t" + rs.addTrain(tst2) + "\t;\t" + tst2);
        System.out.println("\t t: \t" + rs.addTrain(tst3) + "\t;\t" + tst3);
        System.out.println("\t t: \t" + rs.addTrain(tst4) + "\t;\t" + tst4);
        System.out.println("\t t: \t" + rs.addTrain(tst5) + "\t;\t" + tst5);
        System.out.println("\t t: \t" + rs.addTrain(tst6) + "\t;\t" + tst6);



        RailwayStation rs_full = new RailwayStation();
        for (int i = 0; i <= 150; i++)
            rs_full.addTrain(new Train("rio", rand.nextInt(23) + 1, rand.nextInt(59) + 1, 210, 25, (int) (Math.random() * 100) + +35, 55));
        System.out.print("\t f:\t \t");
        System.out.print(rs_full.addTrain(new Train("Not Same", 0, 0, 210, 25, 0, 55)));
        System.out.println("\t;\tAdd one to full RailwayStation\t");

        System.out.print("\t f:\t \t");
        System.out.print(rs.addTrain(null));
        System.out.println("\t;\tAdd null train\t");


        System.out.println("\n");

        System.out.println("2. check removeTrain:");
        //need to check holes after remove.


        //RemoveTrain
//        System.out.println(rs);
        RailwayStation rs_empty = new RailwayStation();
        System.out.println("\tt:\t regular remove f11:    \t" + rs.removeTrain(tst3));
//        System.out.println(rs);
        System.out.print("\tf:\t remove not exist one : \t");
        System.out.println(rs_full.removeTrain(new Train("Not Same", 0, 0, 210, 25, 0, 55)));
        System.out.print("\tf:\t remove when empty :    \t");
        System.out.println(rs_empty.removeTrain(new Train("Not Same", 0, 0, 210, 25, 0, 55)));
        System.out.println("\tf:\t remove null train :\t\t" +rs.removeTrain(null));




        //First Train to Destination
        RailwayStation rs_one_train = new RailwayStation();
        Train f3a = new Train("Tel-Aviv", 7, 15, 180, 200, 200, 35);
        rs.addTrain(f3a);
        Train f3 = new Train("Tel-Aviv", 11, 35, 180, 100, 200, 35);
        rs.addTrain(f3);

        Train f3b = new Train("Tel-Aviv", 8, 14, 180, 200, 199, 35);

        rs.addTrain(f3b);
        System.out.println();

        System.out.println("3. check firstDepartureToDestination:");

        Time1 t1 = rs.firstDepartureToDestination("Tel-Aviv");
        System.out.println("\t07:15:\tThe first train to Tel-Aviv departs at  " + t1);

        f11 =  new Train("Tokyo", 23, 59, 180, 200, 200, 35);
        rs_one_train.addTrain(f11);
        Time1 t11 = rs_one_train.firstDepartureToDestination("Tokyo");
        System.out.println("\t23:59:\tThe first train to Tel-Aviv departs at  " + t11);

        Time1 t2 = rs_full.firstDepartureToDestination("Tel-Aviv");
        System.out.println("\tnull:\tThe Destination not there:  \t\t\t" + t2);

        Time1 t3 = rs_empty.firstDepartureToDestination("Haifa");
        System.out.println("\tnull:\tThe RailwayStation is empty:   \t\t\t" + t3);

        Time1 t4 = rs.firstDepartureToDestination(null);
        System.out.println("\tnull:\tThe Destination is null:   \t\t\t\t" + t4);

        rsEmptyDes.addTrain(f16);
        Time1 t5 = rsEmptyDes.firstDepartureToDestination("");
        System.out.println("\t22:22:\tThe Destination time is \"\":   \t\t\t" + t5);

        System.out.println();


        //toString
        System.out.println("4. toString:");
        System.out.println("empty: \n" + rs_empty);
        System.out.println("------------------------------------");
        System.out.println("\n" + rs);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println(rs_one_train);

        String shouldBeA =  "The trains today are:\nTrain to Tokyo departs at 23:59. Train is full.\n";
        String shouldBeB =  "The trains today are:\nTrain to Tokyo departs at 23:59. Train is full\n";
        String shouldBeEmpty =  "There are no trains today.";

        System.out.println("is to string ok:\n\t check regular station - " +  (shouldBeA.equals(rs_one_train.toString()) || shouldBeB.equals(rs_one_train.toString()) )
                +"\n\t check empty station - " +  shouldBeEmpty.equals(rs_empty.toString()) );
        System.out.println();
        System.out.println();


        //HowMany Full Trains
        RailwayStation rs_full_full = new RailwayStation();
        for (int i = 0; i <= 150; i++)
            rs_full_full.addTrain(new Train("rio", rand.nextInt(23) + 1, rand.nextInt(59) + 1, 210, 900, (int) (Math.random() * 100) + +35, 55));

        System.out.println("5. How Many Full Trains:");
        int x = rs.howManyFullTrains();
        System.out.println("\t4\t - rs_reg: number if Full Trains - " + x);
        System.out.println("\t0\t - rs_empty:   - " + rs_empty.howManyFullTrains());
        System.out.println("\t0\t - rs all not Full :  - " + rs_full.howManyFullTrains());
        System.out.println("\t100\t - rs all full :  - " + rs_full_full.howManyFullTrains());
        System.out.println();

        //Most Popular Destination
        System.out.println("6. Most Popular Destination:");
        String mostPopular = rs.mostPopularDestination();
        System.out.println("\tsame \t - " + mostPopular);
        System.out.println("\tnull \t - " + rs_empty.mostPopularDestination());
        System.out.println("\trio  \t - " + rs_full.mostPopularDestination());
        System.out.println("\tTokyo\t - " + rs_one_train.mostPopularDestination());
        System.out.println();

        //Most Expensive Ticket
        rsTestCost.addTrain(f17);
        rsTestCost.addTrain(f18);
        rsTestCost.addTrain(f12);
        rsTestCost.addTrain(f15);

        System.out.println("7. Most Expensive Ticket:");
        Train mostExpensive = rs.mostExpensiveTicket();
        System.out.println("\tHaifa    \t - Most Expensive Ticket - " + mostExpensive);
        System.out.println("\tAmsterdam\t - Most Expensive Ticket - " + rsTestCost.mostExpensiveTicket());
        System.out.println("\tnull     \t - Most Expensive Ticket - " + rs_empty.mostExpensiveTicket());
        System.out.println();


        //Longest Train
        System.out.println("8. Longest Train:");
        Train longestTrain = rs.longestTrain();
        System.out.println("\tHaifa    \t - Longest Train - " + longestTrain);
        System.out.println("\tAmsterdam\t - Longest Train - " + rsTestCost.longestTrain());
        System.out.println("\tnull     \t - Longest Train - " + rs_empty.longestTrain());

    }
}