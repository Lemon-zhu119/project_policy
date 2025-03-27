package com.ruoyi.web.controller.ast;

import java.util.ArrayList;
import java.util.List;

class Lexer {
    private String input;
    private int position = 0;

    Lexer(String input) {
        this.input = input;
    }

    List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < input.length()) {
            char ch = input.charAt(position);
            if (Character.isWhitespace(ch)) {
                position++;
                continue;
            }
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                while (position < input.length() && Character.isDigit(input.charAt(position))) {
                    sb.append(input.charAt(position++));
                }
                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            }
            switch (ch) {
                case '(':
                    tokens.add(new Token(TokenType.LPAREN, "("));
                    position++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RPAREN, ")"));
                    position++;
                    break;
                case '|':
                    tokens.add(new Token(TokenType.OR, "|"));
                    position++;
                    break;
                case '&':
                    tokens.add(new Token(TokenType.AND, "&"));
                    position++;
                    break;
                default:
                    throw new RuntimeException("Unexpected character: " + ch);
            }
        }
        return tokens;
    }
}