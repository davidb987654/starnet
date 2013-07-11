package fr.labri.timedautomata;

import java.io.IOException;


import org.jdom2.JDOMException;

import fr.labri.DotViewer;
import fr.labri.timedautomata.ITimedAutomata.Spawner;
import fr.labri.timedautomata.TimedAutomata;
import fr.labri.timedautomata.ITimedAutomata.NodeFactory;
import fr.labri.timedautomata.ITimedAutomata.Action;
import fr.labri.timedautomata.ITimedAutomata.Predicate;
import fr.labri.timedautomata.TimedAutomata.TransitionAdapter;

public class TestTA {
	public static void main(String[] args) throws JDOMException, IOException {
		
		TimedAutomata<Object> b = new TimedAutomataFactory<>(getSimpleNodeBuilder()).loadXML(TestTA.class.getResourceAsStream("ex1.xml"));
		//DotViewer.view(b.toDot("ex1"));
		System.out.println(b.compile().toDot("G"));
		//AutomataViewer.viewAsFrame(b);
		//DotViewer.view(b.compile().toDot("ex1"));
//		
//		TimedAutomata<Object> c = TimedAutomata.getTimedAutoma(null, getSimpleNodeBuilder());
//		c.loadXML(TestTA.class.getResourceAsStream("ex2.xml"), false);
//		//DotViewer.view(b.toDot("ex2"));
//		System.out.println(c.toString());
//		AutomataViewer.viewAsFrame(c);
//
//		CompositeAutomata<Object> composite = new CompositeAutomata<>(b, null, getSimpleNodeBuilder());
//		composite.start(c);
//		System.out.println(composite.toDot("G"));
//		DotViewer.view(composite.toDot("G"));
	}
	
	static <C> NodeFactory<C> getSimpleNodeBuilder() {
		return new NodeFactory<C>() {
			public Predicate<C> newPredicate(final String name) {
				return new TransitionAdapter<C>() {
					public String getType() {
						return name;
					}
				};
			}

			@Override
			public Action<C> newAction(final String type, final String attr) {
				return new ITimedAutomata.StateAdapter<C>() {
					public String getType() {
						return type+":"+attr;
					}
				};
			}

			@Override
			public Spawner<C> newSpawner(final String type) {
				return new Spawner<C>() {
					@Override
					public String getSpawnerKey(C context, String parentKey) {
						return null;
					}

					@Override
					public String getType() {
						return type;
					}
				};
			}
		};
	}
}
