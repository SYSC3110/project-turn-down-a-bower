package Model.Metrics;

import Model.Features.*;
import Model.Storage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class CartesianEuclideanMetric extends GenericMetric implements Serializable {

	private static final long serialVersionUID = 1L;

	/* This constructor requires two integers and a reference to the storage for the problem.
     * The values are then stored into a two element array as a x-value and a y-value.
     * With a reference to the storage stored locally.
     *
     * @author Logan MacGillivray, Ethan Morrill
     */
    public CartesianEuclideanMetric(String name, Storage storage){
    	super(name, storage);
    }

    /* See GenericMetrics.getDifference(GenericMetric metric) for full java doc
     * This particular function will return a hashmap of the example key and euclidean distance
     * of the provided feature and each learned example.
     * The value shall be returned as a Hashmap of {key, positive integer distance}.
     * returns null if provided feature is of the wrong type.
     *
     * @author Logan MacGillivray, Ethan Morrill
     */
    public HashMap<String, Double> getDistance(GenericFeature feature){
        int[] value = (int[])feature.getValue();
        if(feature instanceof CartesianFeature){
            super.getDistance(feature);
            for(String key : keys) {

                int [] learnedValue = (int[]) learnedFeature.get(key).getValue();
                double squareSum = 0;
                for(int i = 0; i < value.length; i++){
                    squareSum += Math.pow(value[i]-learnedValue[i], 2);
                }
                distances.put(key, Math.sqrt(squareSum));

            }
            return distances;
        }
        return null;
    }

    /* See GenericMetrics.getInternalDifference(GenericFeature feature, HashMap<String,GenericFeature>
     *internalLearnedFeature) for full java doc
     * This particular function will return a hashmap of the example key and Polar distance
     * of the provided feature and each learned example of an internal feature.
     * The value shall be returned as a Hashmap of {key, positive double distance}.
     * returns null if provided feature is of the wrong type.
     *
     * @author Ethan Morrill
     */
    public HashMap<String, Double> getInternalDistance(GenericFeature feature, HashMap<String,GenericFeature> internalLearnedFeature){

        int[] value = (int[])feature.getValue();
        if((feature instanceof CartesianFeature)){
            HashMap<String, Double> internalDistances = new HashMap<>();
            Set<String> internalKeys = internalLearnedFeature.keySet();
            for(String key : internalKeys){
                int [] learnedValue = (int[]) internalLearnedFeature.get(key).getValue();
                double squareSum = 0;
                for(int i = 0; i < value.length; i++){
                    squareSum += Math.pow(value[i]-learnedValue[i], 2);
                }
                internalDistances.put(key, Math.sqrt(squareSum));
            }
            return internalDistances;
        }
        return null;
    }
}
