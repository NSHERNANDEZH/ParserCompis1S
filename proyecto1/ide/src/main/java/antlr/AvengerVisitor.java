// Generated from C:/Users/gabri/OneDrive - Universidad Rafael Landivar/Quinto Semestre/Compiladores/ParserCompis1S/proyecto1/src/main/java/antlr/Avenger.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AvengerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AvengerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AvengerParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(AvengerParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtVarDecl}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtVarDecl(AvengerParser.StmtVarDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtAssign}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAssign(AvengerParser.StmtAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtIf}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtIf(AvengerParser.StmtIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtWhile}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtWhile(AvengerParser.StmtWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtFor}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFor(AvengerParser.StmtForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtFuncDecl}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFuncDecl(AvengerParser.StmtFuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtFuncDeclVoid}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFuncDeclVoid(AvengerParser.StmtFuncDeclVoidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtReturn}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtReturn(AvengerParser.StmtReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtRead}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtRead(AvengerParser.StmtReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtWrite}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtWrite(AvengerParser.StmtWriteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtImport}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtImport(AvengerParser.StmtImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtAssemble}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAssemble(AvengerParser.StmtAssembleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtFuncCall}
	 * labeled alternative in {@link AvengerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtFuncCall(AvengerParser.StmtFuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvengerParser#tipoVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipoVar(AvengerParser.TipoVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvengerParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(AvengerParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvengerParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(AvengerParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPrimary}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrimary(AvengerParser.ExprPrimaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMulDiv}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMulDiv(AvengerParser.ExprMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprSumResta}
	 * labeled alternative in {@link AvengerParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSumResta(AvengerParser.ExprSumRestaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryNegativo}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryNegativo(AvengerParser.PrimaryNegativoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryAgrupado}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryAgrupado(AvengerParser.PrimaryAgrupadoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryFuncCall}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryFuncCall(AvengerParser.PrimaryFuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryId}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryId(AvengerParser.PrimaryIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryEntero}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryEntero(AvengerParser.PrimaryEnteroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryFlotante}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryFlotante(AvengerParser.PrimaryFlotanteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryCadena}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryCadena(AvengerParser.PrimaryCadenaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryBooleano}
	 * labeled alternative in {@link AvengerParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryBooleano(AvengerParser.PrimaryBooleanoContext ctx);
}