package com.ruoyi.web.controller.ast;

abstract class ExpressionAST {
    public abstract void accept(ASTVisitor visitor);
}
