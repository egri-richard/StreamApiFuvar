package com.home;

import javax.sql.rowset.Predicate;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Fuvar> fuvarList = new ArrayList<>();

        try {
            BufferedReader read = new BufferedReader(new FileReader("fuvar.csv"));
            read.readLine();
            String line = read.readLine();
            while(line != null) {
                fuvarList.add(new Fuvar(line));
                line = read.readLine();
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(fuvarList);
        System.out.println(countOfFuvar(fuvarList));
        incomeOfFuvar(fuvarList, 6185);
        distanceTravelledSum(fuvarList);
        longestFuvar(fuvarList);
        mostBorravaloFuvar(fuvarList);
        distanceTravelledBy(fuvarList, 4261);
        fuvarErrors(fuvarList);
        isTaxi(fuvarList, 1452);
        Shortest(fuvarList, 3);
        numOfFuvarOnDate(fuvarList, "-12-24");
        relationOfBorravaloOnDate(fuvarList, "-12-31");
    }

    public static long countOfFuvar(List<Fuvar> fuvarList) {
        return fuvarList.stream().count();
    }

    public static void incomeOfFuvar(List<Fuvar> fuvarList ,int id) {
        double sum = fuvarList
                .stream()
                .filter(taxi -> taxi.getTaxi_id() == id)
                .mapToDouble(taxi -> taxi.getViteldij() + taxi.getBorravalo())
                .sum();
        long rides = fuvarList.stream().filter(taxi -> taxi.getTaxi_id() == id).count();
        System.out.println("Fuvarok száma: " + rides + " bevétel: " + sum);
    }

    public static void distanceTravelledSum(List<Fuvar> fuvarList) {
        double distance = fuvarList.stream()
                .mapToDouble(taxi -> taxi.getTavolsag())
                .sum();
        System.out.println("Össz távolság: " + distance);
    }

    public static void longestFuvar(List<Fuvar> fuvarList) {
        Optional<Fuvar> longest = fuvarList.stream().max(Comparator.comparingDouble(Fuvar::getIdotartam));

        System.out.println(longest);
    }

    public static void mostBorravaloFuvar(List<Fuvar> fuvarList) {
        Optional<Fuvar> mostBorravalo = fuvarList.stream()
                .max((taxi, taxi2) -> (int) ((taxi.getBorravalo() / taxi.getViteldij()) - (taxi2.getBorravalo() / taxi2.getViteldij())));

        System.out.println(mostBorravalo);
    }

    public static void distanceTravelledBy(List<Fuvar> fuvarList ,int id) {
        double distance = fuvarList.stream().filter(taxi -> taxi.getTaxi_id() == id).mapToDouble(Fuvar::getTavolsag).sum();
        distance = distance * 1.6;
        System.out.println(distance);
    }

    public static void fuvarErrors(List<Fuvar> fuvarList) {
        long count = fuvarList.stream().filter(taxi -> taxi.getTavolsag() == 0 && taxi.getIdotartam() > 0 && taxi.getViteldij() > 0).count();

        int sumOfIdotartam = fuvarList.stream().filter(taxi -> taxi.getTavolsag() == 0 && taxi.getIdotartam() > 0 && taxi.getViteldij() > 0)
                .mapToInt(Fuvar::getIdotartam).sum();

        double sumOfViteldij = fuvarList.stream().filter(taxi -> taxi.getTavolsag() == 0 && taxi.getIdotartam() > 0 && taxi.getViteldij() > 0)
                .mapToDouble(Fuvar::getViteldij).sum();

        System.out.println("Hibás sorok száma: " +count+ " össz időtartam: " +sumOfIdotartam+ " össz viteldíj: " +sumOfViteldij);
    }

    public static void isTaxi(List<Fuvar> fuvarList, int id) {
        List<Fuvar> existsList = fuvarList.stream().filter(taxi -> taxi.getTaxi_id() == id).toList();

        if (existsList.isEmpty()) {
            System.out.println("Nem létezik az "+id+" azonosítojú taxis");
        } else {
            System.out.println("Létezik az "+id+" azonosítojú taxis");
        }
    }

    public static void Shortest(List<Fuvar> fuvarList, int limit) {
        List<Fuvar> listOfShortest = fuvarList.stream().filter(taxi -> taxi.getIdotartam() != 0)
                .sorted(Comparator.comparingInt(Fuvar::getIdotartam)).limit(limit).toList();

        System.out.println(listOfShortest);
    }

    public static void numOfFuvarOnDate(List<Fuvar> fuvarList, String dateString) {
        long count = fuvarList.stream().filter(taxi -> taxi.getIndulas().contains(dateString)).count();

        System.out.println(count + " a fuvarok száma " + dateString + " dátumon");
    }

    public static void relationOfBorravaloOnDate(List<Fuvar> fuvarList, String dateString) {
        OptionalDouble relation = fuvarList.stream().filter(taxi -> taxi.getIndulas().contains(dateString))
                .mapToDouble(taxi -> taxi.getBorravalo() / taxi.getViteldij())
                .average();

        System.out.println(relation + " a borravalók aránya " +dateString+ " dátumon");
    }
}
