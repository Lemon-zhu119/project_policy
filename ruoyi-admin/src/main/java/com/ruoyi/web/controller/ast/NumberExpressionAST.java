package com.ruoyi.web.controller.ast;

class NumberExpressionAST extends ExpressionAST {
    int value;

    NumberExpressionAST(int value) {
        this.value = value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
