package comp3350.srsys.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.Map;

import comp3350.srsys.objects.Product;

public class ProductLogic
{
    // Purpose: Functions for filtering out products and helper functions for dealing with Products

    public static Long generateID()
    {
        return new Date().getTime();
    }

    public static ArrayList<Product> filterFeed(Map<String, String> filters, ArrayList<Product> productList){
        ArrayList<Product> newProductList = new ArrayList<>(productList);
        if (filters != null){
            Iterator it = filters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String key = (String) pair.getKey();
                String value = (String) pair.getValue();
                if (value != null && !(value.trim().equals("")))
                    newProductList = filterFeedBy(key, value, newProductList);
                it.remove();
            }
        }

        return newProductList;
    }

    private static ArrayList<Product> filterFeedBy(String key, String value, ArrayList<Product> productList){
        ArrayList<Product> filteredList = new ArrayList<>();

        switch(key) {
            case "query":
                filteredList = filterByQuery(productList, value);
                break;
            case "minMaxBid":
                filteredList = filterByBid(productList, value);
                break;
        }
        return filteredList;
    }

    private static ArrayList<Product> filterByQuery(ArrayList<Product> productList, String value) {
        ArrayList<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if ((product.getName().contains(value))) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private static ArrayList<Product>  filterByBid(ArrayList<Product> productList, String value){
        ArrayList<Product> filteredList = new ArrayList<>();
        String [] input = value.split(";");
        String minVal;
        String maxVal;
        double minValMum = 0;
        double maxValNum = Integer.MAX_VALUE;
        try {
            minVal = input[0];
            minValMum = Double.parseDouble(minVal);
        } catch(Exception e){
            System.out.println("minValMum NOT PRESENT");
        }

        try {
            maxVal = input[1];
            maxValNum = Double.parseDouble(maxVal);
        } catch(Exception e){
            System.out.println("maxValNum NOT PRESENT");
        }

        for(Product product : productList) {
            if ((maxValNum >= product.getCurrentBid()) && (product.getCurrentBid() >= minValMum)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

}
