package com.mycompany.unipathui;

import java.util.*;

public class AnswerLog {
    public enum DepartmentType {
        ART, MEDICINE, ECONOMICS, ENGINEERING, SOCIAL, SCIENCE
    }
    
    private final DepartmentType[] questionDepartments;
    private final int[] answers;
    
    //Ανάθεση επιστημονικού κλάδου που αφορά η κάθε ερώτηση
    public AnswerLog(){
        questionDepartments = new DepartmentType[]{
            DepartmentType.ECONOMICS, //ΕΡ1
            DepartmentType.ART, //ΕΡ2
            DepartmentType.ENGINEERING, //ΕΡ3
            DepartmentType.MEDICINE, //ΕΡ4
            DepartmentType.SCIENCE, //ΕΡ5
            DepartmentType.SOCIAL, //ΕΡ6
            DepartmentType.ECONOMICS, //ΕΡ7
            DepartmentType.ART, //ΕΡ8
            DepartmentType.SCIENCE, //ΕΡ9
            DepartmentType.MEDICINE, //ΕΡ10
            DepartmentType.SCIENCE, //ΕΡ11
            DepartmentType.SOCIAL, //ΕΡ12
            DepartmentType.ECONOMICS, //ΕΡ13
            DepartmentType.ENGINEERING, //ΕΡ14
            DepartmentType.ART, //ΕΡ15
            DepartmentType.MEDICINE, //ΕΡ16
            DepartmentType.SCIENCE, //ΕΡ17
            DepartmentType.SOCIAL //ΕΡ18
        };
        answers = new int[questionDepartments.length];
        Arrays.fill(answers,-1); //-1 -> Όχι απάντηση
    }    
    
    public void setAnswer(int questionIndex, int optionIndex){
        answers[questionIndex] = optionIndex;
    }
    //Λειτουργικότητα κουμπιού "Εκκαθάριση Quiz"
    public void clearAnswers(){
        Arrays.fill(answers, -1);
    } 
    
    //Υπολογισμός σκορ
    public Map<DepartmentType, Integer> calculateScores(){
        Map<DepartmentType, Integer> scores = new EnumMap<>(DepartmentType.class);
        for(DepartmentType dt: DepartmentType.values()){
            scores.put(dt, 0);
        }
        
        for(int i=0; i<answers.length; i++){
            int answer = answers[i];
            if(answer == -1) continue;
            DepartmentType dept = questionDepartments[i];
            
            int points = switch (answer){
                case 0 -> 4; //Πόντοι στο "Συμφωνώ Απόλυτα"
                case 1 -> 3;  //Πόντοι στο "Συμφωνώ"
                case 2 -> 2;  //Πόντοι στο "Είμαι Ουδέτερος/η"
                case 3 -> 1;  //Πόντοι στο "Διαφωνώ"
                case 4 -> -1; //Πόντοι στο "Διαφωνώ Απόλυτα"
                default -> 0;  
            };
            
            scores.put(dept, scores.get(dept) + points);
        }
        return scores;
    }
    
    //Υπολογισμός ποσοστών
    public Map<DepartmentType, Integer> getSortedPercentages(){
        Map<DepartmentType, Integer> scores = calculateScores();
        
        Map<DepartmentType, Integer> maxScores = new EnumMap<>(DepartmentType.class);
        for(DepartmentType dt : DepartmentType.values()){
            maxScores.put(dt,0);
        }
        for(int i=0; i<questionDepartments.length; i++){
            DepartmentType dept = questionDepartments[i];
            if(answers[i]!=-1){
                maxScores.put(dept, maxScores.get(dept) + 4); //4 είναι το μεγαλύτερο δυνατό σκορ απάντησης
            }
        }
        
        Map<DepartmentType, Integer> percentages = new EnumMap<>(DepartmentType.class);
        for(DepartmentType dt : DepartmentType.values()){
            int score = scores.get(dt);
            int max = maxScores.get(dt);
            int percent = (max==0) ? 0 : Math.max(0, (int) Math.round(score*100.0/max));
            percentages.put(dt, percent);
        }
        return percentages.entrySet().stream()
            .sorted(Map.Entry.<DepartmentType, Integer>comparingByValue().reversed())
            .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), LinkedHashMap::putAll);    
    }
    
    public int[] retrieveQuizData(){
        return answers;
    }
    public void showNoLogError(){System.out.println("Log does not exist");}
}
