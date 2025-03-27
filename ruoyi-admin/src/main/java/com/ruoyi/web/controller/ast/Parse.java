package com.ruoyi.web.controller.ast;

import java.util.List;


class Parser {
    private List<Token> tokens;
    private int current = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    ExpressionAST parse() {
        ExpressionAST expression = parseExpression();
        if (current < tokens.size()) {
            throw new RuntimeException("Unexpected token at position " + current);
        }
        return expression;
    }

    private ExpressionAST parseExpression() {
        ExpressionAST expression = parseTerm();
        while (current < tokens.size()) {
            if (tokens.get(current).type == TokenType.AND || tokens.get(current).type == TokenType.OR) {
                TokenType operator = tokens.get(current).type;
                current++; // consume the operator token
                ExpressionAST nextTerm = parseTerm();
                expression = new BinaryExpressionAST(expression, operator, nextTerm);
            } else {
                break;
            }
        }
        return expression;
    }

    private ExpressionAST parseTerm() {
        if (expect(TokenType.LPAREN)) {
            ExpressionAST expr = parseExpression();
            if (!expect(TokenType.RPAREN)) {
                throw new RuntimeException("Missing closing parenthesis at position " + current);
            }
            return expr;
        } else if (expect(TokenType.NUMBER)) {
            Token numberToken = tokens.get(current - 1);
            return new NumberExpressionAST(Integer.parseInt(numberToken.value));
        } else {
            throw new RuntimeException("Unexpected token at position " + current);
        }
    }

    private boolean expect(TokenType type) {
        if (current < tokens.size() && tokens.get(current).type == type) {
            current++;
            return true;
        }
        return false;
    }
}