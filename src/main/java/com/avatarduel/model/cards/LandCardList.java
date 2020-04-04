package com.avatarduel.model.cards;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.avatarduel.util.*;
import com.avatarduel.enums.Element;

public class LandCardList 
{
    private static LandCardList landCardListInstance = null;

    private Map<Integer, String[]> landCardList;
    private Set<Integer> landCardIdList;

    // Define filepath to the CSV file
    private static final String LAND_CARD_FILE_PATH = "../../card/data/land.csv";

    private LandCardList() throws URISyntaxException, IOException
    {
        // Define file from known filepaths
        File landCardFile = new File(getClass().getResource(LAND_CARD_FILE_PATH).toURI());
        
        // Define CSV readers for each file
        CSVReader landCardReader = new CSVReader(landCardFile, "\t");

        landCardReader.setSkipHeader(true);
        
        // Generate list of entry for card data
        List<String[]> landCardData = landCardReader.read();
        
        // Input data to map
        this.landCardList = new HashMap<Integer, String[]>();
        this.landCardIdList = new TreeSet<Integer>();

        for(String[] landCardEntry : landCardData)
        {

            this.landCardList.put(Integer.valueOf(landCardEntry[0]), landCardEntry);
            this.landCardIdList.add(Integer.parseInt(landCardEntry[0]));
        }

    }

    public Map<Integer, String[]> getLandCardList()
    {
        return this.landCardList;
    }

    public Set<Integer> getLandCardIdList()
    {
        return this.landCardIdList;
    }

    public static int getLandCardCount() throws URISyntaxException, IOException
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();
        
        return landCardListInstance.getLandCardIdList().size();
    }

    public static boolean isIdLandCard(int id) throws URISyntaxException, IOException
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();

        return landCardListInstance.getLandCardIdList().contains(new Integer(id));
    }

    public static LandCard getLandCardById(int id) throws URISyntaxException, IOException
    {
        if(landCardListInstance == null)
            landCardListInstance = new LandCardList();
        
        String[] cardData = landCardListInstance.getLandCardList().get(id);

        return new LandCard(id, cardData[1], Element.valueOf(cardData[2]), cardData[3], cardData[4]);
    }
     
}