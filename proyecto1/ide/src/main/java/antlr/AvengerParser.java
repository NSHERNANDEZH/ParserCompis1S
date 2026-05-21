// Generated from C:/Users/gabri/OneDrive - Universidad Rafael Landivar/Quinto Semestre/Compiladores/ParserCompis1S/proyecto1/src/main/java/antlr/Avenger.g4 by ANTLR 4.13.2
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class AvengerParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STARK=1, BANNER=2, ROGERS=3, THOR=4, BOB=5, VISION=6, WANDA=7, LOKI=8, 
		FURY=9, GAMORA=10, NEBULA=11, RECRUIT=12, ASSEMBLE=13, RETURN=14, JARVIS=15, 
		PARKER=16, ODIN=17, NOJARVIS=18, JARVISJARVIS=19, PLUS=20, MINUS=21, MULT=22, 
		DIV=23, LPAREN=24, RPAREN=25, LBRACE=26, RBRACE=27, SEMI=28, COMMA=29, 
		BOOL_THOR=30, NUMERO_BANNER=31, NUMERO_STARK=32, STRING_ROGERS=33, IDENTIFICADOR=34, 
		WS=35, COMMENT=36;
	public static final int
		RULE_prog = 0, RULE_statement = 1, RULE_tipoVar = 2, RULE_param = 3, RULE_condition = 4, 
		RULE_expr = 5, RULE_primary = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "statement", "tipoVar", "param", "condition", "expr", "primary"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'stark'", "'banner'", "'rogers'", "'thor'", "'bob'", "'vision'", 
			"'wanda'", "'loki'", "'fury'", "'gamora'", "'nebula'", "'recruit'", "'assemble'", 
			"'return'", "'jarvis'", "'<'", "'>'", "'!='", "'=='", "'+'", "'-'", "'*'", 
			"'/'", "'('", "')'", "'{'", "'}'", "';'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "STARK", "BANNER", "ROGERS", "THOR", "BOB", "VISION", "WANDA", 
			"LOKI", "FURY", "GAMORA", "NEBULA", "RECRUIT", "ASSEMBLE", "RETURN", 
			"JARVIS", "PARKER", "ODIN", "NOJARVIS", "JARVISJARVIS", "PLUS", "MINUS", 
			"MULT", "DIV", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "SEMI", "COMMA", 
			"BOOL_THOR", "NUMERO_BANNER", "NUMERO_STARK", "STRING_ROGERS", "IDENTIFICADOR", 
			"WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Avenger.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AvengerParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AvengerParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
				{
				{
				setState(14);
				statement();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtReturnContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(AvengerParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtReadContext extends StatementContext {
		public TerminalNode GAMORA() { return getToken(AvengerParser.GAMORA, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtReadContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtRead(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtFuncDeclContext extends StatementContext {
		public TipoVarContext tipoVar() {
			return getRuleContext(TipoVarContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(AvengerParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AvengerParser.RBRACE, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AvengerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AvengerParser.COMMA, i);
		}
		public StmtFuncDeclContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtFuncDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtFuncDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtAssembleContext extends StatementContext {
		public TerminalNode ASSEMBLE() { return getToken(AvengerParser.ASSEMBLE, 0); }
		public TerminalNode STRING_ROGERS() { return getToken(AvengerParser.STRING_ROGERS, 0); }
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtAssembleContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtAssemble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtAssemble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtAssemble(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtWhileContext extends StatementContext {
		public TerminalNode LOKI() { return getToken(AvengerParser.LOKI, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(AvengerParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AvengerParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StmtWhileContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtWriteContext extends StatementContext {
		public TerminalNode NEBULA() { return getToken(AvengerParser.NEBULA, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtWriteContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtWrite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtWrite(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtImportContext extends StatementContext {
		public TerminalNode RECRUIT() { return getToken(AvengerParser.RECRUIT, 0); }
		public TerminalNode STRING_ROGERS() { return getToken(AvengerParser.STRING_ROGERS, 0); }
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtImportContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtImport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtImport(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtForContext extends StatementContext {
		public TerminalNode FURY() { return getToken(AvengerParser.FURY, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public TipoVarContext tipoVar() {
			return getRuleContext(TipoVarContext.class,0);
		}
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(AvengerParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(AvengerParser.IDENTIFICADOR, i);
		}
		public List<TerminalNode> JARVIS() { return getTokens(AvengerParser.JARVIS); }
		public TerminalNode JARVIS(int i) {
			return getToken(AvengerParser.JARVIS, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(AvengerParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(AvengerParser.SEMI, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(AvengerParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AvengerParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StmtForContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtFor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtAssignContext extends StatementContext {
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode JARVIS() { return getToken(AvengerParser.JARVIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtAssignContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtVarDeclContext extends StatementContext {
		public TipoVarContext tipoVar() {
			return getRuleContext(TipoVarContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode JARVIS() { return getToken(AvengerParser.JARVIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public StmtVarDeclContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtFuncDeclVoidContext extends StatementContext {
		public TerminalNode BOB() { return getToken(AvengerParser.BOB, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(AvengerParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(AvengerParser.RBRACE, 0); }
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AvengerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AvengerParser.COMMA, i);
		}
		public StmtFuncDeclVoidContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtFuncDeclVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtFuncDeclVoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtFuncDeclVoid(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtIfContext extends StatementContext {
		public TerminalNode VISION() { return getToken(AvengerParser.VISION, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public List<TerminalNode> LBRACE() { return getTokens(AvengerParser.LBRACE); }
		public TerminalNode LBRACE(int i) {
			return getToken(AvengerParser.LBRACE, i);
		}
		public List<TerminalNode> RBRACE() { return getTokens(AvengerParser.RBRACE); }
		public TerminalNode RBRACE(int i) {
			return getToken(AvengerParser.RBRACE, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode WANDA() { return getToken(AvengerParser.WANDA, 0); }
		public StmtIfContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtIf(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtFuncCallContext extends StatementContext {
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(AvengerParser.SEMI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AvengerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AvengerParser.COMMA, i);
		}
		public StmtFuncCallContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterStmtFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitStmtFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitStmtFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new StmtVarDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				tipoVar();
				setState(23);
				match(IDENTIFICADOR);
				setState(24);
				match(JARVIS);
				setState(25);
				expr(0);
				setState(26);
				match(SEMI);
				}
				break;
			case 2:
				_localctx = new StmtAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				match(IDENTIFICADOR);
				setState(29);
				match(JARVIS);
				setState(30);
				expr(0);
				setState(31);
				match(SEMI);
				}
				break;
			case 3:
				_localctx = new StmtIfContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				match(VISION);
				setState(34);
				match(LPAREN);
				setState(35);
				condition();
				setState(36);
				match(RPAREN);
				setState(37);
				match(LBRACE);
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
					{
					{
					setState(38);
					statement();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(44);
				match(RBRACE);
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WANDA) {
					{
					setState(45);
					match(WANDA);
					setState(46);
					match(LBRACE);
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
						{
						{
						setState(47);
						statement();
						}
						}
						setState(52);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(53);
					match(RBRACE);
					}
				}

				}
				break;
			case 4:
				_localctx = new StmtWhileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				match(LOKI);
				setState(57);
				match(LPAREN);
				setState(58);
				condition();
				setState(59);
				match(RPAREN);
				setState(60);
				match(LBRACE);
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
					{
					{
					setState(61);
					statement();
					}
					}
					setState(66);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(67);
				match(RBRACE);
				}
				break;
			case 5:
				_localctx = new StmtForContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(69);
				match(FURY);
				setState(70);
				match(LPAREN);
				setState(71);
				tipoVar();
				setState(72);
				match(IDENTIFICADOR);
				setState(73);
				match(JARVIS);
				setState(74);
				expr(0);
				setState(75);
				match(SEMI);
				setState(76);
				condition();
				setState(77);
				match(SEMI);
				setState(78);
				match(IDENTIFICADOR);
				setState(79);
				match(JARVIS);
				setState(80);
				expr(0);
				setState(81);
				match(RPAREN);
				setState(82);
				match(LBRACE);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
					{
					{
					setState(83);
					statement();
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(89);
				match(RBRACE);
				}
				break;
			case 6:
				_localctx = new StmtFuncDeclContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(91);
				tipoVar();
				setState(92);
				match(IDENTIFICADOR);
				setState(93);
				match(LPAREN);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0)) {
					{
					setState(94);
					param();
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(95);
						match(COMMA);
						setState(96);
						param();
						}
						}
						setState(101);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(104);
				match(RPAREN);
				setState(105);
				match(LBRACE);
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
					{
					{
					setState(106);
					statement();
					}
					}
					setState(111);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(112);
				match(RBRACE);
				}
				break;
			case 7:
				_localctx = new StmtFuncDeclVoidContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(114);
				match(BOB);
				setState(115);
				match(IDENTIFICADOR);
				setState(116);
				match(LPAREN);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0)) {
					{
					setState(117);
					param();
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(118);
						match(COMMA);
						setState(119);
						param();
						}
						}
						setState(124);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(127);
				match(RPAREN);
				setState(128);
				match(LBRACE);
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17179901822L) != 0)) {
					{
					{
					setState(129);
					statement();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(135);
				match(RBRACE);
				}
				break;
			case 8:
				_localctx = new StmtReturnContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(136);
				match(RETURN);
				setState(137);
				expr(0);
				setState(138);
				match(SEMI);
				}
				break;
			case 9:
				_localctx = new StmtReadContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(140);
				match(GAMORA);
				setState(141);
				match(LPAREN);
				setState(142);
				match(IDENTIFICADOR);
				setState(143);
				match(RPAREN);
				setState(144);
				match(SEMI);
				}
				break;
			case 10:
				_localctx = new StmtWriteContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(145);
				match(NEBULA);
				setState(146);
				match(LPAREN);
				setState(147);
				expr(0);
				setState(148);
				match(RPAREN);
				setState(149);
				match(SEMI);
				}
				break;
			case 11:
				_localctx = new StmtImportContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(151);
				match(RECRUIT);
				setState(152);
				match(STRING_ROGERS);
				setState(153);
				match(SEMI);
				}
				break;
			case 12:
				_localctx = new StmtAssembleContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(154);
				match(ASSEMBLE);
				setState(155);
				match(STRING_ROGERS);
				setState(156);
				match(SEMI);
				}
				break;
			case 13:
				_localctx = new StmtFuncCallContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(157);
				match(IDENTIFICADOR);
				setState(158);
				match(LPAREN);
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33304870912L) != 0)) {
					{
					setState(159);
					expr(0);
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(160);
						match(COMMA);
						setState(161);
						expr(0);
						}
						}
						setState(166);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(169);
				match(RPAREN);
				setState(170);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoVarContext extends ParserRuleContext {
		public TerminalNode STARK() { return getToken(AvengerParser.STARK, 0); }
		public TerminalNode BANNER() { return getToken(AvengerParser.BANNER, 0); }
		public TerminalNode ROGERS() { return getToken(AvengerParser.ROGERS, 0); }
		public TerminalNode THOR() { return getToken(AvengerParser.THOR, 0); }
		public TipoVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipoVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterTipoVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitTipoVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitTipoVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TipoVarContext tipoVar() throws RecognitionException {
		TipoVarContext _localctx = new TipoVarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tipoVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TipoVarContext tipoVar() {
			return getRuleContext(TipoVarContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			tipoVar();
			setState(176);
			match(IDENTIFICADOR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PARKER() { return getToken(AvengerParser.PARKER, 0); }
		public TerminalNode ODIN() { return getToken(AvengerParser.ODIN, 0); }
		public TerminalNode NOJARVIS() { return getToken(AvengerParser.NOJARVIS, 0); }
		public TerminalNode JARVISJARVIS() { return getToken(AvengerParser.JARVISJARVIS, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			expr(0);
			setState(179);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(180);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPrimaryContext extends ExprContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public ExprPrimaryContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterExprPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitExprPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitExprPrimary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMulDivContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(AvengerParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(AvengerParser.DIV, 0); }
		public ExprMulDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterExprMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitExprMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitExprMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprSumRestaContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(AvengerParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(AvengerParser.MINUS, 0); }
		public ExprSumRestaContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterExprSumResta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitExprSumResta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitExprSumResta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ExprPrimaryContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(183);
			primary();
			}
			_ctx.stop = _input.LT(-1);
			setState(193);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(191);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMulDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(185);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(186);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(187);
						expr(4);
						}
						break;
					case 2:
						{
						_localctx = new ExprSumRestaContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(188);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(189);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(195);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	 
		public PrimaryContext() { }
		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryAgrupadoContext extends PrimaryContext {
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public PrimaryAgrupadoContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryAgrupado(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryAgrupado(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryAgrupado(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryEnteroContext extends PrimaryContext {
		public TerminalNode NUMERO_STARK() { return getToken(AvengerParser.NUMERO_STARK, 0); }
		public PrimaryEnteroContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryEntero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryEntero(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryEntero(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryFlotanteContext extends PrimaryContext {
		public TerminalNode NUMERO_BANNER() { return getToken(AvengerParser.NUMERO_BANNER, 0); }
		public PrimaryFlotanteContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryFlotante(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryFlotante(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryFlotante(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryCadenaContext extends PrimaryContext {
		public TerminalNode STRING_ROGERS() { return getToken(AvengerParser.STRING_ROGERS, 0); }
		public PrimaryCadenaContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryCadena(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryCadena(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryCadena(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryFuncCallContext extends PrimaryContext {
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public TerminalNode LPAREN() { return getToken(AvengerParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(AvengerParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AvengerParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AvengerParser.COMMA, i);
		}
		public PrimaryFuncCallContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryFuncCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryFuncCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryNegativoContext extends PrimaryContext {
		public TerminalNode MINUS() { return getToken(AvengerParser.MINUS, 0); }
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryNegativoContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryNegativo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryNegativo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryNegativo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryIdContext extends PrimaryContext {
		public TerminalNode IDENTIFICADOR() { return getToken(AvengerParser.IDENTIFICADOR, 0); }
		public PrimaryIdContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryId(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryBooleanoContext extends PrimaryContext {
		public TerminalNode BOOL_THOR() { return getToken(AvengerParser.BOOL_THOR, 0); }
		public PrimaryBooleanoContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).enterPrimaryBooleano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AvengerListener ) ((AvengerListener)listener).exitPrimaryBooleano(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AvengerVisitor ) return ((AvengerVisitor<? extends T>)visitor).visitPrimaryBooleano(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primary);
		int _la;
		try {
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new PrimaryNegativoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(196);
				match(MINUS);
				setState(197);
				primary();
				}
				break;
			case 2:
				_localctx = new PrimaryAgrupadoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				match(LPAREN);
				setState(199);
				expr(0);
				setState(200);
				match(RPAREN);
				}
				break;
			case 3:
				_localctx = new PrimaryFuncCallContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				match(IDENTIFICADOR);
				setState(203);
				match(LPAREN);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33304870912L) != 0)) {
					{
					setState(204);
					expr(0);
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(205);
						match(COMMA);
						setState(206);
						expr(0);
						}
						}
						setState(211);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(214);
				match(RPAREN);
				}
				break;
			case 4:
				_localctx = new PrimaryIdContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(215);
				match(IDENTIFICADOR);
				}
				break;
			case 5:
				_localctx = new PrimaryEnteroContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(216);
				match(NUMERO_STARK);
				}
				break;
			case 6:
				_localctx = new PrimaryFlotanteContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(217);
				match(NUMERO_BANNER);
				}
				break;
			case 7:
				_localctx = new PrimaryCadenaContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(218);
				match(STRING_ROGERS);
				}
				break;
			case 8:
				_localctx = new PrimaryBooleanoContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(219);
				match(BOOL_THOR);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001$\u00df\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0005\u0000\u0010"+
		"\b\u0000\n\u0000\f\u0000\u0013\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001(\b\u0001"+
		"\n\u0001\f\u0001+\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u00011\b\u0001\n\u0001\f\u00014\t\u0001\u0001\u0001\u0003\u0001"+
		"7\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001?\b\u0001\n\u0001\f\u0001B\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001U\b\u0001"+
		"\n\u0001\f\u0001X\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001b\b\u0001"+
		"\n\u0001\f\u0001e\t\u0001\u0003\u0001g\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001l\b\u0001\n\u0001\f\u0001o\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001y\b\u0001\n\u0001\f\u0001|\t\u0001\u0003\u0001"+
		"~\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001\u0083\b\u0001"+
		"\n\u0001\f\u0001\u0086\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u0001\u00a3\b\u0001\n\u0001\f\u0001\u00a6\t\u0001\u0003\u0001\u00a8\b"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u00ac\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00c0"+
		"\b\u0005\n\u0005\f\u0005\u00c3\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0005\u0006\u00d0\b\u0006\n\u0006\f\u0006\u00d3"+
		"\t\u0006\u0003\u0006\u00d5\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00dd\b\u0006\u0001\u0006"+
		"\u0000\u0001\n\u0007\u0000\u0002\u0004\u0006\b\n\f\u0000\u0004\u0001\u0000"+
		"\u0001\u0004\u0001\u0000\u0010\u0013\u0001\u0000\u0016\u0017\u0001\u0000"+
		"\u0014\u0015\u00fc\u0000\u0011\u0001\u0000\u0000\u0000\u0002\u00ab\u0001"+
		"\u0000\u0000\u0000\u0004\u00ad\u0001\u0000\u0000\u0000\u0006\u00af\u0001"+
		"\u0000\u0000\u0000\b\u00b2\u0001\u0000\u0000\u0000\n\u00b6\u0001\u0000"+
		"\u0000\u0000\f\u00dc\u0001\u0000\u0000\u0000\u000e\u0010\u0003\u0002\u0001"+
		"\u0000\u000f\u000e\u0001\u0000\u0000\u0000\u0010\u0013\u0001\u0000\u0000"+
		"\u0000\u0011\u000f\u0001\u0000\u0000\u0000\u0011\u0012\u0001\u0000\u0000"+
		"\u0000\u0012\u0014\u0001\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000"+
		"\u0000\u0014\u0015\u0005\u0000\u0000\u0001\u0015\u0001\u0001\u0000\u0000"+
		"\u0000\u0016\u0017\u0003\u0004\u0002\u0000\u0017\u0018\u0005\"\u0000\u0000"+
		"\u0018\u0019\u0005\u000f\u0000\u0000\u0019\u001a\u0003\n\u0005\u0000\u001a"+
		"\u001b\u0005\u001c\u0000\u0000\u001b\u00ac\u0001\u0000\u0000\u0000\u001c"+
		"\u001d\u0005\"\u0000\u0000\u001d\u001e\u0005\u000f\u0000\u0000\u001e\u001f"+
		"\u0003\n\u0005\u0000\u001f \u0005\u001c\u0000\u0000 \u00ac\u0001\u0000"+
		"\u0000\u0000!\"\u0005\u0006\u0000\u0000\"#\u0005\u0018\u0000\u0000#$\u0003"+
		"\b\u0004\u0000$%\u0005\u0019\u0000\u0000%)\u0005\u001a\u0000\u0000&(\u0003"+
		"\u0002\u0001\u0000\'&\u0001\u0000\u0000\u0000(+\u0001\u0000\u0000\u0000"+
		")\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*,\u0001\u0000\u0000"+
		"\u0000+)\u0001\u0000\u0000\u0000,6\u0005\u001b\u0000\u0000-.\u0005\u0007"+
		"\u0000\u0000.2\u0005\u001a\u0000\u0000/1\u0003\u0002\u0001\u00000/\u0001"+
		"\u0000\u0000\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u0000"+
		"23\u0001\u0000\u0000\u000035\u0001\u0000\u0000\u000042\u0001\u0000\u0000"+
		"\u000057\u0005\u001b\u0000\u00006-\u0001\u0000\u0000\u000067\u0001\u0000"+
		"\u0000\u00007\u00ac\u0001\u0000\u0000\u000089\u0005\b\u0000\u00009:\u0005"+
		"\u0018\u0000\u0000:;\u0003\b\u0004\u0000;<\u0005\u0019\u0000\u0000<@\u0005"+
		"\u001a\u0000\u0000=?\u0003\u0002\u0001\u0000>=\u0001\u0000\u0000\u0000"+
		"?B\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000"+
		"\u0000AC\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0005\u001b"+
		"\u0000\u0000D\u00ac\u0001\u0000\u0000\u0000EF\u0005\t\u0000\u0000FG\u0005"+
		"\u0018\u0000\u0000GH\u0003\u0004\u0002\u0000HI\u0005\"\u0000\u0000IJ\u0005"+
		"\u000f\u0000\u0000JK\u0003\n\u0005\u0000KL\u0005\u001c\u0000\u0000LM\u0003"+
		"\b\u0004\u0000MN\u0005\u001c\u0000\u0000NO\u0005\"\u0000\u0000OP\u0005"+
		"\u000f\u0000\u0000PQ\u0003\n\u0005\u0000QR\u0005\u0019\u0000\u0000RV\u0005"+
		"\u001a\u0000\u0000SU\u0003\u0002\u0001\u0000TS\u0001\u0000\u0000\u0000"+
		"UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000"+
		"\u0000WY\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000YZ\u0005\u001b"+
		"\u0000\u0000Z\u00ac\u0001\u0000\u0000\u0000[\\\u0003\u0004\u0002\u0000"+
		"\\]\u0005\"\u0000\u0000]f\u0005\u0018\u0000\u0000^c\u0003\u0006\u0003"+
		"\u0000_`\u0005\u001d\u0000\u0000`b\u0003\u0006\u0003\u0000a_\u0001\u0000"+
		"\u0000\u0000be\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001"+
		"\u0000\u0000\u0000dg\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000"+
		"f^\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hi\u0005\u0019\u0000\u0000im\u0005\u001a\u0000\u0000jl\u0003\u0002"+
		"\u0001\u0000kj\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000mk\u0001"+
		"\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000np\u0001\u0000\u0000\u0000"+
		"om\u0001\u0000\u0000\u0000pq\u0005\u001b\u0000\u0000q\u00ac\u0001\u0000"+
		"\u0000\u0000rs\u0005\u0005\u0000\u0000st\u0005\"\u0000\u0000t}\u0005\u0018"+
		"\u0000\u0000uz\u0003\u0006\u0003\u0000vw\u0005\u001d\u0000\u0000wy\u0003"+
		"\u0006\u0003\u0000xv\u0001\u0000\u0000\u0000y|\u0001\u0000\u0000\u0000"+
		"zx\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000{~\u0001\u0000\u0000"+
		"\u0000|z\u0001\u0000\u0000\u0000}u\u0001\u0000\u0000\u0000}~\u0001\u0000"+
		"\u0000\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0005\u0019\u0000"+
		"\u0000\u0080\u0084\u0005\u001a\u0000\u0000\u0081\u0083\u0003\u0002\u0001"+
		"\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000\u0000"+
		"\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000"+
		"\u0000\u0085\u0087\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000"+
		"\u0000\u0087\u00ac\u0005\u001b\u0000\u0000\u0088\u0089\u0005\u000e\u0000"+
		"\u0000\u0089\u008a\u0003\n\u0005\u0000\u008a\u008b\u0005\u001c\u0000\u0000"+
		"\u008b\u00ac\u0001\u0000\u0000\u0000\u008c\u008d\u0005\n\u0000\u0000\u008d"+
		"\u008e\u0005\u0018\u0000\u0000\u008e\u008f\u0005\"\u0000\u0000\u008f\u0090"+
		"\u0005\u0019\u0000\u0000\u0090\u00ac\u0005\u001c\u0000\u0000\u0091\u0092"+
		"\u0005\u000b\u0000\u0000\u0092\u0093\u0005\u0018\u0000\u0000\u0093\u0094"+
		"\u0003\n\u0005\u0000\u0094\u0095\u0005\u0019\u0000\u0000\u0095\u0096\u0005"+
		"\u001c\u0000\u0000\u0096\u00ac\u0001\u0000\u0000\u0000\u0097\u0098\u0005"+
		"\f\u0000\u0000\u0098\u0099\u0005!\u0000\u0000\u0099\u00ac\u0005\u001c"+
		"\u0000\u0000\u009a\u009b\u0005\r\u0000\u0000\u009b\u009c\u0005!\u0000"+
		"\u0000\u009c\u00ac\u0005\u001c\u0000\u0000\u009d\u009e\u0005\"\u0000\u0000"+
		"\u009e\u00a7\u0005\u0018\u0000\u0000\u009f\u00a4\u0003\n\u0005\u0000\u00a0"+
		"\u00a1\u0005\u001d\u0000\u0000\u00a1\u00a3\u0003\n\u0005\u0000\u00a2\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a8"+
		"\u0001\u0000\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7\u009f"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005\u0019\u0000\u0000\u00aa\u00ac"+
		"\u0005\u001c\u0000\u0000\u00ab\u0016\u0001\u0000\u0000\u0000\u00ab\u001c"+
		"\u0001\u0000\u0000\u0000\u00ab!\u0001\u0000\u0000\u0000\u00ab8\u0001\u0000"+
		"\u0000\u0000\u00abE\u0001\u0000\u0000\u0000\u00ab[\u0001\u0000\u0000\u0000"+
		"\u00abr\u0001\u0000\u0000\u0000\u00ab\u0088\u0001\u0000\u0000\u0000\u00ab"+
		"\u008c\u0001\u0000\u0000\u0000\u00ab\u0091\u0001\u0000\u0000\u0000\u00ab"+
		"\u0097\u0001\u0000\u0000\u0000\u00ab\u009a\u0001\u0000\u0000\u0000\u00ab"+
		"\u009d\u0001\u0000\u0000\u0000\u00ac\u0003\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0007\u0000\u0000\u0000\u00ae\u0005\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0003\u0004\u0002\u0000\u00b0\u00b1\u0005\"\u0000\u0000\u00b1\u0007"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b3\u0003\n\u0005\u0000\u00b3\u00b4\u0007"+
		"\u0001\u0000\u0000\u00b4\u00b5\u0003\n\u0005\u0000\u00b5\t\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0006\u0005\uffff\uffff\u0000\u00b7\u00b8\u0003"+
		"\f\u0006\u0000\u00b8\u00c1\u0001\u0000\u0000\u0000\u00b9\u00ba\n\u0003"+
		"\u0000\u0000\u00ba\u00bb\u0007\u0002\u0000\u0000\u00bb\u00c0\u0003\n\u0005"+
		"\u0004\u00bc\u00bd\n\u0002\u0000\u0000\u00bd\u00be\u0007\u0003\u0000\u0000"+
		"\u00be\u00c0\u0003\n\u0005\u0003\u00bf\u00b9\u0001\u0000\u0000\u0000\u00bf"+
		"\u00bc\u0001\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1"+
		"\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2"+
		"\u000b\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c5\u0005\u0015\u0000\u0000\u00c5\u00dd\u0003\f\u0006\u0000\u00c6\u00c7"+
		"\u0005\u0018\u0000\u0000\u00c7\u00c8\u0003\n\u0005\u0000\u00c8\u00c9\u0005"+
		"\u0019\u0000\u0000\u00c9\u00dd\u0001\u0000\u0000\u0000\u00ca\u00cb\u0005"+
		"\"\u0000\u0000\u00cb\u00d4\u0005\u0018\u0000\u0000\u00cc\u00d1\u0003\n"+
		"\u0005\u0000\u00cd\u00ce\u0005\u001d\u0000\u0000\u00ce\u00d0\u0003\n\u0005"+
		"\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d2\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d4\u00cc\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00dd\u0005\u0019\u0000"+
		"\u0000\u00d7\u00dd\u0005\"\u0000\u0000\u00d8\u00dd\u0005 \u0000\u0000"+
		"\u00d9\u00dd\u0005\u001f\u0000\u0000\u00da\u00dd\u0005!\u0000\u0000\u00db"+
		"\u00dd\u0005\u001e\u0000\u0000\u00dc\u00c4\u0001\u0000\u0000\u0000\u00dc"+
		"\u00c6\u0001\u0000\u0000\u0000\u00dc\u00ca\u0001\u0000\u0000\u0000\u00dc"+
		"\u00d7\u0001\u0000\u0000\u0000\u00dc\u00d8\u0001\u0000\u0000\u0000\u00dc"+
		"\u00d9\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dc"+
		"\u00db\u0001\u0000\u0000\u0000\u00dd\r\u0001\u0000\u0000\u0000\u0014\u0011"+
		")26@Vcfmz}\u0084\u00a4\u00a7\u00ab\u00bf\u00c1\u00d1\u00d4\u00dc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}