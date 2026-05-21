// Generated from C:/Users/gabri/OneDrive - Universidad Rafael Landivar/Quinto Semestre/Compiladores/ParserCompis1S/proyecto1/src/main/java/antlr/Avenger.g4 by ANTLR 4.13.2
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
	 * Enter a parse tree produced by the {@code StmtVarDecl}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtVarDecl(AvengerParser.StmtVarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtVarDecl}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtVarDecl(AvengerParser.StmtVarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtAssign}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtAssign(AvengerParser.StmtAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtAssign}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtAssign(AvengerParser.StmtAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtIf}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtIf(AvengerParser.StmtIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtIf}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtIf(AvengerParser.StmtIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtWhile}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtWhile(AvengerParser.StmtWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtWhile}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtWhile(AvengerParser.StmtWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtFor}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFor(AvengerParser.StmtForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtFor}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFor(AvengerParser.StmtForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtFuncDecl}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFuncDecl(AvengerParser.StmtFuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtFuncDecl}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFuncDecl(AvengerParser.StmtFuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtFuncDeclVoid}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFuncDeclVoid(AvengerParser.StmtFuncDeclVoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtFuncDeclVoid}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFuncDeclVoid(AvengerParser.StmtFuncDeclVoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtReturn}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtReturn(AvengerParser.StmtReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtReturn}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtReturn(AvengerParser.StmtReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtRead}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtRead(AvengerParser.StmtReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtRead}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtRead(AvengerParser.StmtReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtWrite}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtWrite(AvengerParser.StmtWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtWrite}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtWrite(AvengerParser.StmtWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtImport}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtImport(AvengerParser.StmtImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtImport}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtImport(AvengerParser.StmtImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtAssemble}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtAssemble(AvengerParser.StmtAssembleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtAssemble}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtAssemble(AvengerParser.StmtAssembleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtFuncCall}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStmtFuncCall(AvengerParser.StmtFuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtFuncCall}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStmtFuncCall(AvengerParser.StmtFuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvengerParser#tipoVar}.
	 * @param ctx the parse tree
	 */
	void enterTipoVar(AvengerParser.TipoVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvengerParser#tipoVar}.
	 * @param ctx the parse tree
	 */
	void exitTipoVar(AvengerParser.TipoVarContext ctx);
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
	 * Enter a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPrimary(AvengerParser.ExprPrimaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPrimary(AvengerParser.ExprPrimaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMulDiv}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMulDiv(AvengerParser.ExprMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMulDiv}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMulDiv(AvengerParser.ExprMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSumResta}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprSumResta(AvengerParser.ExprSumRestaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSumResta}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprSumResta(AvengerParser.ExprSumRestaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryNegativo}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryNegativo(AvengerParser.PrimaryNegativoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryNegativo}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryNegativo(AvengerParser.PrimaryNegativoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryAgrupado}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryAgrupado(AvengerParser.PrimaryAgrupadoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryAgrupado}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryAgrupado(AvengerParser.PrimaryAgrupadoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryFuncCall}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryFuncCall(AvengerParser.PrimaryFuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryFuncCall}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryFuncCall(AvengerParser.PrimaryFuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryId(AvengerParser.PrimaryIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryId(AvengerParser.PrimaryIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryEntero}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryEntero(AvengerParser.PrimaryEnteroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryEntero}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryEntero(AvengerParser.PrimaryEnteroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryFlotante}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryFlotante(AvengerParser.PrimaryFlotanteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryFlotante}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryFlotante(AvengerParser.PrimaryFlotanteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryCadena}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryCadena(AvengerParser.PrimaryCadenaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryCadena}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryCadena(AvengerParser.PrimaryCadenaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryBooleano}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryBooleano(AvengerParser.PrimaryBooleanoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryBooleano}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryBooleano(AvengerParser.PrimaryBooleanoContext ctx);
}