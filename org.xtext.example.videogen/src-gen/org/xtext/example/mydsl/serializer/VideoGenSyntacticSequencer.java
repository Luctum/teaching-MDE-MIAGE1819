/*
 * generated by Xtext 2.15.0
 */
package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.xtext.example.mydsl.services.VideoGenGrammarAccess;

@SuppressWarnings("all")
public class VideoGenSyntacticSequencer extends AbstractSyntacticSequencer {

	protected VideoGenGrammarAccess grammarAccess;
	protected AbstractElementAlias match_VideoDescription___LEFT_BRACKETTerminalRuleCall_3_0_RIGHT_BRACKETTerminalRuleCall_3_6__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (VideoGenGrammarAccess) access;
		match_VideoDescription___LEFT_BRACKETTerminalRuleCall_3_0_RIGHT_BRACKETTerminalRuleCall_3_6__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getVideoDescriptionAccess().getLEFT_BRACKETTerminalRuleCall_3_0()), new TokenAlias(false, false, grammarAccess.getVideoDescriptionAccess().getRIGHT_BRACKETTerminalRuleCall_3_6()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (ruleCall.getRule() == grammarAccess.getLEFT_BRACKETRule())
			return getLEFT_BRACKETToken(semanticObject, ruleCall, node);
		else if (ruleCall.getRule() == grammarAccess.getRIGHT_BRACKETRule())
			return getRIGHT_BRACKETToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal LEFT_BRACKET: '{' ;
	 */
	protected String getLEFT_BRACKETToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "{";
	}
	
	/**
	 * terminal RIGHT_BRACKET: '}';
	 */
	protected String getRIGHT_BRACKETToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "}";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_VideoDescription___LEFT_BRACKETTerminalRuleCall_3_0_RIGHT_BRACKETTerminalRuleCall_3_6__q.equals(syntax))
				emit_VideoDescription___LEFT_BRACKETTerminalRuleCall_3_0_RIGHT_BRACKETTerminalRuleCall_3_6__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     (LEFT_BRACKET RIGHT_BRACKET)?
	 *
	 * This ambiguous syntax occurs at:
	 *     location=STRING (ambiguity) (rule end)
	 */
	protected void emit_VideoDescription___LEFT_BRACKETTerminalRuleCall_3_0_RIGHT_BRACKETTerminalRuleCall_3_6__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
