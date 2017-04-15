package util;

import edu.stanford.nlp.ie.crf.CRFClassifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ashish Mehrotra on 4/14/2017.
 */
public class NerModelTrainer {
    private static final String PROPERTIES_FILE = "util.NerModelTrainer";
    public static void main(String[] args) throws IOException {
        NerModelTrainer modelTrainer = new NerModelTrainer();
        modelTrainer.initiateTraining();
    }

    public void initiateTraining() throws IOException{
        ResourceBundle properties = ResourceBundle.getBundle(PROPERTIES_FILE);
        String propertiesFile = properties.getString("propertiesPath");
        String outputFile = properties.getString("serializeTo");

        Properties nerProperties = loadProperties(propertiesFile);
        CRFClassifier classifier = setupClassifier(nerProperties);
        trainClassifier(classifier, outputFile);
    }

    private Properties loadProperties(String propFileName)throws IOException {
        File prop = new File(propFileName);
        Properties properties = new Properties();
        properties.load(new FileInputStream(prop));
        return properties;
    }

    private CRFClassifier setupClassifier(Properties propFile){
        CRFClassifier classifier = new CRFClassifier(propFile);
        return classifier;
    }

    private static void trainClassifier(CRFClassifier classifier, String outputPath) {
        classifier.train();
        classifier.serializeClassifier(outputPath);
    }
}
