package com.ruoyi.web.controller.ast;

class ASTVisitor {
    void visit(BinaryExpressionAST node) {
        node.left.accept(this);
        System.out.print(" " + node.operator + " ");
        node.right.accept(this);
    }

    void visit(NumberExpressionAST node) {
        System.out.print(node.value);
    }
}
