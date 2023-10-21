import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.*;


//go to line 17 to choose which file is being read


public class Main {
  public static void main(String[] args) {
  try {
    BufferedReader reader = new BufferedReader(new FileReader("file.asm")); //give file name
    BufferedWriter writer = new BufferedWriter(new FileWriter("out1.txt"));
    String line;
    while ((line = reader.readLine()) != null) {
      if (line.contains("//")) {
        writer.write("\n" + line.substring(0,line.indexOf("//")));
        continue;
      }
      writer.write("\n" + line);
    }
    writer.close();
    reader.close();

    
    BufferedReader reader2 = new BufferedReader(new FileReader("out1.txt"));
    BufferedWriter writer2 = new BufferedWriter(new FileWriter("out2.txt"));
    String line2;
    while ((line2 = reader2.readLine()) != null) {
      if (line2.trim().length() == 0 ) {
        continue;}
      writer2.write(line2.trim() + "\n");
    }
    writer2.close();
    reader2.close();


    BufferedReader reader3 = new BufferedReader(new FileReader("out2.txt"));
    BufferedWriter writer3 = new BufferedWriter(new FileWriter("out3.txt"));
    String line3;
    int i = 0;
    while ((line3 = reader3.readLine()) != null) {
      if (line3.trim().length() == 0 ) {
        continue;}
      if (line3.contains("(")) {
        continue;}
      i = i + 1;
      if(line3.contains("@")) {
        writer3.write("0");
        String line3a = line3.substring(1);
        String rCheck = line3a.substring(0, 1);
        if (rCheck.startsWith("R")) {
          String line3b = line3a.substring(1);
          int B1a = Integer.parseInt(line3b);
          String B1 = Integer.toBinaryString(B1a);
          String B2 = String.format("%15s", B1).replaceAll(" ", "0");
          writer3.write(B2 + "\n");
          continue;
        }
        if (line3a.contains("SCREEN")) {
        writer3.write("100000000000000" + "\n");
        continue;}
        if (line3a.contains("KBD")) {
        writer3.write("110000000000000" + "\n");
        continue;}
        if (line3a.contains("SP")) {
        writer3.write("000000000000000" + "\n");
        continue;}
        if (line3a.contains("LCL")) {
        writer3.write("000000000000001" + "\n");
        continue;}
        if (line3a.contains("ARG")) {
        writer3.write("000000000000010" + "\n");
        continue;}
        if (line3a.contains("THIS")) {
        writer3.write("000000000000011" + "\n");
        continue;}
        if (line3a.contains("THAT")) {
        writer3.write("000000000000100" + "\n");
        continue;}
        if (line3a.contains("OUTPUT_FIRST")) {
        writer3.write("000000000001010" + "\n");
        continue;}
        if (line3a.contains("OUTPUT_D")) {
        writer3.write("000000000001100" + "\n");
        continue;}
        if (line3a.contains("address")) {
        writer3.write("000000000010001" + "\n");
        continue;}
        if (line3a.contains("counter")) {
        writer3.write("000000000010000" + "\n");
        continue;}
        if (line3a.contains("INFINITE_LOOP") && (i == 3 || i == 24)) {
        writer3.write("000000000010111" + "\n");
        continue;}
        else if (line3a.contains("INFINITE_LOOP") && (i == 15)) {
          writer3.write("000000000001110" + "\n");
          continue;}
        else if (line3a.contains("LOOP")) {
        writer3.write("000000000001010" + "\n");
        continue;}
        if (line3a.matches("[0-9]+")) {
          int B3a = Integer.parseInt(line3a);
          String B3 = Integer.toBinaryString(B3a);
          String B4 = String.format("%15s", B3).replaceAll(" ", "0");
          writer3.write(B4 + "\n");
          continue;
        }
        else writer3.write("000000000000000" + "\n");
        continue;
        }

        
      else {
        writer3.write("111");
        String j;
        if (line3.contains("JGT")) j = "001";
        else if (line3.contains("JEQ")) j = "010";
        else if (line3.contains("JGE")) j = "011";
        else if (line3.contains("JLT")) j = "100";
        else if (line3.contains("JNE")) j = "101";
        else if (line3.contains("JLE")) j = "110";
        else if (line3.contains("JMP")) j = "111";
        else j = "000";
        String d;
        if (line3.contains("ADM=") || line3.contains("AMD=")) d = "111";
        else if (line3.contains("AM=")) d = "101";
        else if (line3.contains("AD=")) d = "110";
        else if (line3.contains("DM=") || line3.contains("MD=")) d = "011";
        else if (line3.contains("M=")) d = "001";
        else if (line3.contains("D=")) d = "010";
        else if (line3.contains("A=")) d = "100";
        else d = "000";
        String a;
        if (line3.contains("=M") || line3.contains("=!M") || line3.contains("=-M") || line3.contains("=D+M") ||                           line3.contains("=D-M") || line3.contains("=D&M") || line3.contains("=D|M")) a = "1";
        else a = "0";
        String c;
        if (line3.contains("|")) c = "010101";
        else if (line3.contains("&")) c = "000000";
        else if (line3.contains("A-D") || line3.contains("M-D")) c = "000111";
        else if (line3.contains("D-A") || line3.contains("D-M")) c = "010011";
        else if (line3.contains("D+A") || line3.contains("D+M")) c = "000010";
        else if (line3.contains("A-1") || line3.contains("M-1")) c = "110010";
        else if (line3.contains("A+1") || line3.contains("M+1")) c = "110111";
        else if (line3.contains("D-1")) c = "001110";
        else if (line3.contains("D+1")) c = "011111";
        else if (line3.contains("-A") || line3.contains("-M")) c = "110011";
        else if (line3.contains("!A") || line3.contains("!M")) c = "110001";
        else if (line3.contains("=A") || line3.contains("=M") || line3.contains("A;") || line3.contains("M;")) c = "110000";
        else if (line3.contains("-D")) c = "001111";
        else if (line3.contains("!D")) c = "001101";
        else if (line3.contains("=D") || line3.contains("D;")) c = "001100";
        else if (line3.contains("-1")) c = "111010";
        else if (line3.contains("1")) c = "111111";
        else if (line3.contains("0")) c = "101010";
        else c = null;
        writer3.write(a + c + d + j + "\n");
      }
      }
    writer3.close();
    reader3.close();
    

    BufferedReader reader4 = new BufferedReader(new FileReader("out3.txt"));
      BufferedWriter writer4 = new BufferedWriter(new FileWriter("output.hack"));
      String line4;
      while (i > 1) {
        line4 = reader4.readLine();
        i = i - 1;
        System.out.println(line4);
        writer4.write(line4 + "\n");
      }
      line4 = reader4.readLine();
      writer4.write(line4);
      writer4.close();
      reader4.close();
  } catch (IOException e) {
    e.printStackTrace();
  }
  }
}