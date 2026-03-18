import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.Set;

import java.util.HashSet;


public class Anagrams {
    
    /* iv */
    ArrayList<String> words;
    Map<String, ArrayList<String>> Anagrams = new HashMap<>(); //all words
    Map<String, ArrayList<String>> anagrams = new HashMap<>(); //only words that have anagrams


    public Anagrams(){
        words = new ArrayList<>();
        Anagrams = new HashMap<>();
        anagrams = new HashMap<>();
    }



    public void generate_dictionary(){
    /* 
    
    Anagrams : {
            {act : [act, cat] } 
        } 
    
        */
        // First, remove duplicates from words
        Set<String> uniqueWords = new HashSet<>(words);
        
        for (String word : uniqueWords) {
            String signature = get_signature(word);
            
            if (Anagrams.containsKey(signature)) {
                // Check if word already exists in the list to avoid duplicates
                ArrayList<String> list = Anagrams.get(signature);
                if (!list.contains(word)) {
                    list.add(word);
                }
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(word);
                Anagrams.put(signature, list);
            }
        }

    }




    public void read_in_text_file(){
        String file_path = "joyce1922_ulysses-1.text";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            String line;
            while((line = br.readLine() )!= null){
               /*  System.out.println(line); */

                String[] Words = line.split("\\s+");
                for (String w : Words){
                    String clean_word = w.replaceAll("[^a-zA-Z']", "");
                    if (!clean_word.equals("")){
                        words.add(clean_word.toLowerCase());
                    } 
                }

                /* System.out.println(Arrays.toString(Words)); */


                
            }
                
            
            /* System.out.println(Arrays.toString(words.toArray())); */

            
        } catch (Exception e) {
            // TODO: handle exception
            
        }
    }

    public void export_file() {
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter("theAnagrams.tex");
            
            writer.println("\\documentclass{article}");
            writer.println("\\usepackage{multicol}");
            writer.println("\\usepackage{geometry}");
            writer.println("\\geometry{a4paper, margin=1in}");
            writer.println("\\begin{document}");
            writer.println("\\title{Anagrams in Ulysses}");
            writer.println("\\maketitle");
            writer.println("\\begin{multicols}{3}");
            writer.println("\\begin{itemize}");
            
            for (java.util.Map.Entry<String, ArrayList<String>> entry : anagrams.entrySet()) {
                if (entry.getValue().size() > 1) {
                    String key = entry.getKey();
                    ArrayList<String> words = entry.getValue();
                    java.util.Collections.sort(words);
                    
                    String line = "\\item[" + key + "] ";
                    for (int i = 0; i < words.size(); i++) {
                        line += words.get(i);
                        if (i < words.size() - 1) {
                            line += ", ";
                        }
                    }
                    writer.println(line);
                }
            }
            
            writer.println("\\end{itemize}");
            writer.println("\\end{multicols}");
            writer.println("\\end{document}");
            writer.close();
            
            System.out.println("Exported to theAnagrams.tex");
            
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }
    public static String get_signature(String word){
        char[] chars = word.toCharArray();

        java.util.Arrays.sort(chars);

        return new String(chars);
    }

    public void discard_non_anagrams() {
    anagrams.clear();
    
    for (Map.Entry<String, ArrayList<String>> entry : Anagrams.entrySet()) {
        String signature = entry.getKey();
        ArrayList<String> wordList = entry.getValue();
        
        if (wordList.size() > 1) {
            anagrams.put(signature, wordList);
        }
    }
    
    System.out.println("Found " + anagrams.size() + " anagram groups");
}
    
    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        System.out.println(get_signature("sodarktheconofman"));
        System.out.println(get_signature("madonnaoftherocks"));
        anagrams.read_in_text_file();
        anagrams.generate_dictionary();
        /* System.out.println(Arrays.toString(anagrams.Anagrams.get(get_signature("listen")).toArray())); */
        anagrams.discard_non_anagrams();
        System.out.println(anagrams.anagrams);
        anagrams.export_file();
    }
}
