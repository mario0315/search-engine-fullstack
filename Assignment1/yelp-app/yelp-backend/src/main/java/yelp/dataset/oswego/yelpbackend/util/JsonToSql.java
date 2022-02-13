package yelp.dataset.oswego.yelpbackend.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import yelp.dataset.oswego.yelpbackend.hashing.HashTable;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;
// import yelp.dataset.oswego.yelpbackend.repositories.BusinessRepository;
import yelp.dataset.oswego.yelpbackend.repositories.BusinessRepository;

@Component // this + CommandLineRunner are used to run code at application startup
            // like useEffect in React
public class JsonToSql implements CommandLineRunner{


    @Autowired
    private BusinessRepository businessRepository; // repo to store data

    @Override
    public void run(String... args) throws Exception {
        // jsonToSql();
    }

    public void jsonToSql() {
        try {
            // businessRepository.deleteAllInBatch();
            // buffrer reader to read lines in json file
            FileReader reader = new FileReader("/Users/logan/coding/SUNY_Oswego/CSC-365/In_Class/Assignment1/yelp-app/yelp-dataset/business.json");
            BufferedReader br = new BufferedReader(reader);
            String line = "";

            // loop through the json file
            for (int i = 0; i < 10000; i++) {
                // each line of the file is a json object
                line = br.readLine();

                JSONObject bData = new JSONObject(line); // this is the whole Object for the whole line
                
                // attributes
                String name = bData.get("name").toString();
                String business_id = bData.get("business_id").toString();
                String address = bData.get("address").toString();
                Double stars = bData.getDouble("stars");
                Double reviews = bData.getDouble("review_count");
                Double similarityRate = 99999.0;

                // A list of string for categories
                ArrayList<String> bCategories = new ArrayList<String>();

                // get the values for categories-key and push them into an array
                String[] categories = bData.get("categories").toString().split(",");
                // String categoriesString = bData.get("categories").toString();

                // add each category value to bCategories list
                for (int j =0; j<categories.length; j++) {
                    bCategories.add(categories[j]);
                }

                // a BusinessModel instance
                BusinessModel bModel = new BusinessModel(0, business_id, name, address, stars, reviews, similarityRate, bCategories);
            
                bModel.setCategories(bCategories);

                Testing(bModel);

                // businessRepository.save(bModel);

            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Testing(BusinessModel business) {
        HashTable ht = new HashTable(500, 0.75);
        int index = ht.unhashedIndex(business.hashCode());
        System.out.println("unhashedIndex" + index);
    }
    
}
