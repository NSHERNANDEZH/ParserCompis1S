// Generated from C:/Users/Sam/Documents/URL/QuintoSemestre/Compiladores/Proyecto1/ParserCompis1S/proyecto1/src/main/java/antlr/Avenger.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AvengerParser}.
 */
public interface AvengerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AvengerParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(AvengerParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(AvengerParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AvengerParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AvengerParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(AvengerParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(AvengerParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(AvengerParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#assignStmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(AvengerParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(AvengerParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(AvengerParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(AvengerParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(AvengerParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(AvengerParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#forStmt}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(AvengerParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(AvengerParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(AvengerParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(AvengerParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(AvengerParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(AvengerParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(AvengerParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(AvengerParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#returnStmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(AvengerParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(AvengerParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(AvengerParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#argList}.
	 * @param ctx the parse tree
	 */
	void enterArgList(AvengerParser.ArgListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#argList}.
	 * @param ctx the parse tree
	 */
	void exitArgList(AvengerParser.ArgListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#readStmt}.
	 * @param ctx the parse tree
	 */
	void enterReadStmt(AvengerParser.ReadStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#readStmt}.
	 * @param ctx the parse tree
	 */
	void exitReadStmt(AvengerParser.ReadStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#writeStmt}.
	 * @param ctx the parse tree
	 */
	void enterWriteStmt(AvengerParser.WriteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#writeStmt}.
	 * @param ctx the parse tree
	 */
	void exitWriteStmt(AvengerParser.WriteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void enterImportStmt(AvengerParser.ImportStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#importStmt}.
	 * @param ctx the parse tree
	 */
	void exitImportStmt(AvengerParser.ImportStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(AvengerParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(AvengerParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(AvengerParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(AvengerParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(AvengerParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(AvengerParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(AvengerParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(AvengerParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(AvengerParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(AvengerParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(AvengerParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(AvengerParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(AvengerParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(AvengerParser.AtomContext ctx);
}