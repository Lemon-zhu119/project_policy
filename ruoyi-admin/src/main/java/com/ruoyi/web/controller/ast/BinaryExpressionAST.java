package com.ruoyi.web.controller.ast;

class BinaryExpressionAST extends ExpressionAST {
    ExpressionAST left;
    TokenType operator;
    ExpressionAST right;

    BinaryExpressionAST(ExpressionAST left, TokenType operator, ExpressionAST right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
