package org.main.Services;

import java.util.*;

public class ProcessText {
    public ProcessText() {
    }
    private final String[] listDiatriticsArr = new String[]{"ă","â","î","ş","ţ"};
    private final List<String> listDiatritics = Arrays.asList(listDiatriticsArr);
    private final Map<String,String> replaceDiacriticsMap = new HashMap<>(){
        {
            put("ă","a");
            put("â","a");
            put("î","i");
            put("ş","s");
            put("ţ","t");
        }
    };

    public List<String> replaceDiacritics(List<String> someList){
        Iterator<String> line = someList.iterator();
        List<String> result = new ArrayList<>();
        while (line.hasNext()){
            String cleanLine = this.replaceDiacriticsInLine(line.next());
            if (cleanLine.length()<2) continue;
            result.add(cleanLine);
        }
        return result;
    }

    public String replaceDiacriticsInLine(String line){
        String sub1, sub2;
        line = line.toLowerCase().trim();
        System.out.println(line);
        for (int i = 0; i<line.length();i++){
            String s = Character.toString(line.charAt(i));
//            if (!Character.isLetter(line.charAt(i))){
//                line = line.substring(0,i);
//                break;
//            }
            if (!listDiatritics.contains(s)) continue;
            sub1 = line.substring(0,i);
            sub2 = line.substring(i+1);
            line = sub1+replaceDiacriticsMap.get(s)+sub2;
        }
        return line;
    }
}
