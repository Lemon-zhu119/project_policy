package com.ruoyi.web.controller.ast;


import java.util.List;

public class BitwiseParser {
    public static void main(String[] args) {
        String expression = "3 | 4 & ( 1 & (4 | 5 | 6))";
        String res = BitwiseParser.ast(expression);
        if(res=="Success"){
            // 返回成功
            System.err.println("\n"+res);
        }else {
            // 返回错误信息
            System.err.println("\n"+res);
        }
    }

    static public String ast(String expression){
        Lexer lexer = new Lexer(expression);
        List<Token> tokens = lexer.tokenize();

        Parser parser = new Parser(tokens);
        try {
            ExpressionAST ast = parser.parse();
            ASTVisitor visitor = new ASTVisitor();
            ast.accept(visitor);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
        return "Success";
    }
}