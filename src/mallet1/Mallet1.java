/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mallet1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author King
 */
public class Mallet1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, SQLException, ParseException {
        // TODO code application logic here
        //        String malletbatch= "C:"+File.separator+"mallet"+File.separator+"bin"+File.separator+"mallet.bat";
        File[] directories = new File("/home/amin-mirlohi/Direct/Data/tag/Taged2").listFiles(File::isDirectory);
        for (File i : directories){
            String s = i.getPath();
            System.out.println(s);
            String[] parts = s.split("/");
            String s2 = parts[0]+File.separator+parts[1]+File.separator+parts[2]+File.separator+parts[3]+File.separator+parts[4]+File.separator+parts[5]+File.separator+"Taged3"+File.separator+parts[7];
            String malletbatch= "/home/amin-mirlohi/Direct/Data/mallet/bin/mallet";        
            String inputfile= s;
            String outputfile=s+"/tutorial.mallet";
            String[] command = {malletbatch,
            "import-dir",
            "--input", inputfile,
            "--output", outputfile,
            "--keep-sequence",
            "--remove-stopwords"
            };
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(command);
            pb.redirectErrorStream(true);

            Process p = pb.start();  
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
            String outputdir = s2;
            String[] command2 = {malletbatch,
            "train-topics",
            "--num-topics", "10",
            "--input", outputfile,
            "--output-topic-keys", outputdir +File.separator+ "topic_top_words.txt", //The filename in which to write the top words for each topic and any Dirichlet parameters.  By default this is null, indicating that no file will be written.
            "--output-doc-topics", outputdir +File.separator+ "topic_document_weights.txt",//The filename in which to write the topic proportions per document, at the end of the iterations.  By default this is null, indicating that no file will be written
            "--topic-word-weights-file", outputdir +File.separator+ "topic_word_weights.txt" //The filename in which to write "unnormalized" weights for every topic and word type. Most of these will be equal to the smoothing parameter beta.
            //"--topic-word-weights-mode", "none"
            };
            pb = new ProcessBuilder();
            pb.command(command2);
            pb.redirectErrorStream(true);

            p = pb.start();  
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line="";
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
        }





    }
    
}
