package com.dcpear.main;
/**
 * We have a stateful session here. Initially when counter1 is inserted and rules fired all three rules get fired.
 * Later we insert counter2 and fire rules again. In this case the execution of rule "Counter shower 2"
 * changes the value of cnt in working memory.
 * As this is a stateful session so this rule is again executed for counter1 also as working memory value is changed.
 * creating new using overloaded constructor
 * creating new using overloaded constructor
 *
 * fire rules after inserting counter1
 *
 * Counter there (1) : 1 and the name is : cnt1
 * Counter there (2) : 1 and the name is : cnt1 accumaulated value is 1
 * Counters there (3) :
 *
 * fire rules after inserting counter2
 *
 * Counter there (1) : 2 and the name is : cnt2
 * Counter there (2) : 1 and the name is : cnt1 accumaulated value is 2
 * Counter there (2) : 2 and the name is : cnt2 accumaulated value is 2
 * Counters there (3) :
 */

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.dcpear.model.Counter;

public class DroolsTest {

	public static final void main(String[] args) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rule");

			Counter cnt1 = new Counter(1, "cnt1");
			Counter cnt2 = new Counter(2, "cnt2");

			System.out.println();
			System.out.println("fire rules after inserting counter1");
			System.out.println();
			kSession.insert(cnt1);
			//fire rules with counter1
			kSession.fireAllRules();

			System.out.println();
			System.out.println("fire rules after inserting counter2");
			System.out.println();
			kSession.insert(cnt2);
			//fire rules with already existing counter1 and newly inserted counter2
			kSession.fireAllRules();

			//Dispose the session at the end.
			kSession.dispose();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
