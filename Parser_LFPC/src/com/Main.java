package com;

import com.lexer.Lexer;
import com.lexer.Token;
import com.parser.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;

class Main {

  public static void fatalError(String message) {
    System.out.println(message);
    System.exit(1);
  }

  public static void main(String[] args) {

    if (args.length == 0) {
      fatalError("You did not pass an input file.");
    }

    Lexer lex = new Lexer();

    Parser parser = new Parser();

    StringBuilder sb = new StringBuilder();
    File inputFile = new File(args[0]);

    try {
      FileReader fileReader = new FileReader(inputFile);
      BufferedReader reader = new BufferedReader(fileReader);
      String line;
      int lineNumber = 1;

      while ((line = reader.readLine()) != null) {
        lex.scan(line, lineNumber);
        lineNumber++;
      }
      fileReader.close();

    } catch (Exception e) { fatalError("There was a problem reading the file"); }

    for (Token token:lex.getTokens()) {
      System.out.println(token);
      System.out.println("");
    }

    parser.parse(lex.getTokens());
  }
}
