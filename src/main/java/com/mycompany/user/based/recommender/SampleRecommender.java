/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user.based.recommender;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 *
 * @author nsl-gtorres
 */
public class SampleRecommender {

    public static void main(String argv[]) {
        try {
            DataModel model = new FileDataModel(
                    new File(
                            "/home/nsl-gtorres/Documents/week 3/"
                            + "User-Based Recommender/dataset.csv"
                    )
            );

            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(
                    0.1, similarity, model
            );

            UserBasedRecommender recommender = new GenericUserBasedRecommender(
                    model, neighborhood, similarity
            );

            List<RecommendedItem> recommendations = recommender.recommend(2, 3);

            recommendations.forEach((recommendation) -> {
                System.out.println(recommendation);
            });

        } catch (IOException | TasteException ex) {
            Logger.getLogger(SampleRecommender.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }
}
